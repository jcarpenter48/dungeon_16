package src;

import javafx.stage.Stage;
// Junit
import org.junit.Test;
import static org.junit.Assert.assertTrue;
// TestFX
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;

// Controller
import org.testfx.matcher.base.WindowMatchers;

public class ConfigTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main controller = new Main();
        controller.start(primaryStage);
        clickOn(443.0, 403.0);
    }

    @Test
    public void testGuiElement() {
        // Verify correct screen is run
        verifyThat(window("Dungeon 16 Config Screen"), WindowMatchers.isShowing());
    }
}
