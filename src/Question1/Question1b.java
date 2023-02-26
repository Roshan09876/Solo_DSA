//Answer using java with comments. Assume you were hired to create an application
// for an ISP, and there is n number of network devices, such as routers, that are
// linked together to provides internet access to home user users. You are given a
// 2D array that represents network connections between these network devices such that a[i]=[xi,yi]
// where xi is connected to yi device.  Suppose there is a power outage on a certain device provided
// as int n represents id of the device on which power failure occurred)), Write an algorithm
// to return impacted network devices due to breakage of the link between network devices.
// These impacted device list assists you notify linked consumers that there is a power outage
// and it will take some time to rectify an issue.
// Note that: node 0 will always represent a source of internet or gateway to international network..
//
//
//
//        Input: edges= {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}}
//        Target Device (On which power Failure occurred): 4
//        Output (Impacted Device List) = {5,7}
//        Explanation: power failure on network device 4 will disconnect 5 and 7 from internet
package Question1;

import java.util.*;

public class Question1b {
    public static List<Integer> getImpactedDevices(int[][] edges, int targetDevice) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        Set<Integer> visited = new HashSet<>();
        visited.add(targetDevice);
        dfs(targetDevice, visited, graph);

        List<Integer> impactedDevices = new ArrayList<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (visited.contains(u) && !visited.contains(v) && u != 0 && !isReachable(u, v, graph, visited)) {
                impactedDevices.add(v);
            } else if (visited.contains(v) && !visited.contains(u) && v != 0 && !isReachable(v, u, graph, visited)) {
                impactedDevices.add(u);
            }
        }

        return impactedDevices;
    }

    private static void dfs(int u, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        if (!graph.containsKey(u)) {
            return;
        }
        for (int v : graph.get(u)) {
            if (v != visited.iterator().next()) { // Exclude target device from visited set
                if (!visited.contains(v)) {
                    visited.add(v);
                    dfs(v, visited, graph);
                }
            }
        }
    }

    private static boolean isReachable(int u, int v, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        if (!graph.containsKey(u)) {
            return false;
        }
        if (u == v) {
            return true;
        }
        visited.add(u);
        for (int w : graph.get(u)) {
            if (!visited.contains(w)) {
                if (isReachable(w, v, graph, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
public static void main(String[] args) {
    int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
    int targetDevice = 4;
    List<Integer> impactedDevices = getImpactedDevices(edges, targetDevice);
    System.out.println("Impacted devices: " + impactedDevices);
    // print impacted devices if any
    if (!impactedDevices.isEmpty()) {
        System.out.println("Impacted device list = " + impactedDevices);
    }
}

}



