package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FilesHandler {

    public static List<File> getFilesFromPath(String path) {
        List<File> files = new ArrayList<>();
        final File indexableDirectory = new File(path);
        for (File file : Objects.requireNonNull(indexableDirectory.listFiles())) {
            if (file.isFile()) {
                files.add(file);
            }
        }
        return files;
    }

    public static String readContentOfFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append(" "));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
