import java.io.File;
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

    int countLines(String pathToFile) {
        String content;
        try {
            content = readFileIntoString(pathToFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        content = content.replaceAll("\\\\\"", "");
        content = removeFromString(content, "\"", "\"");
        content = removeFromString(content, "/*", "*/");

        return (int) Arrays.stream(content.split("\n"))
                .map(String::trim)
                .filter(s -> skipPredicates.stream().allMatch(predicate -> predicate.test(s)))
                .count();
    }

    private String removeFromString(String s, String openStr, String closeStr) {
        StringBuilder builder = new StringBuilder(s);

        while (true) {
            int openBlockIndex = builder.indexOf(openStr);
            int closeBlockIndex;

            if (openStr.equals(closeStr)) {
                closeBlockIndex = builder.indexOf(closeStr, openBlockIndex + 1);
            } else {
                closeBlockIndex = builder.indexOf(closeStr);
            }

            if (openBlockIndex == -1 || closeBlockIndex == -1) {
                break;
            }

            builder.replace(openBlockIndex, closeBlockIndex + closeStr.length(), "");
        }

        return builder.toString();
    }


    public static void main(String[] args) {
        CodeLines codeLines = new CodeLines();

        if (args.length != 1) {
            System.out.println("Please run program in a such way:java CodeLines pathToFIle/pathToFolder");
            System.exit(1);
        }

        File file = new File(args[0]);

        if (file.isDirectory()) {
            Tree tree = new Tree();
            Node root = new Node(null);
            tree.buildTree(file, root);

            root = root.getChildren().get(0);

            tree.printTree(root, "");
        } else {
            System.out.println(file.getName() + ":" + codeLines.countLines(args[0]));
        }
    }
}
