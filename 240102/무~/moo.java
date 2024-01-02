import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // moo의 길이와 현재 몇단계인지 구한다.
        int len = 3;
        int t = 0;
        while(len < N) {
            t++;
            len = len * 2 + 1 + (t + 2);
        }
        
        System.out.println(findCharacter(N, len, t));
    }

    private static Character findCharacter(int n, int len, int t) {
        if(t == 0) {
            return n == 1 ? 'm' : 'o';
        }

        // 왼쪽, 중간, 오른쪽 세 부분으로 나눠 처리.
        int mid = 1 + (t + 2);
        int left = (len - mid) / 2;

        if(n <= left) {
            return findCharacter(n, left, t - 1);
        }else if(n > left && n <= left + mid) {
            return n == left + 1 ? 'm' : 'o';
        }else {
            return findCharacter(n - (mid + left), left, t - 1);
        }
    }

}