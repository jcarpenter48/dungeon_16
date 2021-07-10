package src;

import java.net.URI;
import java.io.File;
// this is for images and background
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;

public class Room {
    private Room left;
    private Room right;
    private Room up;
    private Room down;
    private RoomTile[][] tiles;
    private boolean isRoomExit = false;
    
    private String RoomVariant = "room_"; //you must set room adjacents in udlr order!
    
    private File srcPathFile = new File(System.getProperty("user.dir"));
    //private File relativePathFile = new File(srcPathFile.getParent() + "");
    private URI relativePath = srcPathFile.toURI();
    private String RoomBackground = (relativePath
            + "/res/environments/");

    public Room() {
        tiles = (RoomTile[][]) new RoomTile[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tiles[i][j] = new RoomTile(null);
            }
        }
        tiles[0][5] = new RoomTile("DoorUp");
        tiles[5][0] = new RoomTile("DoorLeft");
        tiles[5][8] = new RoomTile("DoorRight");
        tiles[8][5] = new RoomTile("DoorDown");
    }
    public RoomTile retRoomTiles(int x, int y) {
        return tiles[x][y];
    }
    public void setRoomVariant(String newVariant) {
        this.RoomVariant = newVariant;
    }
    public void setUp(Room x) {
        up = x;
        if (up != null) {
            RoomVariant = RoomVariant + "u";
        }
    }
    public void setDown(Room x) {
        down = x;
        if (down != null) {
            RoomVariant = RoomVariant + "d";
        }
    }
    public void setLeft(Room x) {
        left = x;
        if (left != null) {
            RoomVariant = RoomVariant + "l";
        }
    }
    public void setRight(Room x) {
        right = x;
        if (right != null) {
            RoomVariant = RoomVariant + "r";
        }
    }
    public void setExit() {
        isRoomExit = true;
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
    public boolean isExit() {
        return isRoomExit;
    }
    
    public BackgroundImage createBackground() {
        System.out.println("Background Name: " + RoomBackground + RoomVariant + ".png");
        try {
            Image backgroundImage = new Image(RoomBackground + RoomVariant + ".png");
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