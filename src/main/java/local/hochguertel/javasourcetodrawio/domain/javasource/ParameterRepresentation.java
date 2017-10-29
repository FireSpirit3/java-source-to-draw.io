package local.hochguertel.javasourcetodrawio.domain.javasource;

import java.util.Objects;

/**
 * ParameterRepresentation
 * <p>
 * Created on 29.10.17
 */
public class ParameterRepresentation {

    private final String parameterReferenceType;
    private final String name;

    public ParameterRepresentation(String parameterReferenceType, String name) {
        this.parameterReferenceType = parameterReferenceType;
        this.name = name;
    }

    public String getParameterReferenceType() {
        return parameterReferenceType;
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
        ParameterRepresentation that = (ParameterRepresentation) o;
        return Objects.equals(parameterReferenceType, that.parameterReferenceType) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameterReferenceType, name);
    }

    @Override
    public String toString() {
        return '{' + parameterReferenceType + ' ' + name + '}';
    }
}
