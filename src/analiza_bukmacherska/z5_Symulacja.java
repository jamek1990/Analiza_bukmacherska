package analiza_bukmacherska;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.SeriesException;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
*
* @author User
*/
public class z5_Symulacja extends JLayeredPane{
    m1_okienko jPPreferencje;
    m1_okienko jPWykres;
    m1_okienko jPTabela;

    //textfieldy, labele, buttony
    JTextField jTFData1;
    JTextField jTFData2;
    JTextField jTFSaldo;

    JButton jBSymulacja;

    JLabel jLData1;
    JLabel jLData2;
    JLabel jLSaldo;
    JLabel jLStanPoczatkowy;
    JLabel jLStan;
    //z5_SymulacjaPanel z5;

    private Test test; //generuje strategie
    private Vector<mecz> mecze; //mecze do obstawienia
    private double stanKonta;
    JFreeChart chart;
    public z5_Symulacja(){
        stanKonta = 0;
        test = new Test();
        //z5 = new z5_SymulacjaPanel();
        //add(z5);
        //z5.setBounds(0, 0, 1024, 530);
        setLocation(0,0);
        setBounds(0, 0, 1024, 530);

        //okienko z preferencjami
        jPPreferencje = new m1_okienko(180, 513, 5, 5, "PREFERENCJE");
        jPPreferencje.setBackground(new java.awt.Color(209, 210, 211));
        jPPreferencje.setOpaque(true);

        jPWykres = new m1_okienko(825, 228, 190, 290, "STAN KONTA");
        jPWykres.setBackground(new java.awt.Color(209, 210, 211));
        jPWykres.setOpaque(true);

        jPTabela = new m1_okienko(825, 280, 190, 5, "TABELA");
        jPTabela.setBackground(new java.awt.Color(209, 210, 211));
        jPTabela.setOpaque(true);

        //elementy preferencji
        jLData1 = new JLabel("DATA ROZPOCZĘCIA");
        jLData1.setHorizontalAlignment(SwingConstants.CENTER);
        jLData1.setBounds(10, 30, 160, 20);

        jTFData1 = new JTextField("20080101");
        jTFData1.setBounds(10, 55, 160, 20);

        jLData2 = new JLabel("DATA ZAKOŃCZENIA");
        jLData1.setHorizontalAlignment(SwingConstants.CENTER);
        jLData2.setBounds(10, 80, 160, 20);

        jTFData2 = new JTextField("20090101");
        jTFData2.setBounds(10, 105, 160, 20);

        jLSaldo = new JLabel("SALDO POCZĄTKOWE");
        jLSaldo.setHorizontalAlignment(SwingConstants.CENTER);
        jLSaldo.setBounds(10, 130, 160, 20);

        jTFSaldo = new JTextField("100");
        jTFSaldo.setBounds(10, 155, 160, 20);

        jLStanPoczatkowy = new JLabel("0.00");
        jLStanPoczatkowy.setBounds(10, 180, 160, 40);
        jLStanPoczatkowy.setFont(new Font("Arial Black", 1, 28));
        jLStanPoczatkowy.setHorizontalAlignment(SwingConstants.CENTER);
        jLStanPoczatkowy.setVerticalAlignment(SwingConstants.CENTER);
        jLStan = new JLabel("0.00");
        jLStan.setBounds(10, 225, 160, 40);
        jLStan.setFont(new Font("Arial Black", 1, 28));
        jLStan.setHorizontalAlignment(SwingConstants.CENTER);
        jLStan.setVerticalAlignment(SwingConstants.CENTER);

        jBSymulacja = new JButton("SYMULUJ");
        jBSymulacja.setBackground(Color.GRAY);
        jBSymulacja.setBounds(10, 270, 160, 40);

        //action listener
        jBSymulacja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                    System.out.println("ActionPerformed");
                    String data1 = jTFData1.getText();
                    String data2 = jTFData2.getText();

                    double stan0 = 0;//stan poczatkowy tymczasowy
                    int intData1 = 20080701;
                    int intData2 = 20080801;
                    try
                    {
                        intData1 = Integer.parseInt(data1);
                        intData2 = Integer.parseInt(data2);



                    System.out.println(data1 + "\n" + data2);
                    if(!sprawdzDate(data1) || !sprawdzDate(data2) ||
intData1 >= intData2)
                    {
                        JOptionPane.showMessageDialog(jPPreferencje,
"Sprawdz daty");
                    }

                    stanKonta = Double.parseDouble(jTFSaldo.getText());
                    stan0 = (double) stanKonta;
                    //Strategia dla danych dat
                    mecze = test.getStrategy(intData1, intData2);
                    //do tabeli
                    //do wykresu TimeSeries
                    XYSeries xy = new XYSeries("Saldo");
                    xy.add(0, stanKonta);
                    int counter = 1;
                    for(mecz i : mecze)
                    {
                        if(i.R1 > i.R2)
                        {
                            stanKonta += i.stawka*stanKonta*i.kurs;
                        }
                        else
                        {
                            stanKonta = (1 - i.stawka)*stanKonta;
                        }
                        try {
                            xy.add(counter, stanKonta);
                            counter++;
                        }
                        catch (SeriesException ex) {
                            System.err.println("Error adding to series");
                        }
                    }

                    XYSeries xy2 = new XYSeries("Saldo Koncowe");
                    int counter2 = 0;
                    while(counter-- > 0)
                    {
                        xy2.add(counter2, stanKonta);
                        counter2++;
                    }
                    //ustawiamy na jlabelu
                    DecimalFormat df = new DecimalFormat("#.##");
                    if(stan0 > stanKonta)
                    {
                        jLStan.setForeground(Color.RED);
                    }
                    else
                    {
                        jLStan.setForeground(Color.green);
                    }
                    jLStan.setText(df.format(stanKonta));
                    jLStanPoczatkowy.setText(df.format(stan0));

                    XYSeriesCollection dataset = new XYSeriesCollection(xy);
                    dataset.addSeries(xy2);
                    chart = createChart(dataset);
                    chart.setBackgroundPaint(Color.LIGHT_GRAY);


                    XYPlot plot = chart.getXYPlot();
                    NumberAxis range = (NumberAxis) plot.getDomainAxis();
                    range.setTickUnit(new NumberTickUnit(1));

                    //Zmiana backgroundu
                    //CategoryPlot catplot = (CategoryPlot) chart.getPlot();
                    //catplot.setBackgroundPaint(Color.LIGHT_GRAY);
                    //catplot.setRangeGridlinePaint(Color.DARK_GRAY);

                    ChartPanel chartPanel = new ChartPanel(chart);
                    chartPanel.setPreferredSize(new
java.awt.Dimension(825, 188));

                    //825, 228, 190, 290
                    chartPanel.setBounds(0, 30, 825, 188);
                    jPWykres.add(chartPanel);
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(jPPreferencje,
"Sprawdz wprowadzone dane");
                }
            }
        });

        jPPreferencje.add(jLData1);
        jPPreferencje.add(jTFData1);
        jPPreferencje.add(jLData2);
        jPPreferencje.add(jTFData2);
        jPPreferencje.add(jLSaldo);
        jPPreferencje.add(jTFSaldo);
        jPPreferencje.add(jLStanPoczatkowy);
        jPPreferencje.add(jLStan);
        jPPreferencje.add(jBSymulacja);

        add(jPPreferencje);
        add(jPWykres);
        add(jPTabela);
    }

    private JTable createTable()
    {
        String[] columnNames = {"Team1",
                        "Team2",
                        "Kurs",
                        "Wynik 1",
                        "Wynik 2",
                        "Stawka",
                        "Aktualna kwota"};
        //Object[][] values = {"", "", "","",""};
        JTable t = null;
        //t = new JTable(columnNames);

        return t;
    }

    private JFreeChart createChart(XYDataset dataset)
    {
        final JFreeChart output = ChartFactory.createXYLineChart(
            "", // chart title
            "Kolejka", // domain axis label
            "", // range axis label
            dataset, // data
            PlotOrientation.VERTICAL,
            true, // include legend
            true,
            false
        );

        return output;
    }

    private boolean sprawdzDate(String data)
    {
        return (Integer.parseInt(data) > 19800000 &&
Integer.parseInt(data) < 20130000) ? true : false;
    }
}