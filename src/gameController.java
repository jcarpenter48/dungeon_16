public gameController {
    private Player playerChar;
    private Room current;
    public gameController(Room roomStart, Player playerChar) {
        current = roomStart;
        this.playerChar = playerChar;
        playerChar.setCoords(5, 5);
    }
    //player movement
    //check valid with roomtile
    private void checkNextRoom() {
        if (playerChar.getYCoord() == 0 && player.getXCoord() == 5) {
            if (current.retRoomTiles(0, 5).getEntityType() == "Door") {
                current = current.retUp();
                playerChar.setCoords(8, 5);
            }
        }
        else if (playerChar.getYCoord() == 8 && player.getXCoord() == 5) {
            if (current.retRoomTiles(8, 5).getEntityType() == "Door") {
                current = current.retDown();
                playerChar.setCoords(0, 5);
            }
        }
        else if (playerChar.getYCoord() == 5 && player.getXCoord() == 0) {
            if (current.retRoomTiles(8, 5).getEntityType() == "Door") {
                current = current.retLeft();
                playerChar.setCoords(5, 8);
        }
        else if (playerChar.getYCoord() == 5 && player.getXCoord() == 8) {
            current = current.retRight();
            playerChar.setCoords(5, 0);
        }
    }
    public boolean moveUp() {
        if (playerChar.getYCoord() == 0 || current.retRoomTiles(playerChar.getXCoord(), playerChar.getYCoord() - 1).entityLocated()) {
            return false;
        } else {
            playerChar.setYCoord(playerChar.getYCoord() - 1);
            checkNextRoom();
            return true;
        }
    }
    public boolean moveDown() {
        if (playerChar.getYCoord() == 8 || current.retRoomTiles(playerChar.getXCoord(), playerChar.getYCoord() + 1).entityLocated()) {
            return false;
        } else {
            playerChar.setYCoord(playerChar.getYCoord() + 1);
            checkNextRoom();
            return true;
        }
    }
    public boolean moveLeft() {
        if (playerChar.getXCoord() == 0 || current.retRoomTiles(playerChar.getXCoord() - 1, playerChar.getYCoord()).entityLocated()) {
            return false;
        } else {
            playerChar.setXCoord(playerChar.getXCoord() - 1);
            checkNextRoom();
            return true;
        }
    }
    public boolean moveRight() {
        if (playerChar.getXCoord() == 8 || current.retRoomTiles(playerChar.getXCoord() + 1, playerChar.getYCoord()).entityLocated()) {
            return false;
        } else {
            playerChar.setXCoord(playerChar.getXCoord() + 1);
            checkNextRoom();
            return true;
        }
    }
}