package local.hochguertel.javasourcetodrawio;

import java.io.FileNotFoundException;
import org.apache.commons.cli.ParseException;

/**
 * ToUML
 * <p>
 * Created on 29.10.17
 */
public class ToUMLDev {

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        final String[] args1 = {
                "--path-to-source=/Users/hochguertelto/03 Home/03 Privat/03 Projekte/03 Java/java-source-to-draw.io/Foo.java",
                "--write-output-to-file=Foo.xml"
        };
        final String[] args2 = {
                "--path-to-source=/Users/hochguertelto/03 Home/03 Privat/03 Projekte/03 Java/java-source-to-draw.io/src/main/java/local/hochguertel/javasourcetodrawio/ToUML.java",
                "--write-output-to-file=ToUML.xml"
        };
        ToUML.main(args2);
    }

}
