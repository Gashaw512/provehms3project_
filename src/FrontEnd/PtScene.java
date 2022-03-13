package FrontEnd;

import Controller.InpatientDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.SQLException;

public class PtScene {

    private BorderPane pane;
    private PtScene ptScene;
    private Form_Window form_window;
    private Menu_scene menu_scene;
    private patient_table_model table;
    private Pt_form_pane form_pane;
    private Menu orderMenu;
    private InpatientTableModel in_table;

    public PtScene() {
        this.table = new patient_table_model();
        this.form_pane = new Pt_form_pane();
        this.orderMenu = new Menu("Order by");
    }

    public Pane content() throws SQLException {
        in_table = new InpatientTableModel();

        javafx.scene.control.TextField search = new javafx.scene.control.TextField();
        javafx.scene.control.Label lblSearch = new javafx.scene.control.Label("\t");
        search.setOnMousePressed(e -> lblSearch.setText("Search"));

        Tooltip tip = new Tooltip();

        javafx.scene.control.Label tipLabel = Pt_form_pane.lbl_format("Search by \n Name or ID");
        tipLabel.setFont(javafx.scene.text.Font.font(15));
        tipLabel.setTextFill(javafx.scene.paint.Color.BLUE);
        tip.setGraphic(tipLabel);
        search.setTooltip(tip);
        search.setPromptText("Search");
        search.setFocusTraversable(false);

        GridPane grid = new GridPane();

        search.widthProperty().addListener(ov ->
        {
            // search.setPrefWidth(grid.getWidth());
            search.setPrefWidth(210);
        });
        grid.setStyle("-fx-border-width: 20;-fx-background-color: #D3D3D3D3");
        grid.add(lblSearch, 0, 0);
        grid.add(search, 1, 0);
        grid.setHgap(15);
        grid.setPadding(new Insets(2, 10, 2, 2));//top->right->bottom->left
        grid.setAlignment(Pos.TOP_RIGHT);

        search.setOnKeyTyped(e ->
        {
            try {
                if (InputValidator.validateNumberType(search.getText().trim(), "process")) {
                    if (!search.getText().trim().isEmpty())
                        in_table.showSpecificpatient(Integer.parseInt(search.getText()), "");
                    else in_table.showPatientS();


                } else {
                    in_table.showSpecificpatient(0, search.getText());
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });

        Tooltip tipa = new Tooltip();
        javafx.scene.control.Label tipLabela = Pt_form_pane.lbl_format("Click to add new Patient");
        tipLabela.setFont(javafx.scene.text.Font.font(15));
        tipLabela.setTextFill(javafx.scene.paint.Color.BLUE);
        tipa.setGraphic(tipLabela);

        javafx.scene.control.Button btnAdd = new javafx.scene.control.Button("Register");
        btnAdd.setDefaultButton(true);
        btnAdd.setTooltip(tipa);
        btnAdd.setCursor(javafx.scene.Cursor.HAND);
        btnAdd.setOnAction(e -> pt_form_pane(1));

        Tooltip tipd = new Tooltip();
        javafx.scene.control.Label tipLabeld = Pt_form_pane.lbl_format("Click to delete  selected record \n row");
        tipLabeld.setFont(javafx.scene.text.Font.font(15));
        tipLabeld.setTextFill(javafx.scene.paint.Color.BLUE);
        tipd.setGraphic(tipLabeld);
        javafx.scene.control.Button btnDelete = new javafx.scene.control.Button("_Delete");
        btnDelete.setCursor(javafx.scene.Cursor.HAND);
        btnDelete.setTooltip(tipd);

        InpatientTableModel inpatientTableModel = new InpatientTableModel();
        btnDelete.setOnAction(e -> inpatientTableModel.deleteRecored());

        Tooltip tipe = new Tooltip();
        javafx.scene.control.Label tipLabele = Pt_form_pane.lbl_format("Click to Edit  selected record \n row");
        tipLabele.setFont(Font.font(15));
        tipLabele.setTextFill(Color.BLUE);
        tipe.setGraphic(tipLabele);
        javafx.scene.control.Button btnEdit = new Button("_Edit");
        btnEdit.setCursor(Cursor.HAND);
        btnEdit.setTooltip(tipe);
        btnEdit.setOnAction(e -> pt_form_pane(2));

        HBox btnLayout = new HBox();
        btnLayout.setSpacing(30);
        btnLayout.setPadding(new Insets(10, 5, 5, 5));
        btnLayout.setAlignment(Pos.CENTER);
        btnLayout.getChildren().addAll(btnEdit, btnAdd, btnDelete);

        VBox co = new VBox();
        co.getChildren().addAll(Menu_scene.logout(), grid);
        co.setAlignment(Pos.TOP_RIGHT);
        co.setSpacing(4);

        HBox content = new HBox();

        content.setSpacing(150);
        content.getChildren().addAll(btnLayout, co);
        content.setPadding(new javafx.geometry.Insets(0, 0, 10, 10));// top->right->bottom->left
        content.setAlignment(Pos.TOP_RIGHT);
        content.setMaxWidth(670);

        BorderPane contentPane = new BorderPane();
        contentPane.setTop(content);
        BorderPane.setAlignment(content, Pos.TOP_RIGHT);

        VBox layout = new VBox();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setSpacing(20);

        layout.getChildren().addAll(contentPane, in_table.table());
        in_table.showPatientS();
        return layout;

    }


    public void pt_form_pane(int mode) {
        if (mode == 1) {
            form_window = new Form_Window("Add New Patient", 450, 550);
            form_window.settingStage(mode);
        } else if (mode == 2) {
            if (table.selectedDoctor() == null) {
                AlertBox.selectionChecker("Please select the record  that you want to edit");

            } else {
                Form_Window form_window = new Form_Window("Edit Patient's Information", 450, 550);
                form_window.settingStage(mode);
            }

        }

    }

    public void file(Menu file) {

        MenuItem add = new MenuItem("add");
        MenuItem edit = new MenuItem("edit");
        MenuItem delete = new MenuItem("delete");
        MenuItem exit = new MenuItem("exit");

        add.setOnAction(e -> pt_form_pane(3));
        //add.setAccelerator(KeyCombination.keyCombination("ShortCut+A"));
        edit.setOnAction(e -> pt_form_pane(4));
        edit.setAccelerator(KeyCombination.keyCombination("ShortCut+E"));
        exit.setOnAction(e -> AlertBox.closeProgram(Login.stageProvider()));
        exit.setAccelerator(KeyCombination.keyCombination("ShortCut+X"));
        exit.setOnAction(e -> AlertBox.closeProgram(Login.stageProvider()));

        file.getItems().addAll(add, edit, delete);
        file.getItems().addAll(new SeparatorMenuItem());
        file.getItems().addAll(exit);

    }


}
