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
    private src.Room dummyRoom1 = new src.Room();
    private src.Room dummyRoom2 = new src.Room();
    private src.Room dummyRoom3 = new src.Room();
    private src.Room dummyRoom4 = new src.Room();    
    @Before
    public void setup() {
        testRoom = new src.Room();
    }
    @Test
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
    }
    @Test
    public void startRoomTestGen() {
        testRoom.setRoomVariant("room_start");
        testRoom.setExit(false);
        testRoom.setUp(null);
        testRoom.setDown(null);
        testRoom.setLeft(null);
        testRoom.setRight(null);
        assertNull(testRoom.retUp());
        assertNull(testRoom.retDown());
        assertNull(testRoom.retLeft());
        assertNull(testRoom.retRight());        
        assertEquals("room_start", testRoom.retRoomVariant());
    }
}
