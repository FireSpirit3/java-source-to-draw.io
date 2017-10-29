package local.hochguertel.javasourcetodrawio;

import java.io.FileNotFoundException;
import java.util.Arrays;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import local.hochguertel.javasourcetodrawio.components.commandlinerunner.Commandlinerunner;
import local.hochguertel.javasourcetodrawio.components.drawiodiagram.Drawiodiagram;
import local.hochguertel.javasourcetodrawio.components.drawiodiagram.DrawiodiagramImpl;
import local.hochguertel.javasourcetodrawio.components.javasource.ClassOrInterfaceRepresentationCollector;
import local.hochguertel.javasourcetodrawio.components.javasource.JavaSourceService;
import local.hochguertel.javasourcetodrawio.components.javasource.JavaSourceServiceImpl;
import local.hochguertel.javasourcetodrawio.domain.drawio.MxGraphProxy;
import local.hochguertel.javasourcetodrawio.domain.drawio.UmlClassShape;

/**
 * ToUML
 * <p>
 * Created on 29.10.17
 */
public class ToUML {

    private final static Logger logger = LoggerFactory.getLogger(ToUML.class);
    private static Commandlinerunner clir = new Commandlinerunner();

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        logger.info("Given commandline-parameter: \n{}", Arrays.toString(args));
        ClassOrInterfaceRepresentationCollector classCollector = new ClassOrInterfaceRepresentationCollector();
        clir.call(new String[] {
                "--path-to-source=/Users/hochguertelto/03 Home/03 Privat/03 Projekte/03 Java/java-source-to-draw.io/Foo.java",
                "--write-output-to-file=Foo.xml"
        });

        JavaSourceService javaSourceService = new JavaSourceServiceImpl(clir.getSourcePath(), classCollector);

        Drawiodiagram drawiodiagram = new DrawiodiagramImpl(javaSourceService, new MxGraphProxy());
        UmlClassShape classDiagram = drawiodiagram.getDiagram();
    }

}
