import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import view.Main;
import model.Room;

public class DoorTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main dungeon16 = new Main();
        dungeon16.start(primaryStage);
    }

    private Door doorTester;
    @Before
    public void setup() {
        doorTester = new Door("Up", true, true);
    }
    @Test
    public void testDoorUnlock() {
        doorTester.setLock(false);
        assertEquals(false, doorTester.returnLocked);
    }
}