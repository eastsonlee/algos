package leetcode;

import java.util.ArrayList;
import java.util.List;

import utils.MatrixOps;

public class SpiralMatrix {
    /*
        DFS
     */
    enum Direction {
        RIGHT, DOWN, LEFT, UP
    }

    List<Integer> order = new ArrayList<>();
    int value = 0;

    public List<Integer> spiralOrderDfs(int[][] matrix) {
        if (matrix.length > 0) {
            move(Direction.RIGHT, 0, 0, matrix,
                    -1, matrix[0].length, -1, matrix.length, false);
        }
        return order;
    }

    public int[][] generateMatrix(int n) {
        if (n > 0) {
            int[][] matrix = new int[n][n];
            move(Direction.RIGHT, 0, 0, matrix, -1, n, -1, n, true);
            return matrix;
        }
        return null;
    }

    private void move(Direction direction, int row, int col, int[][] matrix,
                      int leftBound, int rightBound, int upBound, int downBound,
                      boolean generate) {

        // System.out.println("row: " + row + " [" + upBound + "," + downBound + "]");
        // System.out.println("col: " + col + " [" + leftBound + "," + rightBound + "]");
        if (col > leftBound && col < rightBound && row > upBound && row < downBound) {
            if (generate) {
                matrix[row][col] = ++value;
            } else {
                order.add(matrix[row][col]);
            }
            // System.out.println(matrix[row][col] + " " + "\n");
        }

        if ((rightBound - leftBound <= 2) && (downBound - upBound <= 2)) {
            return;
        }

        switch (direction) {
            case RIGHT:
                if (col >= rightBound) {
                    // System.out.println("turn down");
                    move(Direction.DOWN, row+1, col-1, matrix, leftBound, rightBound, upBound+1, downBound, generate);
                } else {
                    move(Direction.RIGHT, row, col+1, matrix, leftBound, rightBound, upBound, downBound, generate);
                }
                return;
            case LEFT:
                if (col <= leftBound) {
                    // System.out.println("turn up");
                    move(Direction.UP, row-1, col+1, matrix, leftBound, rightBound, upBound, downBound-1, generate);
                } else {
                    move(Direction.LEFT, row, col-1, matrix, leftBound, rightBound, upBound, downBound, generate);
                }
                return;
            case DOWN:
                if (row >= downBound) {
                    // System.out.println("turn left");
                    move(Direction.LEFT, row-1, col-1, matrix, leftBound, rightBound-1, upBound, downBound, generate);
                } else {
                    move(Direction.DOWN, row+1, col, matrix, leftBound, rightBound, upBound, downBound, generate);
                }
                return;
            case UP:
                if (row <= upBound) {
                    // System.out.println("turn right");
                    move(Direction.RIGHT, row+1, col+1, matrix, leftBound+1, rightBound, upBound, downBound, generate);
                } else {
                    move(Direction.UP, row - 1, col, matrix, leftBound, rightBound, upBound, downBound, generate);
                }
                return;
            default:
                return;
        }
    }

    /*
        Loop
     */
    public List<Integer> spiralOrderLoop(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix.length == 0) {
            return order;
        }
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int rowIdx = 0;
        int colIdx = -1;

        while (true) {
            // go right
            for (int i = 0; i < numCols; i++) {
                order.add(matrix[rowIdx][++colIdx]);
            }
            if (--numRows == 0) {
                break;
            }

            // go down
            for (int i = 0; i < numRows; i++) {
                order.add(matrix[++rowIdx][colIdx]);
            }
            if (--numCols == 0) {
                break;
            }

            // go left
            for (int i = 0; i < numCols; i++) {
                order.add(matrix[rowIdx][--colIdx]);
            }
            if (--numRows == 0) {
                break;
            }

            // go up
            for (int i = 0; i < numRows; i++) {
                order.add(matrix[--rowIdx][colIdx]);
            }
            if (--numCols == 0) {
                break;
            }
        }

        return order;
    }

    public static void main(String[] args) {
        SpiralMatrix sm = new SpiralMatrix();
        MatrixOps mo = new MatrixOps();

        // generate spiral matrix
        int[][] matrixG = sm.generateMatrix(3);
        mo.printMatrix(matrixG);

        // convert sprial matrix to list
        int[][] matrixA = {
                {0,0,5},
                {4,3,1},
                {0,1,4}};

        int[][] matrixB = {
                {0,0,0,5},
                {4,3,1,4},
                {0,1,1,4},
                {1,2,1,3},
                {0,0,1,1}};

        mo.printMatrix(matrixB);
        System.out.println(sm.spiralOrderDfs(matrixB).toString());
        System.out.println(sm.spiralOrderLoop(matrixB).toString());
    }
}
