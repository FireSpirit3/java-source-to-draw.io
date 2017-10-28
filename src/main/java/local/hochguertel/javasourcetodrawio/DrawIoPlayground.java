package local.hochguertel.javasourcetodrawio;

import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxIGraphModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DrawIoPlayground
 * <p>
 * Created on 28.10.17
 */
public class DrawIoPlayground {

    private final static Logger logger = LoggerFactory.getLogger(DrawIoPlayground.class);

    public static void main(String[] args) {
        final mxIGraphModel mxGraphModel = new mxGraphModel();
        logger.info("What do we get from .toString()? \n {}", mxGraphModel.toString());

        //        final Object add = mxGraphModel.add();
    }

}
