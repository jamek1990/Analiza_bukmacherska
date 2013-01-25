package analiza_bukmacherska;

import java.awt.Color;
import java.util.List;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class PieRenderer 
{ 
    private Color[] color; 

    public PieRenderer(Color[] color) 
    { 
        this.color = color; 
    }        

    public void setColor(PiePlot plot, PieDataset dataset) 
    { 
        List <Comparable> keys = dataset.getKeys(); 
        int aInt; 

        for (int i = 0; i < keys.size(); i++) 
        { 
            aInt = i % this.color.length; 
            plot.setSectionPaint(keys.get(i), this.color[aInt]); 
        } 
    } 
} 

