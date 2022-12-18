package cn.saladday.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 操作数据库的工具类
 */
public final class  JDBCUtils {
    private static DataSource ds;
    private JDBCUtils(){};

    static {

        try {
            //静态代码块，在类加载的时候执行
            Properties pro = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            //通过配置文件创建连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取连接池对象
     * @return
     */
    public static DataSource getDateSource(){
        return ds;
    }

    /**
     * 获取数据库连接对象，将异常抛出给上级检查
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
