import java.util.Scanner;

public class InitMarkov {
    static final int NBLINES = 5;
    static final int NBCOLUMNS = 6;
    static final Cellule HMM[][] = new Cellule[NBLINES][NBCOLUMNS];
    static final Cellule MM[][] = new Cellule[NBLINES][NBCOLUMNS];
    static final String separator = "|-------------------------";

    public static void main(String[] args) {
        int prev = 0, curr = 0, nextPage = -1;
        int[] w = {1, 2, 3, 1, 2, 1, 4, 2, 1, 4, 1, 2, 0, 1, 2, 1, 3, 2};

        for (int i = 0; i < NBLINES; ++i) {
            for (int j = 0; j < NBCOLUMNS; ++j) {
                HMM[i][j] = new Cellule(0, 0);
                MM[i][j] = new Cellule(0, 0);
            }
        }
        int it = 0;
        while (it != w.length) {
            System.out.println("Currently on: " + (prev == -1 ? "BEGINNING" : curr) );
            nextPage = w[it];
            System.out.println("We are moving to (to exit the program press " + NBLINES + ") :" + nextPage);

            long startTime = System.nanoTime();

            while (nextPage < 0 || nextPage >= NBLINES) {
                if (nextPage == NBLINES) {
                    System.out.println("Exit of program");
                    System.exit(1);
                }
                System.out.println("We are moving to (to exit the program press " + NBLINES + ") :");
            }
            prev = curr;
            curr = nextPage;

            double max_ind_from = 0,  stat = 0;
            for (int i = 0; i < NBLINES; ++i) {
                if (MM[i][curr].stat > stat) {
                    max_ind_from = i;
                    stat = MM[i][curr].stat;
                }
            }
            System.out.println("Most likely from: " + max_ind_from);

            HMM[prev][curr].nb += 1;
            HMM[prev][NBCOLUMNS - 1].nb += 1;
            for (int i = 0; i < NBCOLUMNS; ++i) {
                HMM[prev][i].stat = (float) HMM[prev][i].nb / HMM[prev][NBCOLUMNS - 1].nb;
            }
            MM[prev][curr].nb += 1;
            MM[curr][NBCOLUMNS - 1].nb += 1;
            for (int i = 0; i < NBLINES; ++i) {
                MM[i][curr].stat = (float) MM[i][curr].nb / MM[curr][NBCOLUMNS - 1].nb;
            }


            printResults();

            System.out.print("Where is likely to go? ");
            double max_ind = 0;
            stat = 0;
            for (int i = 0; i < NBCOLUMNS - 1; ++i) {
                if (HMM[curr][i].stat > stat) {
                    max_ind = i;
                    stat = HMM[curr][i].stat;
                }
            }

            System.out.print(max_ind + "\n");

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Duration: " + duration / 1000000 + "ms\n");
            ++it;
        }

        //WITH CUSTOM INPUT
        /*while (true) {
            System.out.println("Moved from: " + prev + "\nCurrently on: " + curr);

            Scanner sc = new Scanner(System.in);
            System.out.println("Next page (to exit the program press " + NBLINES + ") :");
            nextPage = sc.nextInt();

            while (nextPage < 0 || nextPage >= NBLINES) {
                if (nextPage == NBLINES) {
                    System.out.println("Exit of program");
                    System.exit(1);
                }
                System.out.println("Next page (to exit the program press " + NBLINES + ") :");
                nextPage = sc.nextInt();
            }

            long startTime = System.nanoTime();
            double max_ind_from = 0,  stat = 0;
            for (int i = 0; i < NBLINES; ++i) {
                if (MM[i][curr].stat > stat) {
                    max_ind_from = i;
                    stat = MM[i][curr].stat;
                }
            }
            System.out.println("Most likely from: " + max_ind_from);

            prev = curr;
            curr = nextPage;
            HMM[prev][curr].nb += 1;
            HMM[prev][NBCOLUMNS - 1].nb += 1;
            for (int i = 0; i < NBCOLUMNS; ++i) {
                HMM[prev][i].stat = (float) HMM[prev][i].nb / HMM[prev][NBCOLUMNS - 1].nb;
            }
            MM[prev][curr].nb += 1;
            MM[curr][NBCOLUMNS - 1].nb += 1;
            for (int i = 0; i < NBLINES; ++i) {
                MM[i][curr].stat = (float) MM[i][curr].nb / MM[curr][NBCOLUMNS - 1].nb;
            }

            printResults();

            System.out.print("Where is likely to go? ");
            double max_ind = 0;
            stat = 0;
            for (int i = 0; i < NBCOLUMNS - 1; ++i) {
                if (HMM[curr][i].stat > stat) {
                    max_ind = i;
                    stat = HMM[curr][i].stat;
                }
            }

            System.out.print(max_ind + "\n");

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Duration: " + duration / 1000000 + "ms\n");
            ++it;
        }*/
    }

    public static void printLine(int n, int length) {
        int i;
        for (i = 0; i < length; ++i) {
            System.out.print(" ");
        }
        for (i = 0; i < n; ++i) {
            System.out.print(separator);
        }
        System.out.print("|");
    }

    public static void printResults() {
        int i, j;
        String prev = "Previous page: 0";
        for (int k = 0; k < prev.length(); ++k) {
            System.out.print(" ");
        }
        for (i = 0; i < NBCOLUMNS - 1; ++i) {
            StringBuilder title = new StringBuilder("  Current page: " + i);
            int len = Math.abs(separator.length() - title.length());

            for (int k = 0; k < len; k++) {
                title.append(" ");
            }

            System.out.print(title);
        }
        System.out.print("Total :\n");

        for (i = 0; i < NBLINES; ++i) {
            prev = "Previous page: " + i;
            printLine(NBCOLUMNS, prev.length());
            System.out.print("\n");

            for (j = 0; j < NBCOLUMNS; ++j) {
                if (j == 0) {
                    System.out.print(prev);
                }
                StringBuilder content = new StringBuilder(HMM[i][j].toString());
                content.append(String.format("%.2f", MM[i][j].stat * 100)).append("%]");
                if (j == NBCOLUMNS - 1) {
                    content = new StringBuilder(" " + HMM[i][j].nb);
                }
                int l = content.length();
                for (int k = 0; k < Math.abs(l - separator.length()); ++k) {
                    content.append(" ");
                }
                System.out.print(content);
            }
            System.out.println("|");
        }
        printLine(NBCOLUMNS, prev.length());
        System.out.println();
    }
}
