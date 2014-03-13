/*******************************************************************************
 * Copyright (c) 2012 Rushan R. Gilmullin and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Rushan R. Gilmullin - initial API and implementation
 *******************************************************************************/

package org.lunifera.examples.vaaclipse.demo1.e4.views;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Credits;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.lunifera.examples.vaaclipse.demo1.e4.data.Task;
import org.lunifera.examples.vaaclipse.demo1.e4.editors.TaskEvents;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class StatisticsView {

	@Inject
	private IEventBroker eventBroker;
	private VerticalLayout parent;

	@Inject
	public StatisticsView(VerticalLayout parent, IEclipseContext context,
			MApplication app) {
		this.parent = parent;
		parent.setSizeFull();
	}

	@PostConstruct
	protected void setup() {
		eventBroker.subscribe(TaskEvents.TOPIC_SELECTED, new EventHandler() {
			@Override
			public void handleEvent(Event event) {
				parent.removeAllComponents();
				Task task = (Task) event.getProperty("org.eclipse.e4.data");
				switch (task.getType()) {
				case ITEM:
					parent.addComponent(createChart1());
					break;
				case CRM:
					parent.addComponent(createChart2());
					break;
				}
			}
		});
	}

	public static Chart createChart1() {
		Chart chart = new Chart(ChartType.PIE);

		Configuration conf = chart.getConfiguration();

		conf.setTitle("Browser market shares at a specific website, 2010");

		PlotOptionsPie plotOptions = new PlotOptionsPie();
		plotOptions.setCursor(Cursor.POINTER);
		Labels dataLabels = new Labels();
		dataLabels.setEnabled(true);
		dataLabels.setColor(new SolidColor(0, 0, 0));
		dataLabels.setConnectorColor(new SolidColor(0, 0, 0));
		dataLabels
				.setFormatter("''+ this.point.name +': '+ this.percentage +' %'");
		plotOptions.setDataLabels(dataLabels);
		conf.setPlotOptions(plotOptions);

		DataSeries series = new DataSeries();
		series.add(new DataSeriesItem("Firefox", 45.0));
		series.add(new DataSeriesItem("IE", 26.8));
		DataSeriesItem chrome = new DataSeriesItem("Chrome", 12.8);
		chrome.setSliced(true);
		chrome.setSelected(true);
		series.add(chrome);
		series.add(new DataSeriesItem("Safari", 8.5));
		series.add(new DataSeriesItem("Opera", 6.2));
		series.add(new DataSeriesItem("Others", 0.7));
		conf.setSeries(series);

		chart.drawChart(conf);

		return chart;
	}

	protected Chart createChart2() {

		Chart chart = new Chart(ChartType.AREA);

		Configuration conf = chart.getConfiguration();

		conf.setTitle(new Title("Area chart with negative values"));

		XAxis xAxis = new XAxis();
		xAxis.setCategories("Apples", "Oranges", "Pears", "Grapes", "Bananas");
		conf.addxAxis(xAxis);

		conf.setCredits(new Credits(false));

		Tooltip tooltip = new Tooltip();
		tooltip.setFormatter("this.series.name +': '+ this.y");
		conf.setTooltip(tooltip);

		conf.addSeries(new ListSeries("John", 5, 3, 4, 7, 2));
		conf.addSeries(new ListSeries("Jane", 2, -2, -3, 2, 1));
		conf.addSeries(new ListSeries("Joe", 3, 4, 4, -2, 5));

		chart.drawChart(conf);

		return chart;
	}

}
