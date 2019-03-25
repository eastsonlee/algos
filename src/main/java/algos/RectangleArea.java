package algos;

public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int width = intersectRange(A, C, E, G);
        int height = intersectRange(B, D, F, H);
        return rectangleArea(A, B, C, D) + rectangleArea(E, F, G, H) - width * height;
    }

    private int rectangleArea(int leftX, int leftY, int rightX, int rightY) {
        return (rightX - leftX) * (rightY - leftY);
    }

    private int intersectRange(int startA, int endA, int startB, int endB) {
        if (startA > startB) {
            return intersectRange(startB, endB, startA, endA);
        }

        if (endA > startB) {
            return Math.min(endA, endB) - startB;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        RectangleArea result = new RectangleArea();
        System.out.println(result.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }
}
