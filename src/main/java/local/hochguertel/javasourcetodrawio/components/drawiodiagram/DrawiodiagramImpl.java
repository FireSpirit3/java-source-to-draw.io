package local.hochguertel.javasourcetodrawio.components.drawiodiagram;

import java.util.ArrayList;
import java.util.List;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.view.mxGraph;
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
    private final MxGraphProxy diagram;
    private final UmlAccessSpecifierConverter umlAccessSpecifierConverter = new UmlAccessSpecifierConverter();

    public DrawiodiagramImpl(JavaSourceService javaSourceService, MxGraphProxy diagram) {
        this.javaSourceService = javaSourceService;
        this.diagram = diagram;
    }

    @Override
    public List<UmlClassShape> getDiagram() {

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

        List<UmlClassShape> umlClassShapes = new ArrayList<>();
        List<ClassRepresentation> classRepresentations = javaSourceService.getClassRepresentations();
        classRepresentations.forEach(classRepresentation -> {
            final UmlClassShape umlClassShape = new UmlClassShape(diagram);
            umlClassShapes.add(umlClassShape);
            classDrawioumlmapper.map(classRepresentation, umlClassShape);
            umlClassShape.traceYAxes();
        });

        arrangeShapes();

        // @TODO: NEXT
        // ... Next should be creating edges from one class to another class...
        // Javaparser should have this information for us?
        // Ah! That's the purpose of javaparser symbol resolver... see javaparservisited.pdf p.56 ff.

        logger.info("How does the diagram look as xml? \n{}", DrawIoDiagramOutputUtil.getDiagram(diagram));

        return umlClassShapes;
    }

    private void arrangeShapes() {
        final mxGraph graph = diagram.getGraph();
        graph.getModel().beginUpdate();
        mxIGraphLayout layout = new mxCircleLayout(graph);
        layout.execute(graph.getDefaultParent());
        graph.getModel().endUpdate();
    }
}
