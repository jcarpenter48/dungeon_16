import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
public class PlayerTest {
    private Player playerCharacter;
    @Before
    public void setup() {
        playerCharacter = new Player("Test", "Medium", "Broadsword");
    }
    @Test
    public void returnData() {
        assertEquals(75, playerCharacter.returnCHP());
        assertEquals("Tank", playerCharacter.returnWeaponClass());
        assertEquals(75, playerCharacter.returnMHP());
        assertEquals(3, playerCharacter.dealDamage(), 0.01);
    }
}