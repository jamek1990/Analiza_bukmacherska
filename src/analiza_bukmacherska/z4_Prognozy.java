import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.math.*;
import java.util.Vector;
        

public class z4_Prognozy extends JLayeredPane{
    m1_okienko  jP_TeamVsTeam;
    String[] names;
    double[] courses;
    double[] preView;
    double[] expectedValues;
    //boolean[] tableOfCases;
    int money;
    public z4_Prognozy(){
        jP_TeamVsTeam = new m1_okienko(400,200,0,3,"Prognozy");
        setLocation(0,0);
        setBounds(0, 0, 1024, 300);
        add(jP_TeamVsTeam);
    }    
    public z4_Prognozy(String[] namesBis, double[] coursesBis, double[] preViewBis) {
        //if (namesBis.length == coursesBis.length && coursesBis.length == preViewBis.length){
        names = namesBis;
        courses = coursesBis;
        preView = preViewBis;
        //}else{throw new}
        jP_TeamVsTeam = new m1_okienko(400,200,0,3,"Prognozy");
        setLocation(0,0);
        setBounds(0, 0, 1024, 300);
        add(jP_TeamVsTeam);
        
    }
    private void lameMethode(){   
        //double max = 0.75*countMaxOfExpectedValue();
        Vector<Integer> optionsNumber = new Vector<Integer>(0);
        int n = expectedValues.length;
        for (int i = 0; i < n; i++){
            if(expectedValues[i]>0){
                optionsNumber.add(i);
            }
        }
        double[] antyRisk = new double[optionsNumber.size()];
        for (int i = 0; i < optionsNumber.size(); i++){
            antyRisk[i] = 1.0-getRisk(next(optionsNumber.get(i)));
        }
        double maksimum = max(antyRisk);
        int i = 0;
        while(antyRisk[i] != maksimum){
            i++;
        }
        int[] table = {optionsNumber.get(i)};
        setResult(table);
    }
    private void optimalMethode(){        
        double max = 0.3*countMaxOfExpectedValue();
        Vector<Integer> optionsNumber = new Vector<Integer>(0);
        int n = expectedValues.length;
        for (int i = 0; i < n; i++){
            if(expectedValues[i]>max){
                optionsNumber.add(i);
            }
        }
        double[] antyRisk = new double[optionsNumber.size()];
        for (int i = 0; i < optionsNumber.size(); i++){
            antyRisk[i] = 1.0-getRisk(next(optionsNumber.get(i)));
        }
        double maksimum = max(antyRisk);
        int i = 0;
        while(antyRisk[i] != maksimum){
            i++;
        }
        int[] table = {optionsNumber.get(i)};
        setResult(table);
    }
    private void agresiveMethode(){        
        double max = 0.75*countMaxOfExpectedValue();
        Vector<Integer> optionsNumber = new Vector<Integer>(0);
        int n = expectedValues.length;
        for (int i = 0; i < n; i++){
            if(expectedValues[i]>max){
                optionsNumber.add(i);
            }
        }
        double[] antyRisk = new double[optionsNumber.size()];
        for (int i = 0; i < optionsNumber.size(); i++){
            antyRisk[i] = 1.0-getRisk(next(optionsNumber.get(i)));
        }
        double maksimum = max(antyRisk);
        int i = 0;
        while(antyRisk[i] != maksimum){
            i++;
        }
        int[] table = {optionsNumber.get(i)};
        setResult(table);
    }
    private double getRisk(int[] table){
        int n = table.length;
        double p = 1.0;
        for (int i = 0; i < n; i++){
            p *= preView[table[i]];
        }
        return p;
    }
    private void setResult(int[] table){
    }
    private double countMaxOfExpectedValue(){
        double n = Math.pow(2,courses.length)-1;
        //tableOfCases = new boolean[courses.length];
        expectedValues = new double[(int)n];                
        for (int i = 0; i < n; i++){
            expectedValues[i] = expectedValueOf(next(i+1));
        }
        return max(expectedValues);
    }
    private double max(double[] table){
        int n = table.length;        
        double r = 0.0;
        for (int i = 0; i < n; i++){
            if (r < table[i]){
                r = table[i];
            }
        }        
        return r;
    }             
    private int[] next(int i){
        int[] result = new int[preView.length];
        int p = 0;
        while (i > 0){            
            int w = (int) (Math.log10((double)i)/Math.log10(2.0));
            i -= (int) Math.pow(2.0, (double)w);
            result[p] = w;
            ++p;
        }        
        int[] result2 = new int[p];
        for(int j = 0; j<p;j++){
            result2[j] = result[j];
        }
        return result2;
    }
    private double expectedValueOf(int[] table){
        int n = table.length;
        double k = 1.0;
        double p = 1.0;
        for (int i = 0; i < n; i++){
            k *= courses[table[i]];
            p *= preView[table[i]];
        }
        return ((0.88*k+0.12)*p-1);
    }
}
