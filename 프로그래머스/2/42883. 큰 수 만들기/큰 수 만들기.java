class Solution {
    public String solution(String number, int k) {
        StringBuilder stack = new StringBuilder();
        int removeCount = 0;

        for (char num : number.toCharArray()) {
            while (stack.length() > 0 && removeCount < k && stack.charAt(stack.length() - 1) < num) {
                stack.deleteCharAt(stack.length() - 1);
                removeCount++;
            }
            stack.append(num);
        }

        return stack.substring(0, number.length() - k);
    }
}