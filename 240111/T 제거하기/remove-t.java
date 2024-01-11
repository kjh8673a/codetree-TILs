import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();
        char tail = t.charAt(t.length() - 1);

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            stack.add(s.charAt(i));
            
            if(stack.size() >= t.length() && stack.peek() == tail) {
                String tmp = "";
                for(int j = 0; j < t.length(); j++) {
                    tmp = stack.pop() + tmp;
                }

                if(!tmp.equals(t)) {
                    for(int j = 0; j < t.length(); j++) {
                        stack.add(tmp.charAt(j));
                    }
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.reverse().toString());
    }
}