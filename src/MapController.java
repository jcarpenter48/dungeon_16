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

public class MapController extends Application {
    private static Stage stage;
    private MapGenerator dungeonMap = new MapGenerator();
    private Room currentRoom = new Room();
    private static Player playerCharacter;

    MapController(Player playerPass) {
        playerCharacter = playerPass;
    }

    private final int width = 800;
    private final int height = 600;

    private File srcPathFile = new File(System.getProperty("user.dir"));
    //private File relativePathFile = new File(srcPathFile.getParent() + "");
    private URI relativePath = srcPathFile.toURI();

    private MediaPlayer musicBus;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        startRoomScene();
    }

    private void startRoomScene() throws Exception {
        musicBus = new MediaPlayer(new Media(relativePath + "/res/sound/tempDungeonTheme.mp3"));
        musicBus.setCycleCount(MediaPlayer.INDEFINITE);
        musicBus.setAutoPlay(true);

        musicBus.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                musicBus.seek(Duration.seconds(0));
                musicBus.play();
            }
        });
        BorderPane root = new BorderPane();
        Scene roomScene = new Scene(root, width, height);

        currentRoom = dungeonMap.getStart();
        root.setBackground(new Background(currentRoom.createBackground()));

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
        stage.setTitle("Starting Room");
        stage.setResizable(false);
        stage.show();
    }  
}