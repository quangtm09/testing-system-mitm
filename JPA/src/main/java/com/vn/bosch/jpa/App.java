package com.vn.bosch.jpa;

import javax.persistence.EntityManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vn.bosch.jpa.config.PersistenceJPAConfig;
import com.vn.bosch.jpa.config.PersistenceJPAConfigXml;
import com.vn.bosch.jpa.persistence.model.Bar;
import com.vn.bosch.jpa.persistence.model.Foo;
import com.vn.bosch.jpa.persistence.service.FooService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    	ctx.register(PersistenceJPAConfig.class);
    	ctx.refresh();

    	FooService fooService = ctx.getBean(FooService.class);
    			   
    	Bar bar = new Bar("Test Bar");
    	
    	
        Foo foo = new Foo("Test Foo");
//        foo.setBar(bar);
        
        fooService.create(foo);
        
        Foo foo2 = new Foo("Test Foo 2");
//        foo2.setBar(bar);
        
        fooService.create(foo2);
    }
}
