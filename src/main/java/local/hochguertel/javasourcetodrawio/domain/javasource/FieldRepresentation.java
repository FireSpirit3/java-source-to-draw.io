package local.hochguertel.javasourcetodrawio.domain.javasource;

import java.util.Objects;

/**
 * FieldRepresentation
 * <p>
 * Created on 29.10.17
 */
public class FieldRepresentation implements HasUmlAccessSpecifer {

    private final String accessSpecifier;
    private final String referenceType;
    private final String name;

    public FieldRepresentation(String accessSpecifier, String referenceType, String name) {
        this.accessSpecifier = accessSpecifier;
        this.referenceType = referenceType;
        this.name = name;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FieldRepresentation that = (FieldRepresentation) o;
        return Objects.equals(accessSpecifier, that.accessSpecifier) &&
                Objects.equals(referenceType, that.referenceType) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessSpecifier, referenceType, name);
    }

    public String getAccessSpecifier() {
        return accessSpecifier;
    }

}
