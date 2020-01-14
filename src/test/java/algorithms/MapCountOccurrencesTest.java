package algorithms;

import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapCountOccurrencesTest {

    MapCountOccurrences mapCountOccurrences = new MapCountOccurrences();

    @Test
    public void testIfcountsAreCorrect() {
        //Given
        String wordsToCount = "test1 test2 test1";
        // When
        Map<String, Integer> occurrencesCount = mapCountOccurrences.countOccurrences(wordsToCount);
        //Then
        assertTrue(occurrencesCount.containsKey("test1") && occurrencesCount.containsKey("test2")
                && occurrencesCount.get("test1").equals(2) && occurrencesCount.get("test2").equals(1));
    }

    @Test
    public void testIfWordsFromFileAreAddedToWordsByFile() {
        //Given
        String fileName = "fileForTest1.txt";
        File file = new File(
                getClass().getClassLoader().getResource(fileName).getFile()
        );
        // When
        mapCountOccurrences.addFileContent(file);
        //Then
        assertTrue(mapCountOccurrences.getWordsByFile().containsKey(fileName) &&
                mapCountOccurrences.getWordsByFile().get(fileName).containsKey("dog") &&
                mapCountOccurrences.getWordsByFile().get(fileName).get("dog").equals(2));
    }
}
