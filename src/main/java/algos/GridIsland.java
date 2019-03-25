package algos;

public class GridIsland {

    public int largestIsland(int[][] grid) {
        int max = 0;
        if (grid.length == 0 || grid[0].length == 0) {
            return max;
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                max = Math.max(max, dfs(grid, row, col, 0));
                /* count the number of island
                if (dfs(grid, row, col, 0) > 0) {
                    max++;
                }
                 */
            }
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 2) {
                    grid[row][col] = 1;
                }
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int row, int col, int area) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return area;
        }
        if (grid[row][col] == 1) {
            grid[row][col] = 2;
            area++;
            area = dfs(grid, row + 1, col, area);
            area = dfs(grid, row - 1, col, area);
            area = dfs(grid, row, col + 1, area);
            area = dfs(grid, row, col - 1, area);
        }
        return area;
    }

    public static void main(String[] args) {
        GridIsland gridIsland = new GridIsland();
        int[][] grid = new int[][] {
                {0, 1, 1, 0},
                {1, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 1}
        };
        System.out.println(gridIsland.largestIsland(grid));
        System.out.println(gridIsland.largestIsland(new int[][]{}));
    }
}
