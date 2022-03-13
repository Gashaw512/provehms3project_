package FrontEnd;

import DataModel.Patient;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Date;

public class patient_table_model {

    private TableView<Patient> table;

    ////////////////column name///////////////////
    private TableColumn<Patient, String> pt_ID;
    private TableColumn<Patient, String> pt_firstName;
    private TableColumn<Patient, String> pt_lastName;
    private TableColumn<Patient, String> pt_gender;
    private TableColumn<Patient, Date> pt_birthDate;
    private TableColumn<Patient, String> pt_address;
    private TableColumn<Patient, Integer> pt_roomNum;
    private TableColumn<Patient, String> pt_phoneNum;
    private TableColumn<Patient, Date> pt_dateOfAdmission;

    public patient_table_model() {

    }

    public Node table() {
        return table;
    }

    public Patient selectedDoctor() {
        return null;
    }
}
