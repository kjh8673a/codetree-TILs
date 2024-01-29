import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        List<String> wordList = new ArrayList<>();
        Map<String, Integer> wordMap = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            String word = br.readLine();
            wordList.add(word);
            wordMap.put(word, i);
        }
        Collections.sort(wordList, (o1, o2) -> o1.compareTo(o2));

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            String keyword = st.nextToken();

            int index = -1;
            for(int j = 0; j < wordList.size(); j++) {
                if(wordList.get(j).startsWith(keyword)) {
                    index = j;
                    break;
                }
            }

            if(wordList.size() < index + k || index == -1) {
                sb.append(-1).append("\n");
            }else if(!wordList.get(index + k - 1).startsWith(keyword)) {
                sb.append(-1).append("\n");
            }else {
                sb.append(wordMap.get(wordList.get(index + k - 1))).append("\n");
            }
        }

        System.out.println(sb);
    }
}