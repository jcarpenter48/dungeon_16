import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
public class RoomTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        src.Main dungeon16 = new src.Main();
        dungeon16.start(primaryStage);
    }

    private src.Room testRoom;
    private src.Room roomTester;
    private src.Room dummyRoom1 = new src.Room();
    private src.Room dummyRoom2 = new src.Room();
    private src.Room dummyRoom3 = new src.Room();
    private src.Room dummyRoom4 = new src.Room();    
    @Before
    public void setup() {
        testRoom = new src.Room();
        roomTester = new src.Room();
    }
    @Test //David's Test
    public void returnData() {
        roomTester.setRight(null);
        roomTester.setLeft(new src.Room());
        roomTester.setDown(new src.Room());
        roomTester.setUp(null);
        assertEquals(null, roomTester.retRoomTiles(5, 8).getEntityType());
        assertEquals("Door", roomTester.retRoomTiles(8, 5).getEntityType());
        assertEquals("Door", roomTester.retRoomTiles(5, 0).getEntityType());
        assertEquals(null, roomTester.retRoomTiles(0, 5).getEntityType());
    }    
    @Test //Ian's Tests
    public void attach4way() {
        testRoom = new src.Room();
        testRoom.setUp(dummyRoom2);
        testRoom.setDown(dummyRoom4);
        testRoom.setLeft(dummyRoom1);
        testRoom.setRight(dummyRoom3);    
        assertEquals(dummyRoom2, testRoom.retUp());
        assertEquals(dummyRoom4, testRoom.retDown()); 
        assertEquals(dummyRoom1, testRoom.retLeft());        
        assertEquals(dummyRoom3, testRoom.retRight());         
        assertEquals("room_udlr", testRoom.retRoomVariant());
    }
    @Test
    public void attach3way() {
        testRoom = new src.Room();
        testRoom.setUp(dummyRoom1);
        testRoom.setDown(dummyRoom2);
        testRoom.setLeft(dummyRoom3); 
        assertNull(testRoom.retRight());  
        assertEquals(dummyRoom1, testRoom.retUp());
        assertEquals(dummyRoom2, testRoom.retDown()); 
        assertEquals(dummyRoom3, testRoom.retLeft());     
        assertEquals("room_udl", testRoom.retRoomVariant());
    }
    @Test
    public void attach2way() {
        testRoom = new src.Room();
        testRoom.setUp(dummyRoom1);
        testRoom.setLeft(dummyRoom2); 
        assertNull(testRoom.retDown());
        assertNull(testRoom.retRight());
        assertEquals(dummyRoom1, testRoom.retUp());
        assertEquals(dummyRoom2, testRoom.retLeft());
        assertEquals("room_ul", testRoom.retRoomVariant());
    } 
    @Test
    public void exitRoomTestGen() {
        testRoom.setRoomVariant("room_exit");
        testRoom.setExit(true);
        testRoom.setUp(null);
        testRoom.setDown(null);
        testRoom.setLeft(null);
        testRoom.setRight(null);
        assertNull(testRoom.retUp());
        assertNull(testRoom.retDown());
        assertNull(testRoom.retLeft());
        assertNull(testRoom.retRight());
        assertEquals("room_exit", testRoom.retRoomVariant());
        assertEquals(true, testRoom.isExit());
    }
    @Test
    public void startRoomTestGen() {
        testRoom.setExit(false);
        testRoom.setUp(dummyRoom3);
        testRoom.setDown(dummyRoom2);
        testRoom.setLeft(dummyRoom4);
        testRoom.setRight(dummyRoom1);
        testRoom.setRoomVariant("room_start");
        assertEquals(dummyRoom3, testRoom.retUp());
        assertEquals(dummyRoom2, testRoom.retDown()); 
        assertEquals(dummyRoom4, testRoom.retLeft());        
        assertEquals(dummyRoom1, testRoom.retRight());
        assertEquals(false, testRoom.isExit());
        assertEquals("room_start", testRoom.retRoomVariant());
    }
}
