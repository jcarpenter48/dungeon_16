public class Door extends Entity{
    private String direction;
    public Door(String direction, boolean c) {
        super(c);
        this.direction = direction;
    }
    public String returnDirection() {
        return direction;
    }
}