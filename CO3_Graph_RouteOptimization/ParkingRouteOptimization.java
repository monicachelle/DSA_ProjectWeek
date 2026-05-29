package CO3_Graph_RouteOptimization;

import java.util.LinkedList;
import java.util.Queue;

public class ParkingRouteOptimization {
    static void bfs(int[][] graph, int start) {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("Parking route path using BFS: ");

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print((char) (node + 65) + " ");

            for (int i = 0; i < graph.length; i++) {
                if (graph[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    static void dfs(int[][] graph, int node, boolean[] visited) {
        visited[node] = true;
        System.out.print((char) (node + 65) + " ");

        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] == 1 && !visited[i]) {
                dfs(graph, i, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] parkingMap = {
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 0, 0, 0},
            {0, 1, 0, 0}
        };

        System.out.println("Smart Parking Route Navigation");
        bfs(parkingMap, 0);

        System.out.print("Parking route path using DFS: ");
        boolean[] visited = new boolean[parkingMap.length];
        dfs(parkingMap, 0, visited);

        System.out.println();
        System.out.println("Route connectivity check completed successfully.");
    }
}
