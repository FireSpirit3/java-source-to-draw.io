package local.hochguertel.javasourcetodrawio;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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

    public static void main(String[] args) throws ParseException, IOException, com.github.javaparser.ParseException {
        logger.info("Given commandline-parameter: \n{}", Arrays.toString(args));
        ClassOrInterfaceRepresentationCollector classCollector = new ClassOrInterfaceRepresentationCollector();
        final boolean cliSuccess = clir.call(args);

        if (!cliSuccess) {
            System.out.println("\n");
            clir.printHelp();
            return;
        }
        JavaSourceService javaSourceService = new JavaSourceServiceImpl(clir.getSourcePath(), classCollector);

        Drawiodiagram drawiodiagram = new DrawiodiagramImpl(javaSourceService, new MxGraphProxy());
        List<UmlClassShape> classDiagram = drawiodiagram.getDiagram();
    }

}
