package org.lunifera.examples.runtime.web.ecp.uimodel.presentation.vaadin;

import org.eclipse.emf.ecp.ecview.common.context.ContextException;
import org.eclipse.emf.ecp.ecview.common.model.core.YView;
import org.eclipse.emf.ecp.ecview.common.model.datatypes.YDatadescription;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YAlignment;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YCheckBox;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YComboBox;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YHorizontalLayout;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YHorizontalLayoutCellStyle;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YLabel;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YList;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YTable;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YTextArea;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YTextField;
import org.eclipse.emf.ecp.ecview.extension.model.extension.util.SimpleExtensionModelFactory;
import org.lunifera.web.ecp.uimodel.presentation.vaadin.VaadinRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

public class HorizontalLayoutFactory {

	private static final Logger logger = LoggerFactory
			.getLogger(UiModelDemoUI.class);

	private static final long serialVersionUID = 1L;

	private SimpleExtensionModelFactory factory = new SimpleExtensionModelFactory();

	public Component createComponent() {

		CssLayout renderingContent = new CssLayout();
		renderingContent.setSizeFull();

		// build the view model
		// ...> yView
		// ......> yLayout
		// .........> yText1
		// .........> yText2
		// .........> yText3
		// .........> yText4
		// .........> yText5
		// .........> yText6
		// .........> yText7
		// .........> yText8
		// .........> yText9
		// .........> yText10
		YView yView = factory.createView();
		yView.setCssClass("horizontalLayoutExample");

		// create the layout
		YHorizontalLayout yLayout = factory.createHorizontalLayout();
		yLayout.setCssClass("horizontalLayout");
		yView.setContent(yLayout);
		yLayout.setSpacing(true);
		yLayout.setMargin(true);

		// add label for textfields
		YLabel yLabel1 = newLabel("Textfields");
		yLayout.getElements().add(yLabel1);

		// add some text fields
		//
		YTextField yText1 = newText("Text1");
		yLayout.getElements().add(yText1);
		YTextField yText2 = newText("Text2");
		yLayout.getElements().add(yText2);
		YTextField yText3 = newText("Text3");
		yLayout.getElements().add(yText3);
		YTextField yText4 = newText("Text4");
		yLayout.getElements().add(yText4);
		YTextField yText5 = newText("Text5");
		yLayout.getElements().add(yText5);
		YTextField yText6 = newText("Text6");
		yLayout.getElements().add(yText6);
		YTextField yText7 = newText("Text7");
		yLayout.getElements().add(yText7);
		YTextField yText8 = newText("Text8");
		yLayout.getElements().add(yText8);
		YTextField yText9 = newText("Text9");
		yLayout.getElements().add(yText9);
		YTextField yText10 = newText("Text10");
		yLayout.getElements().add(yText10);

		// add label for textAreas
		YLabel yLabel2 = newLabel("TextAreas");
		yLayout.getElements().add(yLabel2);

		// add some text areas
		//
		YTextArea yTextArea1 = newTextArea("TextArea1");
		yLayout.getElements().add(yTextArea1);

		// add label for check boxes
		YLabel yLabel3 = newLabel("CheckBoxes");
		yLayout.getElements().add(yLabel3);

		// add some check boxes
		//
		YCheckBox yCheckBox1 = newCheckBox("CheckBox1");
		yLayout.getElements().add(yCheckBox1);

		// add label for combo boxes
		YLabel yLabel4 = newLabel("ComboBoxes");
		yLayout.getElements().add(yLabel4);

		// add some combo boxes
		//
		YComboBox yComboBox1 = newComboBox("ComboBox1");
		yLayout.getElements().add(yComboBox1);

		// add label for lists
		YLabel yLabel5 = newLabel("Lists");
		yLayout.getElements().add(yLabel5);

		// add some lists
		//
		YList yList1 = newList("List1");
		yLayout.getElements().add(yList1);

		// add label for tables
		YLabel yLabel6 = newLabel("Tables");
		yLayout.getElements().add(yLabel6);

		// add some tables
		//
		YTable ytable1 = newTable("Table1");
		yLayout.getElements().add(ytable1);

		// create the styling information
		//
		// // label 1 -> alignment
		YHorizontalLayoutCellStyle yStyleLabel1 = createCellStyle(yLayout,
				yLabel1);
		yStyleLabel1.setAlignment(YAlignment.TOP_LEFT);
		//
		// text 1 -> alignment
		YHorizontalLayoutCellStyle yStyle1 = createCellStyle(yLayout, yText1);
		yStyle1.setAlignment(YAlignment.TOP_LEFT);
		// text 2 -> alignment
		YHorizontalLayoutCellStyle yStyle2 = createCellStyle(yLayout, yText2);
		yStyle2.setAlignment(YAlignment.MIDDLE_CENTER);
		// text 3 -> alignment
		YHorizontalLayoutCellStyle yStyle3 = createCellStyle(yLayout, yText3);
		yStyle3.setAlignment(YAlignment.BOTTOM_RIGHT);
		// text 4 -> alignment
		YHorizontalLayoutCellStyle yStyle4 = createCellStyle(yLayout, yText4);
		yStyle4.setAlignment(YAlignment.FILL_LEFT);
		// text 5 -> alignment
		YHorizontalLayoutCellStyle yStyle5 = createCellStyle(yLayout, yText5);
		yStyle5.setAlignment(YAlignment.MIDDLE_FILL);
		// text 6 -> alignment
		YHorizontalLayoutCellStyle yStyle6 = createCellStyle(yLayout, yText6);
		yStyle6.setAlignment(YAlignment.MIDDLE_FILL);
		// text 7 -> alignment
		YHorizontalLayoutCellStyle yStyle7 = createCellStyle(yLayout, yText7);
		yStyle7.setAlignment(YAlignment.FILL_FILL);
		// text 8 -> alignment
		YHorizontalLayoutCellStyle yStyle8 = createCellStyle(yLayout, yText8);
		yStyle8.setAlignment(YAlignment.BOTTOM_LEFT);

		// label 2 -> alignment
		YHorizontalLayoutCellStyle yStyleLabel2 = createCellStyle(yLayout,
				yLabel2);
		yStyleLabel2.setAlignment(YAlignment.BOTTOM_CENTER);
		// textArea 1 -> alignment
		YHorizontalLayoutCellStyle yStyleArea1 = createCellStyle(yLayout,
				yTextArea1);
		yStyleArea1.setAlignment(YAlignment.BOTTOM_LEFT);

		// label 3 -> alignment
		YHorizontalLayoutCellStyle yStyleLabel3 = createCellStyle(yLayout,
				yLabel3);
		yStyleLabel3.setAlignment(YAlignment.BOTTOM_CENTER);
		// checkBox 1 -> alignment
		YHorizontalLayoutCellStyle yStyleChBox1 = createCellStyle(yLayout,
				yCheckBox1);
		yStyleChBox1.setAlignment(YAlignment.BOTTOM_LEFT);

		// label 4 -> alignment
		YHorizontalLayoutCellStyle yStyleLabel4 = createCellStyle(yLayout,
				yLabel4);
		yStyleLabel4.setAlignment(YAlignment.BOTTOM_CENTER);
		// comboBox 1 -> alignment
		YHorizontalLayoutCellStyle yStyleComboBox1 = createCellStyle(yLayout,
				yComboBox1);
		yStyleComboBox1.setAlignment(YAlignment.BOTTOM_LEFT);

		// label 5 -> alignment
		YHorizontalLayoutCellStyle yStyleLabel5 = createCellStyle(yLayout,
				yLabel5);
		yStyleLabel5.setAlignment(YAlignment.BOTTOM_CENTER);
		// list 1 -> alignment
		YHorizontalLayoutCellStyle yStyleList1 = createCellStyle(yLayout,
				yList1);
		yStyleList1.setAlignment(YAlignment.BOTTOM_LEFT);

		// label 6 -> alignment
		YHorizontalLayoutCellStyle yStyleLabel6 = createCellStyle(yLayout,
				yLabel6);
		yStyleLabel6.setAlignment(YAlignment.BOTTOM_CENTER);
		// comboBox 1 -> alignment
		YHorizontalLayoutCellStyle yStyleTable1 = createCellStyle(yLayout,
				ytable1);
		yStyleTable1.setAlignment(YAlignment.BOTTOM_LEFT);

		try {
			VaadinRenderer renderer = new VaadinRenderer();
			renderer.render(renderingContent, yView, null);
		} catch (ContextException e) {
			logger.error("{}", e);
		}

		return renderingContent;
	}

