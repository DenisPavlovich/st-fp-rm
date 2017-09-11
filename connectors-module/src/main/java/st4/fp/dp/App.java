package st4.fp.dp;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class App {
    public static void main(String[] args) throws SQLException {
        DbcpServiceImpl db = DbcpServiceImpl.getInstance();

        Connection con = db.getConnection();

        try (Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(2));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            con.close();
        }
    }
}

class DbcpServiceImpl implements Closeable{

    private static final DbcpServiceImpl instance = new DbcpServiceImpl();
    private BasicDataSource connectionPool = new BasicDataSource();

    private DbcpServiceImpl() {
        ResourceBundle rb = ResourceBundle.getBundle("db");

        connectionPool.setDriverClassName(rb.getString("db.driver"));
        connectionPool.setUrl(rb.getString("db.url"));
        connectionPool.setUsername(rb.getString("db.name"));
        connectionPool.setPassword(rb.getString("db.password"));
        connectionPool.setInitialSize(Integer.parseInt(rb.getString("db.count.pools")));
    }

    public static DbcpServiceImpl getInstance(){
        return instance;
    }

    ///////////////////////////////////////////////////////////////////////////
    // public
    ///////////////////////////////////////////////////////////////////////////

    public Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    public void close() throws IOException {
        try {
            connectionPool.close();
        } catch (SQLException e) {
            e.printStackTrace(); //// TODO: 10.09.17 log
        }
    }
}
