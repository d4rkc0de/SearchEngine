import algorithms.MapCountOccurrences;
import utils.FilesHandler;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static utils.IOHandler.readLine;

public class SearchEngine {


    private MapCountOccurrences mapCountOccurrences = new MapCountOccurrences();

    void run(String path) {
        preProcessing(path);
        while (true) {
            String line = readLine();
            String[] wordsToSearchFor = line.split(" ");
            Map<String, Double> scores = getScoresUsingMapCountOccurrences(wordsToSearchFor);
            for (String fileName : scores.keySet()) {
                System.out.println(fileName + ":" + scores.get(fileName));
            }
        }
    }

    Map<String, Double> getScoresUsingMapCountOccurrences(String[] wordsToSearchFor) {
        Map<String, Double> scores = new HashMap<>();
        double partScore = 100. / wordsToSearchFor.length;
        for (String fileName : mapCountOccurrences.getWordsByFile().keySet()) {
            double score = 0.0;
            Map<String, Integer> countByWord = mapCountOccurrences.getWordsByFile().get(fileName);
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

    void preProcessing(String path) {
        List<File> files = FilesHandler.getFilesFromPath(path);
        for (File file : files) {
            // Occurrences count
            mapCountOccurrences.addFileContent(file);

            // Suffix Array
            // TODO
        }
    }

}
