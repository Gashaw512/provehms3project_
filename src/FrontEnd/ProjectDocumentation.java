package FrontEnd;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class ProjectDocumentation {

    public static void notes() {

        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.getFullScreenExitKeyCombination();//esc key
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        // stage.setOnCloseRequest(e->AlertBox.closeProgram(stage));

        ScrollBar hrScrol = new ScrollBar();
        hrScrol.setValue(120);
        hrScrol.setMin(5);
        hrScrol.setBlockIncrement(5);
        ScrollBar vrScroll = new ScrollBar();
        vrScroll.setMax(100);
        vrScroll.setMin(5);
        hrScrol.setBlockIncrement(5);
        vrScroll.setOrientation(Orientation.VERTICAL);


        Hyperlink link = new Hyperlink("for detail goto web");
        link.setOnAction(e -> openWeb("http://docs.oracle.com/javase/8/javafx"));
        Hyperlink link1 = new Hyperlink("Java DB Developer's Guide ");
        link1.setOnAction(e -> openWeb("https://download.oracle.com/javadb/10.6.1.0/devguide/devguide-single.html"));

        Label title = new Label("System structure");
        title.setFont(Font.font("New Romans", FontWeight.BOLD, FontPosture.ITALIC, 13));

        GridPane pane = new GridPane();
        pane.setVgap(12);
        pane.setPadding(new Insets(10, 10, 10, 10));
        String notes1 = "        This System uses  Object oriented programing as front end software and Database as back end software " +
                " hhhhhhhhhhhhhhhhhkjkgkfdfstjrrrrrrrrrrgyuhuihggffdserdghuujhh ghjlffdfdfdrrddxgvhjbhjghgg gvffdxffvhjbkjkh " +
                "hcjckcjzkjyaudahqnffnfnfnjHKUSHfaapfksbvcxfhbzdmffnjndffkdfkdkhgghkfggkjljllk;lklk" +
                "fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff" +
                "ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg" +
                "jjjjjjjjjjjjjjjjjjjjjjjjttttttttttttttttttttttttttttttttttttttttt ";
        Label lbl = Menu_scene.formattingLabel(notes1, Color.BLACK, 12);
        lbl.setWrapText(true);
       /// lbl.setText(notes1);
       // lbl.setWrapText(true);


        pane.add(title, 0, 0);
        pane.add(lbl, 1, 1);
        GridPane.setRowSpan(lbl, 4);
        GridPane.setColumnSpan(lbl, 4);
        pane.add(link, 4, 10);
        pane.add(link1, 4, 11);
        pane.setPickOnBounds(true);
        //  pane.setLayoutX(120);
        // pane.layoutXProperty().bind(hrScrol.valueProperty().multiply(lbl.widthProperty().divide(hrScrol.maxProperty())));

        BorderPane paneConten = new BorderPane();
        paneConten.setCenter(pane);
        paneConten.setRight(vrScroll);
        paneConten.setBottom(hrScrol);

        hrScrol.valueProperty().addListener(ov ->
        {

        });


        Scene scene = new Scene(paneConten, 750, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void openWeb(String adress) {
        try {
            java.awt.Desktop.getDesktop().browse(new URI(adress));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
}
