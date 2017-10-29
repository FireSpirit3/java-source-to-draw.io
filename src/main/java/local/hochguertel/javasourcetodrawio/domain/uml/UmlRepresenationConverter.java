package local.hochguertel.javasourcetodrawio.domain.uml;

/**
 * UmlRepresenationConverter
 * <p>
 * Created on 29.10.17
 * <p>
 */
public interface UmlRepresenationConverter<T> {

    String convert(T source);

}