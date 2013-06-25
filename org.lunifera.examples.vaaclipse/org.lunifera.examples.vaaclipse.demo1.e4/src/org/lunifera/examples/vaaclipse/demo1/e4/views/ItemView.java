package org.lunifera.examples.vaaclipse.demo1.e4.views;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.emf.ecp.ecview.common.beans.ISlot;
import org.eclipse.emf.ecp.ecview.common.context.ContextException;
import org.eclipse.emf.ecp.ecview.common.context.IViewContext;
import org.eclipse.emf.ecp.ecview.common.context.ViewContext;
import org.eclipse.emf.ecp.ecview.common.editpart.DelegatingEditPartManager;
import org.eclipse.emf.ecp.ecview.common.editpart.IFieldEditpart;
import org.eclipse.emf.ecp.ecview.common.editpart.ILayoutEditpart;
import org.eclipse.emf.ecp.ecview.common.editpart.IViewEditpart;
import org.eclipse.emf.ecp.ecview.common.model.core.YEmbeddable;
import org.eclipse.emf.ecp.ecview.common.model.core.YField;
import org.eclipse.emf.ecp.ecview.common.model.core.YLayout;
import org.eclipse.emf.ecp.ecview.common.model.core.YView;
import org.eclipse.emf.ecp.ecview.common.model.datatypes.YDatadescription;
import org.eclipse.emf.ecp.ecview.common.presentation.IWidgetPresentation;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YGridLayout;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YGridLayoutCellStyle;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YTextField;
import org.eclipse.emf.ecp.ecview.extension.model.extension.util.SimpleExtensionModelFactory;
import org.lunifera.examples.ecview.api.service.IECViewModelService;
import org.lunifera.examples.vaaclipse.demo1.e4.Demo1Activator;
import org.lunifera.examples.vaaclipse.demo1.e4.editors.TaskEvents;
import org.lunifera.examples.vaaclipse.demo1.e4.views.model.ItemDTO;
import org.lunifera.examples.vaaclipse.demo1.e4.views.model.ItemDTOService;
import org.lunifera.runtime.web.ecview.presentation.vaadin.VaadinRenderer;
import org.lunifera.runtime.web.vaadin.databinding.VaadinObservables;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.semanticsoft.vaaclipse.publicapi.resources.ResourceHelper;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Specify the class name after the factory name.
 */
