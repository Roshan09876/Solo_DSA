package Question5;

import java.util.*;

public class BatteryReplacement {

    public static int minBatteryReplacements(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {
        // create a priority queue to store the service centers based on their battery capacity
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return b[1] - a[1];
            }
        });
        for (int[] sc : serviceCenters) {
            pq.offer(sc);
        }

        int currPos = 0, currCharge = startChargeCapacity, batteryReplacements = 0;
        while (currPos + currCharge < targetMiles) {
            if (pq.isEmpty()) {
                return -1; // not enough battery capacity to reach destination
            }

            int[] sc = pq.poll();
            int nextPos = sc[0], nextCharge = sc[1];

            if (nextPos - currPos > currCharge) {
                return -1; // not enough battery capacity to reach next service center
            }

            currCharge -= (nextPos - currPos);
            currPos = nextPos;
            currCharge += nextCharge;
            batteryReplacements++;

            pq.offer(new int[]{currPos, currCharge});
        }

        return batteryReplacements;
    }

    public static void main(String[] args) {
        int[][] serviceCenters = {{10,60},{20,30},{30,30},{60,40}};
        int targetMiles = 100, startChargeCapacity = 10;
        int result = minBatteryReplacements(serviceCenters, targetMiles, startChargeCapacity);
        System.out.println(result);
        //  output: 2
    }
}
