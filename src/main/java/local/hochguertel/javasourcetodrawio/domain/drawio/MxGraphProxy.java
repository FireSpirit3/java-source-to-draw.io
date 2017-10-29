package local.hochguertel.javasourcetodrawio.domain.drawio;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.view.mxGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MxGraphProxy
 * <p>
 * Created on 29.10.17
 * <p>
 */
public class MxGraphProxy {

    private final static Logger logger = LoggerFactory.getLogger(MxGraphProxy.class);
    private final mxGraph graph = new mxGraph();

    public mxCell getDefaultParent() {
        return (mxCell) graph.getDefaultParent();
    }

    public mxIGraphModel getModel() {
        return graph.getModel();
    }

    public mxCell insertVertex(mxCell parent, String id, String value, int x, int y, int width, int height, String style) {
        return (mxCell) graph.insertVertex(parent, id, value, x, y, width, height, style);
    }
}
