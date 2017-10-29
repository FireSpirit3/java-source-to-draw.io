package local.hochguertel.javasourcetodrawio.components.javasource;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import local.hochguertel.javasourcetodrawio.domain.javasource.ClassRepresentation;
import local.hochguertel.javasourcetodrawio.domain.javasource.FieldRepresentation;
import local.hochguertel.javasourcetodrawio.domain.javasource.MethodRepresentation;

/**
 * ClassOrInterfaceRepresentationCollector
 * <p>
 * Created on 29.10.17
 */
public class ClassOrInterfaceRepresentationCollector extends VoidVisitorAdapter<List<ClassRepresentation>> {

    @Override
    public void visit(ClassOrInterfaceDeclaration cd, List<ClassRepresentation> collector) {
        super.visit(cd, collector);

        collector.add(new ClassRepresentation(
                        getAccessSpecifier(cd),
                        getName(cd),
                        getFields(cd),
                        getMethods(cd)
                )
        );
    }

    private String getName(ClassOrInterfaceDeclaration cd) {
        return cd.getName().asString();
    }

    private String getAccessSpecifier(ClassOrInterfaceDeclaration cd) {
        final EnumSet<Modifier> modifiers = cd.getModifiers();
        return Modifier.getAccessSpecifier(modifiers).asString();
    }

    private List<MethodRepresentation> getMethods(ClassOrInterfaceDeclaration ast) {
        final MethodRepresentationCollector collector = new MethodRepresentationCollector();
        final List<MethodRepresentation> methods = new ArrayList<>();
        collector.visit(ast, methods);
        return methods;
    }

    private List<FieldRepresentation> getFields(ClassOrInterfaceDeclaration ast) {
        final FieldRepresentationDeclarationCollector collector = new FieldRepresentationDeclarationCollector();
        final List<FieldRepresentation> fields = new ArrayList<>();
        collector.visit(ast, fields);
        return fields;
    }
}
