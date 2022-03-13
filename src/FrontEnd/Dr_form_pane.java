package FrontEnd;

import Controller.DoctorDAO;
import DataModel.Doctor;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Dr_form_pane {
    private BorderPane pane = new BorderPane();
    private GridPane gPane = new GridPane();
    private doctor_table_model doctor_table_model1;
  // private  Menu_scene menu_scene=new Menu_scene();
    private TextField txtFName = new TextField();
    private TextField txtLName = new TextField();
    private ComboBox<String> txtGender;
    private TextField txtId = new TextField();
    private DatePicker txtBirthDat = new DatePicker();
    private TextField txtAddress = new TextField();
    private TextField txtPhoneNumber = new TextField();
    private TextField txtQualification = new TextField();

    private TextField[] txt = {txtFName, txtLName, txtId, txtAddress, txtPhoneNumber, txtQualification};


    ////////////change labels to tooltip//////////
   /* private static Tooltip tip0Information = new Tooltip();
    private static Tooltip tip1Information = new Tooltip();
    private static Tooltip tip2Information = new Tooltip();
    private static Tooltip tip3Information = new Tooltip();
    private static Tooltip tip4Information = new Tooltip();
    private static Tooltip tip5Information = new Tooltip();*/

    private Tooltip[] tooltips = {new Tooltip(), new Tooltip(), new Tooltip(), new Tooltip(), new Tooltip(), new Tooltip()};


    public Dr_form_pane() {

        gPane.setHgap(12);
        gPane.setVgap(10);
        doctor_table_model1=new doctor_table_model();
    }

    public Pane addDoctor(int mode) {

        Label lblFName = FrontEnd.Pt_form_pane.lbl_format("First _Name:");
        gPane.add(lblFName, 0, 0);

        txtFName.widthProperty().addListener(ov ->
                txtFName.setPrefWidth(15 + Form_Window.stageProvider().getWidth() - 450));
        gPane.add(txtFName, 1, 0);

        Label lblLName = FrontEnd.Pt_form_pane.lbl_format("Last _Name:");
        gPane.add(lblLName, 0, 1);
        gPane.add(txtLName, 1, 1);

        Label lblGender = FrontEnd.Pt_form_pane.lbl_format("_Gender:");
        gPane.add(lblGender, 0, 2);

        txtGender = new ComboBox<>();
        txtGender.getItems().addAll("Male ", "Female\t\t\t\t ", "Both");

        txtGender.getSelectionModel().selectFirst();
        gPane.add(txtGender, 1, 2);

        Label lblPhone = FrontEnd.Pt_form_pane.lbl_format("Phone N_o:");
        gPane.add(lblPhone, 0, 3);
        gPane.add(txtPhoneNumber, 1, 3);

        Label lblBirth = FrontEnd.Pt_form_pane.lbl_format("Birth _Date:");
        txtBirthDat.setValue(LocalDate.now());
        gPane.add(lblBirth, 0, 4);
        gPane.add(txtBirthDat, 1, 4);

        Label lblId = FrontEnd.Pt_form_pane.lbl_format("I_D:");
        gPane.add(lblId, 0, 5);
        txtId.setText("12");
        gPane.add(txtId, 1, 5);

        Label lblAd = FrontEnd.Pt_form_pane.lbl_format("_Address:");
        gPane.add(lblAd, 0, 6);
        gPane.add(txtAddress, 1, 6);

        Label lblQual = FrontEnd.Pt_form_pane.lbl_format("_Qualification:");
        gPane.add(lblQual, 0, 7);
        gPane.add(txtQualification, 1, 7);
        gPane.setPadding(new Insets(15, 0, 0, 25));


        Label label = new Label("Doctor Registration Form");// fill the Whole fields.
        label.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 20));
        label.setTextFill(Color.CORNFLOWERBLUE);
        label.setUnderline(true);

        HBox textBox = new HBox();
        textBox.getChildren().addAll(label);
        textBox.setAlignment(Pos.CENTER);

        Button btnAdd = new Button("Add");
        Button btnCancel = new Button("Cancel");
        Button btnClear = new Button("Clear");

        btnAdd.setDefaultButton(true);
        btnAdd.setOnAction(e1 -> {
            try {
                handle_addingDoctor_information();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btnCancel.setOnAction(e -> FrontEnd.Form_Window.closeWindow());
        btnClear.setOnAction(e -> clear_txt_field());

        HBox btnBox = new HBox();
        btnBox.setSpacing(15);
        btnBox.getChildren().addAll(btnClear, btnAdd, btnCancel);
        btnBox.setAlignment(Pos.BOTTOM_RIGHT);

        pane.setTop(textBox);
        pane.setCenter(gPane);
        pane.setBottom(btnBox);
        pane.setPadding(new Insets(5, 5, 5, 5));

        //////////////////////validate input///

        FrontEnd.Pt_form_pane pt_form_pane = new Pt_form_pane();
        String action_type = "process";
        tooltips[0] = tooltip("Invalid name. Please try again!");
        txtFName.setOnKeyTyped(e -> FrontEnd.InputValidator.keyTypedLetterHandler(tooltips[0], txtFName, action_type));

        tooltips[1] = tooltip("Invalid name. Please try again!");
        txtLName.setOnKeyTyped(e -> InputValidator.keyTypedLetterHandler(tooltips[1], txtLName, action_type));
        if (mode == 4) {
            editFormPanel();
        }
        return pane;
    }

    private void handle_addingDoctor_information() throws SQLException {
        if (!InputValidator.isInvalid(tooltips)) {
            AlertBox.message("\t👉Unable to add this information!\n You have invalid field !" +
                    "\n\t\tPlease try again!");
        } else if (!InputValidator.isEmpty(txt, gPane)) {
            AlertBox.message("You have empty field.");
        } else save_doctors();
    }


    public void clear_txt_field() {
        txtFName.clear();
        txtLName.clear();
        txtAddress.clear();
        txtId.clear();
        txtPhoneNumber.clear();
        System.out.println(txtBirthDat.getValue());
        txtQualification.clear();
        for (int i = 0; i < tooltips.length; i++) {
            if (gPane.getChildren().contains(tooltips[i])) {
                gPane.getChildren().remove(tooltips[i]);
            }
        }
    }

    public void editFormPanel() {
        Doctor doctor = new Doctor();

        txtFName.setText(doctor.getFirstName());
        txtLName.setText(doctor.getLastName());
        txtId.setText(Integer.toString(doctor.getId()));
        txtPhoneNumber.setText(doctor.getPhone());
        txtAddress.setText(doctor.getAddress());
        txtGender.getSelectionModel().select(doctor.getGender());

        // txtBirthDat.setValue(doctor.getAge());
    }

    public static void delete_record() {
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        doctor_table_model table = new doctor_table_model();
        if (table.selectedDoctor() == null) {
            AlertBox.selectionChecker("Please select the record  that you want to delete");
        } else {
            ObservableList<Doctor> selectedDoctor, allDoctor;
            allDoctor = table.doctorTableView().getItems();
            selectedDoctor = table.doctorTableView().getSelectionModel().getSelectedItems();
            selectedDoctor.forEach(allDoctor::remove);// it removes the item from GUi.
        }
    }

    public void save_doctors() throws SQLException {
        String firstName = txtFName.getText();
        String lastName = txtLName.getText();
        String phone = txtPhoneNumber.getText();
        int id = Integer.parseInt(txtId.getText());
        String gender = txtGender.getSelectionModel().getSelectedItem();
        String address = txtAddress.getText();
        java.sql.Date age = Date.valueOf(txtBirthDat.getValue());
        String qualification = txtQualification.getText();
        Doctor doctor = new Doctor(id, firstName, lastName, phone, gender, address, age, qualification);
        DoctorDAO.insertStaffInfo(id, firstName, lastName, gender, age, address, phone, qualification);//add to database

        //doctor_table_model1.doctorTableView().setItems(DoctorDAO.getStaffList());
        Menu_scene menu_scene=new Menu_scene();
        menu_scene.handle_btn_doctor();
    }

    public static Tooltip tooltip(String message) {
        Tooltip tipInformation = new Tooltip();
        tipInformation.setText(message);
        tipInformation.setMaxWidth(280);
        tipInformation.setWrapText(true);
        tipInformation.setShowDelay(Duration.ZERO);
        tipInformation.setAutoHide(false);
        tipInformation.setStyle("-fx-text-fill:red;" +
                "-fx-background-color:white");
        tipInformation.setFont(Font.font("Gloucester MT", FontWeight.BOLD, FontPosture.ITALIC, 12));

        return tipInformation;
    }

    public static void hideTooltip(Tooltip tooltip) {
        tooltip.hide();
    }

    public static void showTooltip(TextField pF, Tooltip tooltip) {
        Point2D p = pF.localToScene(pF.getBoundsInLocal().getMaxX(), pF.getBoundsInLocal().getMaxY());
        tooltip.show(pF,
                p.getX() + Form_Window.stageProvider().getScene().getX() + Form_Window.stageProvider().getX() + 5,
                p.getY() + Form_Window.stageProvider().getScene().getY() + Form_Window.stageProvider().getY() - 15);
    }
}

