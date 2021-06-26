package src;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.testfx.matcher.base.WindowMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class TitleTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main dungeon16 = new Main();
        dungeon16.start(primaryStage);
    }

    @Test
    public void testTitleScreen() {
        //check that our scene is title screen scene
        verifyThat(window("Dungeon 16 Title Screen"), WindowMatchers.isShowing());
    }

    @Test
    public void testStartBtn() {
        clickOn(893.0, 547.0);
        //check that we moved to the player config screen after hitting start
        verifyThat(window("Dungeon 16 Config Screen"), WindowMatchers.isShowing());
    }
}