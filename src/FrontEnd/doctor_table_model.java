package FrontEnd;

import Controller.DoctorDAO;
import Controller.InpatientDAO;
import DataModel.Doctor;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.*;

import java.sql.Date;
import java.sql.SQLException;

public class doctor_table_model {
    private TableView<Doctor> table;

    public doctor_table_model() {
        table = new TableView<>();
        // table.setItems(); default value that table contain.
    }


    public TableView<Doctor> table() {
        ////////////////column name///////////////////
        TableColumn<Doctor, Integer> dr_ID = new TableColumn<>("ID");
        dr_ID.setPrefWidth(95);
        dr_ID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<Doctor, String> dr_firstName = new TableColumn<>("First Name");
        dr_firstName.setPrefWidth(200);
        dr_firstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        TableColumn<Doctor, String> dr_lastName = new TableColumn<>("Last Name");
        dr_lastName.setPrefWidth(200);
        dr_lastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        TableColumn<Doctor, String> dr_gender = new TableColumn<>("Gender");
        dr_gender.setPrefWidth(90);
        dr_gender.setCellValueFactory(cellData -> cellData.getValue().genderProperty());

        TableColumn<Doctor, Date> dr_birthDate = new TableColumn<>("Birth Date");
        dr_birthDate.setPrefWidth(150);
        dr_birthDate.setCellValueFactory(cellData -> cellData.getValue().ageProperty());

        TableColumn<Doctor, String> dr_address = new TableColumn<>("Address");
        dr_address.setPrefWidth(200);
        dr_address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

        TableColumn<Doctor, String> dr_phoneNumber = new TableColumn<>("Phone Number");
        dr_phoneNumber.setPrefWidth(150);
        dr_phoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        TableColumn<Doctor, String> dr_qualification = new TableColumn<>("Qualification");
        dr_qualification.setPrefWidth(200);
        dr_qualification.setCellValueFactory(cellData -> cellData.getValue().qualificationProperty());

        table.setEditable(true);
        table.setLayoutX(190);
        table.setLayoutY(102);
        table.setTableMenuButtonVisible(true);
        table.setPrefHeight(600);
        table.setPrefWidth(600);
        table.setPlaceholder(new Label("Empty List"));


        table.getColumns().addAll(dr_ID, dr_firstName, dr_lastName, dr_gender, dr_birthDate, dr_address,
                dr_phoneNumber, dr_qualification);
        table.setOnMousePressed(e -> System.out.println("No"));
        table.setOnKeyPressed(e -> System.out.println("Yes"));

        table.setContextMenu(context_menu());/////add,edit,delete,sort By,refresh

        return table;
    }

    public void showStaffInfo() throws SQLException {
        table.setItems(DoctorDAO.getStaffList());
    }

    private ContextMenu context_menu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem add_menu = new MenuItem("_Add New Patient");
        MenuItem edit_menu = new MenuItem("_Edit Selected Row");
        MenuItem delete_menu = new MenuItem("_Delete Selected Row");
        MenuItem refresh = new MenuItem("_Refresh");

        Menu sort_menu = new Menu("_Sort By");
        ToggleGroup toggles = new ToggleGroup();
        RadioMenuItem name_menu = new RadioMenuItem("_Name");
        name_menu.setToggleGroup(toggles);
        RadioMenuItem id_menu = new RadioMenuItem("Patient _ID");
        id_menu.setToggleGroup(toggles);
        RadioMenuItem date_menu = new RadioMenuItem("_Date Modified");
        date_menu.setToggleGroup(toggles);
        sort_menu.getItems().addAll(name_menu, id_menu, date_menu);
        id_menu.setSelected(true);
        contextMenu.getItems().addAll(add_menu, edit_menu, delete_menu, sort_menu, refresh);
        return contextMenu;
    }

    /////////populate  Doctors for tableView
    public void populate_patient(ObservableList<Doctor> doctorData) throws ClassNotFoundException {
        table.setItems(doctorData);
    }

    public Doctor selectedDoctor()// it returns single row.
    {
        return table.getSelectionModel().getSelectedItem();//Doctor doctor=
    }

    public TableView doctorTableView() {
        return table;
    }// provides table.

    public void showSpecificStaff(int id, String name) throws SQLException {
        table.setItems(DoctorDAO.getSpecificSatffInfo(id, name));
    }
}
