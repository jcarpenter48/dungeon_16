package src;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import javafx.stage.Stage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.KeyCode;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.robot.Motion;

import static org.testfx.api.FxAssert.verifyThat;

public class TestOptionsConfigScreen extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main dungeon16 = new Main();
        dungeon16.start(primaryStage);
        dungeon16.startConfigScreen();
    }

    @Test
    public void testConfigScreen() {
        //check that our scene is config screen scene
        verifyThat(window("Dungeon 16 Config Screen"), WindowMatchers.isShowing());
    }
    
    @Test
    public void easyInputTest() {
        moveTo(point("Enter Name..."));
        clickOn(MouseButton.PRIMARY).type(KeyCode.T, KeyCode.E, KeyCode.S, KeyCode.T);
        clickOn("Difficulty?");
        clickOn("Easy");
        clickOn("Weapon?");
        clickOn("Katana");
        verifyThat("Easy", NodeMatchers.isNotNull());
        verifyThat("test", NodeMatchers.isNotNull());
        verifyThat("Katana", NodeMatchers.isNotNull());
    }
}