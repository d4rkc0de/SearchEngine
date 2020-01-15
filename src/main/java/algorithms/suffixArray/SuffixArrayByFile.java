package algorithms.suffixArray;

import java.util.HashMap;
import java.util.Map;

public class SuffixArrayByFile {

    Map<String, SuffixArray> suffixArrayByFile = new HashMap<>();

    public Map<String, SuffixArray> getSuffixArrayByFile() {
        return suffixArrayByFile;
    }

    public void addFileContent(String fileName, String fileContent) {
        SuffixArray suffixArray = new SuffixArray(fileContent);
        suffixArrayByFile.put(fileName, suffixArray);
    }
}
