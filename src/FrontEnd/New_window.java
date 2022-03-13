package FrontEnd;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class New_window {
    private Stage stage;
    private Menu_scene menu_scene;
    private Login login;

    public void create_window(int mode) throws SQLException, ClassNotFoundException {
        stage = new Stage();
        login = new Login();

        menu_scene = new Menu_scene();
        stage.setTitle("Hospital Management System");
        switch (mode) {
            case 0:
               // Menu_scene f=new Menu_scene();
               // stage.setScene(new Scene(f.main(),1200,600));
                break;
            case 1:
                stage.setScene(login.loginScene());
        }
        stage.show();
    }

    public Stage stage_provider() {
        return stage;
    }
}
