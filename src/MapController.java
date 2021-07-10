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
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.KeyCode;
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
        currentRoom = dungeonMap.getStart();
        GameController gameControls = new GameController(currentRoom, playerCharacter);
        
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

        root.setBackground(new Background(currentRoom.createBackground()));

        ImageView playerCharacterModel = new ImageView(playerCharacter.returnSprite());
        root.getChildren().add(playerCharacterModel);
        playerCharacterModel.setX(305);
        playerCharacterModel.setY(160);
        playerCharacter.setCoords(305, 160);
        
        HBox information = new HBox(8);
        VBox spacer = new VBox(10);
        spacer.getChildren().addAll(information);
        Label goldCount = new Label("Gold Count: "
            + Integer.toString(playerCharacter.returnGold()));
        goldCount.setTextFill(Color.web("#FFFFFF"));
        information.getChildren().addAll(goldCount);
        root.setBottom(spacer);
        //event handler for game input
        roomScene.setOnKeyPressed(e -> {
            playerCharacter.setSprite("Move");
            playerCharacterModel.setImage(playerCharacter.returnSprite());
            switch (e.getCode()) {
            case W:
                //System.out.println("W key was pressed");
                playerCharacterModel.setY(playerCharacterModel.getY() - 5);
                System.out.println("X: " + playerCharacterModel.getX() + " Y: "
                    + playerCharacterModel.getY());
                playerCharacter.setXCoord((int) playerCharacterModel.getX());
                playerCharacter.setYCoord((int) playerCharacterModel.getY());
                //check if we move to next room or if we hit the game exit
                if (GameController.checkExit(playerCharacter, currentRoom)) {
                    try {
                        startEndScreen();
                    } catch (Exception eex) {
                        System.out.println("Failed to initialize win screen");
                        System.out.println("Exception: " + eex);
                    }
                    break;
                }
                currentRoom = GameController.checkNextRoomUp(playerCharacter, currentRoom);
                root.setBackground(new Background(currentRoom.createBackground()));
                playerCharacterModel.setX(playerCharacter.getXCoord());
                playerCharacterModel.setY(playerCharacter.getYCoord());
                System.out.println(currentRoom.toString());
                break;
            case S:
                //System.out.println("S key was pressed"); 
                playerCharacterModel.setY(playerCharacterModel.getY() + 5);
                System.out.println("X: " + playerCharacterModel.getX() + " Y: "
                    + playerCharacterModel.getY());
                playerCharacter.setXCoord((int) playerCharacterModel.getX());
                playerCharacter.setYCoord((int) playerCharacterModel.getY());
                //check if we move to next room
                currentRoom = GameController.checkNextRoomDown(playerCharacter, currentRoom);
                root.setBackground(new Background(currentRoom.createBackground()));
                playerCharacterModel.setX(playerCharacter.getXCoord());
                playerCharacterModel.setY(playerCharacter.getYCoord()); 
                System.out.println(currentRoom.toString());
                break;
            case A:
                //System.out.println("A key was pressed");
                playerCharacterModel.setX(playerCharacterModel.getX() - 5);
                System.out.println("X: " + playerCharacterModel.getX() + " Y: "
                    + playerCharacterModel.getY());
                playerCharacter.setXCoord((int) playerCharacterModel.getX());
                playerCharacter.setYCoord((int) playerCharacterModel.getY());
                //check if we move to next room
                currentRoom = GameController.checkNextRoomLeft(playerCharacter, currentRoom);
                root.setBackground(new Background(currentRoom.createBackground()));
                playerCharacterModel.setX(playerCharacter.getXCoord());
                playerCharacterModel.setY(playerCharacter.getYCoord());
                System.out.println(currentRoom.toString());
                break;
            case D:
                //System.out.println("D key was pressed");
                playerCharacterModel.setX(playerCharacterModel.getX() + 5);
                System.out.println("X: " + playerCharacterModel.getX() + " Y: "
                    + playerCharacterModel.getY());
                playerCharacter.setXCoord((int) playerCharacterModel.getX());
                playerCharacter.setYCoord((int) playerCharacterModel.getY());
                //check if we move to next room
                currentRoom = GameController.checkNextRoomRight(playerCharacter, currentRoom);
                root.setBackground(new Background(currentRoom.createBackground()));
                playerCharacterModel.setX(playerCharacter.getXCoord());
                playerCharacterModel.setY(playerCharacter.getYCoord()); 
                System.out.println(currentRoom.toString());
                break;
            default:
                break;
            }
        });
        roomScene.setOnKeyReleased(e -> {
            System.out.println("Keys released");
            playerCharacter.setSprite("Idle");
            playerCharacterModel.setImage(playerCharacter.returnSprite());            
        });
        stage.setScene(roomScene);
        stage.setTitle("Dungeon 16");
        stage.setResizable(false);
        stage.show();
    }

    private void startEndScreen() throws Exception {
        //victory music
        musicBus = new MediaPlayer(new Media(relativePath
            + "/res/sound/victoryscreen_theme.mp3"));  
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
        
        BorderPane endPane = new BorderPane();
        
        Scene endScene = new Scene(endPane, width, height);
        
        // add the background
        endPane.setBackground(new Background(createBackground(relativePath
            + "/res/winscreen.png")));
        
        //set screen to now built config screen
        stage.setTitle("Dungeon 16 Victory Screen");
        stage.setScene(endScene);    
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