package hulio13.notionAlarm.configuration.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Configuration {
    boolean ignoreIfEmpty() default false;
    int order() default 0;
    String defaultPath() default "";
}
