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

    private static Character findCharacter(int n, int len, int t) {
        if(t == 0) {
            if(n == 1) {
                return 'm';
            }else {
                return 'o';
            }
        }

        int mid = 1 + (t + 2);
        int left = (len - mid) / 2;

        if(n <= left) {
            return findCharacter(n, left, t - 1);
        }else if(n > left && n <= left + mid) {
            if(n == left + 1) {
                return 'm';
            }else {
                return 'o';
            }
        }else {
            return findCharacter(n - (mid + left), left, t - 1);
        }
    }

}