package local.hochguertel.javasourcetodrawio;

import java.io.IOException;
import org.apache.commons.cli.ParseException;

/**
 * ToUML
 * <p>
 * Created on 29.10.17
 */
public class ToUMLDev {

    public static void main(String[] args) throws ParseException, IOException, com.github.javaparser.ParseException {
        final String[] args1 = {
                "--path-to-source=/Users/hochguertelto/03 Home/03 Privat/03 Projekte/03 Java/java-source-to-draw.io/Foo.java",
                "--write-output-to-file=Foo.xml"
        };
        final String[] args2 = {
                "--path-to-source=/Users/hochguertelto/03 Home/03 Privat/03 Projekte/03 Java/java-source-to-draw.io/src/main/java/local/hochguertel/javasourcetodrawio/ToUML.java",
                "--write-output-to-file=ToUML.xml"
        };
        final String[] args3 = {
                "--path-to-source=/Users/hochguertelto/03 Home/03 Privat/03 Projekte/03 Java/java-source-to-draw.io/TestCases/uml-parser-test-1",
                "--write-output-to-file=um-parser-test-1.xml"
        };
        //        ToUML.main(args1);
        ToUML.main(args3);
    }

}
