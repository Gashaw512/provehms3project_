package Controller;

import DataModel.HighLevelDB.DBUtil;
import DataModel.Patient;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * This handles patient related database  operation such as deleting,searching and updating with declared sql statement
 * DAO=database access object*/
public class PatientDAO {
    public static Patient search_patient(String id_or_name) {
        //declare select statement
        String select_stmt = "SELECT* FROM patient WHERE pt_id=" + id_or_name;
        //execute statement
       try {
            ResultSet rsPt = DBUtil.dbExecuteQuery(select_stmt);
        } catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }

        return null;
    }
}
