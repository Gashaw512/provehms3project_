package FrontEnd;

import Controller.DoctorDAO;
import DataModel.Doctor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.sql.SQLException;

public class DrScene {
    private BorderPane pane;
    private PtScene ptScene;
    private Form_Window form_window;
    private Menu_scene menu_scene;
    private doctor_table_model table;
    private Dr_form_pane form_pane;
    private Menu orderMenu;

    public DrScene() {
        this.table = new doctor_table_model();
        this.form_pane = new Dr_form_pane();
        this.orderMenu = new Menu("Order by");
    }

    public Pane content() throws SQLException {
        ptScene = new PtScene();
        menu_scene = new Menu_scene();

        javafx.scene.control.TextField search = new javafx.scene.control.TextField();
        Label lblSearch = new Label("\t");
        search.setOnMousePressed(e -> lblSearch.setText("Search"));

        Tooltip tip = new Tooltip();
        tip.setStyle("-fx-background-color:  #b3c6ff;");
        tip.setShowDelay(Duration.ZERO);
        Label tipLabel = Pt_form_pane.lbl_format("Search by \n Name or ID");
        tipLabel.setFont(Font.font(15));
        tipLabel.setTextFill(Color.BLUE);
        tip.setGraphic(tipLabel);
        search.setTooltip(tip);
        search.setPromptText("Search");

        GridPane grid = new GridPane();

        search.widthProperty().addListener(ov ->
        {
            // search.setPrefWidth(grid.getWidth());
            search.setPrefWidth(210);
        });
        grid.setStyle("-fx-border-width: 25;-fx-background-color: #D3D3D3D3");
        grid.add(lblSearch, 0, 0);
        grid.add(search, 1, 0);
        grid.setHgap(15);
        grid.setAlignment(Pos.TOP_RIGHT);
        search.setFocusTraversable(false);

        VBox co = new VBox();
        co.getChildren().addAll(Menu_scene.logout(), grid);
        co.setAlignment(Pos.TOP_RIGHT);
        co.setSpacing(6);

        search.setOnKeyTyped(e ->
        {

            try {
                if (InputValidator.validateNumberType(search.getText().trim(), "process")) {
                    if (!search.getText().trim().isEmpty())
                        table.showSpecificStaff(Integer.parseInt(search.getText()), "");
                    else table.showStaffInfo();


                } else {
                    table.showSpecificStaff(0, search.getText());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Tooltip tipa = new Tooltip();
        tipa.setShowDelay(Duration.ZERO);
        Label tipLabela = Pt_form_pane.lbl_format("Click to add new Doctors");
        tipLabela.setFont(Font.font(15));
        tipLabela.setTextFill(Color.BLUE);
        tipa.setGraphic(tipLabela);

        Button btnAdd = new Button("Register");
        btnAdd.setDefaultButton(true);
        btnAdd.setTooltip(tipa);
        btnAdd.setCursor(Cursor.HAND);
        btnAdd.setOnAction(e -> doctor_form_panel(3));

        Tooltip tipd = new Tooltip();
        tipd.setShowDelay(Duration.ZERO);
        Label tipLabeld = Pt_form_pane.lbl_format("Click to delete  selected record \n ");
        tipLabeld.setFont(Font.font(15));
        tipLabeld.setTextFill(Color.BLUE);

        tipd.setGraphic(tipLabeld);
        Button btnDelete = new Button("_Delete");
        btnDelete.setCursor(Cursor.HAND);
        btnDelete.setTooltip(tipd);
        btnDelete.setOnAction(e -> Dr_form_pane.delete_record());

        Tooltip tipe = new Tooltip();
        tipe.setShowDelay(Duration.ZERO);
        Label tipLabele = Pt_form_pane.lbl_format("Click to Edit  selected record \n ");
        tipLabele.setFont(Font.font(15));
        tipLabele.setTextFill(Color.BLUE);

        tipe.setGraphic(tipLabele);
        Button btnEdit = new Button("_Edit");
        btnEdit.setCursor(Cursor.HAND);
        btnEdit.setTooltip(tipe);
        btnEdit.setOnAction(e -> doctor_form_panel(4));

        HBox btnLayout = new HBox();
        btnLayout.setAlignment(Pos.CENTER);
        btnLayout.setPadding(new Insets(10));
        btnLayout.setSpacing(35);
        btnLayout.getChildren().addAll(btnEdit, btnAdd, btnDelete);

        HBox content = new HBox();
        content.setStyle(
                "-fx-padding:5;" +
                        "-fx-border-insets:7;");
        content.setSpacing(150);
        content.getChildren().addAll(btnLayout, co);
        content.setPadding(new Insets(0, 10, 10, 0));
        content.setAlignment(Pos.TOP_RIGHT);
        content.setMaxWidth(870);

        BorderPane contentPane = new BorderPane();
        contentPane.setTop(content);
        BorderPane.setAlignment(content, Pos.TOP_RIGHT);

        VBox layout = new VBox();
        layout.setPadding(new Insets(5, 5, 10, 10));
        layout.setSpacing(20);
        /////////////////////////set data at initializing///////////
        try {
            table.showStaffInfo();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        layout.getChildren().addAll(contentPane, table.table());

        return layout;
    }

    public void doctor_form_panel(int mode) {

        if (mode == 3) {
            form_window = new Form_Window("Add New Doctor", 400, 550);
            form_window.settingStage(mode);
        } else if (mode == 4) {
            if (table.selectedDoctor() == null) {
                AlertBox.selectionChecker("Please select the record  that you want to edit");

            } else {
                Form_Window form_window = new Form_Window("Edit Dr.'s Information", 450, 550);
                form_window.settingStage(mode);
            }

        }
    }

    public void file(Menu file) {

        MenuItem add = new MenuItem("add");
        MenuItem edit = new MenuItem("edit");
        MenuItem delete = new MenuItem("delete");
        MenuItem exit = new MenuItem("exit");

        add.setOnAction(e -> doctor_form_panel(3));
        //add.setAccelerator(KeyCombination.keyCombination("ShortCut+A"));
        edit.setOnAction(e -> doctor_form_panel(4));
        edit.setAccelerator(KeyCombination.keyCombination("ShortCut+E"));
        exit.setOnAction(e -> AlertBox.closeProgram(Login.stageProvider()));
        exit.setAccelerator(KeyCombination.keyCombination("ShortCut+X"));
        exit.setOnAction(e -> AlertBox.closeProgram(Login.stageProvider()));

        file.getItems().addAll(add, edit, delete);
        file.getItems().addAll(new SeparatorMenuItem());
        file.getItems().addAll(exit);
    }

    public Menu order() {
        ///////order Option menu//////////////

        ToggleGroup orderTogle = new ToggleGroup();

        RadioMenuItem nameOrder = new RadioMenuItem("Name");
        RadioMenuItem idOrder = new RadioMenuItem("ID");
        RadioMenuItem dateOrder = new RadioMenuItem("Date modified");

        idOrder.setSelected(true);
        nameOrder.setToggleGroup(orderTogle);
        dateOrder.setToggleGroup(orderTogle);

        idOrder.setToggleGroup(orderTogle);
        orderMenu.getItems().addAll(nameOrder, idOrder, dateOrder);
        return orderMenu;
    }

    public static Tooltip normalTooltipFormat(String notes) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText(notes);
        tooltip.setShowDelay(Duration.seconds(0.015));
        tooltip.setAutoHide(false);
        tooltip.setShowDuration(Duration.seconds(70));
        tooltip.setStyle("-fx-text-fill:darkgreen;" +
                "-fx-background-color:white");
        tooltip.setFont(Font.font("Gloucester MT", FontWeight.BOLD, FontPosture.ITALIC, 11.5));
        tooltip.setMaxWidth(400);
        tooltip.setWrapText(true);
        return tooltip;
    }
}
