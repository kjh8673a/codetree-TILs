import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();

        while(s.contains(t)) {
            s = s.replaceAll(t, "");
        }

        System.out.println(s);
    }
}