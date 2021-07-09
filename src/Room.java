public class Room {
    private Room left;
    private Room right;
    private Room up;
    private Room down;
    private RoomTile[][] tiles;

    public Room() {
        tiles = (RoomTile[][]) new Object[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tiles[i][j] = new RoomTile(null);
            }
        }
    }
    public RoomTile retRoomTile(int x, int y) {
        return tiles[x][y];
    }
    public void setLeft(Room x) {
        left = x;
        if (x != null) {
            tiles[5][0] = new RoomTile("DoorLeft");
        }
    }
    public void setRight(Room x) {
        right = x;
        if (x != null) {
            tiles[5][8] = new RoomTile("DoorRight");
        }
    }
    public void setUp(Room x) {
        up = x;
        if (x != null) {
            tiles[0][5] = new RoomTile("DoorUp");
        }
    }
    public void setDown(Room x) {
        down = x;
        if (x != null) {
            tiles[8][5] = new RoomTile("DoorDown");
        }
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
    //public void populateRoom () {
    //    
    //}
}
