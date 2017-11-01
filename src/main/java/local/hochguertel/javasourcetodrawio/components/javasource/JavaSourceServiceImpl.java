package local.hochguertel.javasourcetodrawio.components.javasource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import local.hochguertel.javasourcetodrawio.domain.SourcePath;
import local.hochguertel.javasourcetodrawio.domain.javasource.ClassRepresentation;
import local.hochguertel.javasourcetodrawio.util.CollectorUtil;

/**
 * JavaSourceServiceImpl
 * <p>
 * Created on 29.10.17
 */
public class JavaSourceServiceImpl implements JavaSourceService {

    private final static Logger logger = LoggerFactory.getLogger(JavaSourceServiceImpl.class);
    private final ClassOrInterfaceRepresentationCollector classCollector;
    private List<CompilationUnit> asts = new ArrayList<>();

    public JavaSourceServiceImpl(
            SourcePath sourcePath,
            ClassOrInterfaceRepresentationCollector classCollector
    ) throws IOException, ParseException {
        this.classCollector = classCollector;
        createAST(sourcePath);
    }

    private void createAST(SourcePath sourcePath) throws IOException, ParseException {
        if (!sourcePath.isFile()) {
            logger.info("SourcePath is directory.");
            final File srcDir = sourcePath.get().toFile();
            CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
            combinedTypeSolver.add(new ReflectionTypeSolver());
            combinedTypeSolver.add(new JavaParserTypeSolver(srcDir));

            Solver solver = new Solver();
            solver.setTypeSolver(combinedTypeSolver);
            solver.solve(sourcePath);
            asts.addAll(solver.getCompilationUnits());
        }
        if (sourcePath.isFile()) {
            logger.info("Creating AST from {} java-source file.", sourcePath.get().toAbsolutePath());
            final FileInputStream fis = sourcePath.getFileInputStream();
            asts.add(JavaParser.parse(fis));
        }
    }

    @Override
    public List<ClassRepresentation> getClassRepresentations() {
        final List<ClassRepresentation> classes = getClasses();
        CollectorUtil.logClassCollector(classes);
        for (ClassRepresentation classRepresentation : classes) {
            CollectorUtil.logFieldCollector(classRepresentation.getFields());
            CollectorUtil.logMethodCollector(classRepresentation.getMethods());
        }
        return classes;
    }

    private List<ClassRepresentation> getClasses() {
        final List<ClassRepresentation> classes = new ArrayList<>();
        asts.forEach(cu -> classCollector.visit(cu, classes));
        return classes;
    }

}
