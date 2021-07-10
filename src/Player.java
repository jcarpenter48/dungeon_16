package src;

import javafx.scene.image.ImageView;
import java.net.URI;
import java.io.File;

public class Player {
    private String playerName;
    private String weaponClass;
    private int goldCount;
    private double maxHealth;
    private double currentHealth;
    private double roomCountMultiplier;
    private ImageView sprite;
    private Weapon currentWeapon;
    private int x;
    private int y;

    private File srcPathFile = new File(System.getProperty("user.dir"));
    private File relativePathFile = new File(srcPathFile.getParent() + "");
    private URI relativePath = relativePathFile.toURI();

    public Player() {
        playerName = "Dave";
        weaponClass = "DPS";
        goldCount = 1;
        maxHealth = 1;
        currentHealth = maxHealth;
        sprite = new ImageView(relativePath + "/res/players/testsprite.gif");
    }
    public Player(String name, String difficultyChoice, String weaponChoice) {
        playerName = name;

        switch (difficultyChoice) {
        case "Easy":
            maxHealth = 100;
            goldCount = 1000;
            break;
        case "Medium":
            maxHealth = 75;
            goldCount = 750;
            break;
        case "Hard":
            maxHealth = 50;
            goldCount = 500;
            break;
        default:
            goldCount = 1;
            maxHealth = 1;
            break;
        }
        switch (weaponChoice) {
        case "Katana":
            currentWeapon = new Weapon(2, 2);
            weaponClass = "DPS";
            sprite = new ImageView(relativePath + "/res/players/DPS/idle.gif");
            break;
        case "Broadsword":
            currentWeapon = new Weapon(1, 3);
            weaponClass = "Tank";
            sprite = new ImageView(relativePath + "/res/players/Tank/idle.gif");
            break;
        case "Magic":
            currentWeapon = new Weapon(3, 1);
            weaponClass = "Mage";
            sprite = new ImageView(relativePath + "/res/players/Mage/idle.gif");
            break;
        default:
            currentWeapon = new Weapon(1, 1);
            weaponClass = "DPS";
            sprite = new ImageView(relativePath + "/res/players/testsprite.gif");
            break;
        }
        currentHealth = maxHealth;
        roomCountMultiplier = 0;
    }

    public void incrementGold(int inc) {
        goldCount += inc;
    }

    public void increaseMaxHp(double maxInc) {
        maxHealth += maxInc;
        currentHealth += maxInc;
    }

    public void incrementRoomCount() {
        roomCountMultiplier += 0.1;
    }

    public void takeDamage(double damageTaken) {
        currentHealth -= damageTaken;
    }

    public void healHealth(double amountHealed) {
        currentHealth += amountHealed;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public double dealDamage() {
        return currentWeapon.retBaseDamage() * (1 + roomCountMultiplier);
    }

    public void switchWeapon(Weapon newWeapon, boolean confirm) {
        if (confirm) {
            currentWeapon = newWeapon;
        }
    }

    /*public boolean buyItem(int itemCost, Item item) {
        if (itemCost > goldCount) {
            return false;
        } else {
            goldCount -= itemCost;
            //playerInventory.addItem(item);
            return true;
        }
    }*/

    public double returnCHP() {
        return currentHealth;
    }
    public double returnMHP() {
        return maxHealth;
    }
    public int returnGold() {
        return goldCount;
    }
    public String returnWeaponClass() {
        return weaponClass;
    }
    public void setSprite(String animation) {
        sprite = new ImageView(relativePath + "/res/players/" + animation + ".gif");
    }
    public ImageView returnSprite() {
        return sprite;
    }
    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setXCoord(int x) {
        this.x = x;
    }
    public void setYCoord(int y) {
        this.y = y;
    }    
    public void moveUp() {
        y += 5;
    }
    public void moveDown() {
        y -= 5;
    }
    public void moveLeft() {
        x -= 5;
    }
    public void moveRight() {
        x += 5;
    }
    public int getXCoord() {
        return x;
    }
    public int getYCoord() {
        return y;
    }
}