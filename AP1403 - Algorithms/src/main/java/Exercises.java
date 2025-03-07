import java.util.ArrayList;
import java.util.List;
public class Exercises {

    /*
        there is an array of positive integers as input of function and another integer for the target value
        all the algorithm should do is to find those two integers in array which their multiplication is the target
        then it should return an array of their indices
        e.g. {1, 2, 3, 4} with target of 8 -> {1, 3}

        note: you should return the indices in ascending order and every array's solution is unique
    */
    public int[] productIndices(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (values[i] * values[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
    /*
        given a matrix of random integers, you should do spiral traversal in it
        e.g. if the matrix is as shown below:
        1 2 3
        4 5 6
        7 8 9
        then the spiral traversal of that is:
        {1, 2, 3, 6, 9, 8, 7, 4, 5}

        so you should walk in that matrix in a curl and then add the numbers in order you've seen them in a 1D array
    */
    public int[] spiralTraversal(int[][] values, int rows, int cols) {
        int[] n = new int[rows * cols];
        int x = 0;
        int c = 0, r = cols - 1, b = 0, p = rows - 1;

        while (c <= r && b <= p) {
            // Traverse from left to right
            for (int i = c; i <= r; i++) {
                n[x++] = values[b][i];
            }
            b++;

            // Traverse from top to bottom
            for (int i = b; i <= p; i++) {
                n[x++] = values[i][r];
            }
            r--;

            if (b <= p) {
                // Traverse from right to left
                for (int i = r; i >= c; i--) {
                    n[x++] = values[p][i];
                }
                p--;
            }

            if (c <= r) {
                // Traverse from bottom to top
                for (int i = p; i >= b; i--) {
                    n[x++] = values[i][c];
                }
                c++;
            }
        }
        return n;
    }

    /*
        integer partitioning is a combinatorics problem in discreet maths
        the problem is to generate sum numbers which their summation is the input number

        e.g. 1 -> all partitions of integer 3 are:
        3
        2, 1
        1, 1, 1

        e.g. 2 -> for number 4 goes as:
        4
        3, 1
        2, 2
        2, 1, 1
        1, 1, 1, 1

        note: as you can see in examples, we want to generate distinct summations, which means 1, 2 and 2, 1 are no different
        you should generate all partitions of the input number and

        hint: you can measure the size and order of arrays by finding the pattern of partitions and their number
        trust me, that one's fun and easy :)

        if you're familiar with lists and arraylists, you can also edit method's body to use them instead of array
    */
    public int[][] intPartitions(int n) {
        List<List<Integer>> r = new ArrayList<>();
        generatePartitions(n, n, new ArrayList<>(), r);

        int[][] p = new int[r.size()][];
        for (int i = 0; i < r.size(); i++) {
            p[i] = r.get(i).stream().mapToInt(Integer::intValue).toArray();
        }
        return p;
    }
    private void generatePartitions(int n, int max, List<Integer> current, List<List<Integer>> r) {
        if (n == 0) {
            r.add(new ArrayList<>(current));
            return;
        }
        for (int i = Math.min(max, n); i >= 1; i--) {
            current.add(i);
            generatePartitions(n - i, i, current, r);
            current.remove(current.size() - 1);
        }
    }



    public static void main(String[] args) {
        // you can test your code here
    }

}