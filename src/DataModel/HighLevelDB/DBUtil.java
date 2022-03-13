package DataModel.HighLevelDB;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

/*
 * DBUTil class responsible for DB connection, DB disconnection,DB query and update operation.
 * The other Model class uses DBUtil class's methods to do higher level database operation */
public class DBUtil {
    private static final String dbName = "Hospital Management System";
    private static Connection connection = null;
    private static final String password = "";
    public static final String userName = "root";

    public static Connection dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName + "?useSSL=true",userName,
                    password);
        } catch (SQLException e) {
            System.out.println("connection failed,check output console");
            e.printStackTrace();
            throw e;
        }

        return connection;
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public static ResultSet dbExecuteQuery(String statement) throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        ResultSet rsltSet = null;
        CachedRowSetImpl crs = null;
        try {
            dbConnect();
            stmt = connection.createStatement();/// create statement
            rsltSet = stmt.executeQuery(statement);//execute select query
//            crs = new CachedRowSetImpl();
//            crs= (CachedRowSetImpl) RowSetProvider.newFactory().createCachedRowSet();
//            crs.populate(rsltSet);
        } catch (SQLException e) {
            System.out.println("Problem occured at execute Query operation! " + e);
        } finally {
           // if (rsltSet != null)
               // rsltSet.close();
            //if (stmt != null)
               // stmt.close();
        }
       // dbDisconnect();

        return rsltSet;
    }

    /*for insert/update/delete*/
    public static void dbExecuteUpdate(String stmt) throws SQLException, ClassNotFoundException {

    }

}
