import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import view.Main;
import model.Room;
import model.Enemy;

public class EnemyTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main dungeon16 = new Main();
        dungeon16.start(primaryStage);
    }

    private Enemy enemyTester1;
    private Enemy enemyTester2;
    private Enemy enemyTester3;
    @Before
    public void setup() {
        enemyTester1 = new Enemy(1, 0, 0, false);
        enemyTester2 = new Enemy(2, 2, 0, false);
        enemyTester3 = new Enemy(3, 0, 5, false);
    }
    @Test
    public void testTakeDamage() {
        enemyTester1.takeDamage(2);
        assertEquals(3, enemyTester1.returnHP());
        enemyTester2.takeDamage(3);
        assertEquals(0, enemyTester2.returnHP());
        enemyTester3.takeDamage(4);
        assertEquals(-2, enemyTester3.returnHP());
    }
    @Test
    public void testDealDamage() {
        enemyTester1 = new Enemy(1, 0, 0, false);
        enemyTester2 = new Enemy(2, 2, 0, false);
        enemyTester3 = new Enemy(3, 0, 5, false);
        //int x = enemyTester1.returnDamage();
        int x = enemyTester1.attack(0, 1);
        assertEquals(3, x);
        int y = enemyTester2.attack(4, 0);
        assertEquals(4, y);
        int z = enemyTester1.attack(9, 9);
        assertEquals(0, z);
    }
}