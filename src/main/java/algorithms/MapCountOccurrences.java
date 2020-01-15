package algorithms;

import java.util.HashMap;
import java.util.Map;

public class MapCountOccurrences {

    private Map<String, Map<String, Integer>> wordsByFile = new HashMap<>();

    public Map<String, Map<String, Integer>> getWordsByFile() {
        return wordsByFile;
    }

    public Map<String, Integer> countOccurrences(String content) {
        Map<String, Integer> result = new HashMap<>();
        for (String word : content.split(" ")) {
            if (!result.containsKey(word))
                result.put(word, 1);
            else
                result.put(word, result.get(word) + 1);
        }
        return result;
    }

    public void addFileContent(String fileName, String fileContent) {
        Map<String, Integer> occurrencesCount = countOccurrences(fileContent);
        wordsByFile.put(fileName, occurrencesCount);
    }
}
