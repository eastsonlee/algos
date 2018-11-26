package leetcode;

import utils.MatrixOps;

public class SetMatrixZeros {
    public void setZeroes(int[][] matrix) {
        /*
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int i : rows) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }

        for (int j : cols) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][j] = 0;
            }
        }
        */

        boolean isFirstRowZero = false;
        boolean isFirstColZero = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        isFirstRowZero = true;
                    }
                    if (j == 0) {
                        isFirstColZero = true;
                    }
                    if (i != 0 && j != 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (isFirstRowZero) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }

        if (isFirstColZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] arg) {
        SetMatrixZeros smz = new SetMatrixZeros();
        int[][] matrix = {
                {0,0,0,5},
                {4,3,1,4},
                {0,1,1,4},
                {1,2,1,3},
                {0,0,1,1}};

        MatrixOps mo = new MatrixOps();
        mo.printMatrix(matrix);

        smz.setZeroes(matrix);
        mo.printMatrix(matrix);
    }
}
