//Assume an electric vehicle must go from source city s to destination city d. You can locate many service centers along the journey
// that allow for the replacement of batteries; however, each service center provides batteries with a specific capacity. You are given a 2D
// array in which serviceCenter[i]=[xi,yj] indicates that the ith service center is xi miles from the source city and offers yj miles after the
// automobile can travel after replacing batteries at specific service centers. Return the number of times the car's batteries need to be replaced
// before arriving at the destination.
//Input: serviceCenters = [{10,60},{20,30},{30,30},{60,40}], targetMiles= 100, startChargeCapacity = 10
//Output: 2
//Explanation: The car can go 10 miles on its initial capacity; after 10 miles, the car replaces batteries with a capacity of 60 miles; and after
// travelling 50 miles, at position 60 we change batteries with a capacity of 40 miles; and ultimately, we may arrive at our destination.

package Question5;

import java.util.*;

public class Question_5b {

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
