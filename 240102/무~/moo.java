import java.io.*;

public class Main {
    static String s;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        s = "moo";
        int t = 1;
        while(s.length() < N) {
            makeMoo(t++);
        }

        System.out.println(s.charAt(N - 1));
    }

    private static void makeMoo(int t) {
        String tmp = s;
        tmp += "m";
        for(int i = 0; i < t + 2; i++) {
            tmp += "o";
        }
        tmp += s;

        s = tmp;
    }
}