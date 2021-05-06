package com.pq1.java8;

import org.junit.runner.OrderWith;

import javax.xml.bind.annotation.XmlRootElement;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface myAnnotations {
	myAnnotation [] value() ;
}
