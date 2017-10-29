package local.hochguertel.javasourcetodrawio.components.javasource;

import java.util.List;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import local.hochguertel.javasourcetodrawio.domain.javasource.FieldRepresentation;

/**
 * FieldRepresentationDeclarationCollector
 * <p>
 * Created on 29.10.17
 */
public class FieldRepresentationDeclarationCollector extends VoidVisitorAdapter<List<FieldRepresentation>> {

    @Override
    public void visit(FieldDeclaration fd, List<FieldRepresentation> collector) {
        super.visit(fd, collector);

        collector.add(new FieldRepresentation(
                getAccessSpecifier(fd),
                getReferenceType(fd),
                getFieldName(fd))
        );
    }

    private String getFieldName(FieldDeclaration fd) {
        return getVariableDeclarator(fd).getNameAsString();
    }

    private String getReferenceType(FieldDeclaration fd) {
        return getVariableDeclarator(fd).getType().asString();
    }

    private String getAccessSpecifier(FieldDeclaration fd) {
        return Modifier.getAccessSpecifier(fd.getModifiers()).toString();
    }

    private VariableDeclarator getVariableDeclarator(FieldDeclaration fd) {
        return fd.getVariables().get(0);
    }
}
