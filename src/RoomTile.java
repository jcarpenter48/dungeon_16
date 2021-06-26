public class RoomTile {
    private String entityType;
    private Chest chest;
    private Enemy enemy;
    private boolean playerLocated;
    
    public RoomTile (String entity) {
        if (entity == null) {
            entityType = null;
        } else if (entity == "Chest") {
            double rngFactor = Math.random();
            if rngFactor < 0.95 {
            chest = new Chest(Math.random() * 10, null);
            } else {
                int weaponRange = (int) ((Math.random() * 3) + 1);
                int weaponDamage = (int) ((Math.random() * 3) + 1);
                Weapon temp = new Weapon (weaponRange, weaponDamage);
                chest = new Chest(Math.random() * 10, temp);
            }
        } else {
             //entity isEnemyCode
        }
    }
    public void clearTile () {
        entityType = null;
        chest = null;
        enemy = null;
        playerLocated = nulll;
    }
}