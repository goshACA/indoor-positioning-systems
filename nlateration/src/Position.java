public class Position {
    private double x, y, z;

    Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    Position() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
