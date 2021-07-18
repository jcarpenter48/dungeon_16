public class Door extends Entity{
    private String direction;
    private boolean locked;
    public Door(String direction, boolean c, boolean l) {
        super(c);
        this.direction = direction;
        locked = l;
    }
    public String returnDirection() {
        return direction;
    }
    public boolean returnLocked() {
        return locked;
    }
}
