package FrontEnd;

import Controller.InpatientDAO;
import DataModel.Patient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.sql.Date;
import java.sql.SQLException;

public class Pt_form_pane {
    private BorderPane pane = new BorderPane();
    private GridPane gPane = new GridPane();

    private TextField txtFName = new TextField();
    private TextField txtLName = new TextField();
    private ComboBox<String> txtGender;
    private TextField txtId = new TextField();
    private DatePicker txtBirthDat = new DatePicker();
    private TextField txtAddress = new TextField();
    private DatePicker txtDate = new DatePicker();///////////admission date
    private TextField txtContactNumber = new TextField();
    private TextField txtRoomNumber = new TextField();
    private TextField[] textFields = {txtFName, txtLName,txtId,txtAddress,txtRoomNumber,txtRoomNumber};

    private static Tooltip tip0Information=new Tooltip();
    private static Tooltip tip1Information=new Tooltip();;
    private static Tooltip tip2Information=new Tooltip();;
    private static Tooltip tip3Information=new Tooltip();;
    private static Tooltip tip4Information=new Tooltip();;
    private static Tooltip tip5Information=new Tooltip();;

    private Tooltip[] tooltips = {tip0Information, tip1Information, tip2Information, tip3Information, tip4Information, tip5Information};


    public Pt_form_pane() {
        gPane.setVgap(10);
        gPane.setHgap(12);
    }

    public GridPane getgPane() {
        return gPane;
    }

    public TextField getTxtFName() {
        return txtFName;
    }

    public TextField getTxtLName() {
        return txtLName;
    }

    public ComboBox<String> getTxtGender() {
        return txtGender;
    }

    public TextField getTxtId() {
        return txtId;
    }

    public DatePicker getTxtBirthDat() {
        return txtBirthDat;
    }

    public TextField getTxtAddress() {
        return txtAddress;
    }

    public DatePicker getTxtDate() {
        return txtDate;
    }

    public TextField getTxtContactNumber() {
        return txtContactNumber;
    }

    public TextField getTxtRoomNumber() {
        return txtRoomNumber;
    }

    public void setPatient() {
    }

