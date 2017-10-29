package local.hochguertel.javasourcetodrawio.components.javasource;

import java.util.HashSet;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import local.hochguertel.javasourcetodrawio.domain.javasource.ParameterRepresentation;

/**
 * ParameterRepresentationCollector
 * <p>
 * Created on 29.10.17
 */
class ParameterRepresentationCollector {

    public static HashSet<ParameterRepresentation> collector(MethodDeclaration md) {
        final HashSet<ParameterRepresentation> parameters = new HashSet<>();
        final NodeList<Parameter> params = md.getParameters();
        params.forEach(parameter -> parameters.add(
                new ParameterRepresentation(
                        getParameterReferenceType(parameter),
                        getParameterName(parameter)
                ))
        );
        return parameters;
    }

    private static String getParameterName(Parameter parameter) {
        return parameter.getName().asString();
    }

    private static String getParameterReferenceType(Parameter parameter) {
        return parameter.getType().asString();
    }
}
