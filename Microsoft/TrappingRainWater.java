import java.util.*;

public class TrappingRainWater {

    // Function to calculate trapped rain water
    public static int trap(int[] height) {
        int n = height.length;
        
        // If the array has less than 3 elements, no water can be trapped
        if (n < 3) return 0;

        // Arrays to store the maximum height to the left and right of each bar
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // Fill leftMax array
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // Fill rightMax array
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // Calculate the total water trapped
        int waterTrapped = 0;
        for (int i = 0; i < n; i++) {
            // Water trapped on each bar is the minimum of leftMax and rightMax minus the height of the bar
            waterTrapped += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return waterTrapped;
    }

    // Main method to test the function
    public static void main(String[] args) {
        int[] height = {4, 2, 0, 3, 2, 5};
        System.out.println("Total water trapped: " + trap(height));  // Output: 9
    }
}

