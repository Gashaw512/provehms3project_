package FrontEnd;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConfirmBox extends Application {
    private SimpleBooleanProperty showPassword;
    private CheckBox checkBox;
    private Tooltip tooltip;
    private PasswordField pF;

    private Stage stage;

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        showPassword = new SimpleBooleanProperty();
        showPassword.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                showPassword();
            } else {
                hidePassword();
            }
        });
        final Label message = new Label("");
        Label label = new Label("Password");
        tooltip = new Tooltip();
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setAutoHide(false);
        pF = new PasswordField();
        pF.setOnKeyTyped(e ->
        {
            if (showPassword.get()) {
                showPassword();
            }
        });
        HBox hBox = new HBox(10, label, pF);
        hBox.setAlignment(Pos.CENTER);


        checkBox = new CheckBox("Show Password");
        showPassword.bind((checkBox.selectedProperty()));
        VBox vBox = new VBox(10, hBox, checkBox, message);
        vBox.setPadding(new Insets(10));
        stage.setScene(new Scene(vBox, 600, 600));
        stage.show();

    }

    private void hidePassword() {
        tooltip.setText("");
        tooltip.hide();
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setGraphicTextGap(12);

    }

    private void showPassword() {
        Point2D p = pF.localToScene(pF.getBoundsInLocal().getMaxX(), pF.getBoundsInLocal().getMaxY());
        tooltip.setText(pF.getText());
        tooltip.show(pF, p.getX() + stage.getScene().getX() + stage.getX(), p.getY() + stage.getScene().getY() + stage.getY());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
