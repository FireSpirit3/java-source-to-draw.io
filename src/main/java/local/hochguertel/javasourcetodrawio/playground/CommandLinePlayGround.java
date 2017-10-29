package local.hochguertel.javasourcetodrawio.playground;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CommandLinePlayGround
 * <p>
 * Created on 29.10.17
 */
public class CommandLinePlayGround {

    private final static Logger logger = LoggerFactory.getLogger(CommandLinePlayGround.class);
    private final static CommandLineParser parser = new DefaultParser();
    private final static Options options = new Options();

    static {
        // create the Options
        options.addOption("a", "all", false, "do not hide entries starting with .");
        options.addOption("A", "almost-all", false, "do not list implied . and ..");
        options.addOption("b", "escape", false, "print octal escapes for nongraphic characters");
        final Option blockSize = Option.builder()
                .longOpt("block-size")
                .desc("use SIZE-byte blocks")
                .hasArg()
                .argName("SIZE")
                .build();
        options.addOption(blockSize);
        options.addOption("B", "ignore-backups", false, "do not list implied entried ending with ~");
        options.addOption("c", false,
                "with -lt: sort by, and show, ctime (time of last modification of file status information) with -l:show ctime and sort by name otherwise: sort by ctime");
        options.addOption("C", false, "list entries by columns");
    }

    public static void main(String[] args) throws ParseException {
        call(new String[] {"--block-size=10"});
    }

    private static void call(String[] args) throws ParseException {
        // parse the command line arguments
        CommandLine line = parser.parse(options, args);
        // validate that block-size has been set
        if (line.hasOption("block-size")) {
            logger.info("Option: 'block-size' is set to: {}", line.getOptionValue("block-size"));
        }
    }

}
