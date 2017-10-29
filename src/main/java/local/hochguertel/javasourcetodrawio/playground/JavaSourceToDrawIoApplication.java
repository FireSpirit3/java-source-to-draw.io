package local.hochguertel.javasourcetodrawio.playground;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// http://www.javadoc.io/doc/com.github.javaparser/javaparser-core/3.5.0
public class JavaSourceToDrawIoApplication {

    private final static Logger logger = LoggerFactory.getLogger(JavaSourceToDrawIoApplication.class);

    public static void main(String[] args) {
        TypeSolver typeSolver = new CombinedTypeSolver();
        String code = readMyExample();
        logger.info(code);
        CompilationUnit cu = JavaParser.parse(code);
        AssignExpr assignExpr = Navigator.findNodeOfGivenClass(cu, AssignExpr.class);
        System.out.println(String.format("Type of %s is %s", assignExpr, JavaParserFacade.get(typeSolver).getType(assignExpr)));
    }

    private static String readMyExample() {
        return "class Foo {\n"
                + "\n"
                + "    private String a;\n"
                + "\n"
                + "    void aMethod() {\n"
                + "        float a;\n"
                + "        while (true) {\n"
                + "            int a;\n"
                + "            a = a + 1;\n"
                + "        }\n"
                + "    }\n"
                + "}";
    }
}

