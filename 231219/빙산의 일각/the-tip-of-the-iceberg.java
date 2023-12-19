import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] iceberg = new int[N + 2];
        TreeSet<Integer> heightSet = new TreeSet<>(Collections.reverseOrder());
        Queue<Integer> icebergIndex = new LinkedList<>();
        for(int i = 1; i < N + 1; i++) {
            iceberg[i] = Integer.parseInt(br.readLine());
            heightSet.add(iceberg[i]);
            icebergIndex.add(i);
        }

        int answer = 0;

        boolean[] peak = new boolean[N + 2];
        int count = 0;
        for(int height : heightSet) {
            int queueSize = icebergIndex.size();

            for(int i = 0; i < queueSize; i++) {
                int idx = icebergIndex.poll();
                if(iceberg[idx] - height <= 0) {
                    icebergIndex.add(idx);
                    continue;
                }

                peak[idx] = true;
                if(!peak[idx - 1] && !peak[idx + 1]) {
                    count++;
                }else if(peak[idx - 1] && peak[idx + 1]) {
                    count--;
                }
            }

            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }
}