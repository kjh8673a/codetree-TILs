import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int len = 3;
        int t = 0;
        while(len < N) {
            t++;
            len = len * 2 + 1 + (t + 2);
        }

        char c = findCharacter(N, len, t);
        System.out.println(c);
    }

    private static Character findCharacter(int N, int len, int t) {
        if(t == 0) {
            if(N == 1) {
                return 'm';
            }else {
                return 'o';
            }
        }

        int mid = 1 + (t + 2);
        int left = (N - mid) / 2;

        if(N <= left) {
            return findCharacter(N, left, t - 1);
        }else if(N > left && N <= left + mid) {
            if(len == left + 1) {
                return 'm';
            }else {
                return 'o';
            }
        }else {
            return findCharacter(N - mid + left, left, t - 1);
        }
    }

}