package Controller;

import DataModel.HighLevelDB.DBUtil;
import DataModel.Patient;
import FrontEnd.InpatientTableModel;
import FrontEnd.Menu_scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class InpatientDAO {
    private static final String INSERT_QUERY = "INSERT INTO Inpatient (id, first_name, last_name, gender," +
            " phone_no, birth_date,admission_date, address, room) VALUES (?,?,?,?,?,?,?,?,?)";

    private static final String SELECT_QUERY = "SELECT * FROM Inpatient";
    private static final String idSelector = "SELECT id from inpatient";
    private static final String id_checker = "SELECT id from inpatient where id=?";
    private static final String DELETE_QUERY = "DELETE FROM inpatient WHERE id=?";
    private static final String SEARCH_OPERATION = "SELECT * FROM inpatient WHERE id=?";
    // private static final String SEARCH_OPERATION = "SELECT * FROM inpatient WHERE id LIKE '"+;

    public static void insertInpatientInfo(int id, String fName, String lName, String gender, String phone_no,
                                           Date date,
                                           Date admission, String address, int room) throws SQLException {
        try {
            Connection connection = DBUtil.dbConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, fName);
            preparedStatement.setString(3, lName);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, phone_no);
            preparedStatement.setDate(6, date);
            preparedStatement.setDate(7, admission);
            preparedStatement.setString(8, address);
            preparedStatement.setInt(9, room);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        InpatientTableModel in_table = new InpatientTableModel();

        // in_table.showPatientS(); this leads to bussiy.
    }

    public static Patient createInpatient(ResultSet resultSet) {
        Patient patient = new Patient();
        try {
            patient.setId(resultSet.getInt("id"));
            patient.setFirstName(resultSet.getString("first_name"));
            patient.setLastName(resultSet.getString("last_name"));
            patient.setGender(resultSet.getString("gender"));
            patient.setPhone(resultSet.getString("phone_no"));
            patient.setAge(resultSet.getDate("birth_date"));
            patient.setAdmissionDate(resultSet.getDate("admission_date"));
            patient.setAddress(resultSet.getString("address"));
            patient.setRoom(resultSet.getInt("room"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;//
    }

    public static ObservableList<Patient> getInpatientList() throws SQLException {
        ObservableList<Patient> patientList = FXCollections.observableArrayList();
        try {
            Connection connection = DBUtil.dbConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Patient patient = createInpatient(resultSet);
                patientList.add(patient);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return patientList;/// This return final value set to tables.
    }

    public static ObservableList<Patient> getSpecificPatient(int id, String name) throws SQLException {
        final String SEARCH_OPERATION2 = "SELECT * FROM inpatient WHERE id LIKE '%" + id + "%'";
        //"SELECT * FROM inpatient WHERE id LIKE '" + id + "%' OR first_name LIKE '" + name + "%'";
        final String SEARCH_OPERATION1 = "SELECT * FROM inpatient WHERE  first_name LIKE '%" + name + "%'";
        System.out.println("Name= " + name);
        System.out.println("ID= " + id);
        ObservableList<Patient> patientList = FXCollections.observableArrayList();
        try {
            Connection connection = DBUtil.dbConnect();
            PreparedStatement preparedStatement = null;
            if (id != 0) {
                preparedStatement = connection.prepareStatement(SEARCH_OPERATION2);
            } else if (id == 0) {
                preparedStatement = connection.prepareStatement(SEARCH_OPERATION2);
            } else if (name != "") {
                preparedStatement = connection.prepareStatement(SEARCH_OPERATION1);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Patient patient = createInpatient(resultSet);
                patientList.add(patient);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return patientList;/// This return final value set to tables.
    }

    public static int lastIdGetter() {
        int m = 0;
        int id = 0;
        try {
            Connection connection = DBUtil.dbConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(idSelector);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                if (resultSet.next()) {
                    int h = resultSet.getInt("id");
                    if (id + 1 != h) {
                        return id + 1;
                    }
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return id + 1;
    }

    public static String columnSelector(String column, String table, int id) {
        final String columnSelector = "SELECT " + column + " FROM " + table + " WHERE id=?";
        String columnValue = "";
        try {
            Connection connection = DBUtil.dbConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(columnSelector);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                columnValue = resultSet.getString(column);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return columnValue;
    }

    public static boolean idFounderChecker(int id) {
        try {
            Connection connection = DBUtil.dbConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(id_checker);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteSelectedRecorred(int id) {
        InpatientTableModel inpatientTableModel = new InpatientTableModel();
        try {
            Connection connection = DBUtil.dbConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            /// inpatientTableModel.showPatientS();
            System.out.println("You avae to do something ");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
