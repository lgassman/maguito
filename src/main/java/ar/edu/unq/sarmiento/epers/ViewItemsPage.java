package ar.edu.unq.sarmiento.epers;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unq.sarmiento.epers.home.MaguitoHome;
import ar.edu.unq.sarmiento.epers.model.Item;
import ar.edu.unq.sarmiento.epers.model.Maguito;

public class ViewItemsPage extends WebPage {

	
	@Autowired
	MaguitoHome maguitoHome;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewItemsPage(Maguito maguito) {

		IModel<Maguito> model = new CompoundPropertyModel<Maguito>(maguito);
		this.setDefaultModel(model);
		
		Form<Maguito> form = new Form<Maguito>("form", model);
		
		form.add(new Label("nombre"));
		form.add(new Label("vida"));
		form.add(new Label("experiencia"));

		PropertyListView<Item> items = new PropertyListView<Item>("items") {
			@Override
			protected void populateItem(ListItem<Item> itemWrapper) {
				itemWrapper.add(new Label("nombre"));
				itemWrapper.add(new Label("peso"));
			}
		};
		form.add(items);
		items.setOutputMarkupId(true);

		
		Link<Maguito> ab = new Link<Maguito>("volver", new Model<Maguito>((Maguito)this.getDefaultModel().getObject())) {
			public void onClick()
			 {
			     setResponsePage(new HomePage((Maguito)this.getDefaultModelObject()));
			 }
		};
		
		add(ab);

		add(form);


	}

}
