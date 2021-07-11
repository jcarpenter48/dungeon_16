import static org.junit.Assert.assertEquals;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        src.Main dungeon16 = new src.Main();
        dungeon16.start(primaryStage);
    }

    private src.Player playerCharacter;
    @Before
    public void setup() {
        playerCharacter = new src.Player("Test", "Medium", "Broadsword");
    }
    @Test
    public void returnData() {
        assertEquals(75.0, playerCharacter.returnCHP(), 0);
        assertEquals("Tank", playerCharacter.returnWeaponClass());
        assertEquals(75, playerCharacter.returnMHP(), 0);
        assertEquals(3, playerCharacter.dealDamage(), 0.01);
    }
    @Test
    public void setCoordinates() {
        playerCharacter.setCoords(305, 160);
        assertEquals(305, playerCharacter.getXCoord());
        assertEquals(160, playerCharacter.getYCoord());
        playerCharacter.setXCoord(105);
        assertEquals(105, playerCharacter.getXCoord());
        playerCharacter.setYCoord(275);
        assertEquals(275, playerCharacter.getYCoord());
    }
}