	protected YHorizontalLayoutCellStyle createCellStyle(
			YHorizontalLayout yHorizontalLayout, YLabel yLabel1) {
		return factory.createHorizontalLayoutCellStyle(yLabel1,
				yHorizontalLayout);
	}

	protected YHorizontalLayoutCellStyle createCellStyle(
			YHorizontalLayout yHorizontalLayout, YTextField yText1) {
		return factory.createHorizontalLayoutCellStyle(yText1,
				yHorizontalLayout);
	}

	protected YHorizontalLayoutCellStyle createCellStyle(
			YHorizontalLayout yHorizontalLayout, YTextArea yTextArea1) {
		return factory.createHorizontalLayoutCellStyle(yTextArea1,
				yHorizontalLayout);
	}

	protected YHorizontalLayoutCellStyle createCellStyle(
			YHorizontalLayout yHorizontalLayout, YCheckBox yChBox1) {
		return factory.createHorizontalLayoutCellStyle(yChBox1,
				yHorizontalLayout);
	}

	protected YHorizontalLayoutCellStyle createCellStyle(
			YHorizontalLayout yHorizontalLayout, YComboBox yComboBox1) {
		return factory.createHorizontalLayoutCellStyle(yComboBox1,
				yHorizontalLayout);
	}

	protected YHorizontalLayoutCellStyle createCellStyle(
			YHorizontalLayout yHorizontalLayout, YList yList1) {
		return factory.createHorizontalLayoutCellStyle(yList1,
				yHorizontalLayout);
	}

