package leetcode;

import utils.MatrixOps;

public class RotateImage {

    public void rotate(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;

        for (int row = 0; row < height-1; row++) {
            for (int col = row; col < width-row-1; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[height-col-1][row];
                matrix[height-col-1][row] = matrix[height-row-1][height-col-1];
                matrix[height-row-1][height-col-1] = matrix[col][height - row - 1];
                matrix[col][height - row - 1] = temp;
            }
        }
    }

    public static void main(String[] arg) {
        int[][] matrix = {  {5, 1, 9,11},
                            { 2, 4, 8,10},
                            {13, 3, 6, 7},
                            {15,14,12,16}};
        RotateImage ri = new RotateImage();
        MatrixOps matrixOps = new MatrixOps();
        matrixOps.printMatrix(matrix);
        ri.rotate(matrix);
        matrixOps.printMatrix(matrix);
    }

}
