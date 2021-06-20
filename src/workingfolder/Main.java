import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.net.URI;
import java.io.File;
import java.io.IOException;
// this is for images and background
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
//input fields
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
//event handling
import javafx.scene.input.MouseEvent;
//sound library
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Main extends Application {
    private static Stage stage;
    private static Player player1;
    //private static GameState state;
    private int WIDTH = 800;
    private int HEIGHT = 600;

    //click sound and audio setup
    File relativePathFile = new File(System.getProperty("user.dir"));
    URI relativePath = relativePathFile.toURI(); 
    MediaPlayer clickSound = new MediaPlayer(new Media(relativePath+"/click.mp3"));  
    MediaPlayer musicBus;
    //end sound setup
        
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        startTitleScreen();
    }
    
    private void startTitleScreen() throws Exception {
        //titlescreen ambience      
        MediaPlayer ambient = new MediaPlayer(new Media(relativePath+"/titlescreen_ambient.mp3"));  
        ambient.setCycleCount(MediaPlayer.INDEFINITE);
        ambient.setAutoPlay(true);  
        
        ambient.setOnEndOfMedia(new Runnable() {
        @Override
            public void run() {
                ambient.seek(Duration.seconds(1));
                ambient.play();
            }
        }); 
        //titlescreen ambience
        
        Pane root = new Pane();
        
        Scene titleScene = new Scene(root, WIDTH, HEIGHT);
        
        // add the background
        root.setBackground(new Background(createBackground("titlescreen.gif")));
        
        //title and buttons mockup
        ImageView title = new ImageView("title.gif");
        //ImageView options = new ImageView("titlemenu.png");
        ImageView start = new ImageView("title_start.png");
        ImageView options = new ImageView("title_options.png");
        ImageView exit = new ImageView("title_exit.png");
        root.getChildren().add(title);
        root.getChildren().addAll(start, options, exit);
        //buttons debug
        start.setOnMouseClicked(e -> {
                try {
                    clickSound.seek(Duration.seconds(0));
                    clickSound.play();
                    ambient.stop();
                    startConfigScreen();
                } catch(Exception ex) {
                    System.out.println("Failed to initialize Config Screen");
                }
        });
        options.setOnMouseClicked(e -> {
                clickSound.seek(Duration.seconds(0));
                clickSound.play();
                System.out.println("Options Menu Opened");
            });
        exit.setOnMouseClicked(e -> {
                clickSound.seek(Duration.seconds(0));
                clickSound.play();
                stage.close();
            });
      
        stage.setScene(titleScene);
        stage.setTitle("Dungeon 16");
        stage.setResizable(false);
        stage.show();        
    }

    private void startConfigScreen() throws Exception {
        //configscreen music
        musicBus = new MediaPlayer(new Media(relativePath+"/configscreen_theme.mp3"));  
        musicBus.setCycleCount(MediaPlayer.INDEFINITE);
        musicBus.setAutoPlay(true);  
        
        musicBus.setOnEndOfMedia(new Runnable() {
        @Override
            public void run() {
                musicBus.seek(Duration.seconds(0));
                musicBus.play();
            }
        }); 
        //config screen music
        
        BorderPane configPane = new BorderPane();
        
        Scene configScene = new Scene(configPane, WIDTH, HEIGHT);
        
        // add the background
        configPane.setBackground(new Background(createBackground("configscreen_background.png")));
        //labels
        ImageView configLabels = new ImageView("configscreen_labels.png");
        configPane.getChildren().add(configLabels);
        
        //playerPreview
        ImageView playerPreviewFrame = new ImageView("configscreen_previewframe.png");
        ImageView playerPreview = new ImageView("preview.gif");
        //input fields
        VBox inputFields = new VBox(8);
        TextField playerNameChoice = new TextField();
        playerNameChoice.setPromptText("Enter Name...");
        ComboBox<String> difficultyChoice = new ComboBox<>();
            difficultyChoice.getItems().add("Easy");
            difficultyChoice.getItems().add("Medium");
            difficultyChoice.getItems().add("Hard");
        ComboBox<String> weaponChoice = new ComboBox<>();
            weaponChoice.getItems().add("Katana");
            weaponChoice.getItems().add("Broadsword");
            weaponChoice.getItems().add("Magic");        
        inputFields.getChildren().addAll(playerNameChoice, difficultyChoice, weaponChoice);

        configPane.setRight(inputFields);
        BorderPane.setMargin(inputFields, new Insets(30, 30, 30, 30)); // Set margin for right area.
        //continue button and finalize config screen
        ImageView continueButton = new ImageView("config_continue.png");
        configPane.getChildren().addAll(playerPreviewFrame, playerPreview, continueButton);

        continueButton.setOnMouseClicked(e -> {
            clickSound.seek(Duration.seconds(0));
            clickSound.play();
            //create our player
            String temp = playerNameChoice.getText();
            try {
                if ((temp == null) || (temp.isEmpty()) || (!(temp.trim().length() > 0))) {
                    errorAlert("Cannot accept NULL or Empty Name!");
                    return;
                }
                player1 = new Player(playerNameChoice.getText(), difficultyChoice.getValue(), weaponChoice.getValue());
                System.out.println("Saving player data...");
            } catch (Exception eex) {
                errorAlert("Cannot accept NULL selections!");
            }
        });
        
        //set screen to now built config screen
        stage.setScene(configScene);    
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

    public static void errorAlert(String errorDescription) {
        Stage popup = new Stage();
        VBox messageBox = new VBox();
        messageBox.setAlignment(Pos.CENTER);
        Label errorMessage = new Label(errorDescription);
        messageBox.getChildren().add(errorMessage);

        Scene stage = new Scene(messageBox, 320, 240);
        popup.setScene(stage);
        popup.show();
    }    
}