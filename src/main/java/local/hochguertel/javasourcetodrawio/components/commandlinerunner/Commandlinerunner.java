package local.hochguertel.javasourcetodrawio.components.commandlinerunner;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import local.hochguertel.javasourcetodrawio.domain.OutputFile;
import local.hochguertel.javasourcetodrawio.domain.SourcePath;

/**
 * Commandlinerunner
 * <p>
 * Created on 29.10.17
 */
public class Commandlinerunner {

    private final static Logger logger = LoggerFactory.getLogger(Commandlinerunner.class);

    private static final String CLI_SOURCE_PATH = "path-to-source";
    private static final String CLI_OUTPUT_FILENAME = "write-output-to-file";
    private final Options options = new Options();
    private final CommandLineParser parser = new DefaultParser();
    private SourcePath sourcePath;
    private OutputFile outputFile;

    public Commandlinerunner() {
        List<Option> opts = new ArrayList<>();
        opts.add(Option.builder()
                .longOpt(Commandlinerunner.CLI_SOURCE_PATH)
                .desc("path to the java-source files (e.g. git workingcopy, ...)")
                .hasArg()
                .argName("SOURCE")
                .required(true)
                .build());
        opts.add(Option.builder()
                .longOpt(Commandlinerunner.CLI_OUTPUT_FILENAME)
                .desc("write the output to the given filename instead to stdout")
                .hasArg()
                .argName("DESTINATION")
                .required(false)
                .build());
        opts.forEach(this.options::addOption);
    }

    public void printHelp() {
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("target/java-source-to-draw-io-0.0.1-SNAPSHOT.jar", options);
    }

    public boolean call(String[] args) {
        CommandLine cli = null;
        try {
            cli = parser.parse(options, args);
        } catch (ParseException e) {
            //            e.printStackTrace();
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
            return false;
        }

        if (cli.hasOption(CLI_SOURCE_PATH)) {
            logger.info("Option '{}' is given via commandline call.", CLI_SOURCE_PATH);
            sourcePath = new SourcePath(cli.getOptionValue(CLI_SOURCE_PATH));
        }

        if (cli.hasOption(CLI_OUTPUT_FILENAME)) {
            logger.info("Option '{}' is given via commandline call.", CLI_OUTPUT_FILENAME);
            outputFile = new OutputFile(cli.getOptionValue(CLI_OUTPUT_FILENAME));
        }
        return true;
    }

    public SourcePath getSourcePath() {
        return sourcePath;
    }

    public OutputFile getOutputFile() {
        return outputFile;
    }
}
