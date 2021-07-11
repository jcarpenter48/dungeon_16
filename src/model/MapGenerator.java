package src;

public class MapGenerator {
    private Room[][] roomMap;
    private Room start = new Room();
    private Room end = new Room();
    private Room currentRoom = new Room();

    public MapGenerator() {
        int roomWidth = (int) (Math.random() * 6 + 10);
        int roomHeight = (int) (Math.random() * 6 + 10);
        roomMap = (Room[][]) new Room[roomHeight][roomWidth];
        currentRoom = start;
        for (int i = 0; i < roomHeight; i++) {
            for (int j = 0; j < roomWidth; j++) {
                roomMap[i][j] = new Room();
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
        start = roomMap[1][(int) (Math.random() * (roomWidth - 2)) + 1];
        end = roomMap[8][(int) (Math.random() * (roomWidth - 2)) + 1];
        start.setRoomVariant("room_start");
        end.setRoomVariant("room_exit");
        end.setUp(null);
        end.setDown(null);
        end.setLeft(null);
        end.setRight(null);
        end.setExit(true);
    }

    public Room getStart() {
        return start;
    }
}