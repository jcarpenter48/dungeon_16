package src;

import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import static org.testfx.api.FxAssert.verifyThat;


public class ConfigTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main controller = new Main();
        controller.start(primaryStage);
    }

    @Test
    public void testGuiElement() {
        verifyThat(window("Dungeon 16 Title Screen"), WindowMatchers.isShowing());
        clickOn(893.0, 547.0);
        // Verify correct screen is run
        verifyThat(window("Dungeon 16 Config Screen"), WindowMatchers.isShowing());
    }

    @Test
    public void testEmptyTextField(){
        verifyThat(window("Dungeon 16 Title Screen"), WindowMatchers.isShowing());
        clickOn(893.0, 547.0);
        moveTo(point("Enter Name..."));
        clickOn(MouseButton.PRIMARY);
        moveBy(0, 500);
        clickOn(MouseButton.PRIMARY);
        verifyThat(window("Error!"), WindowMatchers.isShowing());
    }
}
