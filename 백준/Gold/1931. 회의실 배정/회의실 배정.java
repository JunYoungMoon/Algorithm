import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static int solution(PriorityQueue<int[]> pq) {
        int[] firstMeeting = pq.poll();
        int lastEndTime = firstMeeting[1];
        int count = 1;

        while (!pq.isEmpty()) {
            int[] meeting = pq.poll();
            int startTime = meeting[0];
            int endTime = meeting[1];

            if (startTime >= lastEndTime) {
                count++;
                lastEndTime = endTime;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            } else {
                return Integer.compare(a[0], b[0]);
            }
        });

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            int arr0 = Integer.parseInt(st.nextToken());
            int arr1 = Integer.parseInt(st.nextToken());
            pq.add(new int[]{arr0, arr1});
        }

        int result = solution(pq);

        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
        br.close();
    }
}