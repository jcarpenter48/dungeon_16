package src;

public class Weapon{
    private int weaponRange;
    private int baseDamage;
    private String weaponType;

    public Weapon(int range, int damage) {
        weaponRange = range;
        baseDamage = damage;
        if (weaponRange == 1) {
            weaponType = "Sword";
        } else if (weaponRange == 2) {
            weaponType = "Spear";
        } else {
            weaponType = "Bow";
        }
    }
    public int retBaseDamage() {
        return baseDamage;
    }
}