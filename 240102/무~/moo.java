import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String s = "moo";
        String mid = "moo";
        while(s.length() < N) {
            mid += "o";
            s = s + mid + s;
        }

        System.out.println(s.charAt(N - 1));
    }

}