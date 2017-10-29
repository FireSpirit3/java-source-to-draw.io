package local.hochguertel.javasourcetodrawio;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import local.hochguertel.javasourcetodrawio.components.javasource.ClassOrInterfaceRepresentationCollector;
import local.hochguertel.javasourcetodrawio.components.javasource.JavaSourceServiceImpl;
import local.hochguertel.javasourcetodrawio.domain.OutputFile;
import local.hochguertel.javasourcetodrawio.domain.SourcePath;
import local.hochguertel.javasourcetodrawio.domain.javasource.ClassRepresentation;

/**
 * ToUML
 * <p>
 * Created on 29.10.17
 */
public class ToUML {

    private static final String CLI_SOURCE_PATH = "path-to-source";
    private static final String CLI_OUTPUT_FILENAME = "write-output-to-file";
    private final static Logger logger = LoggerFactory.getLogger(ToUML.class);
    private final static CommandLineParser parser = new DefaultParser();
    private final static Options options = new Options();
    private static SourcePath sourcePath;
    private static OutputFile outputFile;

    static {
        List<Option> opts = new ArrayList<>();
        opts.add(Option.builder()
                .longOpt(CLI_SOURCE_PATH)
                .desc("path to the java-source files (e.g. git workingcopy, ...)")
                .hasArg()
                .argName("SOURCE")
                .required(true)
                .build());
        opts.add(Option.builder()
                .longOpt(CLI_OUTPUT_FILENAME)
                .desc("write the output to the given filename instead to stdout")
                .hasArg()
                .argName("DESTINATION")
                .required(false)
                .build());
        opts.forEach(options::addOption);
    }

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        call(new String[] {
                "--path-to-source=/Users/hochguertelto/03 Home/03 Privat/03 Projekte/03 Java/java-source-to-draw.io/Foo.java",
                "--write-output-to-file=Foo.xml"
        });

        // What is with Dependecy Injection?
        final JavaSourceServiceImpl javaSourceService = new JavaSourceServiceImpl(
                sourcePath,
                new ClassOrInterfaceRepresentationCollector()
        );

        final List<ClassRepresentation> classRepresentations = javaSourceService.getClassRepresentations();
        
    }

    private static void call(String[] args) throws ParseException {
        CommandLine cli = parser.parse(options, args);

        if (cli.hasOption(CLI_SOURCE_PATH)) {
            sourcePath = new SourcePath(cli.getOptionValue(CLI_SOURCE_PATH));
        }

        if (cli.hasOption(CLI_OUTPUT_FILENAME)) {
            outputFile = new OutputFile(cli.getOptionValue(CLI_OUTPUT_FILENAME));
        }
    }
}
