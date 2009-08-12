package com.parabolika.psave;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Saveable {
	/**
	 * @return whether or not this field is to be used as a key
	 */
	boolean value() default false;
}
