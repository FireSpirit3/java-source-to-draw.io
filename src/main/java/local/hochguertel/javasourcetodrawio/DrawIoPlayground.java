package local.hochguertel.javasourcetodrawio;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static local.hochguertel.javasourcetodrawio.DiagramConverter.getDiagram;

/**
 * DrawIoPlayground
 * <p>
 * Created on 28.10.17
 */
public class DrawIoPlayground {

    private final static Logger logger = LoggerFactory.getLogger(DrawIoPlayground.class);

    private static mxGraph getUmlClassDiagram() {
        mxGraph diagram = new mxGraph();
        Object parent = diagram.getDefaultParent(); // mxCell

        // HEADER:
        // <mxCell id="2" value="Foo" style="swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;swimlaneFillColor=#ffffff;strokeColor=#FFE599;fillColor=none;gradientColor=#ffffff;" vertex="1" parent="1">
        // <mxGeometry x="10" y="10" width="160" height="86" as="geometry"/>
        // </mxCell>

        // FIELD(S):
        // <mxCell id="3" value="- a: String" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="2">
        // <mxGeometry y="26" width="160" height="26" as="geometry"/>
        // </mxCell>

        // FIELD-METHOD-SEPERATOR:
        // <mxCell id="4" value="" style="line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;" vertex="1" parent="2">
        // <mxGeometry y="52" width="160" height="8" as="geometry"/>
        // </mxCell>

        // METHOD(S):
        // <mxCell id="5" value=" aMethod(): void" style="text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="2">
        // <mxGeometry y="60" width="160" height="26" as="geometry"/>
        // </mxCell>

        final String className = "Foo";
        final String field = "- a: String";
        final String method = " aMethod(): void";
        final String headerStyle =
                "swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;swimlaneFillColor=#ffffff;strokeColor=#FFE599;fillColor=none;gradientColor=#ffffff;";
        final Object header = diagram.insertVertex(parent, null, className, 10, 10, 160, 86, headerStyle);

        final String fieldStyle =
                "text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;";
        final Object fields = diagram.insertVertex(header, null, field, 0, 26, 160, 26, fieldStyle);

        final String seperatorStyle =
                "line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;";
        final Object seperator = diagram.insertVertex(header, null, "", 0, 52, 160, 8,
                seperatorStyle);

        final String methodStyle =
                "\"text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;\"";
        final Object methods = diagram.insertVertex(header, null, method, 0, 60, 160, 26,
                methodStyle);

        logger.info("How does the diagram look as xml? \n{}", getDiagram(diagram));

        return diagram;
    }

    public static void main(String[] args) {
        //        getExampleDiagram();
        //        getUmlClassDiagram();
        getUmlClassWithAssociation();
    }

    private static void getExampleDiagram() {
        // Creates graph with model
        mxGraph diagram = new mxGraph();
        Object parent = diagram.getDefaultParent(); // mxCell
        logger.info("What do we get from .toString()? \n{}", parent.toString());

        Object v1, v2, e1;
        v1 = diagram.insertVertex(parent, null, "Hello", 20, 20, 80, 30);
        v2 = diagram.insertVertex(parent, null, "World!", 200, 150, 80, 30);
        e1 = diagram.insertEdge(parent, null, "e1", v1, v2);

        logger.info("How does the diagram look as xml? \n{}", getDiagram(diagram));
    }

    private static void getUmlClassWithAssociation() {
        mxGraph diagram = new mxGraph();
        final Object parent = diagram.getDefaultParent();
        final mxCell fooA = getUmlClassShape(diagram, "FooA", 10, 10);
        final mxCell fooB = getUmlClassShape(diagram, "FooB", 250, 10);
        diagram.insertEdge(parent, null, "e1", fooA, fooB);

        logger.info("How does the diagram look as xml? \n{}", getDiagram(diagram));
    }

    private static mxCell getUmlClassShape(mxGraph diagram, String className, int x, int y) {
        Object parent = diagram.getDefaultParent(); // mxCell

        final String field = "- a: String";
        final String method = " aMethod(): void";
        final String headerStyle =
                "swimlane;fontStyle=1;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeParentMax=0;resizeLast=0;collapsible=1;marginBottom=0;swimlaneFillColor=#ffffff;strokeColor=#FFE599;fillColor=none;gradientColor=#ffffff;";

        final int fieldAndMethodRowHeight = 26;

        final int width = 160;
        final int height = 86;

        final Object header = diagram.insertVertex(parent, null, className, x, y, width, height, headerStyle);

        final String fieldStyle =
                "text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;";
        final int fieldStartY = 26;
        final Object fields = diagram.insertVertex(header, null, field, 0, fieldStartY, width, fieldAndMethodRowHeight, fieldStyle);

        final String seperatorStyle =
                "line;strokeWidth=1;fillColor=none;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;";
        final int seperatorStartY = 52;
        final int seperatorHeight = 8;
        final Object seperator = diagram.insertVertex(header, null, "", 0, seperatorStartY, width, seperatorHeight,
                seperatorStyle);

        final String methodStyle =
                "\"text;strokeColor=none;fillColor=none;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;\"";
        final int methodStartY = 60;

        final Object methods = diagram.insertVertex(header, null, method, 0, methodStartY, width, fieldAndMethodRowHeight,
                methodStyle);

        if (!(header instanceof mxCell)) {
            throw new IllegalStateException("Expected that header is an mxCell class!");
        }
        return (mxCell) header;
    }

}
