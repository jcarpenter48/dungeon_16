public class Player {
    private String playerName;
    private int goldCount;
    private double maxHealth;
    private double currentHealth;
    private double roomCountMultiplier;
    private Weapon currentWeapon;
    //private Bag playerInventory;

    public Player(String, name, String difficultyChoice, String weaponChoice) {
        playerName = name;
        if (weaponChoice == "Spear") {
	    currentWeapon = new Weapon(2, 2);
        } else if (weaponChoice == "Bow") {
             currentWeapon = new Weapon(3, 1);
        } else {
             currentWeapon = new Weapon(1, 3);
        }
        if (difficultyChoice.equals("Easy")) {
            maxHealth = 100;
            gold = 100;
        } else if (difficultyChoice.equals("Normal")) {
            maxHealth = 75;
            gold = 50;
        } else {
            maxHealth = 30;
            gold = 0;
        }
        currentHealth = maxHealth;
        roomCountMultiplier = 0;
    }

    public void incrementGold(int inc) {
        gold += inc;
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
        if (confirm == true) {
            currentWeapon = newWeapon;
        }
    }

    public boolean buyItem(itemCost, Item item) {
        if (itemCost > gold) {
            return false;
        } else {
            gold -= itemCost;
            //playerInventory.addItem(item);
            return true;
        }
    }

    public double returnCHP() {
        return currentHealth;
    }
    public double returnMHP() {
        return maxHealth;
    }
    public int returnGold() {
        return gold;
    }
}