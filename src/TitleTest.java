package src;// JavaFX
import javafx.stage.Stage;
// Junit
import org.junit.Test;
import static org.junit.Assert.assertTrue;
// TestFX
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import static org.testfx.api.FxAssert.verifyThat;

// Controller


public class TitleTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main controller = new Main();
        controller.start(primaryStage);
    }

    @Test
    public void testGuiElement(){
        // Verify correct screen is run
        verifyThat(window("Dungeon 16 Title Screen"), WindowMatchers.isShowing());
    }

    @Test
    public void testStartEvent(){
        verifyThat(window("Dungeon 16 Title Screen"), WindowMatchers.isShowing());
        // Start Button
        clickOn(443.0, 403.0);
        // Verify new screen successfully loaded
        verifyThat(window("Dungeon 16 Config Screen"), WindowMatchers.isShowing());
    }

}