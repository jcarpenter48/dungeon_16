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

public class PlayerTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main dungeon = new Main();
        dungeon.start(primaryStage);
    }

    private Player playerCharacter;
    @Before
    public void setup() {
        playerCharacter = new Player("TestPlayer", "Easy", "Katana");
        playerCharacter.setCoords(5, 5);
    }
    @Test
    public void returnData() {
        assertEquals(100.0, playerCharacter.returnCHP(), 0);
        assertEquals(100, playerCharacter.returnMHP(), 0);
        assertEquals("DPS", playerCharacter.returnWeaponClass());
        assertEquals(2, playerCharacter.dealDamage(), 0.01);
        assertEquals(5, playerCharacter.getXCoord(), 0);
        assertEquals(5, playerCharacter.getYCoord(), 0);
        playerCharacter.moveRight();
        assertEquals(6, playerCharacter.getXCoord(), 0);
    }
}
