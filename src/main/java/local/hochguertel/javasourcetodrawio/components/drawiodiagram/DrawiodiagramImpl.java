package local.hochguertel.javasourcetodrawio.components.drawiodiagram;

import java.util.List;
import local.hochguertel.javasourcetodrawio.components.javasource.JavaSourceService;
import local.hochguertel.javasourcetodrawio.domain.javasource.ClassRepresentation;

/**
 * DrawiodiagramImpl
 * <p>
 * Created on 29.10.17
 */
public class DrawiodiagramImpl implements Drawiodiagram {

    private final JavaSourceService javaSourceService;

    public DrawiodiagramImpl(JavaSourceService javaSourceService) {
        this.javaSourceService = javaSourceService;
    }

    @Override
    public void getDiagram() {
        List<ClassRepresentation> classRepresentations = javaSourceService.getClassRepresentations();

    }

}
