import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
/**
 * Created by astrashko on 15.07.2020.
 */
class CodeLinesTest {
    private  CodeLines codeLines = new CodeLines();

    @Test
    void countLinesHashSet() {
        assertEquals(98, codeLines.countLines(getPathHelper("HashSet.java")));
    }

    @Test
    void countLinesCodeLines() {
        assertEquals(47, codeLines.countLines(getPathHelper("CodeLines.java")));
    }

    @Test
    void countLinesDave() {
        assertEquals(3, codeLines.countLines(getPathHelper("Dave.java")));
    }

    @Test
    void countLinesHello() {
        assertEquals(5, codeLines.countLines(getPathHelper("Hello.java")));
    }


    @Test
    void countLinesFilterReader() {
        assertEquals(32, codeLines.countLines(getPathHelper("FilterReader.java")));
    }

    @Test
    void countLinesLinkedList() {
        assertEquals(606, codeLines.countLines(getPathHelper("LinkedList.java")));
    }

    @Test
    void countLinesHello2() {
        assertEquals(26, codeLines.countLines(getPathHelper("Hello2.java")));
    }

    @Test
    void countLinesHello3() {
        assertEquals(18, codeLines.countLines(getPathHelper("Hello3.java")));
    }

    @Test
    void countLinesCodeLines2() {
        assertEquals(54, codeLines.countLines(getPathHelper("CodeLines2.java")));
    }

    @Test
    void countLinesHello4() {
        assertEquals(14, codeLines.countLines(getPathHelper("Hello4.java")));
    }

    @Test
    void countLinesThread() {
        assertEquals(541, codeLines.countLines(getPathHelper("Thread.java")));
    }

    private String getPathHelper(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        return file.getAbsolutePath();
    }
}