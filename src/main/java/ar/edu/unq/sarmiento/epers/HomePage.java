package ar.edu.unq.sarmiento.epers;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.context.ApplicationContext;

import ar.edu.unq.sarmiento.epers.home.Home;
import ar.edu.unq.sarmiento.epers.model.Item;
import ar.edu.unq.sarmiento.epers.model.Maguito;
import ar.edu.unq.sarmiento.epers.model.SearchModel;

public class HomePage extends WebPage {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HomePage.class);

	private static final long serialVersionUID = -3657874371670902273L;

	@SpringBean(name="maguitoHome")
	private Home<Maguito> maguitoHome;

	Form<SearchModel<Maguito>> form = null;

	@SuppressWarnings("serial")
	
	public HomePage(Maguito maguito) { 
		this();
		((SearchModel<Maguito>)getDefaultModelObject()).setResult(maguito);
	}
	
	public HomePage() {
		log.debug("construyendo form home");

		IModel<SearchModel<Maguito>> model = new CompoundPropertyModel<SearchModel<Maguito>>(
				new SearchModel<Maguito>(maguitoHome));
		this.setDefaultModel(model);
		form = new Form<SearchModel<Maguito>>("form", model);

		form.add(new TextField<String>("search"));

		form.add(new Label("message"));

		form.add(new TextField<String>("result.nombre").setRequired(false));
		form.add(new TextField<String>("result.vida").setRequired(false));
		form.add(new TextField<String>("result.experiencia").setRequired(false));


		AjaxButton ab = new AjaxButton("action") {
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				log.debug("Search button pressed");
				if (target != null) {
					model.getObject().find();

					target.add(form);
				}
			}
		};
		
		form.add(ab);

		ab = new AjaxButton("update") {
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				log.debug("update button pressed");
				if (target != null) {
					model.getObject().update();
					target.add(form);
				}
			}
		};
		form.add(ab);
		
		Link<SearchModel<Maguito>> l = new Link<SearchModel<Maguito>>("details", new Model<SearchModel<Maguito>>((SearchModel<Maguito>)this.getDefaultModelObject())) {
			public void onClick()
			 {
			    setResponsePage(new ViewItemsPage(getModelObject().getResult()));
			 }
		};
		
		form.add(l);
		
		add(form);

		
		log.debug("form construido");
	}
}
