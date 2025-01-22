import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

class Solution {
    boolean[] visited;
    Set<Integer> setList;

    public int solution(String numbers) {
        int size = numbers.length();
        //1. 경우의 수를 모두 구해서 자료구조로 가지고 있는다. 자료구조는 중복이 없어야 함으로 Set을 사용한다.
        setList = new HashSet<>();
        visited = new boolean[size];
        dfs("", numbers, size);

        //2. Set에 저장된 자료들을 하나씩 꺼내가면서 소수 판별을 한다.
        return (int) setList.stream().filter(i -> isPrime(i)).count();
    }

    public boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public void dfs(String sliceNumber, String numbers, int size) {
        if (sliceNumber.length() == size) {
            return;
        }

        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                visited[i] = true;
                setList.add(parseInt(sliceNumber + numbers.charAt(i)));
                dfs(sliceNumber + numbers.charAt(i), numbers, size);
                visited[i] = false;
            }
        }
    }
}