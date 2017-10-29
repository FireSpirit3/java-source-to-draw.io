package local.hochguertel.javasourcetodrawio.components.drawiodiagram;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import local.hochguertel.javasourcetodrawio.components.javasource.JavaSourceService;
import local.hochguertel.javasourcetodrawio.domain.drawio.Drawioumlmapper;
import local.hochguertel.javasourcetodrawio.domain.drawio.MxGraphProxy;
import local.hochguertel.javasourcetodrawio.domain.drawio.UmlClassShape;
import local.hochguertel.javasourcetodrawio.domain.javasource.ClassRepresentation;
import local.hochguertel.javasourcetodrawio.domain.uml.UmlAccessSpecifierConverter;
import local.hochguertel.javasourcetodrawio.domain.uml.UmlFieldRepresentationConverter;
import local.hochguertel.javasourcetodrawio.domain.uml.UmlMethodRepresentationConverter;
import local.hochguertel.javasourcetodrawio.util.DrawIoDiagramOutputUtil;

/**
 * DrawiodiagramImpl
 * <p>
 * Created on 29.10.17
 */
public class DrawiodiagramImpl implements Drawiodiagram {

    private final static Logger logger = LoggerFactory.getLogger(DrawiodiagramImpl.class);
    private final JavaSourceService javaSourceService;
    private final MxGraphProxy graph;
    private final UmlAccessSpecifierConverter umlAccessSpecifierConverter = new UmlAccessSpecifierConverter();

    public DrawiodiagramImpl(JavaSourceService javaSourceService, MxGraphProxy graph) {
        this.javaSourceService = javaSourceService;
        this.graph = graph;
    }

    @Override
    public UmlClassShape getDiagram() {
        UmlClassShape umlClassShape = new UmlClassShape(graph);

        Drawioumlmapper<ClassRepresentation, UmlClassShape> classDrawioumlmapper = new Drawioumlmapper<ClassRepresentation, UmlClassShape>() {

            private UmlFieldRepresentationConverter fieldConverter = new UmlFieldRepresentationConverter();
            private UmlMethodRepresentationConverter methodConverter = new UmlMethodRepresentationConverter();

            @Override
            public void map(ClassRepresentation source, UmlClassShape destination) {
                destination.setClassname(source.getName());
                source.getFields().forEach(fieldRepresentation -> destination.addField(fieldConverter.convert(fieldRepresentation)));
                source.getMethods().forEach(methodRepresentation -> destination.addMethod(methodConverter.convert(methodRepresentation)));
            }
        };

        List<ClassRepresentation> classRepresentations = javaSourceService.getClassRepresentations();
        ClassRepresentation classRepresentation = classRepresentations.get(0);
        classDrawioumlmapper.map(classRepresentation, umlClassShape);

        umlClassShape.traceYAxes();
        logger.info("How does the graph look as xml? \n{}", DrawIoDiagramOutputUtil.getDiagram(graph));

        return umlClassShape;
    }
}
