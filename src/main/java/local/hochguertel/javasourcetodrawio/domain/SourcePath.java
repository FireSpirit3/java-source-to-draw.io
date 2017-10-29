package local.hochguertel.javasourcetodrawio.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SourcePath
 * <p>
 * Created on 29.10.17
 */
public class SourcePath {

    private final static Logger logger = LoggerFactory.getLogger(SourcePath.class);
    private final Path path;

    public SourcePath(String pathTo) {
        path = Paths.get(pathTo);
        validate();
        logger.info("Option: 'SourcePath' is set to: {}", pathTo);
    }

    private void validate() {
        final File file = path.toFile();
        if (!file.exists()) {
            throw new IllegalArgumentException("Path to Source does not exists!");
        }
        logger.info("Validation successfully");
    }

    public Path get() {
        return path;
    }

    public boolean isFile() {
        return path.toFile().isFile();
    }

    public FileInputStream getFileInputStream() throws FileNotFoundException {
        return new FileInputStream(path.toFile());
    }
}
