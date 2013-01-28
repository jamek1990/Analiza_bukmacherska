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
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;

        

public class Test{
    boolean Y = true;
    boolean X = true;
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
     Test test;
    
    
    SQL database;
    Vector<String> hometeam = new Vector<String>();
    Vector<String> awayteam = new Vector<String>();
    Vector<String> ligue = new Vector<String>();
    Vector<Double> H_A = new Vector<Double>();
    Vector<Double> A_H = new Vector<Double>();
    Vector<String> hometeam1 = new Vector<String>();
    Vector<String> awayteam1 = new Vector<String>();
    Vector<Integer> data1 = new Vector<Integer>();
    Vector<String> ligue1 = new Vector<String>();
    Vector<Integer> H = new Vector<Integer>();
    Vector<Integer> A = new Vector<Integer>();  
    
    int TIME;
    public Test(){      
        try{
        start();
        }
        catch(Exception ex){System.out.println(ex.getMessage());}
    }    
    
     public Vector<mecz> getStrategy(int Time1, int Time2){      
        /*try{
            if(Y) generate();
        }
        catch(Exception ex){}
        TIME = Time1;
        Vector<mecz> strategy = new Vector<mecz>();
        while (TIME <= Time2) strategy.addAll(getOptimalStrategy(Time1, true));       
        return strategy;*/
        Vector<mecz> strategy = new Vector<mecz>();
        strategy.add(new mecz());
        strategy.add(new mecz());
        strategy.add(new mecz());
        strategy.add(new mecz());
        strategy.add(new mecz());
        return strategy;
    }     
     
    public Vector<mecz> getStrategy(){
        Vector<mecz> strategy = new Vector<mecz>();
        return strategy;
    }
    public Vector<mecz> getStrategy(int time){
        Vector<mecz> strategy = new Vector<mecz>();
        return strategy;
    }
        
    public Vector<mecz> getLameStrategy(){
        DateFormat dateFormat = new SimpleDateFormat("dd");
        DateFormat dateFormat2 = new SimpleDateFormat("MM");
        DateFormat dateFormat3 = new SimpleDateFormat("yy");
        Calendar cal = Calendar.getInstance();
        String s =dateFormat.format(cal.getTime());
        String s2 =dateFormat2.format(cal.getTime());
        String s3 =dateFormat3.format(cal.getTime());
        Integer date2=Integer.parseInt(s)+Integer.parseInt(s2)*100+(Integer.parseInt(s3)+2000)*10000;
        return getLameStrategy(date2);
        }
        
    public Vector<mecz> getOptimalStrategy(){
        DateFormat dateFormat = new SimpleDateFormat("dd");
        DateFormat dateFormat2 = new SimpleDateFormat("MM");
        DateFormat dateFormat3 = new SimpleDateFormat("yy");
        Calendar cal = Calendar.getInstance();
        String s =dateFormat.format(cal.getTime());
        String s2 =dateFormat2.format(cal.getTime());
        String s3 =dateFormat3.format(cal.getTime());
        Integer date2=Integer.parseInt(s)+Integer.parseInt(s2)*100+(Integer.parseInt(s3)+2000)*10000;
        return getOptimalStrategy(date2, false);
    }

    public Vector<mecz> getAgresiveStrategy(){
        DateFormat dateFormat = new SimpleDateFormat("dd");
        DateFormat dateFormat2 = new SimpleDateFormat("MM");
        DateFormat dateFormat3 = new SimpleDateFormat("yy");
        Calendar cal = Calendar.getInstance();
        String s =dateFormat.format(cal.getTime());
        String s2 =dateFormat2.format(cal.getTime());
        String s3 =dateFormat3.format(cal.getTime());
        Integer date2=Integer.parseInt(s)+Integer.parseInt(s2)*100+(Integer.parseInt(s3)+2000)*10000;
        return getAgresiveStartegy(date2);
    }

    
    private Vector<mecz> getOptimalStrategy(int time, boolean wyniki){
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
//        return mmV();
        return new Vector<mecz>();
    }        
    private Vector<mecz> getLameStrategy(int time){
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
        return new Vector<mecz>();
    }        
    private Vector<mecz> getAgresiveStartegy(int time){
//        if(X) {setResult();return;}
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
//        setResult(table);
    return new Vector<mecz>();
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
        //return ((0.88*k+0.12)*p-1);
        return p*k-1.0;
    }

