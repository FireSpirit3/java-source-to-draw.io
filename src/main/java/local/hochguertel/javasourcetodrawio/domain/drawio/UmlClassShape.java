package local.hochguertel.javasourcetodrawio.domain.drawio;

import java.util.HashSet;
import java.util.Set;
import com.mxgraph.model.mxCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UmlClassShape
 * <p>
 * Created on 29.10.17
 * <p>
 */
public class UmlClassShape implements Drawioshape {

    // Could be improved with an HashMap<String, String> or has mxGraph Constants/Enum for these properties?
    public static final String CLASSNAME_STYLE =
            "swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;swimlaneFillColor=#ffffff;strokeColor=#FFE599;fillColor=none;gradientColor=#ffffff;";
    public static final String FIELD_STYLE =
            "text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;";
    public static final String SEPERATOR_STYLE =
            "line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;";
    public static final String METHOD_STYLE =
            "\"text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;\"";
    public static final int CLASSNAME_HEIGHT = 26;
    public static final int METHOD_HEIGHT = 26;
    public static final int FIELD_HEIGHT = 26;
    public static final int SEPERATOR_HEIGHT = 8;
    public static final int DEFAULT_WIDTH = 160;
    public static final int DEFAULT_HEIGHT = 86;
    public static final int DEFAULT_X = 10;
    public static final int DEFAULT_Y = 10;
    public static final String DEFAULT_ID = null;

    private final static Logger logger = LoggerFactory.getLogger(UmlClassShape.class);
    private final MxGraphProxy graph;
    private final Set<mxCell> fields;
    private final Set<mxCell> methods;
    private mxCell title;
    private mxCell seperator;

    public UmlClassShape(MxGraphProxy graph) {
        this.graph = graph;
        fields = new HashSet<>();
        methods = new HashSet<>();
    }

    public void setClassname(String className) {
        title = createVertex(className, CLASSNAME_STYLE);
    }

    private mxCell createVertex(String text, String style) {
        return graph.insertVertex(graph.getDefaultParent(), DEFAULT_ID, text, DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, style);
    }

    private mxCell createFieldVertex(String umlfield) {
        return graph.insertVertex(title, DEFAULT_ID, umlfield, 0, getFieldYCordinate(), DEFAULT_WIDTH, FIELD_HEIGHT, FIELD_STYLE);
    }

    private mxCell createSeperatorVertex() {
        return graph.insertVertex(title, DEFAULT_ID, "", 0, getSeperatorYCordinate(), DEFAULT_WIDTH, SEPERATOR_HEIGHT, SEPERATOR_STYLE);
    }

    private mxCell createMethodVertex(String umlmethod) {
        return graph.insertVertex(title, DEFAULT_ID, umlmethod, 0, getMethodYCordinate(), DEFAULT_WIDTH, METHOD_HEIGHT, METHOD_STYLE);
    }

    private int getFieldYCordinate() {
        if (isFirstField())
            return CLASSNAME_HEIGHT;
        return CLASSNAME_HEIGHT + getCurrentHeightOfFieldArea();
    }

    private int getAmountOfFields() {
        return fields.size();
    }

    private boolean isFirstField() {
        return getAmountOfFields() == 0;
    }

    private int getSeperatorYCordinate() {
        return getCurrentHeightOfFieldArea() + FIELD_HEIGHT;
    }

    private int getCurrentHeightOfFieldArea() {
        return getAmountOfFields() * FIELD_HEIGHT;
    }

    private int getMethodYCordinate() {
        int originMethodYCordinate = getSeperatorYCordinate() + SEPERATOR_HEIGHT;
        if (methods.size() == 0)
            return originMethodYCordinate;
        return originMethodYCordinate + methods.size() * METHOD_HEIGHT;
    }

    public void addField(String umlfield) {
        if (title == null) {
            throw new IllegalStateException("Classname not yet set. First set the classname and then add Fields.");
        }
        fields.add(createFieldVertex(umlfield));
    }

    public void addMethod(String umlmethod) {
        if (title == null) {
            throw new IllegalStateException("Classname not yet set. First set the classname and then add Methods.");
        }
        if (methods.size() == 0) {
            seperator = createSeperatorVertex();
        }
        methods.add(createMethodVertex(umlmethod));
    }

    public void traceYAxes() {
        fields.forEach(mxCell -> logger.info("Y: {}, F: '{}'", mxCell.getGeometry().getY(), mxCell.getValue()));
        logger.info("Y: {}, S", seperator.getGeometry().getY());
        methods.forEach(mxCell -> logger.info("Y: {}, M: '{}'", mxCell.getGeometry().getY(), mxCell.getValue()));
    }
}
