package Questions;


//Given an array of even numbers sorted in ascending order and an integer k,
//Find the k^th missing even number from provided array
//Input a[] ={0, 2, 6, 18, 22} k=6
//Output: 16 examples:
//Explanation: Missing even numbers on the list are 4, 8, 10, 12, 14, 16, 20 and so on and
// kth missing number is on 6th place of the list i.e. 16

public class Question_8b {

    public static int kthMissingEvenNumber(int[] a, int k) {
        int n = a.length;
        int currentMissing = a[0];
        int missingCount = 0;
        for (int i = 1; i < n; i++) {
            int difference = (a[i] - a[i-1]) / 2 - 1;
            if (difference >= 1) {
                int missing = currentMissing + 2;
                for (int j = 1; j <= difference; j++) {
                    if (missing <= a[i]) {
                        missingCount++;
                        currentMissing = missing;
                        if (missingCount == k) {
                            return currentMissing;
                        }
                        missing += 2;
                    }
                }
            }
        }
        return currentMissing + 2 * k;
    }

    public static void main(String[] args) {
        int[] a = { 4, 8, 10, 12, 14, 16, 20 };
        int k = 6;
        System.out.println("Kth missing even number: " + kthMissingEvenNumber(a, k));

    }
}
