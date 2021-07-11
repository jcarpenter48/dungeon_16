import model.Room;
import org.testfx.framework.junit.ApplicationTest;
import org.junit.Test;
import model.MapGenerator;

import static org.junit.Assert.*;

public class MapGeneratorTest extends ApplicationTest {

    /*
       Tests by Hardik Goel.
       The following tests check that maps are generated and placed correctly.
     */

    @Test
    public void testRoomVariantUDR() {
        MapGenerator dungeonMap = new MapGenerator();

        Room startRoom = dungeonMap.getStart();

        Room currentRoom = startRoom;
        assertEquals(currentRoom.retRoomVariant(), "room_start");

        // Go to the far left end
        while (currentRoom.retLeft() != null) {
            if (currentRoom == startRoom) {
                assertEquals(currentRoom.retRoomVariant(), "room_start");
            } else {
                assertEquals(currentRoom.retRoomVariant(), "room_udlr");
            }
            assertNotNull(currentRoom.retDown());
            assertNotNull(currentRoom.retUp());
            assertNotNull(currentRoom.retLeft());
            assertNotNull(currentRoom.retRight());

            currentRoom = currentRoom.retLeft();
        }

        assertEquals(currentRoom.retRoomVariant(), "room_udr");

        assertNull(currentRoom.retLeft());
        assertNotNull(currentRoom.retDown());
        assertNotNull(currentRoom.retUp());
        assertNotNull(currentRoom.retRight());
    }

    @Test
    public void testRoomVariantUDL() {
        MapGenerator dungeonMap = new MapGenerator();
        Room startRoom = dungeonMap.getStart();

        Room currentRoom = startRoom;
        assertEquals(currentRoom.retRoomVariant(), "room_start");

        // Go to the far left end
        while (currentRoom.retRight() != null) {
            if (currentRoom == startRoom) {
                assertEquals(currentRoom.retRoomVariant(), "room_start");
            } else {
                assertEquals(currentRoom.retRoomVariant(), "room_udlr");
            }
            assertNotNull(currentRoom.retDown());
            assertNotNull(currentRoom.retUp());
            assertNotNull(currentRoom.retLeft());
            assertNotNull(currentRoom.retRight());

            currentRoom = currentRoom.retRight();
        }

        assertEquals(currentRoom.retRoomVariant(), "room_udl");
        assertNull(currentRoom.retRight());
        assertNotNull(currentRoom.retDown());
        assertNotNull(currentRoom.retUp());
        assertNotNull(currentRoom.retLeft());
    }

    @Test
    public void testRoomVariantDLR() {
        MapGenerator dungeonMap = new MapGenerator();
        Room startRoom = dungeonMap.getStart();

        Room currentRoom = startRoom;
        assertEquals(currentRoom.retRoomVariant(), "room_start");

        // Go to the far down end from start
        while (currentRoom.retUp() != null) {
            if (currentRoom == startRoom) {
                assertEquals(currentRoom.retRoomVariant(), "room_start");
            } else {
                assertEquals(currentRoom.retRoomVariant(), "room_udlr");
            }

            assertNotNull(currentRoom.retDown());
            assertNotNull(currentRoom.retUp());
            assertNotNull(currentRoom.retLeft());
            assertNotNull(currentRoom.retRight());

            currentRoom = currentRoom.retUp();
        }

        assertEquals(currentRoom.retRoomVariant(), "room_dlr");

        assertNull(currentRoom.retUp());
        assertNotNull(currentRoom.retDown());
        assertNotNull(currentRoom.retLeft());
        assertNotNull(currentRoom.retRight());
    }

    @Test
    public void testRoomVariantULR() {
        MapGenerator dungeonMap = new MapGenerator();
        Room startRoom = dungeonMap.getStart();

        Room currentRoom = startRoom;
        assertEquals(currentRoom.retRoomVariant(), "room_start");

        // Go to the far down end from start
        while (currentRoom.retDown() != null) {
            if (currentRoom == startRoom) {
                assertEquals(currentRoom.retRoomVariant(), "room_start");
            } else {
                assertEquals(currentRoom.retRoomVariant(), "room_udlr");
            }
            assertNotNull(currentRoom.retDown());
            assertNotNull(currentRoom.retUp());
            assertNotNull(currentRoom.retLeft());
            assertNotNull(currentRoom.retRight());

            currentRoom = currentRoom.retDown();
        }

        assertEquals(currentRoom.retRoomVariant(), "room_ulr");
        assertNull(currentRoom.retDown());
        assertNotNull(currentRoom.retUp());
        assertNotNull(currentRoom.retLeft());
        assertNotNull(currentRoom.retRight());
    }

}
