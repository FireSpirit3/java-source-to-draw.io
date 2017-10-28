package local.hochguertel.javasourcetodrawio;

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

    public static void main(String[] args) {
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

}
