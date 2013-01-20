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
import java.util.Random;
        

public class z4_Prognozy extends JLayeredPane{
    m1_okienko jP_Prognozy;
    m1_okienko okienko;
    
    JTextField myMoney;
    JTextField chooseMyLeague;
    
    JPanel myResults;
    m1_okienko samplePathUp; 
    m1_okienko samplePathDown;
            
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
    
    SQL database;
    Vector<String> hometeam = new Vector<String>();
    Vector<String> awayteam = new Vector<String>();
    Vector<String> ligue = new Vector<String>();
    Vector<Double> H_A = new Vector<Double>();
    Vector<Double> A_H = new Vector<Double>();
    Vector<String> hometeam1 = new Vector<String>();
    Vector<String> awayteam1 = new Vector<String>();
    Vector<Date> data1 = new Vector<Date>();
    Vector<String> ligue1 = new Vector<String>();
    Vector<Integer> H = new Vector<Integer>();
    Vector<Integer> A = new Vector<Integer>();  
    
    public z4_Prognozy(){      
        setLocation(0,0);
        setBounds(0, 0, 1024, 600);
        try{
        generate();
        }
        catch(Exception ex){}
        setWindows();
        /*
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
        }*/

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {lameMethode();}
        });   
        buttonOM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt) {optimalMethode();}
        }); 
        buttonAM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt) {agresiveMethode();}
        });          
        buttonLM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {buttonLM.setIcon(new javax.swing.ImageIcon("images/low1.jpg")); }
        });   
        buttonOM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {buttonOM.setIcon(new javax.swing.ImageIcon("images/opt1.jpg"));}
        }); 
        buttonAM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {buttonAM.setIcon(new javax.swing.ImageIcon("images/agr1.jpg"));}
        });         
        buttonLM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseReleased(java.awt.event.MouseEvent evt) {buttonLM.setIcon(new javax.swing.ImageIcon("images/low2.jpg")); }
        });   
        buttonOM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseReleased(java.awt.event.MouseEvent evt) {buttonOM.setIcon(new javax.swing.ImageIcon("images/opt2.jpg"));}
        }); 
        buttonAM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseReleased(java.awt.event.MouseEvent evt) {buttonAM.setIcon(new javax.swing.ImageIcon("images/agr2.jpg"));}
        });     
        buttonLM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseExited(java.awt.event.MouseEvent evt) {buttonLM.setIcon(new javax.swing.ImageIcon("images/low2.jpg")); }
        });   
        buttonOM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseExited(java.awt.event.MouseEvent evt) {buttonOM.setIcon(new javax.swing.ImageIcon("images/opt2.jpg"));}
        }); 
        buttonAM.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseExited(java.awt.event.MouseEvent evt) {buttonAM.setIcon(new javax.swing.ImageIcon("images/agr2.jpg"));}
        });         
             
        buttonLM.setIcon(new javax.swing.ImageIcon("images/low2.jpg"));          
        buttonOM.setIcon(new javax.swing.ImageIcon("images/opt2.jpg"));
        buttonAM.setIcon(new javax.swing.ImageIcon("images/agr2.jpg"));
        
        add(buttonLM);
        add(buttonOM);                
        add(buttonAM);                    
        
        /////////////////////////////////////////////////////////////Czesc druga
        
        jP_Prognozy = new m1_okienko(400,200,0,3,"Prognozy");
        add(jP_Prognozy);
        /*
        JLabel chooseMyLeagueText = new JLabel();
        chooseMyLeagueText.setBounds(0, 40, 100, 35);
        chooseMyLeagueText.setText("Wybierz lige");
        add(chooseMyLeagueText);
        
        chooseMyLeague = new JTextField();
        chooseMyLeague.setText("To jest miejsce na rozwijane menu z ligami");
        chooseMyLeague.setBounds(105, 40, 40, 35);
        add(chooseMyLeague);
        */
        JLabel myMoneyText = new JLabel();
        myMoneyText.setText("Posiadana kwota");  
        myMoneyText.setBounds(0, 80, 100, 35);
        add(myMoneyText);
        
        myMoney = new JTextField();
        myMoney.setText("Tu wpisz kwote przeznaczona na gre");
        myMoney.setBounds(105, 75, 300, 35);
        add(myMoney);
        
        myResults = new JPanel();
        textFrame = new JTextArea();
        textFrame.setBackground(new Color(209,210,211));
        myResults.add(textFrame);
        myResults.setBackground(new Color(209, 210, 211));
        myResults.setBounds(0, 360, 400, 300);
        add(myResults);
        ///////////////////////////////////////////////////////////czesc trzecia
        
        okienko = new m1_okienko(400,200,0,206,"Wybierz strategię gry");
        add(okienko);      
        
        buttonLM.setBounds(50,250,80,80);                
        buttonOM.setBounds(150,250,80,80);        
        buttonAM.setBounds(250,250,80,80); 
        
        samplePathUp = new m1_okienko(500,270,410,3,""); 
        samplePathDown = new m1_okienko(500,270,410,273,"");
        add(samplePathUp);
        add(samplePathDown);
        
    }
    private void setResult(int[] table){

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
    //na podstawie tabeli 
    private double expectedValueOf(int[] table){
        int n = table.length;
        double k = 1.0;
        double p = 1.0;
        for (int i = 0; i < n; i++){
            k *= courses[table[i]];
            p *= preView[table[i]];
        }
        //return ((0.88*k+0.12)*p-1);
        return p*k-1.0;
    }

    private void generate() throws SQLException, ClassNotFoundException{
        database = new SQL();
        Statement stat;
        stat = database.con.createStatement(); 
        String query = "";
        //String today = System.
        query = "select div, hometeam, awayteam, k1, k2 from Kursy where data > curdate() and (k1 > 1.8 or k2 > 1.8)";
        ResultSet rs = stat.executeQuery(query);

        while (rs.next()) {
            hometeam.add(rs.getString(2));
            awayteam.add(rs.getString(3));
            ligue.add(rs.getString(1));
            H_A.add(Double.parseDouble(rs.getString(4)));
            A_H.add(Double.parseDouble(rs.getString(5)));
        }
        rs.deleteRow();       
                   
        query = "select Div, DATA, HomeTeam as HT, AwayTeam as AT, FTHG, FTAG from (select top 3 Div, DATA, HomeTeam, AwayTeam, FTHG, FTAG from MECZE_STATYSTYKI where HomeTeam = HT or AwayTeam  = HT or AwayTeam = AT or HomeTeam = AT ordered by data desc) ordered by data desc";
        ResultSet rs1 = stat.executeQuery(query);
        while (rs1.next()) {
            hometeam1.add(rs1.getString(3));
            awayteam1.add(rs1.getString(4));
            ligue1.add(rs1.getString(1));
            data1.add(Date.valueOf(rs1.getString(2)));
            H.add(Integer.parseInt(rs1.getString(5)));
            A.add(Integer.parseInt(rs1.getString(6)));
        }
        rs1.deleteRow();
        stat.close();
        int q1 = hometeam.size();        
        String[] names = new String[q1];
        double[] courses = new double[q1];
        double[] preView = new double[q1];        
        
        int k = 0;
        for(int i = 0; i < q1; ++i)
        {
            if(H_A.get(i) > 1.8){
                int rangeH = hRange(i);
                int rangeA = aRange(i);                
                if (H_A.get(i)*(rangeH - rangeA)/100 > 0.1){
                    names[k] = hometeam.get(i) + ".vs." + awayteam.get(i);
                    courses[k] = H_A.get(i);
                    preView[k] = (rangeH - rangeA)/100;
                }
                k++;
            }
            else if(A_H.get(i) > 1.8){
                int rangeH = hRange(i);
                int rangeA = aRange(i);                
                if (H_A.get(i)*(rangeA - rangeH)/100 > 0.1){
                    names[k] = awayteam.get(i) + ".vs." + hometeam.get(i);
                    courses[k] = A_H.get(i);
                    preView[k] = (rangeA - rangeH)/100;
                }                
                k++;
            }
        }
        if(k!=0){
            this.names = new String[k];
            this.courses = new double[k];
            this.preView = new double[k]; 
            while (k > 0){
            this.names[k] = names[k];
            this.courses[k] = courses[k];
            this.preView[k] = preView[k]; 
            }
        }
    }
    
    int hRange(int i){
        int q2 = hometeam1.size();        
        int rangeH = 0;
        boolean a,b,c;        
        int j = 0;
        for(; j < q2; ++j){
            if(hometeam.get(i).equals(hometeam1.get(j))){
                a = (H.get(j) > A.get(j));
                break;
            }
            else if(hometeam.get(i).equals(awayteam1.get(j))){
                a = (H.get(j) > A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(hometeam.get(i).equals(hometeam1.get(j))){
                b = (H.get(j) > A.get(j));
                break;
            }
            else if(hometeam.get(i).equals(awayteam1.get(j))){
                b = (H.get(j) > A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(hometeam.get(i).equals(hometeam1.get(j))){
                c = (H.get(j) > A.get(j));
                break;
            }
            else if(hometeam.get(i).equals(awayteam1.get(j))){
                c = (H.get(j) > A.get(j));
                break;
            }
            else continue;
        }
        Random rand = new Random();
        return (int)rand.nextInt()%100;
        //return rangeH;
    }
    int aRange(int i){
        int rangeA = 0;
        int q2 = hometeam1.size(); 
        boolean a,b,c;
        int k = 0;
        int j = 0;
        for(; j < q2; ++j){
            if(awayteam.get(i).equals(hometeam1.get(j))){
                a = (H.get(j) > A.get(j));
                break;
            }
            else if(awayteam.get(i).equals(awayteam1.get(j))){
                a = (H.get(j) > A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(awayteam.get(i).equals(hometeam1.get(j))){
                b = (H.get(j) > A.get(j));
                break;
            }
            else if(awayteam.get(i).equals(awayteam1.get(j))){
                b = (H.get(j) > A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(awayteam.get(i).equals(hometeam1.get(j))){
                c = (H.get(j) > A.get(j));
                break;
            }
            else if(awayteam.get(i).equals(awayteam1.get(j))){
                c = (H.get(j) > A.get(j));
                break;
            }
            else continue;
        } 
        Random rand = new Random();
        return (int)rand.nextInt()%100;
        //return rangeA;
    }
}
