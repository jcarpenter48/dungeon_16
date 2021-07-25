import static org.junit.Assert.assertEquals;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;

import view.Main;
import model.Player;

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
    @Test //M2 David's Test
    public void returnData() {
        assertEquals(75.0, playerCharacter.returnCHP(), 0);
        assertEquals("Tank", playerCharacter.returnWeaponClass());
        assertEquals(75, playerCharacter.returnMHP(), 0);
        assertEquals(3, playerCharacter.dealDamage(), 0.01);
    }
    @Test //M3 Ian's Test
    public void setCoordinates() {
        playerCharacter.setCoords(305, 160);
        assertEquals(305, playerCharacter.getXCoord());
        assertEquals(160, playerCharacter.getYCoord());
        playerCharacter.setXCoord(105);
        assertEquals(105, playerCharacter.getXCoord());
        playerCharacter.setYCoord(275);
        assertEquals(275, playerCharacter.getYCoord());
    }
    @Test //M3 David's Test
    public void returnData2() {
        playerCharacter = new Player("TestPlayer", "Easy", "Katana");
        playerCharacter.setCoords(5, 5);
        assertEquals(100.0, playerCharacter.returnCHP(), 0);
        assertEquals(100, playerCharacter.returnMHP(), 0);
        assertEquals("DPS", playerCharacter.returnWeaponClass());
        assertEquals(2, playerCharacter.dealDamage(), 0.01);
        assertEquals(5, playerCharacter.getXCoord(), 0);
        assertEquals(5, playerCharacter.getYCoord(), 0);
        playerCharacter.moveRight();
        assertEquals(10, playerCharacter.getXCoord(), 0);
    }    
    @Test //M4 Test Case
    public void returnData3() {
        playerCharacter = new Player("TestPlayer", "Hard", "Magic");
        playerCharacter.setCoords(5, 5);
        assertEquals(50.0, playerCharacter.returnCHP(), 0);
        assertEquals(50, playerCharacter.returnMHP(), 0);
        assertEquals("Mage", playerCharacter.returnWeaponClass());
        assertEquals(1, playerCharacter.dealDamage(), 0.01);
        assertEquals(5, playerCharacter.getXCoord(), 0);
        assertEquals(5, playerCharacter.getYCoord(), 0);
        playerCharacter.moveLeft();
        assertEquals(0, playerCharacter.getXCoord(), 0);
    }    
}