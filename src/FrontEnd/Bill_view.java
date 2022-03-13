package FrontEnd;

import Controller.InpatientDAO;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.Optional;

public class Bill_view {
    private TextField pt_id;
    private TextField bill_no;
    private DatePicker date;
    private TextField roomCharge;
    private TextField doctor_fee;
    private TextField pathology;
    private TextField miscellaneous;
    private TextField total;

    private HBox hBox1;
    private HBox hBox2;
    private HBox hBox3;
    private HBox hBox;
    private GridPane gridPane;
    private VBox vBox;
    private Tooltip tooltip;
    private Tooltip tooltips;
    private Tooltip notFoundTooltip;


    public Bill_view() {
        this.pt_id = new TextField();
        this.bill_no = new TextField();
        this.date = new DatePicker();
        date.setValue(LocalDate.now());
        this.roomCharge = new TextField();
        this.doctor_fee = new TextField();
        this.pathology = new TextField();
        this.miscellaneous = new TextField();
        this.total = new TextField();
        hBox = new HBox();
        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox3 = new HBox();
        gridPane = new GridPane();

        this.vBox = new VBox();
    }

    public Pane view_billForm() {
        tooltip = Dr_form_pane.tooltip("It should be only number. Please try again!");
        tooltips = Dr_form_pane.tooltip("Please insert id of patient.");
        notFoundTooltip = Dr_form_pane.tooltip("Patient Not Found With This ID");

        HBox logout = new HBox(Menu_scene.logout());
        logout.setAlignment(Pos.TOP_RIGHT);

        Label headerText = Menu_scene.lblFormat("Bill", 25);
        headerText.setUnderline(true);
        HBox labe = new HBox(headerText);
        labe.setAlignment(Pos.CENTER);


        hBox1.getChildren().addAll(Menu_scene.lblFormat("Bill NO.", 12), bill_no);
        hBox1.setSpacing(10);
        hBox2.getChildren().addAll(Menu_scene.lblFormat("Date", 12), date);
        hBox2.setSpacing(10);

        hBox.getChildren().addAll(hBox1, hBox2);// top part.
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);

        hBox3.getChildren().addAll((Menu_scene.lblFormat("Patient ID", 12)), pt_id);
        pt_id.setOnKeyTyped(e -> {
            if (tooltips.isShowing())
                tooltips.hide();
            if (notFoundTooltip.isShowing())
                notFoundTooltip.hide();
            InputValidator.keyTypedNumberHandlerM(tooltip, pt_id, "process");
        });
        hBox3.setSpacing(10);
        Button btn = new Button("Patient Detail");
        btn.setOnAction(e -> seeDetailBill());
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(hBox3, btn);
        vBox1.setSpacing(10);
        HBox detail = new HBox(vBox1);
        detail.setAlignment(Pos.CENTER);

        gridPane.add(Menu_scene.lblFormat("Room Charges", 12), 0, 0);
        gridPane.add(roomCharge, 1, 0);
        roomCharge.setOnKeyTyped(e -> {
            InputValidator.keyTypedNumberHandlerM(tooltip, roomCharge, "process");
            totalCharge();
        });

        gridPane.add(Menu_scene.lblFormat("Doctor fee", 12), 0, 1);
        gridPane.add(doctor_fee, 1, 1);
        doctor_fee.setOnKeyTyped(e -> {
            InputValidator.keyTypedNumberHandlerM(tooltip, doctor_fee, "process");
            totalCharge();
        });

        gridPane.add(Menu_scene.lblFormat("Pathology", 12), 0, 2);
        gridPane.add(pathology, 1, 2);
        pathology.setOnKeyTyped(e -> {
            InputValidator.keyTypedNumberHandlerM(tooltip, pathology, "process");
            totalCharge();
        });

        gridPane.setVgap(15);
        gridPane.setHgap(10);


        HBox hBox4 = new HBox();
        hBox4.getChildren().addAll(Menu_scene.lblFormat("Miscellaneous", 12), miscellaneous);
        hBox4.setSpacing(10);
        miscellaneous.setOnKeyTyped(e -> {
            InputValidator.keyTypedNumberHandlerM(tooltip, miscellaneous, "process");
            totalCharge();
        });

        HBox hBox5 = new HBox();
        hBox5.getChildren().addAll(gridPane, hBox4);////Charges
        hBox5.setSpacing(20);
        hBox5.setAlignment(Pos.CENTER);

        HBox hBox6 = new HBox(Menu_scene.lblFormat("Total\t", 12), total);
        total.setEditable(false);
        hBox6.setSpacing(12);
        hBox6.setAlignment(Pos.CENTER);

        Button btnSave = new Button("_Save");
        Button btnClear = new Button("_Clear");
        Button btnEdit = new Button("\t_Edit\t");

        HBox btnBox = new HBox(btnSave, btnEdit, btnClear);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setSpacing(10);

        btnClear.setOnAction(e -> clearTextField());

        vBox.getChildren().addAll(logout, labe, hBox, detail, hBox5, hBox6, btnBox);
        vBox.setPadding(new Insets(3, 3, 3, 3));
        vBox.setSpacing(35);