	protected YHorizontalLayoutCellStyle createCellStyle(
			YHorizontalLayout yHorizontalLayout, YTable yTable1) {
		return factory.createHorizontalLayoutCellStyle(yTable1,
				yHorizontalLayout);
	}

	/**
	 * Creates a new label.
	 * 
	 * @param label
	 *            the label to show
	 * @return label
	 */
	protected YLabel newLabel(String label) {
		YLabel field = factory.createLabel();
		if (label != null) {
			YDatadescription dtd = factory.createDatadescription();
			field.setDatadescription(dtd);
			dtd.setLabel(label);
		}
		return field;
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
			field.setDatadescription(dtd);
			dtd.setLabel(label);
		}
		return field;
	}

	/**
	 * Creates a new text area.
	 * 
	 * @param label
	 *            the label to show
	 * @return textArea
	 */
	protected YTextArea newTextArea(String label) {
		YTextArea area = factory.createTextArea();
		if (label != null) {
			YDatadescription dtd = factory.createDatadescription();
			area.setDatadescription(dtd);
			dtd.setLabel(label);
		}
		return area;
	}

	/**
	 * Creates a new check box.
	 * 
	 * @param label
	 *            the label to show
	 * @return checkBox
	 */
	protected YCheckBox newCheckBox(String label) {
		YCheckBox chBox = factory.createCheckBox();
		if (label != null) {
			YDatadescription dtd = factory.createDatadescription();
			chBox.setDatadescription(dtd);
			dtd.setLabel(label);
		}
		return chBox;
	}

	/**
	 * Creates a new combo box.
	 * 
	 * @param label
	 *            the label to show
	 * @return checkBox
	 */
	protected YComboBox newComboBox(String label) {
		YComboBox comboBox = factory.createComboBox();
		if (label != null) {
			YDatadescription dtd = factory.createDatadescription();
			comboBox.setDatadescription(dtd);
			dtd.setLabel(label);
		}
		return comboBox;
	}

	/**
	 * Creates a new list.
	 * 
	 * @param label
	 *            the label to show
	 * @return list
	 */
	protected YList newList(String label) {
		YList list = factory.createList();
		if (label != null) {
			YDatadescription dtd = factory.createDatadescription();
			list.setDatadescription(dtd);
			dtd.setLabel(label);
		}
		return list;
	}

	/**
	 * Creates a new table.
	 * 
	 * @param label
	 *            the label to show
	 * @return table
	 */
	protected YTable newTable(String label) {
		YTable table = factory.createTable();
		if (label != null) {
			YDatadescription dtd = factory.createDatadescription();
			table.setDatadescription(dtd);
			dtd.setLabel(label);
		}
		return table;
	}

}