package local.hochguertel.javasourcetodrawio.components.javasource;

import java.util.HashSet;
import java.util.List;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import local.hochguertel.javasourcetodrawio.domain.javasource.MethodRepresentation;
import local.hochguertel.javasourcetodrawio.domain.javasource.ParameterRepresentation;

/**
 * MethodRepresentationCollector
 * <p>
 * Created on 29.10.17
 */
public class MethodRepresentationCollector extends VoidVisitorAdapter<List<MethodRepresentation>> {

    @Override
    public void visit(MethodDeclaration md, List<MethodRepresentation> collector) {
        super.visit(md, collector);

        collector.add(new MethodRepresentation(
                getMethodAccessSpecifier(md),
                getMethodReturnType(md),
                getMethodName(md),
                getParameterRepresentations(md))
        );
    }

    private HashSet<ParameterRepresentation> getParameterRepresentations(MethodDeclaration md) {
        return ParameterRepresentationCollector.collector(md);
    }

    private String getMethodName(MethodDeclaration md) {
        return md.getName().asString();
    }

    private String getMethodReturnType(MethodDeclaration md) {
        return md.getType().asString();
    }

    private String getMethodAccessSpecifier(MethodDeclaration md) {
        return Modifier.getAccessSpecifier(md.getModifiers()).asString();
    }

}
