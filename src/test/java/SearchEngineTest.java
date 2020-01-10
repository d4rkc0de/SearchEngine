import org.junit.Before;
import org.junit.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchEngineTest {

    private SearchEngine searchEngine = new SearchEngine();

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testIfWordIsAddedToWordsByFile() {
        //Given
        String fileName = "fileForTest1.txt"
                , word = "test";
        // When
        searchEngine.addWordToWordsByFile(fileName, word);
        //Then
        assertTrue(searchEngine.getWordsByFile().containsKey(fileName) &&
                searchEngine.getWordsByFile().get(fileName).containsKey(word));
    }

    @Test
    public void testIfWordsFromFileAreAddedToWordsByFile() {
        //Given
        String fileName = "fileForTest1.txt";
        File file = new File(
                getClass().getClassLoader().getResource(fileName).getFile()
        );
        // When
        searchEngine.handleFile(file);
        //Then
        assertTrue(searchEngine.getWordsByFile().containsKey(fileName) &&
                searchEngine.getWordsByFile().get(fileName).containsKey("dog") &&
                searchEngine.getWordsByFile().get(fileName).get("dog").equals(2));
    }

    @Test
    public void testIfScoresAreCorrect() {
        //Given
        String fileName1 = "fileForTest1.txt";
        String fileName2 = "fileForTest2.txt";
        String path = "src/main/resources";
        String[] wordsToSearchFor = {"dog", "hello"};
        // When
        searchEngine.init(path);
        //Then
        assertTrue(searchEngine.getScores(wordsToSearchFor).get(fileName1).equals(101.0)
                    && searchEngine.getScores(wordsToSearchFor).get(fileName2).equals(50.0));
    }
}
