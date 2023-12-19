import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] iceberg = new int[N + 2];
        TreeSet<Integer> heightSet = new TreeSet<>(Collections.reverseOrder());
        for(int i = 1; i < N + 1; i++) {
            iceberg[i] = Integer.parseInt(br.readLine());
            heightSet.add(iceberg[i]);
        }

        int answer = 0;

        boolean[] peak = new boolean[N + 2];
        int count = 0;
        for(int height : heightSet) {
            for(int i = 1; i < N + 1; i++) {
                if(peak[i] || iceberg[i] - height <= 0) {
                    continue;
                }

                peak[i] = true;
                if(!peak[i - 1] && !peak[i + 1]) {
                    count++;
                }else if(peak[i - 1] && peak[i + 1]) {
                    count--;
                }
            }

            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }
}