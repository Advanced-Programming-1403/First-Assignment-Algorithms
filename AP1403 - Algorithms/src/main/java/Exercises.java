import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercises {

    /*
        There is an array of positive integers as input of function and another integer for the target value.
        All the algorithm should do is to find those two integers in array which their multiplication is the target.
        Then it should return an array of their indices.
        e.g. {1, 2, 3, 4} with target of 8 -> {1, 3}

        Note: you should return the indices in ascending order and every array's solution is unique.
    */
    public int[] productIndices(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (values[i] * values[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{}; // Return empty array if no pair is found
    }

    /*
        Given a matrix of random integers, you should do spiral traversal in it.
        e.g. if the matrix is as shown below:
        1 2 3
        4 5 6
        7 8 9
        then the spiral traversal of that is:
        {1, 2, 3, 6, 9, 8, 7, 4, 5}

        So you should walk in that matrix in a curl and then add the numbers in order you've seen them in a 1D array.
    */
    public int[] spiralTraversal(int[][] values, int rows, int cols) {
        int[] result = new int[rows * cols];
        int index = 0;
        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;

        while (top <= bottom && left <= right) {
            // Traverse from left to right
            for (int i = left; i <= right; i++) {
                result[index++] = values[top][i];
            }
            top++;

            // Traverse from top to bottom
            for (int i = top; i <= bottom; i++) {
                result[index++] = values[i][right];
            }
            right--;

            // Traverse from right to left
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result[index++] = values[bottom][i];
                }
                bottom--;
            }

            // Traverse from bottom to top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result[index++] = values[i][left];
                }
                left++;
            }
        }

        return result;
    }

    /*
        Integer partitioning is a combinatorics problem in discrete maths.
        The problem is to generate sum numbers which their summation is the input number.

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

        Note: as you can see in examples, we want to generate distinct summations, which means 1, 2 and 2, 1 are no different.
        You should generate all partitions of the input number.

        Hint: you can measure the size and order of arrays by finding the pattern of partitions and their number.
        Trust me, that one's fun and easy :)

        If you're familiar with lists and ArrayLists, you can also edit the method's body to use them instead of array.
    */
    public int[][] intPartitions(int n) {
        List<int[]> partitions = new ArrayList<>();
        generatePartitions(n, n, new ArrayList<>(), partitions);

        // Convert List<int[]> to int[][]
        int[][] result = new int[partitions.size()][];
        for (int i = 0; i < partitions.size(); i++) {
            result[i] = partitions.get(i);
        }

        return result;
    }

    private void generatePartitions(int n, int max, List<Integer> current, List<int[]> result) {
        if (n == 0) {
            int[] partition = new int[current.size()];
            for (int i = 0; i < current.size(); i++) {
                partition[i] = current.get(i);
            }
            result.add(partition);
        } else {
            for (int i = Math.min(n, max); i >= 1; i--) {
                current.add(i);
                generatePartitions(n - i, i, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Exercises exercises = new Exercises();

        // Test productIndices
        int[] values = {1, 2, 3, 4};
        int target = 8;
        int[] indices = exercises.productIndices(values, target);
        System.out.println("Indices for product " + target + ": " + Arrays.toString(indices));

        // Test spiralTraversal
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] spiral = exercises.spiralTraversal(matrix, rows, cols);
        System.out.println("Spiral traversal: " + Arrays.toString(spiral));

        // Test intPartitions
        int n = 4;
        int[][] partitions = exercises.intPartitions(n);
        System.out.println("Partitions of " + n + ":");
        for (int[] partition : partitions) {
            System.out.println(Arrays.toString(partition));
        }
    }
}
