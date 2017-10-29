package local.hochguertel.javasourcetodrawio.domain.javasource;

import java.util.Objects;

/**
 * InterfaceRep
 * <p>
 * Created on 29.10.17
 */
public class InterfaceRepresentation implements HasUmlAccessSpecifer {

    private final String visibility = "public";
    private final String name; // Foo

    public InterfaceRepresentation(String name) {
        this.name = name;
    }

    public String getVisibility() {
        return getAccessSpecifier();
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
        InterfaceRepresentation that = (InterfaceRepresentation) o;
        return Objects.equals(visibility, that.visibility) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visibility, name);
    }

    @Override
    public String getAccessSpecifier() {
        return visibility;
    }
}
