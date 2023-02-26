//Answer using java with comments. There are n nations linked by train routes. You are given a 2D array indicating routes between countries and the time required to reach the target country, such that E[i]=[xi,yi,ki], where xi represents the source country, yi represents the destination country, and ki represents the time required to go from xi to yi. If you are also given information on the charges, you must pay while entering any country. Create an algorithm that returns the cheapest route from county A to county B with a time constraint.
//
//        Input: edge= {{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}}
//        Charges = {10,2,3,25,25,4}
//        Source: 0
//        Destination: 5
//        Output: 64
//        Time Constraint=14 min
//        Note: the path 0, 3, 4, 5 will take minimum time i.e., 13 minutes and which costs around $64. We cannot take path 0,1,2,5 as it takes 15 min and violates time constraint which in 14 min.


package Question1;



import java.util.*;

public class Question_1a {
    private static final int INF = Integer.MAX_VALUE;

    public static int findCheapestRoute(int[][] edges, int[] charges, int source, int destination, int timeConstraint) {
        int n = charges.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> dist[i]));
        pq.add(source);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            visited[u] = true;
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != INF && !visited[v] && dist[u] + graph[u][v] <= timeConstraint) {
                    if (dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                        pq.add(v);
                    }
                }
            }
        }
        return (dist[destination] == INF) ? -1 : (dist[destination] + charges[source] + charges[destination]);
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}};
        int[] charges = {10,2,3,25,25,4};
        int source = 0;
        int destination = 5;
        int timeConstraint = 14;
        int cheapestRoute = findCheapestRoute(edges, charges, source, destination, timeConstraint);
        System.out.println("Cheapest route cost: " + cheapestRoute);
    }
}


