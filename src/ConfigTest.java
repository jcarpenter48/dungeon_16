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

public class ConfigTest extends ApplicationTest {
    @Override
    public void start(final Stage primaryStage) throws Exception {
        Main dungeon16 = new Main();
        dungeon16.start(primaryStage);
        dungeon16.startConfigScreen();
    }
    /**
     * Test that ensures configuration screen loads correctly.
     **/
    @Test
    public void testConfigScreen() {
        //check that our scene is config screen scene
        verifyThat(window("Dungeon 16 Config Screen"),
                WindowMatchers.isShowing());
    }

    /**
     * Tests basic input in all configuration
     * fields: name, difficulty, and weapon.
     * Ensures this will allow screen to change
     * to first room after clicking on "Continue".
     **/
    @Test
    public void basicInputTest() {
        moveTo(point("Enter Name..."));
        clickOn(MouseButton.PRIMARY).type(KeyCode.W, KeyCode.A,
                KeyCode.S, KeyCode.D);
        clickOn("Difficulty?");
        clickOn("Hard");
        clickOn("Weapon?");
        clickOn("Magic");
        verifyThat("Hard", NodeMatchers.isNotNull());
        verifyThat("wasd", NodeMatchers.isNotNull());
        verifyThat("Magic", NodeMatchers.isNotNull());
    }
    /**
     * Test checks that an error window appears
     * after setting difficulty and weapon, but
     * leaving name empty.
     **/
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
    /**
     * Test checks that an error window appears
     * after setting name and weapon, but
     * leaving difficulty empty.
     **/
    @Test
    public void emptyDifficultyTest() {
        moveTo(point("Enter Name..."));
        clickOn(MouseButton.PRIMARY).type(KeyCode.J, KeyCode.A, KeyCode.Y);
        clickOn("Weapon?");
        clickOn("Magic");
        moveBy(0, 360, Motion.DIRECT);
        clickOn(MouseButton.PRIMARY);
        verifyThat(window("Error!"), WindowMatchers.isShowing());
    }
    /**
     * Test checks that an error window appears
     * after setting name and difficulty, but
     * leaving weapon empty.
     **/
    @Test
    public void emptyWeaponTest() {
        moveTo(point("Enter Name..."));
        clickOn(MouseButton.PRIMARY).type(KeyCode.J, KeyCode.A, KeyCode.Y);
        clickOn("Difficulty?");
        clickOn("Medium");
        moveBy(0, 410, Motion.DIRECT);
        clickOn(MouseButton.PRIMARY);
        verifyThat(window("Error!"), WindowMatchers.isShowing());
    }
    /**
     * Test checks that an error window appears
     * after name, difficulty, weapon is empty.
     **/
    @Test
    public void emptyConfigTest() {
        moveTo(point("Enter Name..."));
        moveBy(0, 500, Motion.DIRECT);
        clickOn(MouseButton.PRIMARY);
        verifyThat(window("Error!"), WindowMatchers.isShowing());
    }
}
