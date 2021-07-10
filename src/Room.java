package src;

import java.net.URI;
import java.io.File;
// this is for images and background
import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
//import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;

public class Room {
    private Room left;
    private Room right;
    private Room up;
    private Room down;
    private RoomTile[][] tiles;
    private boolean isRoomExit = false;
    
    private String roomVariant = "room_"; //you must set room adjacents in udlr order!
    
    private File srcPathFile = new File(System.getProperty("user.dir"));
    //private File relativePathFile = new File(srcPathFile.getParent() + "");
    private URI relativePath = srcPathFile.toURI();
    private String roomBackground = (relativePath
            + "/res/environments/");

    public Room() {
        tiles = (RoomTile[][]) new RoomTile[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tiles[i][j] = new RoomTile(null);
            }
        }
    }
    public RoomTile retRoomTiles(int x, int y) {
        return tiles[x][y];
    }
    public void setRoomVariant(String newVariant) {
        this.roomVariant = newVariant;
    }
    public void setUp(Room x) {
        up = x;
        if (up != null) {
            roomVariant = roomVariant + "u";
            tiles[0][5] = new RoomTile("DoorUp");
        }
    }
    public void setDown(Room x) {
        down = x;
        if (down != null) {
            tiles[8][5] = new RoomTile("DoorDown");
            roomVariant = roomVariant + "d";
        }
    }
    public void setLeft(Room x) {
        left = x;
        if (left != null) {
            tiles[5][0] = new RoomTile("DoorLeft");
            roomVariant = roomVariant + "l";
        }
    }
    public void setRight(Room x) {
        right = x;
        if (right != null) {
            tiles[5][8] = new RoomTile("DoorRight");
            roomVariant = roomVariant + "r";
        }
    }
    public void setExit(boolean isSet) {
        isRoomExit = isSet;
    }

    public Room retUp() {
        return this.up;
    }
    public Room retDown() {
        return this.down;
    }
    public Room retLeft() {
        return this.left;
    }
    public Room retRight() {
        return this.right;
    }
    public String retRoomVariant() {
        return roomVariant;
    }
    public boolean isExit() {
        return isRoomExit;
    }
    
    public BackgroundImage createBackground() {
        System.out.println("Background Name: " + roomBackground + roomVariant + ".png");
        try {
            Image backgroundImage = new Image(roomBackground + roomVariant + ".png");
            BackgroundImage fullBackground = new BackgroundImage(backgroundImage,
                        null, null, BackgroundPosition.CENTER, null);
            return fullBackground;
        } catch (Exception eex) {
            System.out.println("Background Image not Found or Invalid");
        }
        return null; //self explanatory method
    }

    //public void populateRoom () {
    //    
    //}
}