public class MapGenerator {
    private Room[][] roomMap;
    private Room start = new Room(0);
    private Room end = new Room(4);
    private Room currentRoom;

    public MapGenerator() {
        int roomWidth = (int) (Math.random() * 6 + 10);
        int roomHeight = (int) (Math.random() * 6 + 10);
        int startCoord = (int) (Math.random() * (roomWidth - 2)) + 1;
        int endCoord = (int) (Math.random() * (roomWidth - 2)) + 1;
        roomMap = (Room[][]) new Room[roomHeight][roomWidth];
        currentRoom = start;
        
        for (int i = 0; i < roomHeight; i++) {
            for (int j = 0; j < roomWidth; j++) {
                if (i == 1 && j == startCoord) {
                    roomMap[i][j] = new Room(0);
                } else if (i == roomHeight - 2 && j == endCoord) {
                    roomMap[i][j] = new Room(4);
                } else {
                    roomMap[i][j] = new Room();
                }
            }
        }
        //you must set room adjacents in udlr order!
        for (int i = 0; i < roomHeight - 1; i++) {
            for (int j = 0; j < roomWidth - 1; j++) {
                if (i - 1 >= 0) {
                    try {
                        roomMap[i][j].setUp(roomMap[i - 1][j]);
                    } catch (NullPointerException npe) {
                        roomMap[i][j].setUp(null);
                    }
                }
                if (i + 1 < roomHeight - 1) {
                    try {
                        roomMap[i][j].setDown(roomMap[i + 1][j]);
                    } catch (NullPointerException npe) {
                        roomMap[i][j].setDown(null);
                    }
                }
                if (j - 1 >= 0) {
                    try {
                        roomMap[i][j].setLeft(roomMap[i][j - 1]);
                    } catch (NullPointerException npe) {
                        roomMap[i][j].setLeft(null);
                    }
                }
                if (j + 1 < roomWidth - 1) {
                    try {
                        roomMap[i][j].setRight(roomMap[i][j + 1]);
                    } catch (NullPointerException npe) {
                        roomMap[i][j].setRight(null);
                    }
                }
            }
        }
        start = roomMap[1][startCoord];
        end = roomMap[roomHeight - 2][endCoord];
        start.setRoomVariant("room_start");
        end.setRoomVariant("room_exit");
        end.setExit(true);
    }

    public Room getStart() {
        return start;
    }
}
