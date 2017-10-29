package local.hochguertel.javasourcetodrawio.domain.uml;

import java.util.Set;
import local.hochguertel.javasourcetodrawio.domain.javasource.MethodRepresentation;
import local.hochguertel.javasourcetodrawio.domain.javasource.ParameterRepresentation;

/**
 * UmlMethodParameterListConverter
 * <p>
 * Created on 29.10.17
 * <p>
 */
public class UmlMethodParameterListConverter implements UmlRepresenationConverter<MethodRepresentation> {

    private UmlMethodParameterRepresentationConverter paramConverter = new UmlMethodParameterRepresentationConverter();

    @Override
    public String convert(MethodRepresentation source) {
        StringBuilder parameterlist = new StringBuilder();
        final Set<ParameterRepresentation> parameters = source.getParameters();
        parameters.forEach(paramRep -> {
            parameterlist.append(paramConverter.convert(paramRep));
            parameterlist.append(",");
        });
        return parameterlist.toString();
    }
}
