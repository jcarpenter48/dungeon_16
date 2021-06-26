//package dungeon16;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.net.URI;
import java.io.File;

public class Player {
    private String name;    
    private int gold;
    private int maxHealth;
    private int curHealth;
    private ImageView sprite;

    File srcPathFile = new File(System.getProperty("user.dir"));
    File relativePathFile = new File(srcPathFile.getParent()+"");
    URI relativePath = relativePathFile.toURI();   
    
    public Player() {
        name = "Dave";
        gold = 1;
        maxHealth = 1;
        curHealth = maxHealth;
        sprite = new ImageView(relativePath+"/res/players/testsprite.gif");
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
            case "Katana":
                sprite = new ImageView(relativePath+"/res/players/DPS/idle.gif");
                break;
            case "Broadsword":
                sprite = new ImageView(relativePath+"/res/players/Tank/idle.gif");
                break;
            case "Magic":
                sprite = new ImageView(relativePath+"/res/players/Mage/idle.gif");
                break;                
            default:
                sprite = new ImageView(relativePath+"/res/players/testsprite.gif");
                break;
        }        
    }
}