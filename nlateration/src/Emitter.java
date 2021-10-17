public class Emitter {
    public Position pos;
    private double d;

    Emitter(Position p, double d) {
        this.pos = p;
        this.d = d;
    }

    public double getD() {
        return d;
    }

    public double getX() {
        return pos.getX();
    }

    public double getY() {
        return pos.getY();
    }

    public double getZ() {
        return pos.getZ();
    }
}
