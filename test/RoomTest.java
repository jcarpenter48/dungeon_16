import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import view.Main;
import model.Room;

public class RoomTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main dungeon16 = new Main();
        dungeon16.start(primaryStage);
    }

    private Room roomTester;
    private Room dummyRoom1 = new Room();
    private Room dummyRoom2 = new Room();
    private Room dummyRoom3 = new Room();
    private Room dummyRoom4 = new Room();    
    @Before
    public void setup() {
        roomTester = new Room(3);
    }
    @Test
    public void testMonsterCreation() {
        assertEquals(3, roomTester.retEnemyList().size());
        roomTester = new Room(6);
        assertEquals(6, roomTester.retEnemyList().size());
        roomTester = new Room(0);
        assertEquals(0, roomTester.retEnemyList().size());
    }
    @Test
    public void testMonsterDefeat() {
        roomTester.retEnemyList().get(0).takeDamage(roomTester.retEnemyList().get(0).returnHp());
        roomTester.destroyMonster();
        assertEquals(2, roomTester.retEnemyList().size());
        roomTester.retEnemyList().get(0).takeDamage(roomTester.retEnemyList().get(0).returnHp());
        assertEquals(1, roomTester.retEnemyList().size());
        roomTester.retEnemyList().get(0).takeDamage(roomTester.retEnemyList().get(0).returnHp());
        assertEquals(0, roomTester.retEnemyList().size());
    }
    public void testDoorLock() {
        roomTester.setUp(new Room());
        roomTester.setDown(new Room());
        roomTester.setLeft(new Room());
        roomTester.setRight(new Room());
        roomTester.retEnemyList().clear();
        assertEquals(true, roomTester.retRoomTiles(5, 0).getEntity().returnLocked());
        boolean temp = roomTester.retRoomTiles(5, 0).getEntity().returnLocked();
        assertEquals(false, temp);
    }
}