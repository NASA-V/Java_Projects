package Regression_Analysis.pkg;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.function.PowerFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
public class RegressionDemo1 extends ApplicationFrame
{
    static class DemoPanel extends JPanel
    {
        private XYDataset createSampleData1()
        {
            XYSeries xyseries = new XYSeries("Series 1");
            xyseries.add(2D, 56.270000000000003D);
            xyseries.add(3D, 41.32D);
            xyseries.add(4D, 31.449999999999999D);
            xyseries.add(5D, 30.050000000000001D);
            xyseries.add(6D, 24.690000000000001D);
            xyseries.add(7D, 19.780000000000001D);
            xyseries.add(8D, 20.940000000000001D);
            xyseries.add(9D, 16.73D);
            xyseries.add(10D, 14.210000000000001D);
            xyseries.add(11D, 12.44D);
            XYSeriesCollection xyseriescollection = new XYSeriesCollection(xyseries);
            return xyseriescollection;
        }
        private JTabbedPane createContent()
        {
            JTabbedPane jtabbedpane = new JTabbedPane();
            jtabbedpane.add("Linear", createChartPanel1());
            jtabbedpane.add("Power", createChartPanel2());
            return jtabbedpane;
        }
        private ChartPanel createChartPanel1()
        {
            NumberAxis numberaxis = new NumberAxis("X");
            numberaxis.setAutoRangeIncludesZero(false);
            NumberAxis numberaxis1 = new NumberAxis("Y");
            XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(false, true);
            XYPlot xyplot = new XYPlot(createSampleData1(), numberaxis, numberaxis1, xylineandshaperenderer);
            double ad[] = Regression.getOLSRegression(createSampleData1(), 0);
            LineFunction2D linefunction2d = new LineFunction2D(ad[0], ad[1]);
            XYDataset xydataset = DatasetUtilities.sampleFunction2D(linefunction2d, 2D, 11D, 100, "Fitted Regression Line");
            xyplot.setDataset(1, xydataset);
            XYLineAndShapeRenderer xylineandshaperenderer1 = new XYLineAndShapeRenderer(true, false);
            xylineandshaperenderer1.setSeriesPaint(0, Color.blue);
            xyplot.setRenderer(1, xylineandshaperenderer1);
            xyplot.setBackgroundPaint(new Color(255, 255, 255));
            //sect1
            JFreeChart jfreechart = new JFreeChart("Linear Regression", JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);
            //sect 2
            ChartPanel chartpanel = new ChartPanel(jfreechart, false);
            return chartpanel;
        }
        private ChartPanel createChartPanel2()
        {
            NumberAxis numberaxis = new NumberAxis("X");
            numberaxis.setAutoRangeIncludesZero(true);
            NumberAxis numberaxis1 = new NumberAxis("Y");
            numberaxis1.setAutoRangeIncludesZero(true);
            XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(false, true);
            XYPlot xyplot = new XYPlot(data1, numberaxis, numberaxis1, xylineandshaperenderer);
            double ad[] = Regression.getPowerRegression(data1, 0);
            PowerFunction2D powerfunction2d = new PowerFunction2D(ad[0], ad[1]);
            XYDataset xydataset = DatasetUtilities.sampleFunction2D(powerfunction2d, 2D, 11D, 100, "Fitted Regression Line");
            XYLineAndShapeRenderer xylineandshaperenderer1 = new XYLineAndShapeRenderer(true, false);
            xylineandshaperenderer1.setSeriesPaint(0, Color.blue);
            xyplot.setDataset(1, xydataset);
            xyplot.setRenderer(1, xylineandshaperenderer1);
            JFreeChart jfreechart = new JFreeChart("Power Regression", JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);
            ChartPanel chartpanel = new ChartPanel(jfreechart, false);
            return chartpanel;
        }
        private XYDataset data1;
        public DemoPanel()
        {
            super(new BorderLayout());
            data1 = createSampleData1();
            add(createContent());
        }
    }
    public RegressionDemo1(String s)
    {
        super(s);
        JPanel jpanel = createDemoPanel();
        getContentPane().add(jpanel);
    }
    public static JPanel createDemoPanel()
    {
        return new DemoPanel();
    }
    public static void main(String args[])
    {
        RegressionDemo1 regressiondemo1 = new RegressionDemo1("Regression Demo 1");
        regressiondemo1.pack();
        RefineryUtilities.centerFrameOnScreen(regressiondemo1);
        regressiondemo1.setVisible(true);
    }
}