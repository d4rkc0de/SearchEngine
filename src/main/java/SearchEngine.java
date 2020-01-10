import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {

    private Map<String, Map<String, Integer>> wordsByFile = new HashMap<>();

    public Map<String, Map<String, Integer>> getWordsByFile() {
        return wordsByFile;
    }

    public void setWordsByFile(Map<String, Map<String, Integer>> wordsByFile) {
        this.wordsByFile = wordsByFile;
    }

    void run(String path) {
        init(path);
        while (true) {
            String line = readLine();
            String[] wordsToSearchFor = line.split(" ");
            Map<String, Double> scores = getScores(wordsToSearchFor);
            for (String fileName : scores.keySet()) {
                System.out.println(fileName + ":" + scores.get(fileName));
            }
        }
    }

    Map<String, Double> getScores(String[] wordsToSearchFor) {
        Map<String, Double> scores = new HashMap<>();
        double partScore = 100. / wordsToSearchFor.length;
        for (String fileName : wordsByFile.keySet()) {
            double score = 0.0;
            Map<String, Integer> countByWord = wordsByFile.get(fileName);
            int occurences = 0;
            for (String wordToSearchFor : wordsToSearchFor) {
                if (countByWord.containsKey(wordToSearchFor)) {
                    score += partScore;
                    occurences += countByWord.get(wordToSearchFor) - 1; // effect 1 / 100
                }
            }
            score += occurences;
            scores.put(fileName, score);
        }
        return scores.entrySet().stream().limit(10)
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    private String readLine() {
        System.out.print("search> ");
        StringTokenizer tok = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (tok == null || !tok.hasMoreTokens()) {
            try {
                tok = new StringTokenizer(in.readLine());
            } catch (IOException e) {
                System.err.println("Error while reading from input: " + e.getMessage());
                return null;
            }
        }
        return tok.nextToken();
    }

    void init(String path) {
        List<File> files = FilesHandler.getFilesFromPath(path);
        for (File file : files) {
            handleFile(file);
        }
    }

    void handleFile(File file) {
        String fileContent = FilesHandler.readContentOfFile(file.getAbsolutePath());
        String fileName = file.getName();
        for (String word : fileContent.split(" ")) {
            addWordToWordsByFile(fileName, word);
        }
    }

    void addWordToWordsByFile(String fileName, String word) {
        if (!wordsByFile.containsKey(fileName)) {
            wordsByFile.put(fileName, new HashMap<>());
        }
        Map<String, Integer> countByWord = wordsByFile.get(fileName);
        if (!countByWord.containsKey(word)) {
            countByWord.put(word, 0);
        }
        countByWord.put(word, countByWord.get(word) + 1);
    }


}
