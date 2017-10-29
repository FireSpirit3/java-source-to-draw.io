package local.hochguertel.javasourcetodrawio.domain.uml;

import local.hochguertel.javasourcetodrawio.domain.javasource.ParameterRepresentation;

/**
 * UmlMethodParameterRepresentationConverter
 * <p>
 * Created on 29.10.17
 * <p>
 */
public class UmlMethodParameterRepresentationConverter implements UmlRepresenationConverter<ParameterRepresentation> {

    @Override
    public String convert(ParameterRepresentation source) {
        return String.format("%1s: %2s", source.getName(), source.getParameterReferenceType());
    }
}
