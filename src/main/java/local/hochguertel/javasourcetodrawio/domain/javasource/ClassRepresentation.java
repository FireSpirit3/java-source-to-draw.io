package local.hochguertel.javasourcetodrawio.domain.javasource;

import java.util.List;
import java.util.Objects;

/**
 * ClassRepresentation
 * <p>
 * Created on 29.10.17
 */
public class ClassRepresentation {

    private final String accessSpecifier;
    private final String name;
    private final List<FieldRepresentation> fields;
    private final List<MethodRepresentation> methods;

    public ClassRepresentation(
            String accessSpecifier,
            String name,
            List<FieldRepresentation> fields,
            List<MethodRepresentation> methods
    ) {
        this.accessSpecifier = accessSpecifier;
        this.name = name;
        this.fields = fields;
        this.methods = methods;
    }

    public String getAccessSpecifier() {
        return accessSpecifier;
    }

    public String getName() {
        return name;
    }

    public List<FieldRepresentation> getFields() {
        return fields;
    }

    public List<MethodRepresentation> getMethods() {
        return methods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ClassRepresentation that = (ClassRepresentation) o;
        return Objects.equals(accessSpecifier, that.accessSpecifier) &&
                Objects.equals(name, that.name) &&
                Objects.equals(fields, that.fields) &&
                Objects.equals(methods, that.methods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessSpecifier, name, fields, methods);
    }
}
