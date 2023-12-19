import java.io.*;
import java.util.*;

public class Main {
    static class Iceberg {
        int idx;
        int height;

        public Iceberg(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TreeSet<Integer> heightSet = new TreeSet<>(Collections.reverseOrder());
        Queue<Iceberg> icebergQueue = new LinkedList<>();
        for(int i = 1; i < N + 1; i++) {
            int height = Integer.parseInt(br.readLine());
            heightSet.add(height);
            icebergQueue.add(new Iceberg(i, height));
        }

        int answer = 0;

        boolean[] peak = new boolean[N + 2];
        int count = 0;
        for(int height : heightSet) {
            int queueSize = icebergQueue.size();

            for(int i = 0; i < queueSize; i++) {
                Iceberg iceberg = icebergQueue.poll();
                
                if(iceberg.height - height <= 0) {
                    icebergQueue.add(iceberg);
                    continue;
                }

                peak[iceberg.idx] = true;
                if(!peak[iceberg.idx - 1] && !peak[iceberg.idx + 1]) {
                    count++;
                }else if(peak[iceberg.idx - 1] && peak[iceberg.idx + 1]) {
                    count--;
                }
            }

            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }
}