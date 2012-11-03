package analiza_bukmacherska;
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
    m1_okienko  jP_Prognozy;
    m1_okienko okienko;
    
    JTextField myMoney;
    JTextField chooseMyLeague;
    
    String[] names;
    double[] courses;
    double[] preView;
    double[] expectedValues;
    JTextArea textFrame;
    //boolean[] tableOfCases;
    JLabel buttonLM;
    JLabel buttonOM;
    JLabel buttonAM;
    int money;          
    public z4_Prognozy(){      
        setLocation(0,0);
        setBounds(0, 0, 1024, 600);
        
        String[] names = {"Borusia","Dortmundt","Real","Madrit"};
        double[] courses = {2.6, 2.7, 2.9, 2.68};
        double[] preView = {0.6, 0.7, 0.9, 0.68};
        int length = names.length;
        this.names = new String[length];
        this.courses = new double[length];
        this.preView = new double[length];
        for (int i = 0 ; i< length; i++){
            this.names[i] = names[i];
            this.courses[i] = courses[i];
            this.preView[i] = preView[i];
        }
        setWindows();
    }    
    public z4_Prognozy(String[] namesBis, double[] coursesBis, double[] preViewBis) {
        //if (namesBis.length == coursesBis.length && coursesBis.length == preViewBis.length){
        names = namesBis;
        courses = coursesBis;
        preView = preViewBis;
        //}else{throw new}
        jP_Prognozy = new m1_okienko(400,200,0,3,"Prognozy");
        setLocation(0,0);
        setBounds(0, 0, 1024, 300);
        add(jP_Prognozy);
        setWindows();
        
    }
    
    private void setWindows(){
        buttonLM = new JLabel();
        buttonOM = new JLabel();
        buttonAM = new JLabel();
        
        buttonLM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {lameMethode();}
        });   
        buttonOM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {optimalMethode();}
        }); 
        buttonAM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {agresiveMethode();}
        });  
        
        buttonLM.setIcon(new javax.swing.ImageIcon("images/button_down_prognoza.jpg"));          
        buttonOM.setIcon(new javax.swing.ImageIcon("images/button_down_prognoza.jpg"));
        buttonAM.setIcon(new javax.swing.ImageIcon("images/button_down_prognoza.jpg"));
        
        add(buttonLM);
        add(buttonOM);                
        add(buttonAM);                    
        
        /////////////////////////////////////////////////////////////Czesc druga
        
        jP_Prognozy = new m1_okienko(400,200,0,3,"Prognozy");
        add(jP_Prognozy);
        
        JLabel chooseMyLeagueText = new JLabel();
        chooseMyLeagueText.setBounds(0, 40, 100, 35);
        chooseMyLeagueText.setText("Wybierz lige");
        add(chooseMyLeagueText);
        
        chooseMyLeague = new JTextField();
        chooseMyLeague.setText("To jest miejsce na rozwijane menu z ligami");
        chooseMyLeague.setBounds(105, 40, 40, 35);
        add(chooseMyLeague);
        
        JLabel myMoneyText = new JLabel();
        myMoneyText.setText("Posiadana kwota");  
        myMoneyText.setBounds(0, 80, 100, 35);
        add(myMoneyText);
        
        myMoney = new JTextField();
        myMoney.setText("Tu wpisz kwote przeznaczona na gre");
        myMoney.setBounds(105, 75, 40, 35);
        add(myMoney);
        
        myMoney.addMouseListener(null);
        
        ///////////////////////////////////////////////////////////czesc trzecia
        
        okienko = new m1_okienko(400,200,0,206,"Wybierz strategię gry");
        add(okienko);      
        
        buttonLM.setBounds(50,250,50,50);                
        buttonOM.setBounds(150,250,50,50);        
        buttonAM.setBounds(250,250,50,50); 
    }
    private void setResult(int[] table){
        textFrame = new JTextArea();
        int[] option = next(table[0]+1);
        String text = "";
        for(int i = 0; i < option.length; i++){
            text += names[option[i]];
            text += ",";
        }
        text += '\n';
        text += "Szansa wygranej wynosi: " + Double.toString(getChance(option))+'\n';
        text += "Przewidywany zysk wynosi: " + Double.toString(expectedValueOf(option));
        textFrame.setText(text);
        add(textFrame);
        textFrame.setBounds(0, 310, 300, 200);
    }
    
    private void lameMethode(){   
        countMaxOfExpectedValue();
        Vector<Integer> optionsNumber = new Vector<Integer>(0);
        int n = expectedValues.length;
        for (int i = 0; i < n; i++){
            if(expectedValues[i]>0){
                optionsNumber.add(i);
            }
        }
        double[] antyRisk = new double[optionsNumber.size()];
        for (int i = 0; i < optionsNumber.size(); i++){
            antyRisk[i] = getChance(next(optionsNumber.get(i)+1));
        }
        //for(int j = 0; j<antyRisk.length;j++){System.out.print(antyRisk[j]);System.out.print(", ");}        
        double maksimum = max(antyRisk);
        int i = 0;
        while(antyRisk[i] != maksimum){
            i++;
        }
        //System.out.println("");
        //System.out.println(i);
        //System.out.println(optionsNumber.get(i));
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
            antyRisk[i] = getChance(next(optionsNumber.get(i)+1));
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
            antyRisk[i] = getChance(next(optionsNumber.get(i)));
        }
        double maksimum = max(antyRisk);
        int i = 0;
        while(antyRisk[i] != maksimum){
            i++;
        }
        int[] table = {optionsNumber.get(i)};
        setResult(table);
    }
    
    private double getChance(int[] table){
        int n = table.length;
        double p = 1.0;
        for (int i = 0; i < n; i++){
            p *= preView[table[i]];
        }
        return p;
    }    
    private double countMaxOfExpectedValue(){
        double n = Math.pow(2.0,(double)courses.length)-1.0;
        expectedValues = new double[(int)n];                
        for (int i = 0; i < n; i++){
            expectedValues[i] = expectedValueOf(next(i+1));
        }
        //for(int i=0;i<expectedValues.length;i++){System.out.print(expectedValues[i]);System.out.print(",");}         
        return max(expectedValues);
    }
    private double max(double[] table){
        int n = table.length;        
        double r = table[0];
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
        //for(i=0;i<result2.length;i++){System.out.print(result2[i]);System.out.print(",");}
        //System.out.println('\n');        
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
