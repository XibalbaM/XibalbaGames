package fr.xibalba.games.main.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Game {

    public String name();

    public String description() default "";

    /*
     * Need to be an absolute path
     */
    public String iconURL() default "";
}
