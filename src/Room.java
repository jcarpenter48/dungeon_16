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
        tiles[0][5] = new RoomTile("DoorUp");
        tiles[5][0] = new RoomTile("DoorLeft");
        tiles[5][8] = new RoomTile("DoorRight");
        tiles[8][5] = new RoomTile("DoorDown");
    }
    public void setLeft(Room x) {
        left = x;
    }
    public void setRight(Room x) {
        right = x;
    }
    public void setUp(Room x) {
        up = x;
    }
    public void setDown(Room x) {
        down = x;
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
