package controller;

import model.Player;
import model.Room;

public class GameController {
    private Player playerChar;
    private Room current;
    public GameController(Room roomStart, Player playerChar) {
        current = roomStart;
        this.playerChar = playerChar;
        //playerChar.setCoords(305, 160);
    }
    //player movement
    //check valid with roomtile
    //for player movement: on keystroke input, call the move functions, if false is returned call 
    //corresponding checknextroom functions
    public static boolean checkExit(Player playerChar, Room current) {
        if (playerChar.getYCoord() < -30 && (playerChar.getXCoord() > 285
            && playerChar.getXCoord() < 320)) {
            if (current.isExit()) {
                System.out.println(current.isExit() + "");
                return true;
            }
        }
        System.out.println(current.isExit() + "");
        return false;
    }
    public static Room checkNextRoomUp(Player playerChar, Room current) {
        if (playerChar.getYCoord() < -30 && (playerChar.getXCoord() > 285
            && playerChar.getXCoord() < 320)) {
            if ((current.retUp()) != null) {
                current = current.retUp();
                playerChar.setCoords(295, 380);
            }
        }
        return current;
    }
    public static Room checkNextRoomDown(Player playerChar, Room current) {
        if (playerChar.getYCoord() > 400 && (playerChar.getXCoord() > 270
            && playerChar.getXCoord() < 345)) {
            if ((current.retDown()) != null) {
                current = current.retDown();
                playerChar.setCoords(295, -20);
            }
        }
        return current;
    }
    public static Room checkNextRoomLeft(Player playerChar, Room current) {
        if (playerChar.getXCoord() < -25 && (playerChar.getYCoord() > 115 
            && playerChar.getYCoord() < 175)) {
            if ((current.retLeft()) != null) {
                current = current.retLeft();
                playerChar.setCoords(610, 160);
            }
        }
        return current;
    }
    public static Room checkNextRoomRight(Player playerChar, Room current) {
        if (playerChar.getXCoord() > 630 && (playerChar.getYCoord() > 115
            && playerChar.getYCoord() < 175)) {
            if ((current.retRight()) != null) {
                current = current.retRight();
                playerChar.setCoords(-20, 160);
            }
        }
        return current;
    }
    public boolean moveUp() {
        if (playerChar.getYCoord() == 0 || current.retRoomTiles(playerChar.getXCoord(),
            playerChar.getYCoord() - 1).entityLocated()) {
            return false;
        } else {
            playerChar.setYCoord(playerChar.getYCoord() - 1);
            //checkNextRoom();
            return true;
        }
    }
    public boolean moveDown() {
        if (playerChar.getYCoord() == 8 || current.retRoomTiles(playerChar.getXCoord(),
            playerChar.getYCoord() + 1).entityLocated()) {
            return false;
        } else {
            playerChar.setYCoord(playerChar.getYCoord() + 1);
            //checkNextRoom();
            return true;
        }
    }
    public boolean moveLeft() {
        if (playerChar.getXCoord() == 0 || current.retRoomTiles(playerChar.getXCoord() - 1,
            playerChar.getYCoord()).entityLocated()) {
            return false;
        } else {
            playerChar.setXCoord(playerChar.getXCoord() - 1);
            //checkNextRoom();
            return true;
        }
    }
    public boolean moveRight() {
        if (playerChar.getXCoord() == 8 || current.retRoomTiles(playerChar.getXCoord() + 1,
            playerChar.getYCoord()).entityLocated()) {
            return false;
        } else {
            playerChar.setXCoord(playerChar.getXCoord() + 1);
            //checkNextRoom();
            return true;
        }
    }
    //Notes:
    /*
    center: 305, 160
    if X < -25.0, exit left - Y Top: 115    Y Bottom: 175
    if X > 630.0, exit right
    if Y < -30, exit up   - X Right: 320    X Left: 285
    if Y > 400.0, exit down - X Right: 345    X Left: 270
    */
}