package local.hochguertel.javasourcetodrawio.domain.drawio;

import local.hochguertel.javasourcetodrawio.domain.javasource.JavaSourceRepresentation;

/**
 * Drawioumlmapper
 * <p>
 * Created on 29.10.17
 * <p>
 */
public interface Drawioumlmapper<T extends JavaSourceRepresentation, T1 extends Drawioshape> {

    void map(T source, T1 destination);

}
