class Solution {
    public int solution(int num1, int num2) {
        float value = ((float) num1 / num2) * 1000;
        return (int) Math.floor(value);
    }
}