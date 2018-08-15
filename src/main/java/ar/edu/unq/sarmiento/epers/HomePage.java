package ar.edu.unq.sarmiento.epers;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import ar.edu.unq.sarmiento.epers.hibernate.MaguitoHome;
import ar.edu.unq.sarmiento.epers.model.Item;
import ar.edu.unq.sarmiento.epers.model.Maguito;
import ar.edu.unq.sarmiento.epers.model.SearchModel;

public class HomePage extends WebPage {

	private static final long serialVersionUID = -3657874371670902273L;
	Form<SearchModel<Maguito>> form = null;

	public HomePage() {


		IModel<SearchModel<Maguito>> model = new CompoundPropertyModel<SearchModel<Maguito>>(new SearchModel<Maguito>(MaguitoHome.getInstance()));
		form = new Form<SearchModel<Maguito>>("form", model);

		form.add(new TextField<String>("search"));

		form.add(new Label("message"));

		form.add(new Label("result.nombre"));
		form.add(new Label("result.vida"));
		
		

	    PropertyListView<Item> items = new PropertyListView<Item>("result.items") {

	        @Override
	        protected void populateItem(ListItem<Item> itemWrapper) {
	        	itemWrapper.add(new Label("nombre"));
	        	itemWrapper.add(new Label("peso"));
	        }
	    };
		form.add(items);
		items.setOutputMarkupId(true);
		

		AjaxButton ab = new AjaxButton("action") {
			@Override
			protected void onSubmit(AjaxRequestTarget target) {

				if (target != null) {
					model.getObject().find();
					target.add(form);
				}

			}

		};

		form.add(ab);
		add(form);

	}

	private String getLocation(Integer cyCode) {

		Map<Integer, String> locations = new HashMap<Integer, String>();
		locations.put(1, "USA");
		locations.put(61, "Australia");
		locations.put(91, "India");
		return locations.get(cyCode);
	}
}