    public void start()throws SQLException, ClassNotFoundException{
    DateFormat dateFormat = new SimpleDateFormat("dd");
        DateFormat dateFormat2 = new SimpleDateFormat("MM");
        DateFormat dateFormat3 = new SimpleDateFormat("yy");
        Calendar cal = Calendar.getInstance();
        String s =dateFormat.format(cal.getTime());
        String s2 =dateFormat2.format(cal.getTime());
        String s3 =dateFormat3.format(cal.getTime());
        Integer date2=Integer.parseInt(s)+Integer.parseInt(s2)*100+(Integer.parseInt(s3)+2000)*10000;
        try{
        generate(date2);}
        catch(SQLException ex){
        throw new SQLException(ex.getMessage());
        }
        catch(ClassNotFoundException ex){
        throw new ClassNotFoundException(ex.getMessage());
        }
    }

    private void generate() throws SQLException, ClassNotFoundException{
        database = new SQL();
        Statement stat;
        stat = database.con.createStatement(); 
        String query = "";
        query = "select div, hometeam, awayteam, k1, k2 from Kursy (k1 > '1.8' or k2 > '1.8')";//where data > '" + date2 + "' and
        ResultSet rs = stat.executeQuery(query);
        while (rs.next()) {
            hometeam.add(rs.getString(2));
            awayteam.add(rs.getString(3));
            ligue.add(rs.getString(1));
            H_A.add(Double.parseDouble(rs.getString(4)));
            A_H.add(Double.parseDouble(rs.getString(5)));
        }
        query = "select DIV, DATA, HomeTeam, AwayTeam, FTHG, FTAG from MECZE_STATYSTYKI order by data desc";
        ResultSet rs1 = stat.executeQuery(query);   
        while (rs1.next()) {          
            hometeam1.add(rs1.getString(3));
            awayteam1.add(rs1.getString(4));
            ligue1.add(rs1.getString(1));
            data1.add(Integer.parseInt(rs1.getString(2)));
            H.add(Integer.parseInt(rs1.getString(5)));
            A.add(Integer.parseInt(rs1.getString(6)));
        }
        stat.close();
        int q1 = hometeam.size();        
        if(q1 > 0) X = false;
        String[] names = new String[q1];
        double[] courses = new double[q1];
        double[] preView = new double[q1];        
        
        int k = 0;
        for(int i = 0; i < q1; ++i)
        {
            if(H_A.get(i) > 1.8 && H_A.get(i) > A_H.get(i)){
                int rangeH = hRange(i);
                int rangeA = aRange(i);
                System.out.println(rangeH + " " + rangeA);
                if (H_A.get(i)*(double)(rangeH - rangeA)/100.0 > 0.1){
                    names[k] = hometeam.get(i) + ".vs." + awayteam.get(i);
                    courses[k] = H_A.get(i);
                    preView[k] = (rangeH - rangeA)/100;
                }
                k++;
            }
            else if(A_H.get(i) > 1.8){
                int rangeH = hRange(i);
                int rangeA = aRange(i); 
                System.out.println(rangeH + " " + rangeA);                
                if (H_A.get(i)*(double)(rangeA - rangeH)/100.0 > 0.1){
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
        q1 = names.length;
        for(int i = 0; i < q1; i++){
            System.out.println(names[i] + " " + preView[i] + " " + courses[i]);
        }       
    }

    
    private void generate(int date2) throws SQLException, ClassNotFoundException{
        database = new SQL();
        Statement stat;
        stat = database.con.createStatement(); 
        String query = "";
        query = "select div, hometeam, awayteam, k1, k2 from Kursy where data > '" + date2 + "' and (k1 > '1.8' or k2 > '1.8')";
        ResultSet rs = stat.executeQuery(query);
        while (rs.next()) {
            hometeam.add(rs.getString(2));
            awayteam.add(rs.getString(3));
            ligue.add(rs.getString(1));
            H_A.add(Double.parseDouble(rs.getString(4)));
            A_H.add(Double.parseDouble(rs.getString(5)));
        }
        query = "select DIV, DATA, HomeTeam, AwayTeam, FTHG, FTAG from MECZE_STATYSTYKI order by data desc";
        ResultSet rs1 = stat.executeQuery(query);   
        while (rs1.next()) {          
            hometeam1.add(rs1.getString(3));
            awayteam1.add(rs1.getString(4));
            ligue1.add(rs1.getString(1));
            data1.add(Integer.parseInt(rs1.getString(2)));
            H.add(Integer.parseInt(rs1.getString(5)));
            A.add(Integer.parseInt(rs1.getString(6)));
        }
        stat.close();
        int q1 = hometeam.size();        
        if(q1 > 0) X = false;
        String[] names = new String[q1];
        double[] courses = new double[q1];
        double[] preView = new double[q1];        
        
        int k = 0;
        for(int i = 0; i < q1; ++i)
        {
            if(H_A.get(i) > 1.8 && H_A.get(i) > A_H.get(i)){
                int rangeH = hRange(i);
                int rangeA = aRange(i);
                System.out.println(rangeH + " " + rangeA);
                if (H_A.get(i)*(double)(rangeH - rangeA)/100.0 > 0.1){
                    names[k] = hometeam.get(i) + ".vs." + awayteam.get(i);
                    courses[k] = H_A.get(i);
                    preView[k] = (rangeH - rangeA)/100;
                }
                k++;
            }
            else if(A_H.get(i) > 1.8){
                int rangeH = hRange(i);
                int rangeA = aRange(i); 
                System.out.println(rangeH + " " + rangeA);                
                if (H_A.get(i)*(double)(rangeA - rangeH)/100.0 > 0.1){
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
        q1 = names.length;
        for(int i = 0; i < q1; i++){
            System.out.println(names[i] + " " + preView[i] + " " + courses[i]);
        }       
    }
    
    int hRange(int i){
        int q2 = hometeam1.size();        
        int rangeH = 50;
        boolean a = false,b = false,c = false;        
        int j = 0;
        for(; j < q2; ++j){
            if(hometeam.get(i).equals(hometeam1.get(j))){
                a = (H.get(j) > A.get(j));
                break;
            }
            else if(hometeam.get(i).equals(awayteam1.get(j))){
                a = (H.get(j) < A.get(j));
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
                b = (H.get(j) < A.get(j));
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
                c = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        if(a) rangeH+=5;
        else  rangeH-=5;
        if(b) rangeH+=5;
        else  rangeH-=5;
        if(c) rangeH+=5;
        else  rangeH-=5;
        if(a && b && c) rangeH+=20;
        else if(a && b && !c) rangeH+=25;
        else if(!a && !b && c) rangeH-=15;
        else if(!a && !b && !c) rangeH-=25; //kiedyś będzie trzeba się temu przyjrzeć dokłądniej. 
        for(j = 0; j < q2; ++j){
            if(hometeam.get(i).equals(hometeam1.get(j)) && awayteam.get(i).equals(awayteam1.get(j))){
                a = (H.get(j) > A.get(j));
                break;
            }
            else if(hometeam.get(i).equals(awayteam1.get(j)) && awayteam.get(i).equals(hometeam1.get(j))){
                a = (H.get(j) < A.get(j));
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
                b = (H.get(j) < A.get(j));
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
                c = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        int p = 0;
        if(a) p++; 
        if(b) p++;
        if(c) p++;
        if(p>=2) rangeH+=10;
        return rangeH;
    }
    int aRange(int i){
        int q2 = hometeam1.size();        
        int rangeA = 50;
        boolean a = false,b = false,c = false;        
        int j = 0;
        for(; j < q2; ++j){
            if(awayteam.get(i).equals(hometeam1.get(j))){
                a = (H.get(j) > A.get(j));
                break;
            }
            else if(awayteam.get(i).equals(awayteam1.get(j))){
                a = (H.get(j) < A.get(j));
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
                b = (H.get(j) < A.get(j));
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
                c = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        if(a) rangeA+=5;
        else  rangeA-=5;
        if(b) rangeA+=5;
        else  rangeA-=5;
        if(c) rangeA+=5;
        else  rangeA-=5;
        if(a && b && c) rangeA+=20;
        else if(a && b && !c) rangeA+=25;
        else if(!a && !b && c) rangeA-=15;
        else if(!a && !b && !c) rangeA-=25; //kiedyś będzie trzeba się temu przyjrzeć dokłądniej. 
        for(j = 0; j < q2; ++j){
            if(awayteam.get(i).equals(hometeam1.get(j)) && hometeam.get(i).equals(awayteam1.get(j))){
                a = (H.get(j) > A.get(j));
                break;
            }
            else if(awayteam.get(i).equals(awayteam1.get(j)) && hometeam.get(i).equals(hometeam1.get(j))){
                a = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(awayteam.get(i).equals(hometeam1.get(j)) && hometeam.get(i).equals(awayteam1.get(j))){
                b = (H.get(j) > A.get(j));
                break;
            }
            else if(awayteam.get(i).equals(awayteam1.get(j)) && hometeam.get(i).equals(hometeam1.get(j))){
                b = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(awayteam.get(i).equals(hometeam1.get(j)) && hometeam.get(i).equals(awayteam1.get(j))){
                c = (H.get(j) > A.get(j));
                break;
            }
            else if(awayteam.get(i).equals(awayteam1.get(j)) && hometeam.get(i).equals(hometeam1.get(j))){
                c = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        int p = 0;
        if(a) p++; 
        if(b) p++;
        if(c) p++;
        if(p>=2) rangeA+=10;
        return rangeA;
    }

}
