package local.hochguertel.javasourcetodrawio;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CuPrinter
 * <p>
 * Created on 26.10.17
 */
class CuPrinter {

    private final static Logger logger = LoggerFactory.getLogger(CuPrinter.class);
    private static CompilationUnit cu;

    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("Foo.java");
        //        FileInputStream in = new FileInputStream("TestCases/uml-parser-test-1/A.java");

        // parse the file
        cu = JavaParser.parse(in);

        final ClassOrInterfaceNameCollector classOrInterfaceNameCollector = new ClassOrInterfaceNameCollector();
        logCollector(getCollector(classOrInterfaceNameCollector));

        final FieldDeclarationCollector fieldDeclarationCollector = new FieldDeclarationCollector();
        logCollector(getCollector(fieldDeclarationCollector));

        final MethodNameCollector methodNameCollector = new MethodNameCollector();
        logCollector(getCollector(methodNameCollector));
        
    }

    private static void logCollector(List<String> collector) {
        collector.forEach(field -> logger.info("Name collected: {}", field));
    }

    private static List<String> getCollector(VoidVisitorAdapter<List<String>> fieldDeclarationCollector) {
        List<String> fieldNames = new ArrayList<>();
        fieldDeclarationCollector.visit(cu, fieldNames);
        return fieldNames;
    }

    private static class MethodNameCollector extends VoidVisitorAdapter<List<String>> {

        @Override
        public void visit(MethodDeclaration md, List<String> collector) {
            super.visit(md, collector);
            collector.add(md.getName().asString());
            //            collector.add(String.valueOf(md.getModifiers())); // package-default
            //            collector.add(String.valueOf(md.getType())); // void
/*            final List<String> parameterCollector = collectParameters(md);
            collector.add(String.valueOf(parameterCollector));*/
        }

        private List<String> collectParameters(MethodDeclaration md) {
            final List<String> collector = new ArrayList<>();
            final NodeList<Parameter> parameters = md.getParameters();
            parameters.forEach(parameter -> collector.add(parameter.getName().asString()));
            return collector;
        }
    }

    private static class ClassOrInterfaceNameCollector extends VoidVisitorAdapter<List<String>> {

        @Override
        public void visit(ClassOrInterfaceDeclaration cd, List<String> collector) {
            super.visit(cd, collector);
            collector.add(cd.getName().asString());
        }
    }

    private static class FieldDeclarationCollector extends VoidVisitorAdapter<List<String>> {

        @Override
        public void visit(FieldDeclaration fd, List<String> collector) {
            super.visit(fd, collector);
            //            collector.add(String.valueOf(fd.getModifiers())); // PRIVATE
            //            collector.add(String.valueOf(fd.getVariables())); // a
            //            collector.add(String.valueOf(fd.getVariables().get(0).getType())); // String

            final String modifiers = String.valueOf(fd.getModifiers());
            final String fieldName = String.valueOf(fd.getVariables());
            final String referenceType = String.valueOf(fd.getVariables().get(0).getType());

            collector.add(String.format("%1s %2s %3s", modifiers, referenceType, fieldName));
        }
    }

}

