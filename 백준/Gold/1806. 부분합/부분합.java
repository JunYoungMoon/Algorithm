import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int solution(int[] nums, int target) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;  // 최소 길이를 저장, 초기값은 매우 큰 값

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];  // 오른쪽 포인터가 가리키는 값을 더함

            // 합이 target 이상이 되면, 왼쪽 포인터를 이동시켜가며 길이를 줄여봄
            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);  // 현재 길이를 최소값과 비교
                sum -= nums[left];  // 왼쪽 값을 빼고
                left++;  // 왼쪽 포인터를 오른쪽으로 이동
            }
        }

        // 최소 길이를 찾지 못한 경우는 0 반환
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 첫 번째 입력: 배열의 크기와 target 값
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());  // 배열의 크기
        int target = Integer.parseInt(st.nextToken());  // target 값

        // 두 번째 입력: 배열의 값들
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());  // 두 번째 입력을 받기 위해 새로운 StringTokenizer 생성
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());  // 배열에 값 입력
        }

        // solution 메서드를 호출하여 결과 출력
        int result = solution(nums, target);
        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
        br.close();
    }
}