public class ItemView implements ItemClickEvent.ItemClickListener,
		IECViewModelService {

	private static final long serialVersionUID = 1L;

	private Map<String, IWidgetPresentation<?>> presentations = new HashMap<String, IWidgetPresentation<?>>();

	private SimpleExtensionModelFactory factory = new SimpleExtensionModelFactory();

	private CssLayout mainLayout;

	private Table masterTable;

	private IViewContext context;

	private ItemDTO itemId;

	private ItemDTOService service = new ItemDTOService();

	private IEventBroker eventBroker;
	private Label label;

	private EventHandler selectionChangedHandler = new EventHandler() {
		public void handleEvent(Event event) {
		}
	};

	@PostConstruct
	void registerHandler() {
		eventBroker.subscribe(TaskEvents.TOPIC_SELECTED,
				selectionChangedHandler);
	}

	@PreDestroy
	void unregisterHandlers() {
		eventBroker.unsubscribe(selectionChangedHandler);
	}

	@Inject
	public ItemView(VerticalLayout parent, IEclipseContext context,
			MApplication app) {

		init(parent);
		eventBroker = context.get(IEventBroker.class);
		Demo1Activator.getInstance().register(this);
	}

	@SuppressWarnings("serial")
	public void init(VerticalLayout content) {

		// create ui
		content.addStyleName(Reindeer.LAYOUT_BLUE);
		content.setMargin(new MarginInfo(false, true, true, true));

		content.addStyleName(Reindeer.LAYOUT_BLUE);
		content.setSpacing(true);
		content.setSizeFull();

		// icePush = new ICEPush();
		// addComponent(icePush);

		Label heading = new Label("Designstudy - Master-Detail-Tiles");
		heading.setWidth("100%");
		heading.addStyleName(Reindeer.LABEL_H1);
		content.addComponent(heading);
		content.setComponentAlignment(heading, Alignment.TOP_CENTER);

		HorizontalLayout toolbar = new HorizontalLayout();
		toolbar.setSpacing(true);
		toolbar.setMargin(new MarginInfo(false, false, false, true));
		toolbar.setWidth("150px");
		toolbar.setHeight("48px");
		toolbar.addStyleName("ec_toolbar");
		content.addComponent(toolbar);

		Embedded load = new Embedded();
		load.setDescription("Load");
		load.setSource(ResourceHelper
				.createResource("platform:/plugin/org.lunifera.examples.vaaclipse.demo1.e4/img/LoadC.png"));
		load.setWidth("32px");
		toolbar.addComponent(load);
		load.addClickListener(new MouseEvents.ClickListener() {
			@Override
			public void click(ClickEvent event) {
				masterTable.setContainerDataSource(createContainer());
			}
		});

		Embedded save = new Embedded();
		save.setDescription("Save");
		save.setSource(ResourceHelper
				.createResource("platform:/plugin/org.lunifera.examples.vaaclipse.demo1.e4/img/SaveC.png"));
		save.setWidth("32px");
		toolbar.addComponent(save);
		save.addClickListener(new MouseEvents.ClickListener() {
			@Override
			public void click(ClickEvent event) {
				if (context != null && service != null) {
					ItemDTO person = (ItemDTO) context.getBean("master");
					service.save(person);
					masterTable.setContainerDataSource(createContainer());
					masterTable.setValue(person);
				}
			}
		});

		Embedded add = new Embedded();
		add.setDescription("Add");
		add.setSource(ResourceHelper
				.createResource("platform:/plugin/org.lunifera.examples.vaaclipse.demo1.e4/img/AddC.png"));
		add.setWidth("32px");
		toolbar.addComponent(add);
		add.addClickListener(new MouseEvents.ClickListener() {
			@Override
			public void click(ClickEvent event) {
				if (context != null && service != null) {
					ItemDTO newEntity = service.createNew();
					ISlot bean = context.getBeanSlot("master");
					bean.setValue(newEntity);
				}
			}
		});

		Label toolbarspanner = new Label();
		toolbar.addComponent(toolbarspanner);
		toolbar.setExpandRatio(toolbarspanner, 1.0f);

		mainLayout = new CssLayout();
		mainLayout.setSizeFull();
		content.addComponent(mainLayout);

		CssLayout spanner = new CssLayout();
		spanner.setSizeFull();
		content.addComponent(spanner);

		content.setExpandRatio(mainLayout, 0.5f);
		content.setExpandRatio(spanner, 0.5f);

	}

	@Override
	public void setModel(String viewModel) {

	}

	@Override
	public void delegateSetModel(YView view) {
		showUI(view);
	}

	public void showUI(YView yView) {
		if (yView == null) {
			return;
		}

		// if (context != null) {
		// context.dispose();
		// context = null;
		// }

		mainLayout.removeAllComponents();

		VaadinObservables.activateRealm(mainLayout.getUI());
		// render the view again
		context = null;
		try {
			IViewEditpart viewEditpart = DelegatingEditPartManager
					.getInstance().getEditpart(yView);
			context = new ViewContext(viewEditpart);
			context.render(VaadinRenderer.UI_KIT_URI, mainLayout, null);

		} catch (ContextException e) {
			e.printStackTrace();
		}

		bind(context);

	}

	/**
	 * Binds the table to the model.<br>
	 * TODO will be extracted to UI model core if function available!
	 * 
	 * @param context
	 */
	private void bind(IViewContext context) {
		// unbind old
		if (masterTable != null) {
			masterTable.removeItemClickListener(this);
		}

		buildMap(((YView) context.getViewEditpart().getModel()).getContent());

		// bind new
		IWidgetPresentation<CssLayout> selectablePresentation = (IWidgetPresentation<CssLayout>) findPresentation("mastertable");
		if (selectablePresentation != null) {
			masterTable = (Table) selectablePresentation.getWidget()
					.getComponent(0);
			if (masterTable != null) {
				masterTable.setSelectable(true);
				masterTable.addItemClickListener(this);
				BeanItemContainer<ItemDTO> personContainer = createContainer();
				masterTable.setContainerDataSource(personContainer);

				context.setBean("master", service.getAll());
			}
		}

		if (itemId != null) {
			ISlot slot = context.getBeanSlot("master");
			masterTable.setValue(itemId);
			slot.setValue(itemId);
		}
	}

	private IWidgetPresentation<?> findPresentation(String id) {
		return presentations.get(id);
	}

	private void buildMap(YEmbeddable child) {
		if (child != null) {
			if (child instanceof YLayout) {
				if (!child.getId().equals("")) {
					ILayoutEditpart lep = DelegatingEditPartManager
							.getInstance().getEditpart(child);
					presentations.put(child.getId(), lep.getPresentation());
				}

				for (YEmbeddable subChild : ((YLayout) child).getElements()) {
					buildMap(subChild);
				}
			} else {
				YField field = (YField) child;
				if (!field.getId().equals("")) {
					IFieldEditpart lep = DelegatingEditPartManager
							.getInstance().getEditpart(field);
					presentations.put(child.getId(), lep.getPresentation());
				}
			}
		}
	}

	/**
	 * Creates the data container.
	 * 
	 * @return
	 */
	private BeanItemContainer<ItemDTO> createContainer() {
		BeanItemContainer<ItemDTO> personContainer = new BeanItemContainer<ItemDTO>(
				ItemDTO.class);
		if (service != null) {
			personContainer.addAll(service.getAll());
		}
		return personContainer;
	}

	protected YGridLayoutCellStyle createCellStyle(YGridLayout yGridLayout,
			YTextField yText1) {
		return factory.createGridLayoutCellStyle();
	}

	/**
	 * Creates a new text field.
	 * 
	 * @param label
	 *            the label to show
	 * @return textField
	 */
	protected YTextField newText(String label) {
		YTextField field = factory.createTextField();
		if (label != null) {
			YDatadescription dtd = factory.createDatadescription();
			dtd.setLabel(label);
			field.setDatadescription(dtd);
		}
		return field;
	}

	private YView createDefaultView() {
		YView yView = factory.createView();
		YGridLayout ymainLayout = factory.createGridLayout();
		yView.setContent(ymainLayout);
		applyLayout(ymainLayout, true);
		return yView;
	}

	/**
	 * Applies the layout settings. Horizontal or vertical.
	 * 
	 * @param ymainLayout
	 * @param horizontal
	 */
	private void applyLayout(YGridLayout ymainLayout, boolean horizontal) {
		if (horizontal) {
			ymainLayout.setColumns(2);
			ymainLayout.setCssClass(Reindeer.LAYOUT_BLUE);
		} else {
			ymainLayout.setColumns(1);
		}
		ymainLayout.setSpacing(true);
		ymainLayout.setFillHorizontal(true);
		ymainLayout.setFillVertical(true);
	}

	@Override
	public void itemClick(ItemClickEvent event) {
		itemId = (ItemDTO) event.getItemId();
		if (context != null) {
			ISlot slot = context.getBeanSlot("master");
			slot.setValue(itemId);
		}
	}
}