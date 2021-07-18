package model;

public class Enemy extends Entity {
    private int currentHP;
    private int damageOutput;
    private int range;
    private int x;
    private int y;

    public Enemy(int r, int x, int y, boolean c) {
        super(c);
        if (r == 1) {
            currentHP = 5;
            damageOutput = 3;
        } else if (r == 2) {
            currentHP = 3;
            damageOutput = 4;
        } else {
            currentHP = 2;
            damageOutput = 1;
        }
        range = r;
        this.x = x;
        this.y = y;
    }

     public int retX() {
        return x;
     }

    public int retY() {
        return y;
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
    public int returnHP() {
        return currentHP;
    }
}