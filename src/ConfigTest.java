package src;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.input.MouseButton;
import static org.junit.Assert.assertTrue;
import javafx.scene.input.KeyCode;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.robot.Motion;

import static org.testfx.api.FxAssert.verifyThat;

public class ConfigTest extends ApplicationTest {
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
    public void basicInputTest() {
        moveTo(point("Enter Name..."));
        clickOn(MouseButton.PRIMARY).type(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D);
        clickOn("Difficulty?");
        clickOn("Hard");
        clickOn("Weapon?");
        clickOn("Magic");
        verifyThat("Hard", NodeMatchers.isNotNull());
        verifyThat("wasd", NodeMatchers.isNotNull());
        verifyThat("Magic", NodeMatchers.isNotNull());
    }
    
    @Test
    public void validNameInputTest() {
        clickOn("Difficulty?");
        clickOn("Medium");
        clickOn("Weapon?");
        clickOn("Broadsword");
        moveBy(0, 360, Motion.DIRECT);
        clickOn(MouseButton.PRIMARY);
        verifyThat(window("Error!"), WindowMatchers.isShowing());
        moveTo(point("Enter Name..."));
        clickOn(MouseButton.PRIMARY).type(KeyCode.SPACE, KeyCode.SPACE);
        clickOn("Broadsword");
        clickOn("Magic");
        moveBy(0, 360, Motion.DIRECT);
        clickOn(MouseButton.PRIMARY);
        verifyThat(window("Error!"), WindowMatchers.isShowing());
    }
}