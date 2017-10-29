package local.hochguertel.javasourcetodrawio.components.javasource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import local.hochguertel.javasourcetodrawio.domain.SourcePath;
import local.hochguertel.javasourcetodrawio.domain.javasource.ClassRepresentation;
import local.hochguertel.javasourcetodrawio.util.CollectorUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * JavaSourceServiceImpl
 * <p>
 * Created on 29.10.17
 */
public class JavaSourceServiceImpl implements JavaSourceService {

    private final static Logger logger = LoggerFactory.getLogger(JavaSourceServiceImpl.class);
    private final ClassOrInterfaceRepresentationCollector classCollector;
    private CompilationUnit ast;

    public JavaSourceServiceImpl(
            SourcePath sourcePath,
            ClassOrInterfaceRepresentationCollector classCollector
    ) throws FileNotFoundException {
        this.classCollector = classCollector;
        createAST(sourcePath);
    }

    private void createAST(SourcePath sourcePath) throws FileNotFoundException {
        if (!sourcePath.isFile()) {
            logger.info("SourcePath is directory.");
            logger.error("Current development-state doesn't support the creation of AST from two or more java-source files.");
            throw new NotImplementedException();
        }
        logger.info("Creating AST from {} java-source file.", sourcePath.get().toAbsolutePath());
        final FileInputStream fis = sourcePath.getFileInputStream();
        ast = JavaParser.parse(fis);
    }

    @Override
    public List<ClassRepresentation> getClassRepresentations() {
        final List<ClassRepresentation> classes = getClasses();
        CollectorUtil.logClassCollector(classes);
        classes.forEach(classRepresentation -> {
            CollectorUtil.logFieldCollector(classRepresentation.getFields());
            CollectorUtil.logMethodCollector(classRepresentation.getMethods());
        });
        return classes;
    }

    private List<ClassRepresentation> getClasses() {
        final List<ClassRepresentation> classes = new ArrayList<>();
        classCollector.visit(ast, classes);
        return classes;
    }

}
