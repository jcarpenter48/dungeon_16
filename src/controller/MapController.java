package controller;

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
import javafx.animation.PauseTransition;
import java.util.concurrent.atomic.AtomicBoolean;
//list related
import java.util.List;
import java.util.ArrayList;
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
import model.MapGenerator;
import model.Player;
import model.Enemy;
import model.Room;

public class MapController extends Application {
    private static Stage stage;
    private MapGenerator dungeonMap = new MapGenerator();
    private Room currentRoom = new Room();
    private static Player playerCharacter;
    private List<Enemy> enemyListMC;
    private List<ImageView> enemyListView;

    public MapController(Player playerPass) {
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
        Label healthDisp = new Label("Health: "
            + Double.toString(playerCharacter.returnCHP()));
        healthDisp.setTextFill(Color.web("#FFFFFF"));
        Label combatDisp = new Label("Status: Nothing to report");
        combatDisp.setTextFill(Color.web("#FFFFFF"));
        information.getChildren().addAll(goldCount, healthDisp, combatDisp);
        root.setBottom(spacer);
        
        //event handler for game input
        AtomicBoolean isCombat = new AtomicBoolean(false);
        AtomicBoolean isPMoving = new AtomicBoolean(false);
        roomScene.setOnKeyPressed(e -> {
            //checkPDamageCollide(playerCharacter); //check if player should be damaged
            if (!isPMoving.get()) {
                playerCharacter.setSprite("Move");
                playerCharacterModel.setImage(playerCharacter.returnSprite());
                isPMoving.set(true);
            }
            switch (e.getCode()) {
            case W:
                combatDisp.setText("Status: On the move...");
                playerCharacterModel.setY(playerCharacterModel.getY() - 5);
                //System.out.println("X: " + playerCharacterModel.getX() + " Y: "
                //    + playerCharacterModel.getY());
                playerCharacter.setXCoord((int) playerCharacterModel.getX());
                playerCharacter.setYCoord((int) playerCharacterModel.getY());
                //check if we move to next room or if we hit the game exit
                if (GameController.checkExit(playerCharacter, currentRoom)) {
                    if (enemyListMC.isEmpty()) {
                        try {
                            startEndScreen();
                        } catch (Exception eex) {
                            System.out.println("Failed to initialize win screen");
                            System.out.println("Exception: " + eex);
                        }
                        break;
                    }
                }
                currentRoom = GameController.checkNextRoomUp(playerCharacter, currentRoom);
                root.setBackground(new Background(currentRoom.createBackground()));
                playerCharacterModel.setX(playerCharacter.getXCoord());
                playerCharacterModel.setY(playerCharacter.getYCoord());
                break;
            case S:
                combatDisp.setText("Status: On the move..."); 
                playerCharacterModel.setY(playerCharacterModel.getY() + 5);
                //System.out.println("X: " + playerCharacterModel.getX() + " Y: "
                //    + playerCharacterModel.getY());
                playerCharacter.setXCoord((int) playerCharacterModel.getX());
                playerCharacter.setYCoord((int) playerCharacterModel.getY());
                //check if we move to next room
                currentRoom = GameController.checkNextRoomDown(playerCharacter, currentRoom);
                root.setBackground(new Background(currentRoom.createBackground()));
                playerCharacterModel.setX(playerCharacter.getXCoord());
                playerCharacterModel.setY(playerCharacter.getYCoord()); 
                break;
            case A:
                combatDisp.setText("Status: On the move...");
                playerCharacterModel.setX(playerCharacterModel.getX() - 5);
                //System.out.println("X: " + playerCharacterModel.getX() + " Y: "
                //    + playerCharacterModel.getY());
                playerCharacter.setXCoord((int) playerCharacterModel.getX());
                playerCharacter.setYCoord((int) playerCharacterModel.getY());
                //check if we move to next room
                currentRoom = GameController.checkNextRoomLeft(playerCharacter, currentRoom);
                root.setBackground(new Background(currentRoom.createBackground()));
                playerCharacterModel.setX(playerCharacter.getXCoord());
                playerCharacterModel.setY(playerCharacter.getYCoord());
                break;
            case D:
                combatDisp.setText("Status: On the move...");
                playerCharacterModel.setX(playerCharacterModel.getX() + 5);
                //System.out.println("X: " + playerCharacterModel.getX() + " Y: "
                //    + playerCharacterModel.getY());
                playerCharacter.setXCoord((int) playerCharacterModel.getX());
                playerCharacter.setYCoord((int) playerCharacterModel.getY());
                //check if we move to next room
                currentRoom = GameController.checkNextRoomRight(playerCharacter, currentRoom);
                root.setBackground(new Background(currentRoom.createBackground()));
                playerCharacterModel.setX(playerCharacter.getXCoord());
                playerCharacterModel.setY(playerCharacter.getYCoord()); 
                break;
            case E:
                //attack case
                //attack sprite
                if (!isCombat.get()) {
                    playerCharacter.setSprite("attack");
                    playerCharacterModel.setImage(playerCharacter.returnSprite());
                    updateEnemySprites("attack", playerCharacter);
                    isCombat.set(true);               
                }
                combatDisp.setText("Status: Attacking...");
                //roll to see if player strikes
                if (((int) (Math.random()*(10 - 1))) + 1 < 7) {
                    checkEDamageCollide(playerCharacter); //check for collision and damage enemy
                    combatDisp.setText("Status: Dealt 3 Damage!");
                }
                //roll to see if enemy strikes
                if (((int) (Math.random()*(10 - 1))) + 1 > 6) {
                    checkPDamageCollide(playerCharacter); //check if player should be damaged
                    combatDisp.setText("Status: Enemy Dealt Damage!");
                }
                checkEnemyDead();
            default:
                break;
            }
            try {
                clearDisplayEnemies(root);
            } catch (Exception npe) {
                System.out.println("No imageviews to clear");
            } //clear our enemies if we can
            spawnDisplayEnemies(root); //spawn our enemies
            //check for collisions
            
            //update health
            healthDisp.setText("Health: "
                + Double.toString(playerCharacter.returnCHP()));        
            checkIfDead(playerCharacterModel);
        });
        roomScene.setOnKeyReleased(e -> {
            //checkPDamageCollide(playerCharacter); //check if player should be damaged
            //System.out.println("Keys released");
            if (isPMoving.get()) {
                if (isCombat.get()) {
                    PauseTransition p = new PauseTransition(Duration.millis(1400));
                    p.setOnFinished(wait -> {
                        isCombat.set(false);
                    });
                    p.play();
                }
                playerCharacter.setSprite("Idle");
                playerCharacterModel.setImage(playerCharacter.returnSprite()); 
                updateEnemySprites("Idle", playerCharacter);
                isPMoving.set(false);
            }
            //clear our enemies and then spawn them to make sure they're currentRoom
            try {
                clearDisplayEnemies(root);
            } catch (Exception npe) {
                System.out.println("No imageviews to clear");
            } //clear our enemies if we can
            spawnDisplayEnemies(root); //spawn our enemies
            //update health
            healthDisp.setText("Health: "
                + Double.toString(playerCharacter.returnCHP()));
            checkIfDead(playerCharacterModel);
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
    
    private void clearDisplayEnemies(BorderPane root) {
        if (enemyListView == null || enemyListView.isEmpty()) {
            return;
        }
        for (int i = 0; i < enemyListView.size(); i++) {
            ImageView temp = enemyListView.get(i);
            root.getChildren().remove(temp);
        } //clear screen of enemies from previous room
    }
    private void spawnDisplayEnemies(BorderPane root) {
        //enemies
        enemyListMC = currentRoom.retEnemyList();
        if (enemyListMC == null || enemyListMC.isEmpty()) {
            return;
        }
        enemyListView = new ArrayList<>();
        for (Enemy element : enemyListMC) {
            enemyListView.add(new ImageView(element.returnSprite()));
        } //add every enemy in a room's enemy list to be drawn
        for (int i = 0; i < enemyListView.size(); i++) {
            ImageView temp = enemyListView.get(i);
            root.getChildren().add(temp);
            temp.setX(enemyListMC.get(i).retX());
            temp.setY(enemyListMC.get(i).retY());
        }
        //end enemies spawn
    }
    private void checkIfDead(ImageView playerCharacterModel) {
        //if dead, lose game
        if (playerCharacter.returnCHP() <= 0) {
            playerCharacter.setSprite("death");
            playerCharacterModel.setImage(playerCharacter.returnSprite());
            PauseTransition p = new PauseTransition(Duration.millis(1400));
            p.setOnFinished(e -> {
                try {
                    startEndScreen();
                } catch (Exception eex) {
                    System.out.println("Failed to initialize lose screen");
                    System.out.println("Exception: " + eex);
                }
            });
            p.play();
        }        
    }
    private void checkEnemyDead() {
        if (enemyListMC == null || enemyListMC.isEmpty()) {
            return;
        }
        for (Enemy element : enemyListMC) {
            if (element.returnHP() <= 0) {
                element.setSprite("death");
                enemyListMC.remove(element);
            }
        } //if enemy health is 0 or less, change sprite to death    
    }
    private void updateEnemySprites(String animation, Player pChar) {
        if (enemyListMC == null || enemyListMC.isEmpty()) {
            return;
        }
        for (Enemy element : enemyListMC) {
            if (checkCollide(pChar, element)) {
                element.setSprite("attack");
            }
        }
    }
    
    private boolean checkCollide(Player pChar, Enemy monster) {
        boolean check = pChar.getBoundary().intersects(monster.getBoundary());
        System.out.println(""+check);
        return check;
    }
    private void checkPDamageCollide(Player pChar) {
        if (enemyListMC != null) {
            if (enemyListMC.isEmpty()) {
                return;
            }
            for (Enemy element : enemyListMC) {
                if (checkCollide(pChar, element)) {
                    pChar.takeDamage(element.returnDamage());
                }
            } //check if any enemies are colliding with player            
        }
    }
    private void checkEDamageCollide(Player pChar) {
        if (enemyListMC != null) {
            if (enemyListMC.isEmpty()) {
                return;
            }
            for (Enemy element : enemyListMC) {
                if (checkCollide(pChar, element)) {
                    element.takeDamage(3);
                }
            } //check if any enemies are colliding with player            
        }
    }
}