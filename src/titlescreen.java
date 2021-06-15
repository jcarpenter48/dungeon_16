import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import java.net.URI;
import java.io.File;
// this is for images and background
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
//sound library
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class titlescreen extends Application {
    //System.out.println("Working Directory = " + System.getProperty("user.dir")); //debug purposes
    
    private int screenWidth = 800;
    private int screenHeight = 600;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //audio
        File relativePathFile = new File(System.getProperty("user.dir"));
        URI relativePath = relativePathFile.toURI();
        
        Media media = new Media(relativePath+"/titlescreen_ambient.mp3");  
        
        MediaPlayer ambient = new MediaPlayer(media);  
        ambient.setCycleCount(MediaPlayer.INDEFINITE);
        ambient.setAutoPlay(true);  
        
        ambient.setOnEndOfMedia(new Runnable() {
        @Override
            public void run() {
                ambient.seek(Duration.seconds(1));
                ambient.play();
            }
        }); 
        //end audio
        
        Pane root = new Pane();
        
        Scene scene = new Scene(root, screenWidth, screenHeight);
        
        // add the background
        root.setBackground(new Background(createBackground("titlescreen.gif")));
        
        //title and buttons mockup
        ImageView title = new ImageView("title.gif");
        ImageView options = new ImageView("titlemenu.png");
        root.getChildren().add(title);
        root.getChildren().add(options);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dungeon 16");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private BackgroundImage createBackground(String backgroundResource) {
        try {
            Image backgroundImage = new Image(backgroundResource);
            BackgroundImage fullBackground = new BackgroundImage(backgroundImage,
                        null, null, BackgroundPosition.CENTER, null);
            return fullBackground;
        } catch (Exception eex) {
            System.out.println("Background Image not Found or Invalid");
        }
        return null; //self explanatory method
    }    
}