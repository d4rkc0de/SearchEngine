import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchEngineTest {

    private SearchEngine searchEngine = new SearchEngine();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testIfScoresUsingMapCountOccurrencesAreCorrect() {
        //Given
        String fileName1 = "fileForTest1.txt";
        String fileName2 = "fileForTest2.txt";
        String path = "src/main/resources";
        String[] wordsToSearchFor = {"dog", "hello"};
        // When
        searchEngine.preProcessing(path);
        //Then
        assertTrue(searchEngine.getScoresUsingMapCountOccurrences(wordsToSearchFor).get(fileName1).equals(101.0)
                && searchEngine.getScoresUsingMapCountOccurrences(wordsToSearchFor).get(fileName2).equals(50.0));
    }
}
