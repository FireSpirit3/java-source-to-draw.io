package local.hochguertel.javasourcetodrawio;

import java.io.FileInputStream;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CuPrinter
 * <p>
 * Created on 26.10.17
 */
class CuPrinter {

    private final static Logger logger = LoggerFactory.getLogger(CuPrinter.class);

    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("Foo.java");

        // parse the file
        CompilationUnit cu = JavaParser.parse(in);
        
        // prints the resulting compilation unit to default system output
        logger.info(cu.toString());
    }

}
