package model;

import javafx.scene.image.Image;
//collision related
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.net.URI;
import java.io.File;

public class Enemy extends Entity {
    private String monsterClass;
    private int currentHP;
    private int damageOutput;
    private int range;
    private int x;
    private int y;
    private int tileX;
    private int tileY;
    //new additions for sprites
    private Image sprite;
    private File srcPathFile = new File(System.getProperty("user.dir"));
    private File relativePathFile = new File(srcPathFile.getParent() + "");
    private URI relativePath = srcPathFile.toURI();
    //end
    
    public Enemy(int r, int x, int y, boolean c) {
        super(c);
        if (r == 1) {
            currentHP = 5;
            damageOutput = 3;
            monsterClass = "Mushroom";
            sprite = new Image(relativePath + "/res/enemies/Mushroom/idle.gif");
        } else if (r == 2) {
            currentHP = 3;
            damageOutput = 4;
            monsterClass = "Skeleton";
            sprite = new Image(relativePath + "/res/enemies/Skeleton/idle.gif");
        } else {
            currentHP = 2;
            damageOutput = 1;
            monsterClass = "Flying_Eye";
            sprite = new Image(relativePath + "/res/enemies/Flying_Eye/idle.gif");
        }
        range = r;
        this.tileX = x;
        this.tileY = y;
        this.x = (int) ((Math.random() * (590 - 5)) + 5);
        this.y = (int) ((Math.random() * (360 - (-20))) + (-20));
    }

     public int retX() {
        return x;
     }

    public int retY() {
        return y;
    }

    public void setSprite(String animation) {
        sprite = new Image(relativePath + "/res/enemies/" + monsterClass + "/"
            + animation + ".gif");
    }
    public Image returnSprite() {
        return sprite;
    }
    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void move(int px, int py, Room r) {
        if (px - x > range && r.retRoomTiles(y, x++).entityLocated()) {
            x++;
        } else if (x - px > range && r.retRoomTiles(y, x--).entityLocated()) {
            x--;
        } else if (py - y > range && r.retRoomTiles(y++, x).entityLocated()) {
            y++;
        } else if (y - py > range && r.retRoomTiles(y--, x).entityLocated()) {
            y--;
        }
    }

    public int attack(int px, int py) {
        int xDist = px - x;
        int yDist = py - y;
        int sqrtDist = (int) (Math.sqrt(Math.pow((double) xDist, 2) + Math.pow((double) yDist, 2)));
        if (sqrtDist <= range) {
            return damageOutput;
        } else {
            return 0;
        }
    }
    public void takeDamage(int d) {
        currentHP = currentHP - d;
    }
    public int returnDamage() {
        return damageOutput;
    }
    public int returnHP() {
        return currentHP;
    }
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.x, this.y, 40, 40);
    }
    /*
    max bounds for enemy spawns    
        5 360 (bottom left)
        5 -20 (top left)
        590 -20 (top right)
        590 360 (bottom right)
    */
}