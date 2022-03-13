package FrontEnd;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Optional;


public class PathologyForm {
    private TextField pt_id;
    private TextField testName;
    private TextArea description;
    private VBox vBox;
    GridPane gridPane;

    public Pane pathologyForm() {
        pt_id = new TextField();
        pt_id.setMaxWidth(170);
        testName = new TextField();
        testName.setMaxWidth(170);
        description = new TextArea();
        description.setWrapText(true);
        description.setMaxSize(270, 120);

        HBox logout = new HBox(Menu_scene.logout());
        logout.setAlignment(Pos.TOP_RIGHT);

        Label label = Menu_scene.lblFormat("PATHOLOGY", 18);
        label.setUnderline(true);
        gridPane = new GridPane();

        gridPane.add(Menu_scene.lblFormat("Patient ID", 13), 0, 0);
        gridPane.add(pt_id, 1, 0);

        gridPane.add(Menu_scene.lblFormat("Test Name", 13), 0, 1);
        gridPane.add(testName, 1, 1);
        GridPane.setHalignment(Menu_scene.lblFormat("Test Name", 13), HPos.CENTER);

        gridPane.add(Menu_scene.lblFormat("Description", 13), 0, 2);
        gridPane.add(description, 1, 2);

        gridPane.setHgap(10);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        Button save = new Button("_Save");
        Button seeDetail = new Button("_See Detail");
        Button btnEdit = new Button("_Edit");
        seeDetail.setOnAction(e -> seeFullInformation());
        Button clear = new Button("_Clear");

        HBox hBox = new HBox();
        hBox.getChildren().addAll(save, seeDetail, btnEdit, clear);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

        vBox = new VBox();
        vBox.getChildren().addAll(logout, label, gridPane, hBox);
        vBox.setSpacing(25);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(3, 40, 20, 20));
        return vBox;
    }

    public static void seeFullInformation() {
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

    public static Pane fillInfo() {
        TextField txtFName = new TextField();
        txtFName.setMaxWidth(150);
        TextField txtLName = new TextField();
        TextField gender = new TextField();
        TextField txtId = new TextField();
        TextField txtBirthDat = new TextField();
        TextField txtAdmission = new TextField();
        TextField txtAddress = new TextField();
        TextField txtContactNumber = new TextField();
        TextField txtRoomNumber = new TextField();
        TextField testName = new TextField();
        TextArea description = new TextArea();
        description.setMaxSize(180, 130);

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

        gPane.add(Menu_scene.lblFormat("Date of \nAdmission:", 12), 0, 8);
        gPane.add(txtAdmission, 1, 8);

        gPane.add(Menu_scene.lblFormat("Test Name:", 12), 0, 9);
        gPane.add(testName, 1, 9);

        gPane.add(Menu_scene.lblFormat("Description:", 12), 0, 10);
        gPane.add(description, 1, 10);

        gPane.setVgap(8);

        gPane.setPadding(new Insets(15, 0, 0, 25));

        return gPane;
    }
}
