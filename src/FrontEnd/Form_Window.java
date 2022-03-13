package FrontEnd;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Form_Window {
    private static Stage stage;
    private Scene scene;

    private int sceneHeight;
    private int sceneWidth;
    private int initialX;
    private int initialY;

    private int mode;// which identifies the form in registration or editing state

    private String stageTitle;

    public Form_Window(String stageTitle, int sceneWidth, int sceneHeight) {

        this.stageTitle = stageTitle;
        this.sceneHeight = sceneHeight;
        this.sceneWidth = sceneWidth;
    }

    public void settingStage(int code) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);

        mode = code;
        switch (mode) {
            case 1://add
            case 2://edit
                stage.setScene(settingPtScene());
                break;
            case 3://add
            case 4://edit
                stage.setScene(settingDrScene());
                break;
            default:
                return;
        }
        stage.setMinWidth(350);
        stage.setMinHeight(400);
        stage.setTitle(stageTitle);
        stage.setAlwaysOnTop(true);
        stage.show();

        ///////////////Moving/////////////////
        /*
        this is lambda with method reference. because when you pass e to the method it causes null pointer exception
        * */
        scene.setOnMousePressed(this::mousePressedHandler);//
        scene.setOnMouseDragged(this::mouseDraggedHandler);
        scene.setOnMouseReleased(this::mouseReleasedHandler);
    }

    public Scene settingDrScene() {
        Dr_form_pane dr_form_pane = new Dr_form_pane();
        scene = new Scene(dr_form_pane.addDoctor(mode), sceneWidth, sceneHeight);
        return scene;
    }

    public Scene settingPtScene() {
        Pt_form_pane pt_form_pane = new Pt_form_pane();
        scene = new Scene(pt_form_pane.addPatient(mode), sceneWidth, sceneHeight);
        return scene;
    }

    public void mousePressedHandler(MouseEvent m) {
        if (m.getButton() == MouseButton.PRIMARY) {
            scene.setCursor(Cursor.MOVE);
            initialX = (int) (stage.getX() - m.getScreenX());
            initialY = (int) (stage.getY() - m.getScreenY());

        }
    }

    public void mouseDraggedHandler(MouseEvent m) {
        if (m.getButton() == MouseButton.PRIMARY) {
            stage.setX(m.getSceneX() + initialX);
            stage.setY(m.getSceneY() + initialY);
        }
        scene.setCursor(Cursor.DEFAULT);

    }

    public void mouseReleasedHandler(MouseEvent m) {
        scene.setCursor(Cursor.DEFAULT);
    }

    public void editingForm() {

    }

    public static void closeWindow() {
        stage.close();
    }

    public static Stage stageProvider() {
        return stage;
    }


}
