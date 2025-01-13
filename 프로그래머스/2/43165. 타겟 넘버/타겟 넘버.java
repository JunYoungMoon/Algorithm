class Solution {
    public static int dfs(int[] nums, int target, int index, int currentSum) {
        if (index == nums.length) {
            return currentSum == target ? 1 : 0;
        }

        return dfs(nums, target, index + 1, currentSum + nums[index])
                + dfs(nums, target, index + 1, currentSum - nums[index]);
    }
    
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }
}