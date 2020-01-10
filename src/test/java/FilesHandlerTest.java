import org.junit.Test;

import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilesHandlerTest {

    @Test
    public void shouldGetFilesFromPath() {
        //Given
        String path = "src/main/resources";
        // When
        List<File> files = FilesHandler.getFilesFromPath(path);
        //Then
        assertEquals(files.size(), 2);
        assertEquals(files.get(0).getName(), "fileForTest1.txt");
        assertEquals(files.get(1).getName(), "fileForTest2.txt");
    }

    @Test
    public void shouldReadContentOfFile() {
        //Given
        String path = "src/main/resources/fileForTest1.txt";
        // When
        String content = FilesHandler.readContentOfFile(path);
        //Then
        assertEquals(content, "cat dog file i love my dog today was a great day hello how are you ? ");
    }
}
