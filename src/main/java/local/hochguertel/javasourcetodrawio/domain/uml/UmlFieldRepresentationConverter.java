package local.hochguertel.javasourcetodrawio.domain.uml;

import local.hochguertel.javasourcetodrawio.domain.javasource.FieldRepresentation;

/**
 * UmlFieldRepresentationConverter
 * <p>
 * Created on 29.10.17
 * <p>
 */
public class UmlFieldRepresentationConverter implements UmlRepresenationConverter<FieldRepresentation> {

    @Override
    public String convert(FieldRepresentation source) {
        return String.format("%1s %2s: %3s", new UmlAccessSpecifierConverter().convert(source), source.getName(), source.getReferenceType());
    }
}