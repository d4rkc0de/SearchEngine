
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to index.");
        }
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.run(args[0]);
    }
}