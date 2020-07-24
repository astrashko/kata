/*****
 * This is a test program with 5 lines of code
 *  \/* no nesting allowed!
 //*****/

import java.util.Arrays;
import java.util.stream.Collectors;

/***/// Slightly pathological comment ending...

public class Hello2 {
    public static final void main(String[] args) { // gotta love Java
        // Say hello
        System./*wait*/out./*for*/println/*it*/("Hello/*");
    }

    private String removeCommentSymbolsInQuotes(String s) {
        return Arrays.stream(s.split("\n"))
                .map(line -> {
                    int indexOpenQuote = line.indexOf("\"");
                    int indexCloseQuote = line.indexOf("\"", indexOpenQuote + 1);

                    if (indexOpenQuote != -1 && indexCloseQuote != -1) {
                        String quote = line.substring(indexOpenQuote, indexCloseQuote);
                        if (quote.contains("/*") && !quote.contains("*/")) {
                            return line.replace("/*", "");
                        } else if (!quote.contains("/*") && quote.contains("*/")) {
                            return line.replace("*/", "");
                        } else {
                            return line;
                        }
                    }

                    return line;
                })
                .collect(Collectors.joining("\n"));
    }

}