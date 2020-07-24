import java.io.File;
import java.util.Objects;

/**
 * Created by astrashko on 21.07.2020.
 */
class Tree {
    private CodeLines codeLines = new CodeLines();

    int buildTree(File file, Node current) {
        Node node = new Node(file);
        current.addChild(node);
        if (file.isDirectory()) {
            int numberOfLines = 0;
            for (File child : Objects.requireNonNull(file.listFiles())) {
                numberOfLines += buildTree(child, node);
            }
            node.setNumberOfCodeLines(numberOfLines);
            return numberOfLines;
        } else {
            int numberOfLines = codeLines.countLines(file.getAbsolutePath());
            node.setNumberOfCodeLines(numberOfLines);
            return numberOfLines;
        }
    }

    void printTree(Node node, String shift) {
        System.out.println(shift + node.getFile().getName() + ":" + node.getNumberOfCodeLines());
        if (node.getChildren() != null) {
            for (Node child : node.getChildren()) {
                printTree(child, shift + "\t");
            }
        }
    }
}
