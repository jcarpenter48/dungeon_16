public class MapGenerator {
    private Room[][] roomMap;
    private Room start;
    private Room end;
    private Room currentRoom;

    public MapGenerator() {
        int roomWidth = (int) (Math.random() * 6 + 10);
        int roomHeight = (int) (Math.random() * 6 + 10);
        roomMap = (Room[][]) new Object[roomHeight][roomWidth];
        start = roomMap[1][(int)(Math.random() * (roomWidth - 2)) + 1];
        end = roomMap[9][(int)(Math.random() * (roomWidth - 2)) + 1];
        currentRoom = start;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                roomMap[i][j] = new Room();
            }
        }
        for (int i = 0; i < roomHeight; i++) {
            for (int j = 0; j < roomWidth; j++) {
                if (i - 1 > 0) {
                    roomMap[i][j].setUp(roomMap[i - 1][j]);
                }
                if (i + 1 > 0) {
                    roomMap[i][j].setDown(roomMap[i - 1][j]);
                }
                if (j - 1 > 0) {
                    roomMap[i][j].setLeft(roomMap[i - 1][j]);
                }
                if (j + 1 > 0) {
                    roomMap[i][j].setRight(roomMap[i - 1][j]);
                }
            }
        }
    }
    public void gameStart(Player playerChar) {
        //initial player coordinates
        playerChar.setCoords(roomHeight / 2, roomWidth / 2);
    }
}