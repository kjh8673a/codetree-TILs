import java.io.*;
import java.util.*;
import java.util.stream.*;

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

            List<String> keywordList = wordList.stream().filter(word -> word.startsWith(keyword)).collect(Collectors.toList());
            if(keywordList.size() < k) {
                sb.append(-1).append("\n");
            }else {
                sb.append(wordMap.get(keywordList.get(k - 1))).append("\n");
            }
        }

        System.out.println(sb);
    }
}