package local.hochguertel.javasourcetodrawio.components.javasource;

import java.util.List;
import local.hochguertel.javasourcetodrawio.domain.javasource.ClassRepresentation;

/**
 * JavaSourceService
 * <p>
 * Created on 29.10.17
 */
public interface JavaSourceService {

    List<ClassRepresentation> getClassRepresentations();

}
