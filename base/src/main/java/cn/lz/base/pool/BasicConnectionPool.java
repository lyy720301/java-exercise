package cn.lz.base.pool;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BasicConnectionPool implements ConnectionPool {

    private final String url;
    private final String user;
    private final String password;
    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();

    public BasicConnectionPool(String url, String user, String password, List<Connection> connectionPool, int size) throws SQLException {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
        for (int i = 0; i < size; i++) {
            Connection connection = createConnection(url, user, password);
            connectionPool.add(connection);
            log.info("成功创建了一个Connection {} {} ", i, connection);
        }
    }

    // standard constructors

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        log.info("消费了一个connection {}", connection);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        log.info("回收了一个connection {}", connection);
        return usedConnections.remove(connection);
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

    // standard getters
}