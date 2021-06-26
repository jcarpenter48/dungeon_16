package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.net.URI;
import java.io.File;
// this is for images and background
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
//input fields
import javafx.scene.control.Label;
//sound library
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
//text color and font
import javafx.scene.paint.Color;

public class StartRoomTemp extends Application {
    private static Stage stage;
    private static Player playerCharacter;

    StartRoomTemp(Player playerPass) {
        playerCharacter = playerPass;
    }

    private final int width = 800;
    private final int height = 600;

    private File srcPathFile = new File(System.getProperty("user.dir"));
    //private File relativePathFile = new File(srcPathFile.getParent() + "");
    private URI relativePath = srcPathFile.toURI();

    private MediaPlayer ambient;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        startRoomScene();
    }

    private void startRoomScene() throws Exception {
        ambient = new MediaPlayer(new Media(relativePath + "/res/sound/tempDungeonTheme.mp3"));
        ambient.setCycleCount(MediaPlayer.INDEFINITE);
        ambient.setAutoPlay(true);

        ambient.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                ambient.seek(Duration.seconds(0));
                ambient.play();
            }
        });
        BorderPane root = new BorderPane();
        Scene roomScene = new Scene(root, WIDTH, HEIGHT);
        root.setBackground(new Background(createBackground(relativePath
            + "/res/environments/room1.png")));
        ImageView playerCharacterModel = new ImageView(relativePath + "/res/players/"
            + playerCharacter.returnWeaponClass() + "/idle.gif");
        root.getChildren().add(playerCharacterModel);
        HBox information = new HBox(8);
        VBox spacer = new VBox(10);
        spacer.getChildren().addAll(information);
        Label goldCount = new Label("Gold Count: "
            + Integer.toString(playerCharacter.returnGold()));
        goldCount.setTextFill(Color.web("#FFFFFF"));
        information.getChildren().addAll(goldCount);
        root.setBottom(spacer);
        stage.setScene(roomScene);
        stage.setTitle("Temporary Starting Room");
        stage.setResizable(false);
        stage.show();
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