package org.lunifera.examples.vaaclipse.demo1.e4.views;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
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

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.server.ClassResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
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

	private TextField numberField;

	private TextField priceField;

	private TextField nameField;

	private TextField weightField;

	private GridLayout imageWrapper;

	private Embedded imageField;

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

		Label heading = new Label("Lunifera.org - Master-Detail-Tiles");
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
				if (masterTable != null) {
					masterTable.setContainerDataSource(createContainer());
					masterTable.setVisibleColumns(new String[] { "number",
							"name", "price", "weight" });
				}
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
					if (itemId != null) {
						service.save(itemId);
						if (masterTable != null) {
							masterTable
									.setContainerDataSource(createContainer());
							masterTable.setVisibleColumns(new String[] {
									"number", "name", "price", "weight" });
						}
						masterTable.select(itemId);
					}
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
					if (masterTable != null) {
						masterTable.getContainerDataSource().addItem(newEntity);
						masterTable.select(newEntity);
						masterTable.markAsDirty();
					}
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

		content.setExpandRatio(mainLayout, 0.9f);
		content.setExpandRatio(spanner, 0.1f);

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

		UI oldUi = UI.getCurrent();
		UI.setCurrent(mainLayout.getUI());
		try {
			mainLayout.removeAllComponents();
			VaadinObservables.activateRealm(UI.getCurrent());
			// render the view again
			context = null;
			try {
				IViewEditpart viewEditpart = DelegatingEditPartManager
						.getInstance().getEditpart(yView);
				context = new ViewContext(viewEditpart);
				context.render(VaadinRenderer.UI_KIT_URI, mainLayout, null);

				bind(context);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			UI.setCurrent(oldUi);
		}
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

		presentations.clear();
		buildMap(((YView) context.getViewEditpart().getModel()).getContent());

		// bind new
		IWidgetPresentation<CssLayout> selectablePresentation = (IWidgetPresentation<CssLayout>) findPresentation("mastertable");
		if (selectablePresentation != null) {
			masterTable = (Table) selectablePresentation.getWidget()
					.getComponent(0);
			if (masterTable != null) {
				masterTable.setSelectable(true);
				masterTable.addItemClickListener(this);
				masterTable
						.addValueChangeListener(new Property.ValueChangeListener() {
							@Override
							public void valueChange(ValueChangeEvent event) {
								itemId = (ItemDTO) event.getProperty()
										.getValue();
								refreshValues();
							}
						});
				BeanItemContainer<ItemDTO> personContainer = createContainer();
				masterTable.setContainerDataSource(personContainer);

				masterTable.setVisibleColumns(new String[] { "number", "name",
						"price", "weight" });
			}
		}
		IWidgetPresentation<CssLayout> codePresentation = (IWidgetPresentation<CssLayout>) findPresentation("NUMBER");
		if (codePresentation != null) {
			numberField = (TextField) codePresentation.getWidget()
					.getComponent(0);
			numberField.setNullRepresentation("");
			numberField
					.addValueChangeListener(new Property.ValueChangeListener() {
						@Override
						public void valueChange(Property.ValueChangeEvent event) {
							itemId.setNumber((String) event.getProperty()
									.getValue());
							masterTable.setValue(itemId);
							masterTable.select(itemId);
						}
					});
		}

		IWidgetPresentation<CssLayout> pricePresentation = (IWidgetPresentation<CssLayout>) findPresentation("PRICE");
		if (pricePresentation != null) {
			priceField = (TextField) pricePresentation.getWidget()
					.getComponent(0);
			priceField.setNullRepresentation("0.00");
			priceField
					.addValueChangeListener(new Property.ValueChangeListener() {
						@Override
						public void valueChange(Property.ValueChangeEvent event) {
							itemId.setPrice((Double.valueOf((String) event
									.getProperty().getValue())));
							masterTable.select(itemId);
							masterTable.setValue(itemId);
						}
					});
		}

		IWidgetPresentation<CssLayout> weightPresentation = (IWidgetPresentation<CssLayout>) findPresentation("WEIGHT");
		if (weightPresentation != null) {
			weightField = (TextField) weightPresentation.getWidget()
					.getComponent(0);
			weightField.setNullRepresentation("0.00");
			weightField
					.addValueChangeListener(new Property.ValueChangeListener() {
						@Override
						public void valueChange(Property.ValueChangeEvent event) {
							itemId.setWeight((Double.valueOf((String) event
									.getProperty().getValue())));
							masterTable.select(itemId);
							masterTable.setValue(itemId);
						}
					});
		}

		IWidgetPresentation<CssLayout> descPresentation = (IWidgetPresentation<CssLayout>) findPresentation("NAME");
		if (descPresentation != null) {
			nameField = (TextField) descPresentation.getWidget()
					.getComponent(0);
			nameField.setNullRepresentation("");
			nameField
					.addValueChangeListener(new Property.ValueChangeListener() {
						@Override
						public void valueChange(Property.ValueChangeEvent event) {
							itemId.setName((String) event.getProperty()
									.getValue());
							masterTable.select(itemId);
						}
					});
		}

		IWidgetPresentation<CssLayout> imagePresentation = (IWidgetPresentation<CssLayout>) findPresentation("image_wrapper_v");
		if (imagePresentation != null) {
			imageWrapper = (GridLayout) imagePresentation.getWidget()
					.getComponent(0);
			ComponentContainer parent = (ComponentContainer) imageWrapper
					.getParent();
			imageField = new Embedded();
			parent.replaceComponent(imageWrapper, imageField);
			imageField.setHeight("200px");
		}

		imagePresentation = (IWidgetPresentation<CssLayout>) findPresentation("image_wrapper_h");
		if (imagePresentation != null) {
			imageWrapper = (GridLayout) imagePresentation.getWidget()
					.getComponent(0);
			ComponentContainer parent = (ComponentContainer) imageWrapper
					.getParent();
			imageField = new Embedded();
			parent.replaceComponent(imageWrapper, imageField);
			imageField.setWidth("250px");
		}

		if (itemId != null) {
			masterTable.setValue(itemId);
			masterTable.select(itemId);
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

		refreshValues();
	}

	private void refreshValues() {
		if (itemId == null) {
			return;
		}

		if (numberField != null) {
			numberField.setValue(itemId.getNumber());
		}
		if (nameField != null) {
			nameField.setValue(itemId.getName());
		}
		if (priceField != null) {
			priceField.setValue(Double.toString(itemId.getPrice()));
		}
		if (weightField != null) {
			weightField.setValue(Double.toString(itemId.getWeight()));
		}
		if (imageField != null) {
			imageField.setVisible(true);
			if (itemId.getImageIndex() < 0) {
				imageField.setVisible(false);
			} else {
				imageField.setSource(new ClassResource(ItemView.class, String
						.format("Image_%s.jpg",
								Integer.toString(itemId.getImageIndex()))));
			}
		}
	}
}