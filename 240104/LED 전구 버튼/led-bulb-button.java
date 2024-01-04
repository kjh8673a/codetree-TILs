import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] lightBulbs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        lightBulbs = new int[N];
        for(int i = 0; i < N; i++) {
            lightBulbs[i] = Integer.parseInt(br.readLine());
        }

        ArrayList<Integer> dp = new ArrayList<>();
        int now = toBit();
        dp.add(now);

        int point = 0;
        for(int i = 0; i < B; i++) {
            pushButton();
            now = toBit();

            if(dp.contains(now)) {
                break;
            }
            dp.add(now);
            point++;
        }

        if(point != B) {
            int mod = (B - point) % dp.size();
            String answerBit = Integer.toBinaryString(mod);
            for(int i = 0; i < N; i++) {
                lightBulbs[i] = answerBit.charAt(i) == '1' ? 1 : 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(lightBulbs[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static int toBit() {
        int tmp = 0;

        int idx = 0;
        for(int i = N - 1; i >= 0; i--) {
            if(lightBulbs[i] == 1) {
                tmp += (1 << idx);
            }
            idx++;
        }

        return tmp;
    }

    private static void pushButton() {
        int[] tmp = new int[N];
        if(lightBulbs[N - 1] == 1) {
            tmp[0] = Math.abs(lightBulbs[0] - 1);
        }else {
            tmp[0] = lightBulbs[0];
        }

        for(int i = 1; i < N; i++) {
            if(lightBulbs[i - 1] == 1) {
                tmp[i] = Math.abs(lightBulbs[i] - 1);
            }else {
                tmp[i] = lightBulbs[i];
            }
        }

        lightBulbs = tmp;
    }
}