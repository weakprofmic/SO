package utm.so.individual;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Set;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utm.so.individual.utility.Item;
import utm.so.individual.utility.Race;


public class App extends Application{
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    Scene startScreen, mainScreen;

    
    @Override
    public void start(Stage primaryStage) throws Exception {

        InputStream iconStream = getClass().getClassLoader().getResourceAsStream("img/small.png");
        Image image = new Image(iconStream);

        stage = primaryStage;
        stage.getIcons().add(image);
        stage.setTitle("Calc representation App");
        stage.setWidth(1600);
        stage.setHeight(1000);

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getClassLoader().getResource("fxml/startScreen.fxml");
        // System.out.println(xmlUrl);
        loader.setLocation(xmlUrl);
        Parent rootStart = loader.load();
        

        startScreen = new Scene(rootStart);
        
        // root.setAlignment(Pos.BOTTOM_CENTER);

        // Label helloWorldLabel = new Label("Hello world!");
        // helloWorldLabel.setFont(new Font(40));
        // helloWorldLabel.setAlignment(Pos.TOP_CENTER);
        // Scene scene = new Scene(helloWorldLabel);
        primaryStage.setScene(startScreen);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(Objects.requireNonNull(App.class.getClassLoader().getResource("fxml/" + fxml)));
        stage.getScene().setRoot(pane);
    }

    private static void test() throws InterruptedException {
        Race r = new Race();
        r.start();
    }
    public static void main(String[] args) throws InterruptedException {
        // test();        
        launch(args);
    }

    
}
