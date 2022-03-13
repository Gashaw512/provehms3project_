package FrontEnd;

import Controller.Login_controller;
import DataModel.Login_model;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

public class Login extends Application {

    private boolean response1 = true;
    private boolean response2 = true;

    private Label userName;
    private Label pass;
    private TextField uname;
    private PasswordField password;
    private Button login;

    private Menu_scene newScene;
    private static Stage stage;
    private LocalDate l;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        stage = primaryStage;
//System.out.println(LocalDate.now());

        stage.setX(0);
        stage.setY(0);
        stage.setMinWidth(920);
        stage.setMinHeight(650);
        stage.setScene(loginScene()); ///fill the stage with scene that contains login form elements

        stage.show();
        stage.setOnCloseRequest(e ->
        {
            e.consume();
            AlertBox.closeProgram(stage);
        });
    }

    public Scene loginScene() {

        stage.setTitle("Login");
        ImageView imgView = new ImageView();
        Image img = new Image("stethoscope.jpeg"); //Image area
        imgView.setFitWidth(320);
        imgView.setFitHeight(420);

        HBox imageSpace = new HBox(imgView);
        imageSpace.setHgrow(imgView, Priority.ALWAYS);
        imageSpace.setAlignment(Pos.CENTER_LEFT);
        imgView.fitWidthProperty().bind(imageSpace.widthProperty());
        imgView.setPreserveRatio(true);
        imgView.setSmooth(true);

        imgView.setImage(img);

        newScene = new Menu_scene();//

        Text text1 = new Text(" WELCOME TO");
        text1.setFont(Font.font("New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
        text1.setUnderline(true);

        Text text2 = new Text("HOSPITAL MANAGEMENT SYSTEM\n\n");
        text2.setFont(Font.font("New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
        text2.setUnderline(true);

        VBox boxText = new VBox();/// title box

        boxText.getChildren().addAll(text1, text2);
        boxText.setAlignment(Pos.CENTER);
        boxText.setSpacing(15);

        BorderPane pane = new BorderPane();

        pane.setTop(boxText);
        BorderPane.setAlignment(boxText, Pos.CENTER);
        pane.setLeft(imageSpace);
        pane.setRight(getGridPane());
        pane.setStyle("-fx-background-color: #b3c6ff;");

        Insets inset = new Insets(15, 15, 15, 20);//top-left-bottom right
        pane.setPadding(inset);

        return new Scene(pane, 950, 750);

    }

    private Pane getGridPane() {


        GridPane pane = new GridPane();
        pane.setPadding(new Insets(0, 10, 10, 10));

        userName = new Label("User Name");
        userName.setWrapText(true);
        userName.setFont(Font.font("New Roman", FontWeight.BOLD, FontPosture.ITALIC, 13));

        uname = new TextField();
        uname.setFocusTraversable(false);
        uname.setPromptText("User Name");
       /* uname.styleProperty().bind(Bindings
                        .when(uname.focusedProperty())
                        .then("-fx-prompt-text-fill: transparent;")
                        .otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));*/
        uname.setOnMouseEntered(e -> stylingText(userName, "@hover"));
        uname.setOnMouseExited(e -> stylingText(userName, "normal"));
        uname.setOnKeyReleased(e -> {
            String text1 = uname.getText();
            response1 = text1.isEmpty() || text1.trim().isEmpty();
            boolean response = response2 || response1;
            handleKeyReleased(response);
        });

        pass = new Label("Password");
        pass.setWrapText(true);
        pass.setFont(Font.font("New Roman", FontWeight.BOLD, FontPosture.ITALIC, 13));

        password = new PasswordField();// how password set visible in javafx?
        password.setFocusTraversable(false);
        password.setPromptText("password");

        password.setOnMouseEntered(e -> stylingText(pass, "@hover"));
        password.setOnMouseExited(e -> stylingText(pass, "normal"));

        password.setOnKeyReleased(e -> {
            String text2 = password.getText();
            response2 = text2.isEmpty() || text2.trim().isEmpty();
            boolean response = response2 || response1;
            handleKeyReleased(response);
        });

        login = new Button("Log In");
        login.setCursor(Cursor.HAND);
        login.setDisable(true);
        login.setDefaultButton(true);
        login.setPrefSize(65, 25);
        login.setTextFill(Color.rgb(255, 64, 0));
        login.setFont(Font.font("New Roman", FontWeight.BLACK, FontPosture.REGULAR, 11));

        login.setOnAction(e ->
        {
            try {
                machingPasswordFromDB(uname.getText().toString(), password.getText().toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        Button quit = new Button("Quit");
        quit.setPrefWidth(180);
        quit.setCursor(Cursor.HAND);
        quit.setCancelButton(true);
        quit.setOnAction(e -> AlertBox.closeProgram(stageProvider()));

        Tooltip qtWindow = new Tooltip("Exit this Application");
        Label lblGraphics = new Label("X");
        lblGraphics.setTextFill(Color.RED);
        lblGraphics.setContentDisplay(ContentDisplay.TOP);
        qtWindow.setGraphic(lblGraphics);

        quit.setTooltip(qtWindow);
        pane.add(userName, 0, 1);
        pane.add(uname, 1, 1);
        pane.add(pass, 0, 2);
        pane.add(password, 1, 2);
        pane.add(login, 1, 3);
        pane.add(quit, 1, 4);


        pane.setAlignment(Pos.BOTTOM_RIGHT);//position of pane
        GridPane.setHalignment(login, HPos.CENTER);//position of login button
        GridPane.setHalignment(quit, HPos.CENTER);

        pane.setHgap(10);
        pane.setVgap(15);
        pane.setMaxSize(400, 170);
        pane.setStyle("-fx-background-color: rgb(0,255,195);");

        VBox paneBox = new VBox();
        paneBox.setPadding(new Insets(255, 110, 100, 200));//top

        Label lblText = new Label("\tPlease Sign in with username and password!");
        lblText.setFont(Font.font("Elephant", FontWeight.BOLD, FontPosture.REGULAR, 14.5));
        lblText.setWrapText(true);
        paneBox.getChildren().addAll(lblText, pane);
        paneBox.setSpacing(15);

        return paneBox;
    }

    public void handleKeyReleased(boolean response) {
        login.setDisable(response);///response should be false to be able.
        if (!response) {
            login.setPrefSize(100, 30);
            login.setTextFill(Color.rgb(128, 0, 255));
            login.setFont(Font.font("Rockwell", FontWeight.BOLD, FontPosture.ITALIC, 15.5));
        } else {
            login.setPrefSize(65, 25);
            login.setTextFill(Color.rgb(255, 64, 0));
            login.setFont(Font.font("New Roman", FontWeight.BOLD, FontPosture.REGULAR, 11));
        }
    }

    private void stylingText(Label label, String code) {
        if (code.equals("@hover")) {
            label.setFont(Font.font("Lucida Calligraphy", FontWeight.BOLD, FontPosture.ITALIC, 14));
        } else if (code.equals("normal"))
            label.setFont(Font.font("New Roman", FontWeight.BOLD, FontPosture.ITALIC, 13));
    }

    /// The stage should be fill by different content to do different activities////////
    public static void sceneManipulator(Scene newScene, String title) {
        stage.setScene(newScene);
        stage.setTitle(title);
        /////////ctrl+ Space for suggestion
    }

    private void machingPasswordFromDB(String userName, String passwords) throws SQLException, ClassNotFoundException {
        try {
            boolean flag = Login_controller.searchPassword(userName, passwords);
            if (!flag) {
                AlertBox.displayAlert(Alert.AlertType.ERROR, "You have entered an invalid username/password", "Form" +
                        " Error!", null);
                password.requestFocus();
            } else {
                sceneManipulator(newScene.main(), "Hospital Management System");
                Menu_scene.setUserName(uname.getText());
                Menu_scene.setPassword(password.getText());
            }
        } catch (SQLException e) {
            System.out.print("Error occurred while searching password from DB " + e);
        }
    }

    public static Stage stageProvider() {
        return stage;
    }

}

