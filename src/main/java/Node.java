import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by astrashko on 21.07.2020.
 */
class Node {
    private File file;
    private List<Node> children;
    private int numberOfCodeLines;

    Node(File file) {
        this.file = file;
    }

    void addChild(Node node) {
        if(children == null) {
            children = new ArrayList<>();
        }

        children.add(node);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    List<Node> getChildren() {
        return children;
    }

    int getNumberOfCodeLines() {
        return numberOfCodeLines;
    }

    void setNumberOfCodeLines(int numberOfCodeLines) {
        this.numberOfCodeLines = numberOfCodeLines;
    }
}
