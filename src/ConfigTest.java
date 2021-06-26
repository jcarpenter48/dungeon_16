import javafx.application.Application;
import javafx.scene.input.KeyCode;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class TitleTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Main dungeon16 = new Main();
        dungeon16.start(primaryStage);
        dungeon16.startConfigScreen();        
    }
    
    //wrapper thread updates boolean if application runs without problem.
    private volatile boolean success = false;
    @Test
    public void testLaunch() {
        Thread thread = new Thread() { //create a wrapper thread for application
            @Override
            public void run() {
                try {
                    Application.launch(Main.class); //attempt to run Dungeon16
                    success = true;
                } catch(Throwable t) {
                    if(t.getCause() != null && t.getCause().getClass().equals(InterruptedException.class)) {
                        success = true; //this exception is expected for interrupting, so it is a mark of success
                        return;
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(3000);  //interrupt after 3 seconds
        } catch(InterruptedException ex) {
            //doesn't matter if wake up occurs early
        }
        thread.interrupt();
        try {
            thread.join(1); //wait 1 second for thread to finish
        } catch(InterruptedException ex) {
            //again, doesn't matter if wake up occurs early
        }
        assertTrue(success); //if it made it, success!
    }

    @Test
    public void testConfigScreen() {
        //check that our scene is title screen scene
        verifyThat("Dungeon 16 Config Screen", NodeMatchers.isNotNull());
    }
    
    @Test
    public void basicInputTest() {
        clickOn("#tfplayerNameChoice").type(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D);
        clickOn("Difficulty?");
        clickOn("Hard");
        clickOn("Weapon?");
        clickOn("Magic");
        clickOn("continueButton");
        verifyThat("Hard", NodeMatchers.isNotNull());
        verifyThat("wasd", NodeMatchers.isNotNull());
        verifyThat("Magic", NodeMatchers.isNotNull());
    }
    
    @Test
    public void validNameInputTest() {
        clickOn("Difficulty?");
        clickOn("Hard");
        clickOn("Weapon?");
        clickOn("Magic");
        clickOn("continueButton");
        verifyThat(((Stage) getWindowByIndex(1)).getTitle(), Matchers.equalTo("Error!"));
        clickOn("#tfplayerNameChoice").type(KeyCode.SPACE, KeyCode.SPACE);
        clickOn("continueButton");
        verifyThat(((Stage) getWindowByIndex(1)).getTitle(), Matchers.equalTo("Error!"));
    }
}