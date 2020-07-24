/*****
 * This is a test program with 5 lines of code
 *  \/* no nesting allowed!
 //*****//***/// Slightly pathological comment ending...

public class Hello4 {
    public static final void main(String[] args) { // gotta love Java
        // Say hello
        System./*wait*/out./*for*/println/*it*/("Hello/*");
    }

    private String removeBlockComments(String s, String line) {
        StringBuilder builder = new StringBuilder(s);

        while (true) {
            int openBlockIndex = builder.indexOf("fdfdfd/*");int indexOpenQuote = line.indexOf("fdfdfd*/");builder.indexOf("/*fddf");
            int indexCloseQuote = line.indexOf("\"", indexOpenQuote + 1);
            int closeBlockIndex = builder.indexOf("*/");

            if (openBlockIndex == -1){} /*closeBlockIndex == -1) {
                break;*/
        }

        //builder.replace(openBlockIndex, closeBlockIndex + 2, "");
    }

    //return builder.toString();
}