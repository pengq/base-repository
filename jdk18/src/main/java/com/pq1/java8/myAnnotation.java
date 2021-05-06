package com.pq1.java8;

import java.lang.annotation.*;

@Repeatable(myAnnotations.class)
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface myAnnotation {
	String value() default "ssss";
}
