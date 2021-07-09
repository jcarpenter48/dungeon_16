public class MapGenerator {
    private Room[][] roomMap;
    private Room start;
    private Room end;
    private Room currentRoom;

    public MapGenerator() {
        int roomWidth = (int) (Math.random() * 6 + 10);
        int roomHeight = (int) (Math.random() * 6 + 10);
        roomMap = (Room[][]) new Object[roomHeight][roomWidth];
        for (int i = 0; i < roomHeight; i++) {
            for (int j = 0; j < roomWidth; j++) {
                roomMap[i][j] = new Room();
            }
        }
        start = roomMap[1][(int)(Math.random() * (roomWidth - 2)) + 1];
        end = roomMap[9][(int)(Math.random() * (roomWidth - 2)) + 1];
        currentRoom = start;
        for (int i = 0; i < roomHeight; i++) {
            for (int j = 0; j < roomWidth; j++) {
                if (i - 1 >= 0) {
                    roomMap[i][j].setUp(roomMap[i - 1][j]);
                }
                if (i + 1 < roomHeight) {
                    roomMap[i][j].setDown(roomMap[i + 1][j]);
                }
                if (j - 1 >= 0) {
                    roomMap[i][j].setLeft(roomMap[i][j - 1]);
                }
                if (j + 1 < roomWidth) {
                    roomMap[i][j].setRight(roomMap[i][j + 1]);
                }
            }
        }
    }
    public Room gameStart() {
        //initial player coordinates
        return current;
    }
}
