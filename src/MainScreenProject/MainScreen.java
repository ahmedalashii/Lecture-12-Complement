package MainScreenProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainScreen extends Application implements Initializable {

    @FXML
    private MenuItem fileItemOpen;

    @FXML
    private MenuItem aboutItem;

    @FXML
    private Slider sliderFontSize;

    @FXML
    private MenuItem fileItemClose;

    @FXML
    private Menu menuFile;

    @FXML
    private HBox hbox1;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem colorItemGold;

    @FXML
    private VBox vbox1;

    @FXML
    private WebView webView;

    @FXML
    private MenuItem fileItemSave;

    @FXML
    private Menu menuColor;

    @FXML
    private MenuItem colorItemCyan;

    @FXML
    private MenuItem fileItemExit;

    @FXML
    private ComboBox<String> comboBoxLinks;

    @FXML
    private MenuItem colorItemRed;

    @FXML
    private Menu menuAbout;

    @FXML
    private TextArea textArea;

    @FXML
    private Label aboutMeLabel;

    @FXML
    private HBox hboxLabel;

    Stage defaultStage;
    About aboutStage;
    Window thisWindow = defaultStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        defaultStage = primaryStage;
        aboutStage = new About();
        // About Class Extends Stage so By Polymorphism it's also Stage ..
        thisWindow = defaultStage;
        FXMLLoader screenLoader = new FXMLLoader(getClass().getResource("Screen.fxml"));
        screenLoader.setController(this);
        Parent screenParent = screenLoader.load();

        Scene screenScene = new Scene(screenParent);

        defaultStage.setTitle("Main Screen Using JavaFX Scene Builder");
        defaultStage.setScene(screenScene);
        defaultStage.setResizable(false);
        defaultStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxLinks.getItems().addAll("Instructor GitHub", "JavaFX Guide", "My GitHub Account");
        comboBoxLinks.getSelectionModel().selectFirst();
        webView.getEngine().load("https://github.com/aashgar"); // by default
        sliderFontSize.valueProperty().addListener(e -> {
            textArea.setStyle("-fx-font-size:" + sliderFontSize.getValue() + "pt;");
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @FXML
    void handle(ActionEvent event) throws IOException { // for most controls

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src/MainScreenProject"));
        if (event.getSource() == fileItemOpen) { // or we can use switch statement
            File openSelectedFile = fileChooser.showOpenDialog(thisWindow); // I created an instance variable thisWindow to reach to it here.
            if (!textArea.getText().isEmpty()) {
                textArea.appendText("\n");
            }
            try {
                Scanner scanner = new Scanner(openSelectedFile);
                while (scanner.hasNext()) {
                    textArea.appendText(scanner.nextLine() + "\n");
                }
                scanner.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        } else if (event.getSource() == fileItemClose) {
            textArea.clear();
        } else if (event.getSource() == fileItemSave) {
            try {
                File saveSelectedFile = fileChooser.showSaveDialog(thisWindow); // this is for store the selected save file in an object just to use it below to delete it to update the data inside it.
                saveSelectedFile.delete(); // we delete the previous file just to update the data inside it not to appent the data to the previous data.
                FileWriter fr = new FileWriter(saveSelectedFile, true); // append
                PrintWriter pw = new PrintWriter(fr);
                pw.print(textArea.getText());
                pw.close();
                fr.close();
            } catch (IOException ex) {
                System.err.println("You didn't Choose any file!");
            }
        } else if (event.getSource() == fileItemExit) {
            defaultStage.close();
            if (aboutStage.isShowing()) { // this if statement is just if I want to close aboutStage with the default defaultStage
                aboutStage.close();
            }
        } else if (event.getSource() == colorItemGold) {
            textArea.setStyle("-fx-text-fill:gold;");
        } else if (event.getSource() == colorItemCyan) {
            textArea.setStyle("-fx-text-fill:cyan;");
        } else if (event.getSource() == colorItemRed) {
            textArea.setStyle("-fx-text-fill:red;");
        } else if (event.getSource() == aboutItem) {
            aboutStage.show(); // About Class Extends Stage so By Polymorphism Definition it's also Stage ..
        }
    }

    @FXML
    void handleComboBox(ActionEvent event) { // for combo box
        if (comboBoxLinks.getValue().equals("Instructor GitHub")) {
            webView.getEngine().load("https://github.com/aashgar");
        } else if (comboBoxLinks.getValue().equals("JavaFX Guide")) {
            webView.getEngine().load("https://en.wikipedia.org/wiki/JavaFX");
        } else if (comboBoxLinks.getValue().equals("My GitHub Account")) {
            webView.getEngine().load("https://github.com/ahmedalashii");
        }
    }
}
