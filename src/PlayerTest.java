package src;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
public class PlayerTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main dungeon16 = new Main();
        dungeon16.start(primaryStage);
    }

    private Player playerCharacter;
    @Before
    public void setup() {
        playerCharacter = new Player("Test", "Medium", "Broadsword");
    }
    @Test
    public void returnData() {
        assertEquals(75.0, playerCharacter.returnCHP(), 0);
        assertEquals("Tank", playerCharacter.returnWeaponClass());
        assertEquals(75, playerCharacter.returnMHP(), 0);
        assertEquals(3, playerCharacter.dealDamage(), 0.01);
    }
}
