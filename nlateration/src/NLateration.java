final class NLateration {
    static private double x_min = Integer.MAX_VALUE, x_max = 0, y_min = Integer.MAX_VALUE, y_max = 0, z_min = Integer.MAX_VALUE, z_max = 0;
    static private Position p = new Position();
    static private double dMin = Integer.MAX_VALUE;
    static private final Emitter[] emitters = EmitterFactory.createEmitters(new double[]{
            0.5, 0.5, 0.5,
            4, 0, 0,
            4, 5, 5,
            3, 3, 3}, new double[]{3, 2, 4.2, 2.5});

    public static void main(String[] args) {
        for (Emitter e : emitters) {
            double c_x_min = e.getX() - e.getD();
            double c_x_max = e.getX() + e.getD();

            double c_y_min = e.getY() - e.getD();
            double c_y_max = e.getY() + e.getD();

            double c_z_min = e.getZ() - e.getD();
            double c_z_max = e.getZ() + e.getD();

            if (c_x_min < x_min) {
                x_min = c_x_min;
            }
            if (c_x_max > x_max) {
                x_max = c_x_max;
            }

            if (c_y_min < y_min) {
                y_min = c_y_min;
            }

            if (c_y_max > y_max) {
                y_max = c_y_max;
            }

            if (c_z_min < z_min) {
                z_min = c_z_min;
            }

            if (c_z_max > z_max) {
                z_max = c_z_max;
            }
        }


        long startTime = System.nanoTime();

        double step = 0.1, d;
        for (double i = x_min; i <= x_max; i += step) {
            for (double j = y_min; j <= y_max; j += step) {
                for (double k = z_min; k <= z_max; k += step) {
                    d = 0;
                    for (Emitter e : emitters) {
                        d += Math.abs(distance(e.pos, i, j, k) - e.getD());
                    }
                    if (d < dMin) {
                        dMin = d;
                        p = new Position(i, j, k);
                    }

                }

            }
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.println(p.getX() + " " + p.getY() + " " + p.getZ() + "     " + duration / 1000000);

    }

    static double distance(Position p, double x, double y, double z) {
        return Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2) + Math.pow(p.getZ() - z, 2));
    }


}
