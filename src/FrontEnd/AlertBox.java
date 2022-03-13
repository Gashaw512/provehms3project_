package FrontEnd;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertBox {
    private static boolean isTry = true;

    public static void displayAlert(Alert.AlertType type, String content, String title, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.setContentText(content);

        ButtonType tryAgain = new ButtonType("Try again");
        ButtonType close = new ButtonType("Exit Application");

        alert.getButtonTypes().addAll(tryAgain, close);
        alert.getButtonTypes().removeAll(ButtonType.OK);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == close) {
            closeProgram(Login.stageProvider());
        }
        if (result.isPresent() && result.get() == tryAgain) {
            // isTry=false;
        }

    }

    public static void closeProgram(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit this Application?");

        alert.getButtonTypes().removeAll(ButtonType.OK, ButtonType.CANCEL);

        ButtonType exit = new ButtonType("Exit");
        ButtonType cancel = new ButtonType("Cancel");
        alert.getButtonTypes().addAll(exit, cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == exit) {
             System.exit(1);// or Platform.exit();=> it exit the application.
            // stage.close();// this close the current window you use it.
        }
    }

    public static void selectionChecker(String text)// for deletion,edition
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("No entry selected");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    /* public static void fill_InfoChecker( String code)
     {
        Alert alert=new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText(null);
         alert.getButtonTypes().removeAll(ButtonType.OK);
         ButtonType trys=new ButtonType("Try Again");
         ButtonType cancel=new ButtonType("Cancel");
         if(code.equals("1"))
         {
             alert.setContentText(Pt_form_pane.forInvalid("1"));
         }
         else if (code.equals("2"))
         {
             alert.setContentText(Pt_form_pane.forInvalid("2"));
         }

         alert.getButtonTypes().addAll(trys,cancel);
         Optional<ButtonType> result=alert.showAndWait();
         if(result.isPresent()&&result.get()==trys)
         {
             PtScene.addingPatient();
         }


     }
 */
    public static void message(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

        alert.setX(Form_Window.stageProvider().getX() + Form_Window.stageProvider().getWidth());
        alert.setY(screenHeight / 2);//middle
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();

    }
}

