package analiza_bukmacherska;

import analiza_bukmacherska.z2_Tabele_Forma.Forma;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.Rotation;
import tabela_ligowa.TabelaLigowa;
import tabela_ligowa.WybranaDruzyna;
import wybierz_lige.WybierzLige;
import wybierz_lige.WybranaLiga;

public class z3_TeamVsTeam extends JLayeredPane{
    
    String currentTeam1;
    String currentTeam2;
    
    JPanel chart1;
    JPanel chart2;
    
    //* forma *//
    JButton label1;
    JButton label3;
    JButton label2;
    JButton label4;
    JButton label5;
    JButton label6;
    
    JButton label7;
    JButton label8;
    JButton label9;
    JButton label10;
    JButton label11;
    JButton label12;
    
    ChartPanel currPanel1;
    ChartPanel currPanel2;
    
    JTextField team1;
    JTextField team2;
    
    String wybrana_liga;
    Forma form;
    WybierzLige mwybierzLige;
    TabelaLigowa mtabelaLigowa;
    JScrollPane jSPliga1;
    
    Connection conn;
    Statement stat;
    public z3_TeamVsTeam() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:mydb.db");
        stat = conn.createStatement();
        
        currentTeam1 = "Arsenal";
        currentTeam2 = "Liverpool";
                        
        setBounds(0, 0, 1024, 530);
        
        chart1 = new JPanel(new BorderLayout());
        chart1.setBackground(Color.LIGHT_GRAY);
        chart2 = new JPanel(new BorderLayout());
        
        chart2.setBackground(Color.LIGHT_GRAY);
        chart1.setBounds(5, 5, 390, 350);
        chart2.setBounds(400, 5, 390, 350);
        
        add(chart1, BorderLayout.CENTER);
        add(chart2, BorderLayout.CENTER);
        
        label1 = new JButton("LAST 5");
        label2 = new JButton("LAST 5");
        
        label1.setBounds(5, 360, 127, 53);
        label2.setBounds(400, 360, 127, 53);
        
        label3 = new JButton("LAST 10");
        label4 = new JButton("LAST 10");
        
        label3.setBounds(137, 360, 127, 53);
        label4.setBounds(532, 360, 127, 53);
        
        label5 = new JButton("LAST 15");
        label6 = new JButton("LAST 15");
        
        label5.setBounds(269, 360, 127, 53);
        label6.setBounds(664, 360, 127, 53);
        
        label1.setBackground(Color.LIGHT_GRAY);
        label2.setBackground(Color.GRAY);
        label3.setBackground(Color.GRAY);
        label4.setBackground(Color.LIGHT_GRAY);
        label5.setBackground(Color.LIGHT_GRAY);
        label6.setBackground(Color.GRAY);
        
        label7 = new JButton("% OF WON");
        label8 = new JButton("% OF WON");
        label7.setBounds(5, 418, 127, 53);
        label8.setBounds(400, 418, 127, 53);
        label7.setBackground(Color.GRAY);
        label8.setBackground(Color.LIGHT_GRAY);
        
        label9 = new JButton("SAVE AS PNG");
        label10 = new JButton("SAVE AS PNG");
        label9.setBounds(269, 418, 127, 53);
        label10.setBounds(664, 418, 127, 53);
        label9.setBackground(Color.GRAY);
        label10.setBackground(Color.LIGHT_GRAY);
        label9.setEnabled(false);
        label10.setEnabled(false);
        
        label11 = new JButton("TIME SERIES");
        label12 = new JButton("TIME SERIES");
        label11.setBounds(137, 418, 127, 53);
        label12.setBounds(532, 418, 127, 53);
        label11.setBackground(Color.LIGHT_GRAY);
        label12.setBackground(Color.GRAY);
        
        team1 = new JTextField("Arsenal");
        team2 = new JTextField("Liverpool");
        currentTeam1 = team1.getText();
        currentTeam2 = team2.getText();
        
        team1.setBounds(796, 418, 220, 24);
        team2.setBounds(796, 446, 220, 24);
                
