package local.hochguertel.javasourcetodrawio.domain.uml;

import local.hochguertel.javasourcetodrawio.domain.javasource.MethodRepresentation;

/**
 * UmlMethodRepresentationConverter
 * <p>
 * Created on 29.10.17
 * <p>
 */
public class UmlMethodRepresentationConverter implements UmlRepresenationConverter<MethodRepresentation> {

    @Override
    public String convert(MethodRepresentation source) {
        return String.format("%1s %2s (%3s): %4s",
                new UmlAccessSpecifierConverter().convert(source),
                source.getName(),
                new UmlMethodParameterListConverter().convert(source),
                source.getReturnReferenceType()
        );
    }
}
