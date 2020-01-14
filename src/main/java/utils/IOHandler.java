package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOHandler {

    public static String readLine() {
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
}
