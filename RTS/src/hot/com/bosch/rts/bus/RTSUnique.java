package com.bosch.rts.bus;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.ValidatorClass;

/**
 * The Interface RTSUnique.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidatorClass(UniqueValidator.class)
public @interface RTSUnique {
	
	/**
	 * Message.
	 *
	 * @return the string
	 */
	String message() default "#{messages['validator.unique']}";

	/**
	 * Entity name.
	 *
	 * @return the string
	 */
	String entityName();

	/**
	 * Field name.
	 *
	 * @return the string
	 */
	String fieldName();

	/**
	 * Entity home component.
	 *
	 * @return the string
	 */
	String entityHomeComponent();

	/**
	 * Id field name.
	 *
	 * @return the string
	 */
	String idFieldName();
}