package local.hochguertel.javasourcetodrawio.domain;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OutputFile
 * <p>
 * Created on 29.10.17
 */
public class OutputFile {

    private final static Logger logger = LoggerFactory.getLogger(SourcePath.class);
    private final Path path;

    public OutputFile(String toFile) {
        path = Paths.get(toFile);
        validate();
        logger.info("Option: 'OutputFile' is set to: {}", toFile);
    }

    private void validate() {
        final File file = path.toFile();
        if (file.exists()) {
            throw new IllegalArgumentException("The Output file already exists. Please change the filename or delete the file!");
        }
        logger.info("Validation successfully");
    }
}
