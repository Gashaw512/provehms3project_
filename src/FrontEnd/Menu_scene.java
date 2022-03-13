package FrontEnd;

import Controller.Login_controller;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;

////This class is called menu class. it display the main function of this system.
public class Menu_scene {
    private static Stage stage;
    private Scene scene;
    private VBox vBox_left;
    private VBox vBox_right;
    private VBox vBox_menu;
    private VBox vBox_content;
    private HBox hBox;
    private BorderPane bPane;
    private MenuBar menuBar;
    private Menu window;
    private Menu help;
    private Menu file;
    private Menu dr_orderMenu;

    private Pt_form_pane pt_form_pane;
    private PtScene ptScene;
    private DrScene drScene;

    private Button btn_in_patient;
    private Button btn_out_patient;
    private Button btnDoctor;
    private Button btnBilling;
    private Button btnPathology;

    private Button btnRoom;
    private Button btn;
    private ContextMenu drMenu;
    private ContextMenu ptMenu;

    private static String userName;
    private static String password;

    ////////////////password text field///////////
    PasswordField txtPassword;
    PasswordField txt_new_password;
    PasswordField txtConfirm;
    PasswordField[] textFields;

    public Menu_scene() {
        this.bPane = new BorderPane();
        this.vBox_left = new VBox();
        this.vBox_right = new VBox();
        this.vBox_content = new VBox();
        this.vBox_menu = new VBox();
        this.hBox = new HBox();
        this.menuBar = new MenuBar();
        this.file = new Menu("_File");
        this.help = new Menu("Help and Settings");
        this.window = new Menu("_Window");
        this.drScene = new DrScene();
        this.ptScene = new PtScene();
        this.dr_orderMenu = drScene.order();
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Menu_scene.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Menu_scene.password = password;
    }

    public Scene main() {

        vBox_left.getChildren().addAll(menu_provider());
        vBox_left.setSpacing(20);
        vBox_left.setPadding(new Insets(10, 10, 10, 10));
        vBox_left.setStyle("-fx-background-color: #b3e0ff;");
        vBox_left.setPrefWidth(205);
        vBox_left.setAlignment(Pos.CENTER);

        bPane.setLeft(vBox_left);

        vBox_content.setAlignment(Pos.CENTER_RIGHT);
        vBox_content.setSpacing(20);

        vBox_right.getChildren().addAll(menu_bar(), vBox_content);
        vBox_right.setAlignment(Pos.TOP_RIGHT);
        bPane.setCenter(vBox_right);

        scene = new Scene(bPane, 1200, 600);
//////////set Accelerator for this scene
        setAccelerator();
        return scene;
    }

