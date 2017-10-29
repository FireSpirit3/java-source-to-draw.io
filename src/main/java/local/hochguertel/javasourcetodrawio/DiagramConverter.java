package local.hochguertel.javasourcetodrawio;

import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

/**
 * DiagramConverter
 * <p>
 * Created on 29.10.17
 */
public class DiagramConverter {

    private final static Logger logger = LoggerFactory.getLogger(DiagramConverter.class);

    public static String getDiagram(mxGraph graph) {
        return getDiagram(graph.getModel());
    }

    public static String getDiagram(mxIGraphModel graph) {
        Node node = NodeConverter.getXmlNode(graph);
        return mxUtils.getPrettyXml(node);
    }
}
