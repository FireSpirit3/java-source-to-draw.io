package local.hochguertel.javasourcetodrawio.util;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import local.hochguertel.javasourcetodrawio.domain.javasource.ClassRepresentation;
import local.hochguertel.javasourcetodrawio.domain.javasource.FieldRepresentation;
import local.hochguertel.javasourcetodrawio.domain.javasource.MethodRepresentation;

/**
 * CollectorUtil
 * <p>
 * Created on 29.10.17
 */
public final class CollectorUtil {

    private final static Logger logger = LoggerFactory.getLogger(CollectorUtil.class);

    public static void logClassCollector(List<ClassRepresentation> classes) {
        classes.forEach(classRep -> logger.info("Class collected: {} {}", classRep.getAccessSpecifier(), classRep.getName()));
    }

    public static void logFieldCollector(List<FieldRepresentation> fields) {
        fields.forEach(fieldRep -> logger.info("Field collected: {} {} {}",
                fieldRep.getAccessSpecifier(),
                fieldRep.getReferenceType(),
                fieldRep.getName()
        ));
    }

    public static void logMethodCollector(List<MethodRepresentation> methods) {
        methods.forEach(methodRep -> {
            logger.info("Method collected: {} {} {} {}",
                    methodRep.getAccessSpecifier(),
                    methodRep.getReturnReferenceType(),
                    methodRep.getName(),
                    methodRep.getParameters()
            );
        });
    }

}