    public void btnStyle(Button btn, String code) {
        if (code.equals("@hover")) {
            btn.setPrefSize(200, 32);
            btn.setFont(Font.font("Lucida Calligraphy", FontWeight.BOLD, FontPosture.ITALIC, 16.5));
            btn.setTextFill((Color.rgb(128, 0, 255)));
            btn.setUnderline(true);
            btn.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else if (code.equals("normal")) {
            btn.setPrefSize(160, 30);
            btn.setTextFill((Color.BLACK));
            btn.setFont(Font.font("New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
            btn.setUnderline(false);
        }
    }

    private Pane menu_provider() {
        ////////////Billing Button's////////////////////////
        btnBilling = new Button("BILLInG");
        btnBilling.setCursor(Cursor.HAND);
        btnBilling.setPrefSize(160, 30);
        btnBilling.setFont(Font.font("New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btnBilling.setOnMouseEntered(e -> btnStyle(btnBilling, "@hover"));
        btnBilling.setOnMouseExited(e -> btnStyle(btnBilling, "normal"));
        btnBilling.setOnAction(e -> handle_btnBilling());

////////////////InPatients's button/////////////
        btn_in_patient = new Button("INPATIENT");
        btn_in_patient.setCursor(Cursor.HAND);
        btn_in_patient.setFont(Font.font("New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btn_in_patient.setPrefSize(160, 30);
        btn_in_patient.setOnMouseEntered(e -> btnStyle(btn_in_patient, "@hover"));
        btn_in_patient.setOnMouseExited(e -> btnStyle(btn_in_patient, "normal"));
        btn_in_patient.setOnAction(e -> {
            try {
                handle_btn_inPatient();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
///////////////Outpatient/////////////////
        btn_out_patient = new Button("OUTPATIENT");
        btn_out_patient.setContextMenu(ptMenu);
        btn_out_patient.setFont(Font.font("New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btn_out_patient.setPrefSize(160, 30);
        btn_out_patient.setOnMouseEntered(e -> btnStyle(btn_out_patient, "@hover"));
        btn_out_patient.setOnMouseExited(e -> btnStyle(btn_out_patient, "normal"));

//////////Staff information///////////////
        Text text = new Text("STAFF INFORMATION");
        text.setFont(Font.font("New Romans", FontWeight.BOLD, FontPosture.REGULAR, 13.5));
        text.setWrappingWidth(100);
        VBox vbox = new VBox(text);
        btnDoctor = new Button();
        vbox.setAlignment(Pos.CENTER);
        btnDoctor.setGraphic(vbox);
        btnDoctor.setCursor(Cursor.HAND);
        btnDoctor.setPrefSize(160, 30);
        btnDoctor.setOnMouseEntered(e -> {
            btnDoctor.setPrefSize(200, 35);
            text.setFont(Font.font("Lucida Calligraphy", FontWeight.BOLD, FontPosture.ITALIC, 16.5));
            text.setFill((Color.rgb(128, 0, 255)));
            text.setUnderline(true);
            btnDoctor.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        });
        btnDoctor.setOnMouseExited(e -> {
            btnDoctor.setPrefSize(160, 35);
            text.setFill((Color.BLACK));
            text.setFont(Font.font("New Roman", FontWeight.BOLD, FontPosture.REGULAR, 13.5));
            text.setUnderline(false);
        });

        btnDoctor.setOnAction(e -> {
            try {
                handle_btn_doctor();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        btnPathology = new Button("PATHOLOGY");
        btnPathology.setCursor(Cursor.HAND);
        btnPathology.setPrefSize(160, 30);
        btnPathology.setFont(Font.font("New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
        btnPathology.setOnMouseEntered(e -> btnStyle(btnPathology, "@hover"));
        btnPathology.setOnMouseExited(e -> btnStyle(btnPathology, "normal"));

        btnPathology.setOnAction(e -> handle_btnPathology());

        ///////////menu container////////////////////////

        vBox_menu.getChildren().addAll(btn_in_patient, btn_out_patient, btnDoctor, btnBilling, btnPathology);
        vBox_menu.setSpacing(20);
        vBox_menu.setAlignment(Pos.CENTER);

        return vBox_menu;
    }

    public void handle_btn_inPatient() throws SQLException {
        PtScene ptScene = new PtScene();
        Login.stageProvider().setTitle("Inpatient");
        vBox_content.getChildren().clear();
        file.getItems().clear();
        ptScene.file(file);
        vBox_content.getChildren().addAll(ptScene.content());
    }

    public void handle_btn_outPatient() {

    }

    public void handle_btn_doctor() throws SQLException {
        DrScene drScene = new DrScene();
        Login.stageProvider().setTitle("Staff Information");
        vBox_content.getChildren().clear();//
        file.getItems().clear();
        drScene.file(file);
        if (!menuBar.getMenus().contains(dr_orderMenu)) {
            menuBar.getMenus().addAll(dr_orderMenu);
        }
        // menuBar.getMenus().remove(1); you can  remove objects by using index.
        vBox_content.getChildren().addAll(drScene.content());
    }

    public void handle_IP_billing() {

    }

    public void handle_btn_OP_billing() {
    }

    public void handle_btn_room() {

    }


    public void handle_btnBilling() {
        Bill_view bill = new Bill_view();
        Login.stageProvider().setTitle("Bill");
        vBox_content.getChildren().clear();
        vBox_content.getChildren().addAll(bill.view_billForm());
    }

    public void handle_btnPathology() {

        PathologyForm form = new PathologyForm();
        Login.stageProvider().setTitle("Pathology");
        vBox_content.getChildren().clear();
        file.getItems().clear();
        ptScene.file(file);
        vBox_content.getChildren().addAll(form.pathologyForm());
    }

    private MenuBar menu_bar() {
        MenuItem restartItem = new MenuItem("_Restart");
        MenuItem logout = new MenuItem("_Logout ");
        Login login = new Login();
        logout.setOnAction(e -> Login.stageProvider().setScene(login.loginScene()));
        MenuItem exit = new MenuItem("_Exit");
        exit.setAccelerator(KeyCombination.keyCombination("Shortcut+X"));
        exit.setOnAction(e -> System.exit(0));

        file.getItems().addAll(restartItem, logout, exit);

        Menu show = new Menu("Show");
        CheckMenuItem person_form = new CheckMenuItem("_Person Form");
        show.getItems().addAll(person_form);
        person_form.setSelected(true);
        Menu new_window = new Menu("_New Window");
        New_window n = new New_window();
        MenuItem with_new = new MenuItem("With Other Account");
        //with_new.setOnAction(e -> n.create_window(1));
        MenuItem this_account = new MenuItem("With This Account");
        // this_account.setOnAction(e -> n.create_window(0));
        new_window.getItems().addAll(this_account, with_new);
        window.getItems().addAll(show, new_window);

        MenuItem shortcut = new MenuItem("_Shortcut");////////ctrl+shift+s
        MenuItem about = new MenuItem("_About..");
        about.setOnAction(e->ProjectDocumentation.notes());
        Menu security = new Menu("_Security settings");
        MenuItem modify = new MenuItem("_Modify privacy password");
        modify.setOnAction(e -> account_form("Change Password", 0));
        MenuItem changAccount = new MenuItem("_Change the account name");
        changAccount.setOnAction(e -> account_form("Rename Account", 1));
        MenuItem createAccount = new MenuItem("_Create new account");
        createAccount.setOnAction(e -> account_form("Add New User", 2));

        MenuItem privacy = new MenuItem("_Privacy");
        privacy.setDisable(true);
        MenuItem userAccounts = new MenuItem("_User Accounts");
        userAccounts.setOnAction(e -> account_form("Manage Accounts", 3));
        security.getItems().addAll(modify, changAccount, createAccount, privacy, userAccounts);
        help.getItems().addAll(about, shortcut, security);

        person_form.setOnAction(e ->
        {
            boolean isSelected = person_form.isSelected();
            vBox_left.setVisible(isSelected);
            vBox_left.managedProperty().bind(vBox_left.visibleProperty());
        });
        menuBar.getMenus().addAll(file, window, help);
        return menuBar;
    }

    private void setAccelerator() {
        bPane.getScene().getAccelerators().put(////ctrl+shift+A
                new KeyCodeCombination(KeyCode.A, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN),
                () -> {
                    try {
                        handle_btn_inPatient();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
        bPane.getScene().getAccelerators().put(////ctrl+shift+A
                new KeyCodeCombination(KeyCode.D, KeyCombination.SHORTCUT_DOWN, KeyCombination.SHIFT_DOWN),
                () -> {
                    try {
                        handle_btn_doctor();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void account_form(String title, int code) {
        stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setTitle(title);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinHeight(250);
        stage.setMinWidth(250);
        stage.setY(0);
        stage.setX(Login.stageProvider().getWidth() / 2);
        ///////////////User profile form////////////
        switch (code) {
            case 0:
                stage.setScene(new Scene(modifyAccount_content()));
                break;
            case 1:
                stage.setScene(new Scene(changeAccountName()));
                break;
            case 2:
                stage.setScene(new Scene(newAccount_content()));
                break;
            case 3:
                stage.setScene(new Scene(manageAccounts()));
                break;
        }
        stage.show();
    }

    private Pane newAccount_content() {
        Label lblAdd = lblFormat("Add a user", 14.0);

        TextField txtUserName = new TextField();
        txtUserName.setPromptText("User Name");
        txtUserName.setFocusTraversable(false);
        txtUserName.setOnKeyTyped(e -> InputValidator.keyHandler1(0));

        TextField txtPassword = new PasswordField();
        txtPassword.setPromptText("Password");
        txtPassword.setFocusTraversable(false);
        txtPassword.setOnKeyTyped(e -> InputValidator.keyHandler1(2));

        TextField txtRePassword = new PasswordField();
        txtRePassword.setPromptText("Reenter Password");
        txtRePassword.setFocusTraversable(false);
        txtRePassword.setOnKeyTyped(e -> InputValidator.keyHandler1(4));

        TextField[] textFields1 = {txtUserName, txtPassword, txtRePassword};

        Label lbl = lblFormat("Account Type", 12.5);
        lbl.setTooltip(DrScene.normalTooltipFormat("Every user who wants to use this software is authenticated by " +
                "means of username and password which is already created by administrator and is stored in database " +
                ",therefore only authenticated users can log on to the program with limited access. "));
        RadioButton radioButton = new RadioButton("Standard      ");
        Tooltip standard = DrScene.normalTooltipFormat("Standard Accounts can use most software ");
        RadioButton radioButton1 = new RadioButton("Administrator");
        Tooltip administrator = DrScene.normalTooltipFormat("Administrator is the only one who  has  complete " +
                "control" +
                " over this software " +
                "and can create a new users to access  this software.");
        ToggleGroup toggle = new ToggleGroup();

        RadioButton[] radioButtons = {radioButton, radioButton1};

        radioButton.setOnAction(e -> InputValidator.rButtonSelectionChecker(radioButtons));
        radioButton.setFocusTraversable(false);
        radioButton1.setOnAction(e -> InputValidator.rButtonSelectionChecker(radioButtons));
        radioButton1.setFocusTraversable(false);


        radioButton.setToggleGroup(toggle);
        radioButton.setTooltip(standard);
        radioButton1.setToggleGroup(toggle);
        radioButton1.setTooltip(administrator);

        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(txtUserName, txtPassword, txtRePassword);
        vBox1.setPrefWidth(290);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setPadding(new Insets(10, 40, 60, 30));///////top,right,bottom,left
        vBox1.setSpacing(20);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(lblAdd, vBox1, lbl, radioButton, radioButton1);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(8, 15, 15, 15));
        vBox.setSpacing(15);

        Button btnCreate = new Button(" _Add ");
        btnCreate.setDefaultButton(true);
        Button btnCancel = new Button("_Cancel");

        btnCreate.setOnAction(e ->
        {
            try {
                // validateAddUserField(textFields1, radioButtons);
                InputValidator.validateAddUser_field(textFields1, radioButtons);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            //if()
        });
        btnCancel.setOnAction(e -> stage.close());

        HBox hBox = new HBox();
        hBox.getChildren().addAll(btnCreate, btnCancel);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.setPadding(new Insets(15, 15, 15, 15));

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vBox);
        borderPane.setBottom(hBox);

        return borderPane;
    }

    private Pane modifyAccount_content() {
        this.txtPassword = new PasswordField();
        this.txt_new_password = new PasswordField();
        this.txtConfirm = new PasswordField();

        textFields = new PasswordField[]{txtPassword, txt_new_password, txtConfirm};

        // Label lblInfo = lblFormat("Change  " + getUserName() + "'s  Password\t\t" + l, 14.0);

        txtPassword.setPromptText("Current Password");
        txtPassword.setFocusTraversable(false);
        txtPassword.setOnKeyTyped(e -> InputValidator.keyHandler(0));

        txt_new_password.setPromptText("New Password");
        txt_new_password.setFocusTraversable(false);
        txt_new_password.setOnKeyTyped(e -> InputValidator.keyHandler(2));

        txtConfirm.setPromptText("Confirm New Password");
        txtConfirm.setFocusTraversable(false);
        txtConfirm.setOnKeyTyped(e -> InputValidator.keyHandler(4));

        Button btnOk = new Button("_Change Password");
        btnOk.setOnAction(e -> {
            try {
                //validate_modify_password(textFields);
                InputValidator.validateModifyPassword(textFields);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        Button btnCancel = new Button("_Cancel");
        btnCancel.setOnAction(e -> stage.close());

        HBox hBox = new HBox();
        hBox.setSpacing(15);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().addAll(btnOk, btnCancel);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(titleStyle("Change  ", getUserName() + "'s", "\tPassword"), txtPassword, txt_new_password, txtConfirm);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(10, 20, 20, 20));
        BorderPane pane = new BorderPane();
        pane.setCenter(vBox);
        pane.setBottom(hBox);
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setPrefSize(315, 330);

        return pane;
    }

    private Pane changeAccountName() {
        // Label lbl = lblFormat("Type a new account name for " , getUserName() , "'s  account", 14.5);
        TextField txtName = new TextField();
        txtName.setOnKeyTyped(e -> InputValidator.keyHandler1(0));
        txtName.setPromptText("New account name");
        txtName.setFocusTraversable(false);

        VBox hBox1 = new VBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().add(txtName);
        hBox1.setPadding(new Insets(20));

        Button btnChange = new Button("Change name");
        btnChange.setOnAction(e -> {
            try {
                InputValidator.validateChangeUserName(txtName);
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(e -> stage.close());

        HBox hBox = new HBox(15);
        hBox.getChildren().addAll(btnChange, btnCancel);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        BorderPane pane = new BorderPane();
        pane.setTop(titleStyle("Type a new account name for  ", getUserName() + "'s", "  account"));
        pane.setCenter(hBox1);
        pane.setBottom(hBox);
        pane.setPadding(new Insets(15, 15, 15, 15));
        pane.setPrefWidth(310);
        return pane;
    }

    private Pane manageAccounts() {
        Label label = new Label("Choose the user you would like to change ");
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font("Latin", FontWeight.BOLD, FontPosture.ITALIC, 14.5));

        Button btnChangeUserName = new Button("Change the account name");
        Button btnChangePassword = new Button("Change the password");
        Button btnChangeType = new Button("Change the Account type");
        Button btnDelete = new Button("Change the account");

        VBox actionBox = new VBox();
        actionBox.getChildren().addAll(btnChangeUserName, btnChangePassword, btnChangeType, btnDelete);
        actionBox.setSpacing(12);

        Label lbl = Pt_form_pane.lbl_format("Account list in table form but table line invisible and " +
                "the table have not title see from your pc ");

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(actionBox);
        borderPane.setCenter(lbl);

        Button btnCreate = new Button("_Add a new user");
        VBox vBox = new VBox();
        vBox.setSpacing(15);
        vBox.getChildren().addAll(label, borderPane, btnCreate);
        vBox.setMinWidth(300);
        vBox.setMinHeight(300);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(15, 15, 15, 15));

        return vBox;
    }

    public static void modifyUserName(TextField textField) throws SQLException, ClassNotFoundException {
        String userName = textField.getText().trim();
        System.out.println(userName);
        Login_controller.updateUserName(getUserName(), userName);
        stage.close();//add thread to create understandable GUI.
    }

    public static void modifyPassword(TextField[] textField) throws SQLException, ClassNotFoundException {
        String userName = Menu_scene.getUserName();
        String password = textField[1].getText();
        Login_controller.updateUserPassword(userName, password);
        stage.close();
    }

    public static void addNewUserToSystem(TextField[] txt, RadioButton[] rbtn) throws SQLException, ClassNotFoundException {
        String user_name = txt[0].getText();
        String password = txt[1].getText();
        String rollType = "";
        if (rbtn[0].isSelected())
            rollType = "Standard";
        else rollType = "Administrator";

        Login_controller.insertUsers(user_name, password, rollType);

    }


    public void validate_modify_password(TextField[] textFields) throws SQLException, ClassNotFoundException {
        InputValidator.validateModifyPassword(textFields);
    }

    public static Label formattingLabel(String text, Color color, int size) {
        Label lbl = new Label(text);
        lbl.setWrapText(true);
        lbl.setFont(Font.font("Sitka Display", FontWeight.BOLD, FontPosture.ITALIC, size));
        lbl.setTextFill(color);
        return lbl;
    }

    public static Label lblFormat(String notes, double fontSize) {
        Label lbl = new Label(notes);
        lbl.setFont(Font.font("Latin", FontWeight.BOLD, FontPosture.ITALIC, fontSize));
        lbl.setTextFill(Color.DARKBLUE);
        lbl.setWrapText(true);
        return lbl;
    }

    public static FlowPane titleStyle(String first, String midle, String end) {

        Label change = lblFormat(first, 14);
        Label u_name = new Label(midle);
        u_name.setFont(Font.font("Lucida Calligraphy", FontWeight.BOLD, FontPosture.ITALIC, 18));
        u_name.setTextFill(Color.BLUE);
        Label password = lblFormat(end, 14);

        FlowPane flowPane = new FlowPane();
        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.getChildren().addAll(change, u_name, password);
        return flowPane;
    }

    public static Button logout() {
        Login login = new Login();
        Button logout = new Button();
        // logout.setTextFill(Color.rgb(255, 0, 64));
        // logout.setStyle("-fx-background-color:rgb(255, 0, 64);");
        logout.setGraphic(lblFormat("Logout", 11));
        logout.setTooltip(new Tooltip("Logout"));
        logout.setOnAction(e -> Login.stageProvider().setScene(login.loginScene()));
        return logout;
    }

    public static Stage account_stageProvider() {
        return stage;
    }
}
