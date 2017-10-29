package local.hochguertel.javasourcetodrawio.domain.uml;

import local.hochguertel.javasourcetodrawio.domain.javasource.HasUmlAccessSpecifer;

public class UmlAccessSpecifierConverter implements UmlRepresenationConverter<HasUmlAccessSpecifer> {

    @Override
    public String convert(HasUmlAccessSpecifer source) {
        Character accessSpecifier;
        switch (source.getAccessSpecifier().toLowerCase()) {
        case "public":
            accessSpecifier = '+';
            break;
        case "private":
            accessSpecifier = '-';
            break;
        case "protected":
            accessSpecifier = '#';
            break;
        case "package":
        default:
            accessSpecifier = '~';
            break;
        }
        return accessSpecifier.toString();
    }
}