package src;

public class RoomTile {
    private String entityType;
    private Entity tileEntity;
    private boolean playerLocated;
    
    public RoomTile (String entity) {
        if (entity == null) {
            entityType = null;
        } /*else if (entity == "Chest") {
            double rngFactor = Math.random();
            if (rngFactor < 0.95) {
            tileEntity = new Chest((int) (Math.random() * 10), null, false);
            } else {
                int weaponRange = (int) ((Math.random() * 3) + 1);
                int weaponDamage = (int) ((Math.random() * 3) + 1);
                Weapon temp = new Weapon (weaponRange, weaponDamage);
                chest = new Chest(Math.random() * 10, temp);
            }
            entityType = "Chest";
        //} else if (entity == "Hazard") {
        //     //haxzard code
        }*/ else if (entity == "DoorUp") {
	    tileEntity = new Door("Up", true);
            entityType = "Door";
        } else if (entity == "DoorDown") {
	    tileEntity = new Door("Down", true);
	    entityType = "Door";
        } else if (entity == "DoorRight") {
	    tileEntity = new Door("Right", true);
	    entityType = "Door";
        } else if (entity == "DoorLeft") {
	    tileEntity = new Door("Left", true);
	    entityType = "Door";
        } else {
             //entity isEnemyCode
             return;
        }
    }
    public void clearTile () {
        entityType = null;
        //chest = null;
        //enemy = null;
        playerLocated = false;
    }
    public boolean entityLocated() {
        if (!playerLocated && entityType == null) {
            return true;
        } else {
            return false;
        }
    }
}