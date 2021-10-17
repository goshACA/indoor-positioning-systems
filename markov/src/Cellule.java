public class Cellule {
    public int nb = 0;
    public float stat = 0;

    public Cellule(int _nb, float _stat){
        this.nb = _nb;
        this.stat = _stat;
    }

    @Override
    public String toString() {
        return "| [ " + nb + " , " + String.format("%.2f", stat * 100) + "%, ";
    }
}
