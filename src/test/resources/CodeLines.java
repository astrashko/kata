import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by astrashko on 15.07.2020.
 */
public class CodeLines {
    private List<Predicate<String>> skipPredicates = new ArrayList<>();

    {
        skipPredicates.add(s -> !s.startsWith("//"));
        skipPredicates.add(s -> !s.isEmpty());
    }

    private String readFileIntoString(String pathToFile) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(pathToFile), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s).append("\n"));
        }

        return builder.toString();
    }

    private int countLines(String pathToFile) throws IOException {
        String content = readFileIntoString(pathToFile);

        content = removeBlockComments(content);

        return (int) Arrays.stream(content.split("\n"))
                .map(String::trim)
                .filter(s -> skipPredicates.stream().allMatch(predicate -> predicate.test(s)))
                .count();
    }

    private String removeBlockComments(String s) {
        StringBuilder builder = new StringBuilder(s);
        while (true) {
            int openBlockIndex = builder.indexOf("/*");
            int closeBlockIndex = builder.indexOf("*/");

            if (openBlockIndex == -1 || closeBlockIndex == -1) {
                break;
            }

            builder.replace(openBlockIndex, closeBlockIndex + 2, "");
        }

        return builder.toString();
    }

    public static void main(String[] args) throws IOException {
        CodeLines codeLines = new CodeLines();

        System.out.println(args[0] + ":" + codeLines.countLines(args[0]));
    }
}
