import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import controller.Main;
import model.Player;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;

public class RoomTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main dungeon = new Main();
        dungeon.start(primaryStage);
    }

    private Room roomTester;
    @Before
    public void setup() {
        roomTester = new Room();
    }
    @Test
    public void returnData() {
        roomTester.setRight(null);
        roomTester.setLeft(new Room());
        roomTester.setDown(new Room());
        roomTester.setUp(null);
        assertEquals(null, roomTester.retRoomTile(5, 8).retEntityType());
        assertEquals("Door", roomTester.retRoomTile(8, 5).retEntityType());
        assertEquals("Door", roomTester.retRoomTile(5, 0).retEntityType());
        assertEquals(null, roomTester.retRoomTile(0, 5).retEntityType());
    }
}