//you are provided certain string and pattern, return true if pattern entirely matches the string otherwise return false.
//        Note: if pattern contains char @ it matches entire sequence of characters and # matches any single character within string.
//        Input: String a=“tt”, pattern =”@”
//        Output: true
//        Input: String a=“ta”, pattern =”t”
//        Output: false
//        Input: String a=“ta”, pattern =”t#”
//        Output: true


package Question3;

public class Question_3b {
    public static boolean isMatch(String a, String pattern) {
        int n = a.length();
        int m = pattern.length();
        int i = 0, j = 0, star = -1, lastMatch = -1;
        while (i < n) {
            if (j < m && (a.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '#')) {
                i++;
                j++;
            } else if (j < m && pattern.charAt(j) == '@') {
                lastMatch = i;
                star = j;
                j++;
            } else if (star != -1) {
                j = star + 1;
                lastMatch++;
                i = lastMatch;
            } else {
                return false;
            }
        }
        while (j < m && pattern.charAt(j) == '@') {
            j++;
        }
        return j == m;
    }


    public static void main(String[] args) {
        String a = "tt";
        String pattern = "@";
        System.out.println(isMatch(a, pattern)); // Output: true

        a = "ta";
        pattern = "t";
        System.out.println(isMatch(a, pattern)); // Output: false

        a = "ta";
        pattern = "t#";
        System.out.println(isMatch(a, pattern)); // Output: true

    }
}
