package local.hochguertel.javasourcetodrawio.util;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxIGraphModel;
import org.w3c.dom.Node;

public final class NodeOutpututil {

    private final static mxCodec codec;

    static {
        codec = new mxCodec();
    }

    public static Node getXmlNode(mxIGraphModel model) {
        return codec.encode(model);
    }

}