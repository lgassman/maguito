package ar.edu.unq.sarmiento.epers;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ar.edu.unq.sarmiento.epers.hibernate.HibernateConf;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see ar.edu.unq.sarmiento.epers.ar.edu.unq.sarmiento.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		// Acá se integra Wicket con Spring
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}
}
