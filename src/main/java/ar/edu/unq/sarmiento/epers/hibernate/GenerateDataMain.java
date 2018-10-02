package ar.edu.unq.sarmiento.epers.hibernate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GenerateDataMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.scan("ar.edu.unq.sarmiento.epers", "ar.edu.unq.sarmiento.epers.hibernate", "ar.edu.unq.sarmiento.epers.home");
		ctx.refresh();
		
		DataGenerator dg = (DataGenerator) ctx.getBean("dataGenerator");
		dg.generate();
		
		ctx.close();
	}

}
