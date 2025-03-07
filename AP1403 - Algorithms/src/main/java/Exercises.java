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
        for (int i = 0; i < values.length; i++) { // we need index so no for each
            for (int j = i + 1; j < values.length; j++) {
                if (values[i] * values[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        //O9(n^2)

        return new int[0]; //BUG
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
        if (values == null || rows == 0 || cols == 0) {
            return new int[0];
        }

        int[] result = new int[rows * cols]; //result array
        int index = 0; // pointer

        int top = 0, bottom = rows - 1;
        int left = 0, right = cols - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) result[index++] = values[top][i];
            top++;

            for (int i = top; i <= bottom; i++) result[index++] = values[i][right];
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) result[index++] = values[bottom][i];
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) result[index++] = values[i][left];
                left++;
            }
        }

        return result;
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
        List<List<Integer>> partitions = new ArrayList<>();

        generatePartitions(n, n, new ArrayList<>(), partitions); // Solve :V

        // no changes in method's body :>
        int[][] result = new int[partitions.size()][];
        for (int i = 0; i < partitions.size(); i++) {
            List<Integer> partition = partitions.get(i);
            result[i] = new int[partition.size()];
            for (int j = 0; j < partition.size(); j++) {
                result[i][j] = partition.get(j);
            }
        }
        return result;
    }

    private void generatePartitions(int target, int max, List<Integer> current, List<List<Integer>> partitions) {
        if (target == 0) {
            // valid partition :> finish
            partitions.add(new ArrayList<>(current));
            return;
        }

        for (int i = Math.min(max, target); i >= 1; i--) {
            // Add the current number to the partition
            current.add(i); // and number to partition
            generatePartitions(target - i, i, current, partitions); //call again with remaining target & current max
            current.removeLast();
        }
    }

    public static void main(String[] args) {
    }
}
