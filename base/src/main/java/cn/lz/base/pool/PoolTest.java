package cn.lz.base.pool;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PoolTest {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://129.226.173.133:3306/java_demo?useSSL=false";
        String user = "root";
        String password = "Lyy780808#";
        List<Connection> pools = new ArrayList<>();
        ConnectionPool connectionPool = new BasicConnectionPool(url, user, password, pools, 10);
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from department;");
        boolean execute = preparedStatement.execute();
        if (execute) {
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                String columnOfIndex1 = resultSet.getString("name");
                log.info("columnOfIndex1 {}", columnOfIndex1);
            }
            preparedStatement.close();
        }
        PreparedStatement select_name_from_department = connection.prepareStatement("select name from department");
        boolean execute1 = select_name_from_department.execute();
        if (execute1) {
            ResultSet resultSet = select_name_from_department.getResultSet();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                log.info("ps2 {}", name);
            }
            select_name_from_department.close();
        }
        connectionPool.releaseConnection(connection);

    }
}
