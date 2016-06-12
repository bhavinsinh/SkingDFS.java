package com.bhavinc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static void dfs(int[][] map, int start, int x, int y, int m, int n, Stack<Integer> path, List<Integer> maxPath, boolean noMoreMovePossible) {
        path.add(map[x][y]);
        noMoreMovePossible = true;
        if ((x - 1 >= 0) && (map[x][y] - map[x - 1][y]) > 0) {
            noMoreMovePossible = false;
            dfs(map, start, x - 1, y, m, n, path, maxPath, noMoreMovePossible);
        }
        if ((x + 1 < m) && (map[x][y] - map[x + 1][y]) > 0) {
            noMoreMovePossible = false;
            dfs(map, start, x + 1, y, m, n, path, maxPath, noMoreMovePossible);
        }
        if ((y - 1 >= 0) && (map[x][y] - map[x][y - 1]) > 0) {
            noMoreMovePossible = false;
            dfs(map, start, x, y - 1, m, n, path, maxPath, noMoreMovePossible);
        }
        if ((y + 1 < n) && (map[x][y] - map[x][y + 1]) > 0) {
            noMoreMovePossible = false;
            dfs(map, start, x, y + 1, m, n, path, maxPath, noMoreMovePossible);
        }
        if (noMoreMovePossible) {
            if (path.size() > maxPath.size()) {
                maxPath.clear();
                maxPath.addAll(path);
            } else if (path.size() == maxPath.size()) {
                if (maxPath.get(0) - maxPath.get(maxPath.size() - 1) < (start - map[x][y])) {
                    maxPath.clear();
                    maxPath.addAll(path);
                }
            }
        }
        path.pop();
    }

    static String findLongestPath(int[][] map, int m, int n) {
        int maxDiffInclination = 0;
        List<Integer> maxPath = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Stack<Integer> path = new Stack<>();
                List<Integer> maxPathDFS = new LinkedList<>();
                dfs(map, map[i][j], i, j, m, n, path, maxPathDFS, false);
                if (maxPathDFS.size() > maxPath.size()) {
                    maxPath.clear();
                    maxPath.addAll(maxPathDFS);
                    maxDiffInclination = maxPath.get(0) - maxPath.get(maxPath.size() - 1);
                } else if (maxPathDFS.size() == maxPath.size()) {
                    if (maxDiffInclination < (maxPathDFS.get(0) - maxPathDFS.get(maxPathDFS.size() - 1))) {
                        maxPath.clear();
                        maxPath.addAll(maxPathDFS);
                        maxDiffInclination = maxPath.get(0) - maxPath.get(maxPath.size() - 1);
                    }
                }
            }
        }
        return maxPath.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        int m, n;
        Scanner sc = new Scanner(new File("C:\\Users\\bhavi\\Documents\\repos\\SkingDP.java\\src\\com\\bhavinc\\map.txt"));
        m = sc.nextInt();
        n = sc.nextInt();
        int[] [] map = new int[m][n];

        for (int x = 0; x < m; x++){
            for (int y = 0; y < n; y++){
                map[x][y] = sc.nextInt();
            }
        }
        Long startTime = System.currentTimeMillis();
        System.out.println("Path" + findLongestPath(map, m, n));
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
