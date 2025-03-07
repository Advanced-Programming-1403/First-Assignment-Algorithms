import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Exercises {

    public int[] productIndices(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (values[i] * values[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public int[]  spiralTraversal(int[][] values, int rows, int cols) {
        int[] result = new int[rows * cols];
        int index = 0;
        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++){
                result[index++] = values[top][i];
            }
            top++;

            if (top <= bottom) {
                for (int i = top; i <= bottom; i++) {
                    result[index++] = values[i][right];
                }
                right--;
            }

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result[index++] = values[bottom][i];
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--){
                    result[index++] = values[i][left];
                }
                left++;
            }
        }
        return result;
    }

    public static int[][] intPartitions(int n) {
        List<int[]> parts = new ArrayList<>();
        int[] partition = new int[n];
        int index = 0;

        partition[index] = n;

        while (true) {
            int[] validParts = Arrays.copyOf(partition, index + 1);
            parts.add(validParts);

            int remaining = 0;
            while (index >= 0 && partition[index] == 1) {
                remaining += partition[index];
                index--;
            }

            if (index < 0) {
                break;
            }

            partition[index]--;
            remaining++;

            while (remaining > partition[index]) {
                partition[index + 1] = partition[index];
                remaining -= partition[index];
                index++;
            }

            partition[index + 1] = remaining;
            index++;
        }

        int[][] result = new int[parts.size()][];
        for (int i = 0; i < parts.size(); i++) {
            result[i] = parts.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        // you can test your code here
    }
}

