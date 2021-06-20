import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Player {
    private String name;    
    private int gold;
    private int maxHealth;
    private int curHealth;
    private ImageView sprite;
    
    public Player() {
        name = "Dave";
        gold = 1;
        maxHealth = 1;
        curHealth = maxHealth;
        sprite = new ImageView("PlayerTestSprite.gif");
    }
    public Player(String name, String difficultyChoice, String weaponChoice) {
        this.name = name;
        switch (difficultyChoice) {
            case "Easy":
                gold = 1000;
                maxHealth = 20;            
                break;
            case "Medium":
                gold = 750;
                maxHealth = 10;            
                break;
            case "Hard":
                gold = 500;
                maxHealth = 5;
                break;
            default:
                gold = 1;
                maxHealth = 1;
                break;
        }
        curHealth = maxHealth;
        switch (weaponChoice) {
            case "DPS":
                sprite = new ImageView("PlayerTestSprite.gif");
                break;
            default:
                sprite = new ImageView("PlayerTestSprite.gif");
                break;
        }        
    }
}