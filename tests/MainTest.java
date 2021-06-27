import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.Assert.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;

import view.Main;

public class MainTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main controller = new Main();
        controller.start(primaryStage);
    }

    //wrapper thread updates boolean if application runs without problem.
    private volatile boolean success = false;
    @Test
    public void testLaunch() {
        //create a wrapper thread for application
        Thread thread = new Thread(() -> {
            try {
                Application.launch(Main.class); //attempt to run Dungeon16
                success = true;
            } catch (Throwable t) {
                if (t.getCause() != null && t.getCause().getClass()
                        .equals(InterruptedException.class)) {
                    success = true; //this exception is expected for
                    //interrupting, so it is a mark of success
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(3000);  //interrupt after 3 seconds
        } catch (InterruptedException ex) {
            //doesn't matter if wake up occurs early
        }
        thread.interrupt();
        try {
            thread.join(1); //wait 1 second for thread to finish
        } catch (InterruptedException ex) {
            //again, doesn't matter if wake up occurs early
        }
        assertTrue(success); //if it made it, success!
    }

    @Test
    public void testTitleScreen() {
        //check that our scene is title screen scene
        verifyThat("Dungeon 16 Title Screen", NodeMatchers.isNotNull());
    }

    @Test
    public void testStartBtn() {
        clickOn("start");
        //check that we moved to the player config screen after hitting start
        verifyThat("Dungeon 16 Config Screen", NodeMatchers.isNotNull());
    }



}