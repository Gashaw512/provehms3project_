package FrontEnd;

import javafx.scene.control.TableView;

import java.sql.*;

/*
 * A TableModel that supplies resultSet Data to javaFx Table.
 * */
public class TableModel /* extends TableView*/ {
    private Connection connection;
    private Statement statement;// use PreparedStatement since it is flexible/dynamic
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRow;
    private boolean databaseConnected = false;

    public TableModel(String url, String userName, String password, String query) throws SQLException {
        connection = DriverManager.getConnection(url, userName, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        databaseConnected = true;// update database connection  status
        setQuery(query);
    }

    /*
     * get Class that represent column type.
     * This class returns a class object that represent the super class of all objects in a particular column **/
    public Class getColomnClass(
            int column) throws IllegalStateException {
        if (!databaseConnected)// ensure database connection is available
            throw new IllegalStateException(" not connected to Database");
        try {
            String className = metaData.getColumnClassName(column + 1);//obtain fully qualified class name for specified column
            return Class.forName(className);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Object.class;// if error occurred
    }

    public void setQuery(String query) throws SQLException {
        resultSet = statement.executeQuery(query);//specify query and execute it.
        metaData = resultSet.getMetaData();//obtain meta data for result set
        resultSet.last();//move to last row
        numberOfRow = resultSet.getRow();//determine number of row.
        ////////notify table that model has changed/////////////

        //write some method

    }

}
