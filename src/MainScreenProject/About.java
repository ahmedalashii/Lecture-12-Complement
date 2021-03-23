package MainScreenProject;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class About extends Stage {

    @FXML
    private Label aboutMeLabel;

    @FXML
    private HBox hboxLabel;

    public About() throws IOException {
        FXMLLoader aboutLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        aboutLoader.setController(this);
        Parent aboutParent = aboutLoader.load();
        
        Scene aboutScene = new Scene(aboutParent);

        setScene(aboutScene);
        setTitle("Information About Me !");
        setResizable(false);   
    }
}
