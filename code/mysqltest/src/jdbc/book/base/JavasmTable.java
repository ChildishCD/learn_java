package jdbc.book.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JavasmTable {
    String value();
}
