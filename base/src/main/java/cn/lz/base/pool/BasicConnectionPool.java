package cn.lz.base.pool;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class BasicConnectionPool implements ConnectionPool {

    private final String url;
    private final String user;
    private final String password;
    private final BlockingQueue<Connection> connectionPool = new LinkedBlockingQueue<>();
    private final List<Connection> usedConnections = new CopyOnWriteArrayList<>();


    public BasicConnectionPool(String url, String user, String password, int size) throws SQLException {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
        this.url = url;
        this.user = user;
        this.password = password;
        try {
            for (int i = 0; i < size; i++) {
                Connection connection = createConnection(url, user, password);
                connectionPool.add(connection);
                log.info("成功创建了一个Connection {} {} ", i, connection);
            }
        } catch (Exception e) {
            log.error("create connection error", e);
            this.connectionPool.forEach(connection -> {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    log.error("close connection error");
                    ex.printStackTrace();
                }
            });
            throw new RuntimeException("create connection error");
        }

    }

    @Override
    public Connection getConnection() {
        try {
            Connection connection = connectionPool.take();
            log.info("消费了一个connection {}", connection);
            usedConnections.add(connection);
            return connection;
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        try {
            connectionPool.put(connection);
            log.info("回收了一个connection {}", connection);
            return usedConnections.remove(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void shutdown() {
        log.info("ready to shutdown");
        int count = 0;
        int polls = 0;
        Iterator<Connection> iterator = connectionPool.iterator();
        Iterator<Connection> usedConnectionIterator = usedConnections.iterator();
        while (count < this.getSize()) {
            while (iterator.hasNext()) {
                try {
                    iterator.next().close();
                    iterator.remove();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    count++;
                    log.info("close {}", count);
                }
            }
            while (polls > 2 && usedConnectionIterator.hasNext()) {
                try {
                    usedConnectionIterator.next().close();
                    usedConnectionIterator.remove();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    count++;
                    log.info("close {}", count);
                }
            }
            polls++;
        }

        log.info("complete shutdown");
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    private Connection createConnection(
            String url, String user, String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

}