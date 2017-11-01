package local.hochguertel.javasourcetodrawio.components.javasource;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.declarations.TypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.model.typesystem.ReferenceType;
import com.github.javaparser.symbolsolver.model.typesystem.Type;
import static com.github.javaparser.symbolsolver.javaparser.Navigator.getParentNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import local.hochguertel.javasourcetodrawio.domain.SourcePath;

/**
 * Solver
 * <p>
 * Created on 01.11.17
 * <p>
 * Extraction from com.github.javaparser.symbolsolver.model.typesystem.Type.SourceFileInfoExtractor
 * Only Quick Copy of some methods for my purpose.
 * <p>
 * This class will change in the future...
 */
public class Solver {

    private final static Logger logger = LoggerFactory.getLogger(Solver.class);
    private TypeSolver typeSolver;

    private int ok = 0;
    private int ko = 0;
    private int unsupported = 0;
    private boolean printFileName = true;
    private PrintStream out = System.out;
    private PrintStream err = System.err;
    private List<CompilationUnit> nodeCollector = new ArrayList<>();

    public List<CompilationUnit> getCompilationUnits() {
        return nodeCollector;
    }

    public void setTypeSolver(TypeSolver typeSolver) {
        this.typeSolver = typeSolver;
    }

    public void solve(SourcePath sourcePath) throws IOException, ParseException {
        final File srcDir = sourcePath.get().toFile();
        this.solve(srcDir);
    }

    public void solve(File file) throws IOException, ParseException {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                solve(f);
            }
        } else {
            if (file.getName().endsWith(".java")) {
                if (printFileName) {
                    out.println("- parsing " + file.getAbsolutePath());
                }
                CompilationUnit cu = JavaParser.parse(file);
                List<Node> nodes = collectAllNodes(cu);
                nodes.forEach(node -> {
                    if (node instanceof CompilationUnit) {
                        nodeCollector.add((CompilationUnit) node);
                    }
                });
                //                nodes.forEach(this::solve);
            }
        }
    }

    private void solve(Node node) {
        if (node instanceof ClassOrInterfaceDeclaration) {
            solveTypeDecl((ClassOrInterfaceDeclaration) node);
        } else if (node instanceof Expression) {
            if ((getParentNode(node) instanceof ImportDeclaration) || (getParentNode(node) instanceof Expression)
                    || (getParentNode(node) instanceof MethodDeclaration)
                    || (getParentNode(node) instanceof PackageDeclaration)) {
                // skip
            } else if ((getParentNode(node) instanceof Statement) || (getParentNode(node) instanceof VariableDeclarator)) {
                try {
                    Type ref = JavaParserFacade.get(typeSolver).getType(node);
                    out.println("  Line " + node.getRange().get().begin.line + ") " + node + " ==> " + ref.describe());
                    ok++;
                } catch (UnsupportedOperationException upe) {
                    unsupported++;
                    err.println(upe.getMessage());
                    throw upe;
                } catch (RuntimeException re) {
                    ko++;
                    err.println(re.getMessage());
                    throw re;
                }
            }
        }
    }

    private void solveTypeDecl(ClassOrInterfaceDeclaration node) {
        TypeDeclaration typeDeclaration = JavaParserFacade.get(typeSolver).getTypeDeclaration(node);
        if (typeDeclaration.isClass()) {
            out.println("\n[ Class " + typeDeclaration.getQualifiedName() + " ]");
            for (ReferenceType sc : typeDeclaration.asClass().getAllSuperClasses()) {
                out.println("  superclass: " + sc.getQualifiedName());
            }
            for (ReferenceType sc : typeDeclaration.asClass().getAllInterfaces()) {
                out.println("  interface: " + sc.getQualifiedName());
            }
        }
    }

    private List<Node> collectAllNodes(Node node) {
        List<Node> nodes = new LinkedList<>();
        collectAllNodes(node, nodes);
        nodes.sort((n1, n2) -> n1.getBegin().get().compareTo(n2.getBegin().get()));
        return nodes;
    }

    private void collectAllNodes(Node node, List<Node> nodes) {
        nodes.add(node);
        node.getChildNodes().forEach(c -> collectAllNodes(c, nodes));
    }
}
