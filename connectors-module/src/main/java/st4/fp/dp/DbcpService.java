package st4.fp.dp;

import java.io.Closeable;
import java.sql.Connection;

/**
 * Created by denis on 10.09.17.
 */
public interface DbcpService extends Closeable{
    Connection getConnection();
}
