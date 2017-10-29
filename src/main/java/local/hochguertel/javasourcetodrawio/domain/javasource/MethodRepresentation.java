package local.hochguertel.javasourcetodrawio.domain.javasource;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * MethodRepresentation
 * <p>
 * Created on 29.10.17
 */
public class MethodRepresentation implements HasUmlAccessSpecifer {

    private final String accessSpecifier;
    private final String returnReferenceType;
    private final String name;
    private final Set<ParameterRepresentation> parameters;

    public MethodRepresentation(String accessSpecifier, String returnReferenceType, String name, Set<ParameterRepresentation> parameters) {
        this.accessSpecifier = accessSpecifier;
        this.returnReferenceType = returnReferenceType;
        this.name = name;
        this.parameters = parameters;
    }

    public String getReturnReferenceType() {
        return returnReferenceType;
    }

    public String getName() {
        return name;
    }

    public Set<ParameterRepresentation> getParameters() {
        return new HashSet<>(parameters);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MethodRepresentation that = (MethodRepresentation) o;
        return Objects.equals(returnReferenceType, that.returnReferenceType) &&
                Objects.equals(name, that.name) &&
                Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(returnReferenceType, name, parameters);
    }

    public String getAccessSpecifier() {
        return accessSpecifier;
    }
}