    public Pane addPatient(int mode) {

        Label lblName = lbl_format("First _Name:");
        gPane.add(lblName, 0, 0);
        gPane.add(txtFName, 1, 0);

        Label lbl_l_Name = lbl_format("Last _Name");
        gPane.add(lbl_l_Name, 0, 1);
        gPane.add(txtLName, 1, 1);

        Label lbl_pt_id = lbl_format("Patient I_D");
        gPane.add(lbl_pt_id, 0, 2);
        txtId.setText(Integer.toString(InpatientDAO.lastIdGetter()));
        txtId.setEditable(false);
        gPane.add(txtId, 1, 2);

        Label lbl_birth_date = lbl_format("Birth _Date:");
        gPane.add(lbl_birth_date, 0, 3);
        gPane.add(txtBirthDat, 1, 3);

        Label lblGender = lbl_format("_Gender:");
        gPane.add(lblGender, 0, 4);
        txtGender = new ComboBox<>();
        txtGender.getItems().addAll("Male", "Female");
        txtGender.setEditable(true);
        txtGender.getSelectionModel().selectFirst();
        gPane.add(txtGender, 1, 4);

        Label lblAddress = lbl_format("Address:");
        gPane.add(lblAddress, 0, 5);
        gPane.add(txtAddress, 1, 5);

        Label room = lbl_format("Room N_o");
        gPane.add(room, 0, 6);
        gPane.add(txtRoomNumber, 1, 6);

        Label lblPhone = lbl_format("Phone N_o:");
        gPane.add(lblPhone, 0, 7);
        gPane.add(txtContactNumber, 1, 7);

        Label lblAdmissionDate = lbl_format("Date of _Admission");
        gPane.add(lblAdmissionDate, 0, 8);
        gPane.add(txtDate, 1, 8);

        gPane.setPadding(new Insets(15, 0, 0, 25));
        /////gPane should be border

        Label label = new Label("Patient Registration Form");// fill the Whole fields.
        label.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 20));
        label.setTextFill(Color.CORNFLOWERBLUE);
        label.setUnderline(true);

        HBox textBox = new HBox();
        textBox.getChildren().addAll(label);
        textBox.setAlignment(Pos.CENTER);

        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(e-> {
            try {
                handle_adding_InpatientInfo();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        Button btnCancel = new Button("Cancel");
        Button btnClear = new Button("Clear");

        btnAdd.setDefaultButton(true);
        btnCancel.setOnAction(e -> Form_Window.closeWindow());
        btnClear.setOnAction(e -> clear_txt_field());

        HBox btnBox = new HBox();
        btnBox.setSpacing(15);
        btnBox.getChildren().addAll(btnClear, btnAdd, btnCancel);
        btnBox.setAlignment(Pos.BOTTOM_RIGHT);
        VBox b = new VBox();
        b.getChildren().addAll(gPane);

        pane.setTop(textBox);
        pane.setCenter(gPane);
        pane.setBottom(btnBox);
        pane.setPadding(new Insets(5, 15, 10, 0));


        ////////////////Validate input/////////////////
        String action_type = "process";
        tooltips[0] = Dr_form_pane.tooltip("Invalid name. Please try again!");
        txtFName.setOnKeyTyped(e -> InputValidator.keyTypedLetterHandler(tooltips[0], txtFName, action_type));

        tooltips[1] = Dr_form_pane.tooltip("Invalid name. Please try again!");
        txtLName.setOnKeyTyped(e -> InputValidator.keyTypedLetterHandler(tooltips[1], txtLName, action_type));

        tooltips[2] = Dr_form_pane.tooltip("Room No should be only digit");
        txtRoomNumber.setOnKeyTyped(e -> InputValidator.keyTypedNumberHandler(tooltips[2], txtRoomNumber, action_type));

        tooltips[3] = Dr_form_pane.tooltip("Invalid Phone Number!");
        txtContactNumber.setOnKeyTyped(e -> InputValidator.keyTypedPhoneHandler(tooltips[3], txtContactNumber, action_type));

        return pane;
    }

    public void editPatient() {
    }

    public static Label lbl(String message) {
        Label lblInformation = new Label(message);
        lblInformation.setWrapText(true);
        lblInformation.setTextFill(Color.RED);
        return lblInformation;
    }

    public static Label lbl_format(String txt) {
        Label lbl = new Label(txt);
        lbl.setWrapText(true);
        lbl.setMnemonicParsing(true);
        lbl.setFont(Font.font("New Roman", FontWeight.BOLD, FontPosture.ITALIC, 11.5));
        return lbl;
    }

    public void clear_txt_field() {
        txtFName.clear();
        txtLName.clear();
        txtAddress.clear();
        txtId.clear();
        txtContactNumber.clear();
        txtRoomNumber.clear();
    }
   private void handle_adding_InpatientInfo() throws SQLException {
        if (!InputValidator.isInvalid(tooltips)) {
            AlertBox.message("\t👉Unable to add this information!\n You have invalid field !" +
                    "\n\t\tPlease try again!");
        } else if (!InputValidator.isEmpty(textFields, gPane)) {
            AlertBox.message("You have empty field.");
        } else save_inPatient();
    }
    public void save_inPatient() throws SQLException {
        String firstName = txtFName.getText();
        String lastName = txtLName.getText();
        String phone = txtContactNumber.getText();
        int id = Integer.parseInt(txtId.getText());
        String gender = txtGender.getSelectionModel().getSelectedItem();
        String address = txtAddress.getText();
        java.sql.Date age = Date.valueOf(txtBirthDat.getValue());
        java.sql.Date admissionDate = Date.valueOf(txtDate.getValue());
        int room = Integer.parseInt(txtRoomNumber.getText());
        Patient patient = new Patient( firstName, lastName, phone, id, gender, address,age, room,
                admissionDate);
        InpatientDAO.insertInpatientInfo(id, firstName, lastName, gender, phone,age,admissionDate, address,
                room);//add
        // to database

        //doctor_table_model1.doctorTableView().setItems(DoctorDAO.getStaffList());
        Menu_scene menu_scene=new Menu_scene();
        menu_scene.handle_btn_doctor();
    }

    public static String forInvalid(String code) {
        if (code.equals("1")) {
            return "You have invalid Information";
        } else if (code.equals("2")) {
            return "You have one or more unfilled fields";
            //////This is a required field.
        } else return null;

    }

}