        return vBox;
    }

    public void seeDetailBill() {
        if (pt_id.getText().trim().isEmpty()) {
            if (tooltip.isShowing())
                tooltip.hide();
            if (notFoundTooltip.isShowing())
                notFoundTooltip.hide();
            if (!tooltips.isShowing())
                showTooltip(pt_id, tooltips);
            return;//
        } else if (tooltip.isShowing())
            return;
        else if (tooltips.isShowing())
            return;
        else if (!InpatientDAO.idFounderChecker(Integer.parseInt(pt_id.getText()))) {
            showTooltip(pt_id, notFoundTooltip);
            return;
        }

        seeFullInformation();


    }

    public void clearTextField() {
        roomCharge.clear();
        pathology.clear();
        doctor_fee.clear();
        miscellaneous.clear();
        pt_id.clear();
        bill_no.clear();
        total.clear();
    }

    public void totalCharge() {
        int roomFee;
        int doctorFee;
        int lab;
        int other;
        if (!tooltip.isShowing()) {
            if (roomCharge.getText().isEmpty())
                roomFee = 0;
            else roomFee = Integer.parseInt(roomCharge.getText());

            if (doctor_fee.getText().isEmpty())
                doctorFee = 0;
            else doctorFee = Integer.parseInt(doctor_fee.getText());
            if (pathology.getText().isEmpty())
                lab = 0;
            else lab = Integer.parseInt(pathology.getText());
            if (miscellaneous.getText().isEmpty())
                other = 0;
            else other = Integer.parseInt(miscellaneous.getText());

            int total_fee = roomFee + doctorFee + lab + other;
            String tot = Integer.toString(total_fee);
            total.setText(tot);
        } else return;
    }

    public void seeFullInformation() {
        DialogPane dialogPane = new DialogPane();
        dialogPane.setHeader(Menu_scene.lblFormat("See Detail Information", 15));

        dialogPane.setContent(fillInfo());

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.getDialogPane().setContent(dialogPane);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.setY(350);
        dialog.setX(550);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.CANCEL)
            System.out.println("Ok pressed");
    }

    public Pane fillInfo() {
        int l = Integer.parseInt(pt_id.getText());
        TextField txtFName = new TextField();
        txtFName.setEditable(false);
        txtFName.setText(InpatientDAO.columnSelector("last_name", "inpatient", l));

        TextField txtLName = new TextField();
        txtLName.setEditable(false);
        txtLName.setText(InpatientDAO.columnSelector("first_name", "inpatient", l));

        TextField gender = new TextField();
        gender.setEditable(false);
        gender.setText(InpatientDAO.columnSelector("gender", "inpatient", l));

        TextField txtId = new TextField();
        txtId.setText(pt_id.getText());
        txtId.setEditable(false);

        TextField txtBirthDat = new TextField();
        txtBirthDat.setEditable(false);
        txtBirthDat.setText(InpatientDAO.columnSelector("birth_date", "inpatient", l));

        TextField txtAdmission = new TextField();
        txtAdmission.setEditable(false);
        txtAdmission.setText(InpatientDAO.columnSelector("admission_date", "inpatient", l));

        TextField txtAddress = new TextField();
        txtAddress.setEditable(false);
        txtAddress.setText(InpatientDAO.columnSelector("address", "inpatient", l));

        TextField txtContactNumber = new TextField();
        txtContactNumber.setEditable(false);
        txtContactNumber.setText(InpatientDAO.columnSelector("phone_no", "inpatient", l));

        TextField txtRoomNumber = new TextField();
        txtRoomNumber.setEditable(false);
        txtRoomNumber.setText(InpatientDAO.columnSelector("room", "inpatient", l));

        GridPane gPane = new GridPane();

        gPane.add(Menu_scene.lblFormat("First Name:", 12), 0, 0);
        gPane.add(txtFName, 1, 0);

        gPane.add(Menu_scene.lblFormat("Last Name:", 12), 0, 1);
        gPane.add(txtLName, 1, 1);

        gPane.add(Menu_scene.lblFormat("Patient\t ID:", 12), 0, 2);
        gPane.add(txtId, 1, 2);

        gPane.add(Menu_scene.lblFormat("Birth Date:", 12), 0, 3);
        gPane.add(txtBirthDat, 1, 3);

        gPane.add(Menu_scene.lblFormat("Gender:", 12), 0, 4);
        gPane.add(gender, 1, 4);

        gPane.add(Menu_scene.lblFormat("Address:", 12), 0, 5);
        gPane.add(txtAddress, 1, 5);

        gPane.add(Menu_scene.lblFormat("Room", 12), 0, 6);
        gPane.add(txtRoomNumber, 1, 6);

        gPane.add(Menu_scene.lblFormat("Phone No:", 12), 0, 7);
        gPane.add(txtContactNumber, 1, 7);

        gPane.add(Menu_scene.lblFormat("Date of \tAdmission:", 12), 0, 8);
        gPane.add(txtAdmission, 1, 8);

        gPane.setVgap(8);

        gPane.setPadding(new Insets(15, 0, 0, 25));

        return gPane;
    }

    public static void showTooltip(TextField pF, Tooltip tooltip) {
        Point2D p = pF.localToScene(pF.getBoundsInLocal().getMaxX(), pF.getBoundsInLocal().getMaxY());
        tooltip.show(pF,
                p.getX() + Login.stageProvider().getScene().getX() + Login.stageProvider().getX() + 5,
                p.getY() + Login.stageProvider().getScene().getY() + Login.stageProvider().getY() - 15);
    }
}
