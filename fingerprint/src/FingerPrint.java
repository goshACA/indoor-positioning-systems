
import java.util.*;
import java.util.List;

public class FingerPrint {


    public static void main(String[] args) {
        int K = 4; //k cases number

        long startTime = System.nanoTime();

        Cellule Tf[][] = new Cellule[3][3];

        Tf[0][0] = new Cellule(new int[]{-38, -27, -54, -13});
        Tf[0][1] = new Cellule(new int[]{-74, -62, -48, -33});
        Tf[0][2] = new Cellule(new int[]{-13, -28, -12, -40});
        Tf[1][0] = new Cellule(new int[]{-34, -27, -38, -41});
        Tf[1][1] = new Cellule(new int[]{-64, -48, -72, -35});
        Tf[1][2] = new Cellule(new int[]{-45, -37, -20, -15});
        Tf[2][0] = new Cellule(new int[]{-17, -50, -44, -33});
        Tf[2][1] = new Cellule(new int[]{-27, -28, -32, -45});
        Tf[2][2] = new Cellule(new int[]{-30, -20, -60, -40});

        Cellule TM = new Cellule(new int[]{-26, -42, -13, -46});

        HashMap<Coordinate, Double> map = new HashMap<>();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                map.put(new Coordinate(i, j), dist(TM, Tf[i][j].tpr));
            }
        }

        List<Map.Entry<Coordinate, Double>> listOfEntries = new ArrayList<>(map.entrySet());


        Comparator<Map.Entry<Coordinate, Double>> valueComparator = (e1, e2) -> {
            Double v1 = e1.getValue();
            Double v2 = e2.getValue();
            return Double.compare(v1, v2);
        };

        listOfEntries.sort(valueComparator);

        int kcases[][] = new int[K][2];


        for (int k = 0; k < K; ++k) {
            kcases[k][0] = 4 * listOfEntries.get(k).getKey().i + 2;
            kcases[k][1] = 4 * listOfEntries.get(k).getKey().j + 2;
        }

        double weights[] = new double[K];
        double whole_dist = 0;
        for (int i = 0; i < K; ++i) {
            whole_dist += 1 / listOfEntries.get(i).getValue();
        }
        for (int i = 0; i < K; ++i) {
            weights[i] = 1 / listOfEntries.get(i).getValue();
            System.out.println(i + "th weight: " + weights[i]);
        }

        double x = 0;
        double y = 0;
        System.out.println("K neighbors :");
        for (int j = 0; j < K; ++j) {
            x += kcases[j][1] * weights[j] / whole_dist;
            y += kcases[j][0] * weights[j] / whole_dist;
            System.out.println("(" + kcases[j][1] + ", " + kcases[j][0] + "), Destination: " + listOfEntries.get(j).getValue());
        }
        System.out.println("Localization: (X :" + x + ", Y " + y + ")");

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Duration: " + duration / 1000000 + "ms");
    }

    public static double dist(Cellule TM, int[] fingerprints) {
        double d = 0;
        for (int i = 0; i < fingerprints.length; ++i) {
            d += Math.abs(TM.tpr[i] - fingerprints[i]);
        }
        return d;
    }
}

class Coordinate {
    int i;
    int j;

    Coordinate(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
