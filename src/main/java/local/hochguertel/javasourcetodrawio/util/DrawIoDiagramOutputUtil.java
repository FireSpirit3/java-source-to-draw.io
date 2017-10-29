package local.hochguertel.javasourcetodrawio.util;

import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;
import org.w3c.dom.Node;
import local.hochguertel.javasourcetodrawio.domain.drawio.MxGraphProxy;

/**
 * DrawIoDiagramOutputUtil
 * <p>
 * Created on 29.10.17
 */
public final class DrawIoDiagramOutputUtil {

    public static String getDiagram(mxGraph graph) {
        return getDiagram(graph.getModel());
    }

    public static String getDiagram(mxIGraphModel graph) {
        Node node = NodeOutpututil.getXmlNode(graph);
        return mxUtils.getPrettyXml(node);
    }

    public static String getDiagram(MxGraphProxy graph) {
        return getDiagram(graph.getModel());
    }
}
