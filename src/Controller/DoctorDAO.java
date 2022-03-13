package Controller;

import DataModel.Doctor;
import DataModel.HighLevelDB.DBUtil;
import DataModel.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DoctorDAO {
    private static final String INSERT_QUERY = "INSERT INTO staff (dr_id, first_name, last_name, gender, birth_date, address, phone_no, qualification) VALUES (?,?,?,?,?,?,?,?)";

    private static final String SELECT_QUERY = "SELECT * FROM staff";
    private static final String SELECT_STAFFBYLASTNAME = "SELECT * FROM  staff WHERE id= ?";

    public static void insertStaffInfo(int id, String fName, String lName, String gender, Date date,
                                       String address, String phone_no, String qualification) {
        try {
            Connection connection = DBUtil.dbConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, fName);
            preparedStatement.setString(3, lName);
            preparedStatement.setString(4, gender);
            preparedStatement.setDate(5, date);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, phone_no);
            preparedStatement.setString(8, qualification);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Doctor createStaff(ResultSet resultSet) {
        Doctor doctor = new Doctor();
        try {
            doctor.setId(resultSet.getInt("dr_id"));
            doctor.setFirstName(resultSet.getString("first_name"));
            doctor.setLastName(resultSet.getString("last_name"));
            doctor.setGender(resultSet.getString("gender"));
            doctor.setAge(resultSet.getDate("birth_date"));
            doctor.setAddress(resultSet.getString("address"));
            doctor.setPhone(resultSet.getString("phone_no"));
            doctor.setQualification(resultSet.getString("qualification"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;// returns single row.
    }

    public static ObservableList<Doctor> getStaffList() throws SQLException {
        ObservableList<Doctor> drList = FXCollections.observableArrayList();
        try {
            Connection connection = DBUtil.dbConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Doctor doctor = createStaff(resultSet);
                drList.add(doctor);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return drList;/// This return final value set to tables.
    }

    public static ObservableList<Doctor> getSpecificSatffInfo(int id, String name) {
        final String SEARCH_OPERATION2 = "SELECT * FROM staff WHERE dr_id LIKE '" + id + "%'";
        //"SELECT * FROM inpatient WHERE id LIKE '" + id + "%' OR first_name LIKE '" + name + "%'";
        final String SEARCH_OPERATION1 = "SELECT * FROM staff WHERE  first_name LIKE '" + name + "%'";
        System.out.println("Name=  " + name);
        System.out.println("ID=    " + id);
        ObservableList<Doctor> doctors = FXCollections.observableArrayList();
        try {
            Connection connection = DBUtil.dbConnect();
            PreparedStatement preparedStatement = null;
            if (id != 0) {
                preparedStatement = connection.prepareStatement(SEARCH_OPERATION2);
            } else if (name != "") {
                preparedStatement = connection.prepareStatement(SEARCH_OPERATION1);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Doctor doctor = createStaff(resultSet);
                doctors.add(doctor);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return doctors;/// This return final value set to tables.
    }
}
