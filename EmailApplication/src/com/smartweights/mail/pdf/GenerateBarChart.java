/**
 * 
 */
package com.smartweights.mail.pdf;

import java.awt.Color;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.smartweights.mail.utils.DateUtils;

/**
 * @author Rama
 *
 */

@Component("generateBarChart")
public class GenerateBarChart {
	
	@Autowired
	private DateUtils dateUtils;
	

	public JFreeChart generateBarChart(List<List<Integer>> recordList) {

		final String last = "LAST WEEK";
		final String curent = "CURRENT WEEK";		
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<Integer> lastWeek = recordList.get(0);
		List<Integer> curentWeek = recordList.get(1);
		List<String> dayStrings = dateUtils.getDayStrings();
		
		
		for(int i=0; i< lastWeek.size(); i++){
			dataset.addValue((Number) lastWeek.get(i), last, dayStrings.get(i));
		}
		for(int i=0; i< curentWeek.size(); i++){
			dataset.addValue((Number) curentWeek.get(i), curent, dayStrings.get(i));
		}
		
		JFreeChart chart = ChartFactory.createBarChart3D("Total Weight Lifited ", " ", " ", dataset, PlotOrientation.VERTICAL, true, true, false);

		CategoryPlot cplot = (CategoryPlot) chart.getPlot();
		cplot.setRangeGridlinePaint(new Color(243, 243, 243));
		cplot.setBackgroundPaint(new Color(242, 244, 244));
	 
		((BarRenderer) cplot.getRenderer()).setBarPainter(new StandardBarPainter());
		BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();

		renderer.setSeriesPaint(0, new Color(194, 210, 243));
		renderer.setSeriesPaint(1, new Color(133, 164, 230));
		renderer.setItemMargin(0.1);
		return chart;
	}

}
