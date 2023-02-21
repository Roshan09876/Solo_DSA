package Question7;

public class MatrixMultiplier {

    private static int[][] multiply(int[][] a, int[][] b) {
        int n = a.length;
        int[][] result = new int[n][n];
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        int blockSize = n / numOfThreads;

        Thread[] threads = new Thread[numOfThreads];

        for (int i = 0; i < numOfThreads; i++) {
            int start = i * blockSize;
            int end = (i == numOfThreads - 1) ? n : start + blockSize;

            threads[i] = new Thread(new Worker(a, b, result, start, end));
            threads[i].start();
        }

        try {
            for (int i = 0; i < numOfThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{5, 6}, {7, 8}};
        int[][] result = multiply(a, b);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static class Worker implements Runnable {
        private int[][] a;
        private int[][] b;
        private int[][] result;
        private int start;
        private int end;

        public Worker(int[][] a, int[][] b, int[][] result, int start, int end) {
            this.a = a;
            this.b = b;
            this.result = result;
            this.start = start;
            this.end = end;
        }

        public void run() {
            int n = a.length;

            for (int i = start; i < end; i++) {
                for (int j = 0; j < n; j++) {
                    int sum = 0;

                    for (int k = 0; k < n; k++) {
                        sum += a[i][k] * b[k][j];
                    }

                    result[i][j] = sum;
                }
            }
        }
    }
}
