public class EmitterFactory {
    static Emitter[] createEmitters(double[] coords, double[] distances) {
        Emitter[] res = new Emitter[distances.length];
        for (int i = 0; i < distances.length; ++i) {
            res[i] = new Emitter(new Position(coords[i * 3], coords[i * 3 + 1], coords[i * 3 + 2]), distances[i]);
        }
        return res;
    }
}
