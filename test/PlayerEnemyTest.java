import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import view.Main;
import model.Room;

public class PlayerEnemyTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main dungeon16 = new Main();
        dungeon16.start(primaryStage);
    }

    private Enemy enemyTester1;
    private Player playerTester;
    @Before
    public void setup() {
        enemyTester1 = new Enemy(1, 0, 0, false);
        playerTester = new Player("Name", "Easy", "Katana");
        playerTester.setCoords(0, 1);
    }
    @Test
    public void playerTakeDamage() {
        playerTester.takeDamage((double)enemyTester1.attack(playerTester.getXCoord(), playerTester.getYCoord()));
        assertEquals(97, playerTester.returnCHP());
    }
    @Test
    public void playerDealDamage() {
        enemyTester1.takeDamage((int) playerTester.dealDamage());
        assertEquals(3, enemyTester1.returnHp());
    }
}