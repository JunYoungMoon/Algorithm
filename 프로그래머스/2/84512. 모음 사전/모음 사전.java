class Solution {
    char[] charArr = new char[]{'A', 'E', 'I', 'O', 'U'};
    int count;

    public int solution(String word) {
        recursive("", word); // 재귀 호출로 탐색
        return count; // 최종 카운트 반환
    }

    public boolean recursive(String current, String target) {
        count++;
        for (char c : charArr) {
            if((current + c).length() > charArr.length){
                return false;
            }

            if((current + c).equals(target)){
                return true;
            }

            if(recursive(current + c, target)){
                return true;
            }
        }
        return false;
    }
}
