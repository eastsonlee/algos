package algos;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        /*
        List<List<Integer>> pascalTriangle = new ArrayList<>();
        List<Integer> prevRow = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> currRow = new ArrayList<>();
            currRow.add(1);
            for (int j = 0; j < prevRow.size(); j++) {
                if (j < prevRow.size() - 1) {
                    currRow.add(prevRow.get(j) + prevRow.get(j+1));
                } else {
                    currRow.add(1);
                }
            }
            pascalTriangle.add(currRow);
            prevRow = currRow;
        }
        */

        List<List<Integer>> pascalTriangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            pascalTriangle.add(new ArrayList<>());
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    pascalTriangle.get(i).add(1);
                } else {
                    pascalTriangle.get(i).add(pascalTriangle.get(i-1).get(j-1)
                            + pascalTriangle.get(i-1).get(j));
                }
            }
        }
        return pascalTriangle;
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> prevRow = new ArrayList<>();
        List<Integer> currRow = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            currRow = new ArrayList();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    currRow.add(1);
                } else {
                    currRow.add(prevRow.get(j - 1) + prevRow.get(j));
                }
            }
            prevRow = currRow;
        }
        return currRow;
    }

    public static void main(String[] arg){
        PascalTriangle pt = new PascalTriangle();
        System.out.println(pt.generate(0).toString());
        System.out.println(pt.generate(1).toString());
        System.out.println(pt.generate(5).toString());

        System.out.println(pt.getRow(0).toString());
        System.out.println(pt.getRow(1).toString());
        System.out.println(pt.getRow(5).toString());
    }
}