        label1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    drawFormChart(chart1, team1.getText(), 5);
                } catch (SQLException ex) {
                    Logger.getLogger(z3_TeamVsTeam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(chart1, "Wybierz poprawną drużynę");
                }
            }
        });     
        
        label2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    drawFormChart(chart2, team2.getText(), 5);
                } catch (SQLException ex) {
                    Logger.getLogger(z3_TeamVsTeam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(chart2, "Wybierz poprawną drużynę");
                }
            }
        }); 
        
        label3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    drawFormChart(chart1, team1.getText(), 10);
                } catch (SQLException ex) {
                    Logger.getLogger(z3_TeamVsTeam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(chart1, "Wybierz poprawną drużynę");
                }
            }
        });  
        label4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    drawFormChart(chart2, team2.getText(), 10);
                } catch (SQLException ex) {
                    Logger.getLogger(z3_TeamVsTeam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(chart2, "Wybierz poprawną drużynę");
                }
            }
        });  
        label5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    drawFormChart(chart1, team1.getText(), 15);
                } catch (SQLException ex) {
                    Logger.getLogger(z3_TeamVsTeam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(chart1, "Wybierz poprawną drużynę");
                }
            }
        });  
        label6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    drawFormChart(chart2, team2.getText(), 15);
                } catch (SQLException ex) {
                    Logger.getLogger(z3_TeamVsTeam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(chart2, "Wybierz poprawną drużynę");
                }
            }
        }); 
        
        label7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    drawPercOfWinChart(chart1, team1.getText(), 15);
                } catch (SQLException ex) {
                    Logger.getLogger(z3_TeamVsTeam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(chart1, "Wybierz poprawną drużynę");
                }
            }
        }); 
        
        label8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    drawPercOfWinChart(chart2, team2.getText(), 15);
                } catch (SQLException ex) {
                    Logger.getLogger(z3_TeamVsTeam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(chart2, "Wybierz poprawną drużynę");
                }
            }
        }); 
        
        label9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try { 
                    JFreeChart ch = currPanel1.getChart();
                    ChartUtilities.saveChartAsPNG(new File("chart.png"), ch, 390, 350);
                } catch (IOException ex) {
                    Logger.getLogger(z3_TeamVsTeam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(chart1, "IOError");
                }
               
            }
        }); 
        
        label10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try { 
                    JFreeChart ch = currPanel2.getChart();
                    ChartUtilities.saveChartAsPNG(new File("chart.png"), ch, 390, 350);
                } catch (IOException ex) {
                    Logger.getLogger(z3_TeamVsTeam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(chart2, "IOError");
                }
               
            }
        }); 
        
        label11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    drawXYChart(chart1, team1.getText(), 30);
                } catch (SQLException ex) {
                    Logger.getLogger(z3_TeamVsTeam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(chart1, "Wybierz poprawną drużynę");
                }
            }
        }); 
        
        label12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    drawXYChart(chart2, team2.getText(), 30);
                } catch (SQLException ex) {
                    Logger.getLogger(z3_TeamVsTeam.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(chart2, "Wybierz poprawną drużynę");
                }
            }
        }); 
        
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(label8);
        add(label9);
        add(label10);
        add(label11);
        add(label12);
        
        add(team1);
        add(team2);
        WybranaLiga  wybranaLiga = new WybranaLiga("E0");
        WybranaDruzyna wybranaDruzyna = new WybranaDruzyna();
        form = new Forma(wybranaDruzyna);
        mwybierzLige = new WybierzLige(wybranaLiga);
        
        mwybierzLige.setBounds(795 ,5, 220, 530);
        add(mwybierzLige);
    }
    
    private void fillJList()
    {
        
    }
    
    private void drawFormChart(JPanel panel, String team, int n) throws SQLException
    {
        DefaultCategoryDataset dataset = createFormDataset(team, n);
        JFreeChart chart = createFormChart(team, dataset);
        chart.setBackgroundPaint(new java.awt.Color(209, 210, 211));
        ChartPanel chartPanel = new ChartPanel(chart);
        
        if(panel == chart1)
        {
            currPanel1 = chartPanel;
            label9.setEnabled(true);
        }
        else 
        {
            currPanel2 = chartPanel;
            label10.setEnabled(true);
        }
        
        chartPanel.setPreferredSize(new Dimension(390, 360));
        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();
        panel.repaint();
    }
    
    private JFreeChart createFormChart(String team, CategoryDataset dataset)
    {
        JFreeChart chart = ChartFactory.createBarChart3D(
            "FORMA DRUŻYNY " + team ,         // chart title
            "",               // domain axis label
            "ILOŚĆ GOLI",             // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            false,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );
         // set the background color for the chart...
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new java.awt.Color(209, 210, 211));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        ValueAxis yaxis = plot.getRangeAxis();
        yaxis.setTickLabelFont(new Font("Arial Black", 1, 10));
        yaxis.setLabelFont(new Font("Arial Black", 1, 10));
        
        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setMaximumBarWidth(.10);
        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        
        chart.setTitle(
            new org.jfree.chart.title.TextTitle("FORMA DRUŻYNY " + team,
            new Font("Arial Black", 1, 10))
        );
        
        //kolorki      
        BarRenderer r = (BarRenderer) chart.getCategoryPlot().getRenderer();
        r.setSeriesPaint(0, new Color( 255,165,0));
        r.setSeriesPaint(1, Color.BLACK);
        return chart;       
    }
    
    //DataSet dla Formy danej druzyny
    private DefaultCategoryDataset createFormDataset(String team, int n) throws SQLException
    {
        //*SQL STUFF
        String stringQuery = "SELECT HomeTeam, FTAG FROM MECZE_STATYSTYKI WHERE AwayTeam = '" + 
                             team + "' ORDER BY DATA LIMIT " + Integer.toString(n) + ";";
        //  
        ResultSet r = stat.executeQuery(stringQuery);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
               
        while(r.next())
        {
            dataset.setValue(Integer.parseInt(r.getString(2)), 
                                "ilosc-goli", 
                                r.getString(1));
        }        
        return dataset;
    }
    
    private void drawPercOfWinChart(JPanel panel, String team, int n) throws SQLException
    {
        PieDataset dataset = createPieDataset(team, n);
        JFreeChart chart = createPieChart(team, dataset);
        chart.setBackgroundPaint(new java.awt.Color(209, 210, 211));
        ChartPanel chartPanel = new ChartPanel(chart);        
        chartPanel.setPreferredSize(new Dimension(390, 360));
        
        if(panel == chart1)
        {
            currPanel1 = chartPanel;
            label9.setEnabled(true);
        }
        else 
        {
            currPanel2 = chartPanel;
            label10.setEnabled(true);
        }
        
        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();
        panel.repaint();
    }
    
    private DefaultPieDataset createPieDataset(String team, int n) throws SQLException
    {
        //*SQL STUFF
        String stringQuery = "SELECT HomeTeam, FTAG, FTHG FROM MECZE_STATYSTYKI WHERE AwayTeam = '" + 
                             team + "' ORDER BY DATA LIMIT " + Integer.toString(n) + ";";
        //  
        ResultSet r = stat.executeQuery(stringQuery);
        
        final DefaultPieDataset result = new DefaultPieDataset();
        int draw = 0, win = 0, loses = 0;
        while(r.next())
        {   
            int FTAG = Integer.parseInt(r.getString(2));
            int FTHG = Integer.parseInt(r.getString(3));
            if(FTAG < FTHG) {
                loses++;
            }
            else if(FTAG == FTHG) draw++;
            else win++;
        }
        
        int sum = draw + win + loses;
        result.setValue("Win", new Double((double) win/sum));
        result.setValue("Draw", new Double((double) draw/sum));
        result.setValue("Loses", new Double((double) loses/sum));
        return result;
    }
    
    //win/draw/loses
    private JFreeChart createPieChart(String team, PieDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createPieChart3D(
            "SKUTECZNOŚĆ " + team,  // chart title
            dataset,                // data
            true,                   // include legend
            false,
            false
        );
      
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        
        
        Color[] colors = {new Color(255,165,0), Color.yellow, Color.BLACK}; 
        PieRenderer renderer = new PieRenderer(colors); 
        renderer.setColor(plot, dataset); 
        
        chart.setTitle(
            new org.jfree.chart.title.TextTitle("SKUTECZNOŚĆ " + team,
            new Font("Arial Black", 1, 10))
        );
        return chart;        
    }
    
    private JFreeChart createXYChart(String team) throws SQLException
    {
        XYSeries series = createXYDatasetFTAG(team, 30);
        XYSeries series2 = createXYDatasetFTHG(team, 30);
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        dataset.addSeries(series2);
        JFreeChart chart = ChartFactory.createXYLineChart(
            "PERFORMANCE " + team, // Title
            "SPOTKANIA", // x-axis Label
            "GOLE", // y-axis Label
            dataset, // Dataset
            PlotOrientation.VERTICAL, // Plot Orientation
            false, // Show Legend
            true, // Use tooltips
            false // Configure chart to generate URLs?
            );
        
        XYPlot plot = (XYPlot) chart.getPlot();
        MyXYAreaRenderer renderer = new MyXYAreaRenderer();
        plot.setRenderer(0, renderer);
        renderer.setSeriesFillPaint(0, new GradientPaint(0f, 0f, Color.red, 0f, 0f, Color.orange));
        renderer.setOutline(true);
        renderer.setSeriesOutlinePaint(0, Color.black);
        chart.setTitle(
            new org.jfree.chart.title.TextTitle("FORMA DRUŻYNY " + team,
            new Font("Arial Black", 1, 10))
        );
        
        return chart;
    }
    
     /*private double[][] createSeriesFTAG(int MAX, String team) throws SQLException {
        String stringQuery = "SELECT HomeTeam, FTAG, FTHG FROM MECZE_STATYSTYKI WHERE AwayTeam = '" + 
                             team + "' ORDER BY DATA LIMIT " + Integer.toString(MAX) + ";";
        //  
        ResultSet r = stat.executeQuery(stringQuery);
        double[][] series = new double[2][MAX];
        XYSeries a = new 
        int i = 0;
        while(r.next())
        {
            dataset.add(i, Integer.parseInt(r.getString(3)));
            i++;
        }
        
            series[0][i] = (double) i;
            series[1][i] = mean + random.nextGaussian() / 2;
            
        }
        return series;
    }*/
    
    private XYSeries createXYDatasetFTHG(String team, int n) throws SQLException
    {
        //*SQL STUFF
        String stringQuery = "SELECT HomeTeam, FTAG, FTHG FROM MECZE_STATYSTYKI WHERE AwayTeam = '" + 
                             team + "' ORDER BY DATA LIMIT " + Integer.toString(n) + ";";
        //  
        ResultSet r = stat.executeQuery(stringQuery);
        
        XYSeries dataset = new XYSeries("gole stracone");
        int i = 0;
        while(r.next())
        {
            dataset.add(i, Integer.parseInt(r.getString(3)));
            i++;
        }
        return dataset;
    }
    
    private XYSeries createXYDatasetFTAG(String team, int n) throws SQLException
    {
        //*SQL STUFF
        String stringQuery = "SELECT HomeTeam, FTAG, FTHG FROM MECZE_STATYSTYKI WHERE AwayTeam = '" + 
                             team + "' ORDER BY DATA LIMIT " + Integer.toString(n) + ";";
        //  
        ResultSet r = stat.executeQuery(stringQuery);
        
        XYSeries dataset = new XYSeries("gole strzelone");
        int i = 0;
        while(r.next())
        {
            dataset.add(i, Integer.parseInt(r.getString(2)));
            i++;
        }
        return dataset;
    }
    
    private void drawXYChart(JPanel panel, String team, int n) throws SQLException
    {
        JFreeChart chart = createXYChart(team);
        chart.setBackgroundPaint(new java.awt.Color(209, 210, 211));
        ChartPanel chartPanel = new ChartPanel(chart);        
        chartPanel.setPreferredSize(new Dimension(390, 360));
        
        if(panel == chart1)
        {
            currPanel1 = chartPanel;
            label9.setEnabled(true);
        }
        else 
        {
            currPanel2 = chartPanel;
            label10.setEnabled(true);
        }
        
        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();
        panel.repaint();
    }
}

