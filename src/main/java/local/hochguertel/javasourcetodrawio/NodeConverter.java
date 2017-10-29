package local.hochguertel.javasourcetodrawio;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxIGraphModel;
import org.w3c.dom.Node;

public class NodeConverter {

    private final static mxCodec codec;

    static {
        codec = new mxCodec();
    }

    public static Node getXmlNode(mxIGraphModel model) {
        return codec.encode(model);
    }

}