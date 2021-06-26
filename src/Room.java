public class Room {
    private Room left;
    private Room right;
    private Room up;
    private Room down;
    private RoomTile[][];

    public Room() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                RoomTile[i][j] = new RoomTile(null);
            }
        }
        populateRoom();
    }
    public Room setLeft (Room x) {
        left = x;
    }
    public Room setRight (Room x) {
        right = x;
    }
    public Room setUp (Room x) {
        up = x;
    }
    public Room setDown (Room x) {
        down = x;
    }
    public void populateRoom () {
        
    }
}