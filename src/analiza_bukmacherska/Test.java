package analiza_bukmacherska;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.*;
import java.util.Arrays;

        

public class Test{
    boolean Y = true;
    boolean X = true;
    String[] names;
    String[] names1;
    String[] names2;
    int[] R1;
    int[] R2;
    int[]dataT;
    double[] courses;
    double[] preView;
    double[] expectedValues;
    int[] result1;
    int[] result2;
    JTextArea textFrame;
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
    Vector<Double> K1 = new Vector<Double>(); 
    Vector<Double> K2 = new Vector<Double>(); 
    
    int TIME;
    public Test(){      
        try{
        start2();
        }
        catch(Exception ex){System.out.println(ex.getMessage());}        
    }    
    public Test(boolean z){      
        try{
        start();
        }
        catch(Exception ex){System.out.println("Zanotowałem blad: "+ex.getMessage());}
    }          
    public Vector<mecz> getStrategy(int Time1, int Time2){      
        if(X) return new Vector<mecz>();
        TIME = Time1;
        Vector<mecz> strategy = new Vector<mecz>();
        while (TIME <= Time2) {
            strategy.addAll(getStrategy(TIME));
            if(TIME%100 > 24){
                if(TIME%10000 >= 1200){
                    TIME += 10000;
                    int a = TIME%100;
                    TIME = TIME - TIME%10000+100;
                    TIME += (a+7)%31;
                }
                else{
                    int a = TIME%100;
                    TIME += 100;
                    TIME = TIME - a + (a+7)%31;                    
                }
            }
            else TIME += 7;            
        }        
        return strategy;
    }         
    public Vector<mecz> getStrategy(int time){
        int s = 0, e = 0;
        while(s < data1.size() && data1.get(s) >= time) {
            s++;
        }        
        --s;
        
        while(e < data1.size()){
            if(365*((data1.get(e) - data1.get(e)%10000)/10000) + 31*((data1.get(e)%10000 - data1.get(e)%100)/100) + data1.get(e)%100 - (365*((time - time%10000)/10000) + 31*((time%10000 - time%100)/100) + time%100) > 7){
                e++;
            }
            else break;
        }
        if(e == data1.size())return new Vector<mecz>();
        int q1 = s-e+1;    
        if(q1 == 0) return new Vector<mecz>();
        
        String[] names1 = new String[q1];
        String[] names2 = new String[q1];
        int[] R1 = new int[q1];
        int[] R2 = new int[q1];
        int[] dataT = new int[q1];
        double[] courses = new double[q1];
        double[] preView = new double[q1];        
        int k = 0;
        for(int i = e; i < s; ++i)        
        {
            if(K1.get(i) > 1.8 && K1.get(i) > K2.get(i)){
                int rangeH = hRangeT(i);
                int rangeA = aRangeT(i);
                if (K1.get(i)*(double)(rangeH - rangeA)/100.0 > 0.1){                                        
                    names1[k] = hometeam1.get(i);
                    names2[k] = awayteam1.get(i);
                    R1[k] = H.get(i);
                    R2[k] = A.get(i);
                    dataT[k] = data1.get(i);
                    courses[k] = K1.get(i);
                    preView[k] = (rangeH - rangeA)/100;
                    k++;
                }
                else if(K2.get(i) > 1.8){              
                    if (K2.get(i)*(double)(rangeA - rangeH)/100.0 > 0.1){
                        names1[k] = awayteam1.get(i);
                        names2[k] = hometeam1.get(i);
                        courses[k] = K2.get(i);
                        preView[k] = (double)(rangeA - rangeH)/100.0;
                        R1[k] = A.get(i);
                        R2[k] = H.get(i);
                        dataT[k] = data1.get(i);
                        k++;
                    }                                
                }
            }
            else if(K2.get(i) > 1.8){
                int rangeH = hRangeT(i);
                int rangeA = aRangeT(i);                
                if (K2.get(i)*(double)(rangeA - rangeH)/100.0 > 0.1){
                        names1[k] = awayteam1.get(i);
                        names2[k] = hometeam1.get(i);
                        courses[k] = K2.get(i);
                        preView[k] = (double)(rangeA - rangeH)/100.0;
                        R1[k] = A.get(i);
                        R2[k] = H.get(i);
                        dataT[k] = data1.get(i);
                        k++;
                }
            if(K1.get(i) > 1.8){
                    if (K1.get(i)*(double)(rangeH - rangeA)/100.0 > 0.1){
                        names1[k] = hometeam1.get(i);
                        names2[k] = awayteam1.get(i);
                        R1[k] = H.get(i);
                        R2[k] = A.get(i);
                        dataT[k] = data1.get(i);
                        courses[k] = K1.get(i);
                        preView[k] = (rangeH - rangeA)/100;
                        k++;
                    }
                }
            }
        }
        if(k!=0){
            this.names1 = new String[k];
            this.names2 = new String[k];
            this.courses = new double[k];
            this.preView = new double[k]; 
            this.R1 = new int[k];
            this.R2 = new int[k];
            this.dataT = new int[k];
            while (k > 0){
            --k;
            this.dataT[k] = dataT[k];
            this.names1[k] = names1[k];
            this.names2[k] = names2[k];
            this.R1[k] = R1[k];
            this.R2[k] = R2[k];            
            this.courses[k] = courses[k];
            this.preView[k] = preView[k];             
            }
        }
        return getOptimalStrategy(0,true);     
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
        if(X) {return new Vector<mecz>();}        
        double max = 0.2*countMaxOfExpectedValue();
        if (max <= 0) return new Vector<mecz>();               
        Vector<Integer> optionsNumber = new Vector<Integer>(0);
        int n = expectedValues.length;      
        for (int i = 0; i < n; i++){
            if(expectedValues[i]>max){
                optionsNumber.add(i);
            }
        }
        double[] antyRisk = new double[optionsNumber.size()];
        for (int i = 0; i < optionsNumber.size(); i++){
            antyRisk[i] = preView[optionsNumber.get(i)];
        }
        double maksimum = max(antyRisk);
        int i = 0;
        while(antyRisk[i] != maksimum){
            i++;
        }
        double[] T = antyRisk;
        Arrays.sort(T);
        if(!wyniki){
            if(antyRisk.length == 1){
                mecz A = new mecz(names1[optionsNumber.get(i)],names2[optionsNumber.get(i)],courses[optionsNumber.get(i)],1.0);            
                Vector<mecz> strategy =  new Vector<mecz>();
                strategy.add(A);               
                return strategy;
            }
            else{           
                int k = T.length - 1;
                while(k >= 0 && T[k] == maksimum) --k;
                ++k;
                double r = 0;
                if(T.length-1-k == 0){
                    r = T[k-1];
                    --k;
                    while(k >= 0 && T[k] == r) --k;
                    int m = T.length-2-k;
                    mecz A = new mecz(names1[optionsNumber.get(i)],names2[optionsNumber.get(i)],courses[optionsNumber.get(i)],0.5);            
                    Vector<mecz> strategy =  new Vector<mecz>();
                    strategy.add(A);                
                    for(int l = 0; l < T.length; l++)
                        if(antyRisk[l] == r){
                            strategy.add(new mecz(names1[optionsNumber.get(l)],names2[optionsNumber.get(l)],courses[optionsNumber.get(l)],0.5/m));   
                        }                  
                return strategy;
                }
                else{
                    Vector<mecz> strategy =  new Vector<mecz>();
                    for(int l = 0; l < T.length; l++) {
                        if(antyRisk[l] == maksimum){
                            strategy.add(new mecz(names1[optionsNumber.get(l)],names2[optionsNumber.get(l)],courses[optionsNumber.get(l)],1.0/(T.length - k)));   
                        }
                    }                
                    return strategy;
                }          
            }
        }
        else{
            if(antyRisk.length == 1){
                mecz A = new mecz(names1[optionsNumber.get(i)],names2[optionsNumber.get(i)],courses[optionsNumber.get(i)],1.0,dataT[optionsNumber.get(i)],R1[optionsNumber.get(i)],R2[optionsNumber.get(i)]);            
                Vector<mecz> strategy =  new Vector<mecz>();
                strategy.add(A);             
                return strategy;
            }
            else{           
                int k = T.length - 1;
                while(k >= 0 && T[k] == maksimum) --k;
                ++k;
                double r = 0;
                if(T.length-1-k == 0){
                    r = T[k-1];
                    --k;
                    while(k >= 0 && T[k] == r) --k;
                    int m = T.length-2-k;                    
                    mecz A = new mecz(names1[optionsNumber.get(i)],names2[optionsNumber.get(i)],courses[optionsNumber.get(i)],0.5,dataT[optionsNumber.get(i)],R1[optionsNumber.get(i)],R2[optionsNumber.get(i)]);            
                    Vector<mecz> strategy =  new Vector<mecz>();
                    strategy.add(A);                
                    for(int l = 0; l < T.length; l++)
                        if(antyRisk[l] == r){
                            strategy.add(new mecz(names1[optionsNumber.get(l)],names2[optionsNumber.get(l)],courses[optionsNumber.get(l)],0.5/m,dataT[optionsNumber.get(l)],R1[optionsNumber.get(l)],R2[optionsNumber.get(l)]));   
                        }                  
                    return strategy;
                }
                else{
                    Vector<mecz> strategy =  new Vector<mecz>();
                    for(int l = 0; l < T.length; l++) {
                        if(antyRisk[l] == maksimum){                            
                            strategy.add(new mecz(names1[optionsNumber.get(l)],names2[optionsNumber.get(l)],courses[optionsNumber.get(l)],1.0/(T.length - k),dataT[optionsNumber.get(l)],R1[optionsNumber.get(l)],R2[optionsNumber.get(l)]));   
                        }
                    }
                    return strategy;
                }         
            }            
        }
    }        
    private Vector<mecz> getLameStrategy(int time){
        if(X) {return new Vector<mecz>();}
        if (countMaxOfExpectedValue() <= 0) return new Vector<mecz>(); 
        Vector<Integer> optionsNumber = new Vector<Integer>(0);
        int n = expectedValues.length;
        for (int i = 0; i < n; i++){
            if(expectedValues[i]>0){
                optionsNumber.add(i);
            }
        }
        double[] antyRisk = new double[optionsNumber.size()];
        for (int i = 0; i < optionsNumber.size(); i++){
            antyRisk[i] = preView[optionsNumber.get(i)];
        }
        double maksimum = max(antyRisk);
        int i = 0;
        while(antyRisk[i] != maksimum){
            i++;
        }
        double[] T = antyRisk;
        Arrays.sort(T);
        if(antyRisk.length == 1){
            mecz A = new mecz(names1[optionsNumber.get(i)],names2[optionsNumber.get(i)],courses[optionsNumber.get(i)],1.0);            
            Vector<mecz> strategy =  new Vector<mecz>();
            strategy.add(A);
            return strategy;
        }
        else{           
            int k = T.length - 1;
            while(k >= 0 && T[k] == maksimum) --k;
            ++k;
            double r = 0;
            if(T.length-1-k == 0){
                --k;
                r = T[k-1];
                while(k >= 0 && T[k] == r) --k;
                int m = T.length-1-k;
                mecz A = new mecz(names1[optionsNumber.get(i)],names2[optionsNumber.get(i)],courses[optionsNumber.get(i)],0.5);            
                Vector<mecz> strategy =  new Vector<mecz>();
                strategy.add(A);                
                for(int l = 0; l < T.length; l++)
                    if(antyRisk[l] == r){
                        strategy.add(new mecz(names1[optionsNumber.get(l)],names2[optionsNumber.get(l)],courses[optionsNumber.get(l)],0.5/m));   
                    }
            return strategy;
            }
            else{
                Vector<mecz> strategy =  new Vector<mecz>();
                for(int l = 0; l < T.length; l++) {
                    if(antyRisk[l] == maksimum){
                        strategy.add(new mecz(names1[optionsNumber.get(l)],names2[optionsNumber.get(l)],courses[optionsNumber.get(l)],1.0/(T.length - k)));   
                    }
                }
                return strategy;
            }       
        }
    }        
    private Vector<mecz> getAgresiveStartegy(int time){
        if(X) {return new Vector<mecz>();}        
        double max = 0.75*countMaxOfExpectedValue();
        if (max <= 0) return new Vector<mecz>();             
        Vector<Integer> optionsNumber = new Vector<Integer>(0);
        int n = expectedValues.length;       
        for (int i = 0; i < n; i++){
            if(expectedValues[i]>max){
                optionsNumber.add(i);
            }
        }
        double[] antyRisk = new double[optionsNumber.size()];
        for (int i = 0; i < optionsNumber.size(); i++){
            antyRisk[i] = preView[optionsNumber.get(i)];
        }
        double maksimum = max(antyRisk);
        int i = 0;
        while(antyRisk[i] != maksimum){
            i++;
        }
        double[] T = antyRisk;
        Arrays.sort(T);
        if(antyRisk.length == 1){
            mecz A = new mecz(names1[optionsNumber.get(i)],names2[optionsNumber.get(i)],courses[optionsNumber.get(i)],1.0);            
            Vector<mecz> strategy =  new Vector<mecz>();
            strategy.add(A);
            return strategy;
        }
        else{           
            int k = T.length - 1;
            while(k >= 0 && T[k] == maksimum) --k;
            ++k;
            double r = 0;
            if(T.length-1-k == 0){
                r = T[k-1];
                --k;
                while(k >= 0 && T[k] == r) --k;
                int m = T.length-1-k;
                mecz A = new mecz(names1[optionsNumber.get(i)],names2[optionsNumber.get(i)],courses[optionsNumber.get(i)],0.5);            
                Vector<mecz> strategy =  new Vector<mecz>();
                strategy.add(A);                
                for(int l = 0; l < T.length; l++)
                    if(antyRisk[l] == r){
                        strategy.add(new mecz(names1[optionsNumber.get(l)],names2[optionsNumber.get(l)],courses[optionsNumber.get(l)],0.5/m));   
                    }
            return strategy;
            }
            else{
                Vector<mecz> strategy =  new Vector<mecz>();
                for(int l = 0; l < T.length; l++) {
                    if(antyRisk[l] == maksimum){
                        strategy.add(new mecz(names1[optionsNumber.get(l)],names2[optionsNumber.get(l)],courses[optionsNumber.get(l)],1.0/(T.length - k)));   
                    }
                }
                return strategy;
            }         
        }
    }    
    private double countMaxOfExpectedValue(){
        expectedValues = new double[courses.length];  
        for(int i = 0; i < courses.length; i++){
            expectedValues[i] = courses[i]*preView[i] - 1;
        }     
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
        return result2;
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
            System.out.println("349");
            throw new SQLException(ex.getMessage());
        }
        catch(ClassNotFoundException ex){
            System.out.println("353");
            throw new ClassNotFoundException(ex.getMessage());
        }
    }
    public void start2()throws SQLException, ClassNotFoundException{
        try{
        generate();}
        catch(SQLException ex){
        throw new SQLException(ex.getMessage());
        }
        catch(ClassNotFoundException ex){
        throw new ClassNotFoundException(ex.getMessage());
        }
    }        
    private void generate() throws SQLException, ClassNotFoundException{
        database.con.close();
        database = new SQL();
        Statement stat;
        stat = database.con.createStatement(); 
        String query = "select DIV, DATA, HomeTeam, AwayTeam, FTHG, FTAG, K1, K2 from MECZE_STATYSTYKI where K1 not null and K2 not null and (K1 > 1.8 or K2 > 1.9) order by data desc";
        ResultSet rs1 = stat.executeQuery(query);   
        while (rs1.next()) {          
            hometeam1.add(rs1.getString(3));
            awayteam1.add(rs1.getString(4));
            ligue1.add(rs1.getString(1));
            data1.add(Integer.parseInt(rs1.getString(2)));
            H.add(Integer.parseInt(rs1.getString(5)));
            A.add(Integer.parseInt(rs1.getString(6)));
            K1.add(Double.parseDouble(rs1.getString(7)));
            K2.add(Double.parseDouble(rs1.getString(8)));
        }
        stat.close();
        if (hometeam1.size() > 0) X = false;
    }        
    private void generate(int date2) throws SQLException, ClassNotFoundException{
        database.con.close();
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
        query = "select DIV, DATA, HomeTeam, AwayTeam, FTHG, FTAG from MECZE_STATYSTYKI where DIV not null and DATA not null and HomeTeam not null and AwayTeam not null and FTHG not null and FTAG not null order by data desc";
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
        String[] names1 = new String[q1];
        String[] names2 = new String[q1];
        double[] courses = new double[q1];
        double[] preView = new double[q1];        
        int k = 0;
        for(int i = 0; i < q1; ++i)
        {
            if(H_A.get(i) > 1.8 && H_A.get(i) > A_H.get(i)){
                int rangeH = hRange(i);
                int rangeA = aRange(i);
                if (H_A.get(i)*(double)(rangeH - rangeA)/100.0 > 0.1){
                    names1[k] = hometeam.get(i); 
                    names2[k] = awayteam.get(i);
                    courses[k] = H_A.get(i);
                    preView[k] = (double)(rangeH - rangeA)/100.0;
                    k++;
                }
                else if(A_H.get(i) > 1.8){               
                    if (A_H.get(i)*(double)(rangeA - rangeH)/100.0 > 0.1){
                        names1[k] = awayteam.get(i);
                        names2[k] = hometeam.get(i);
                        courses[k] = A_H.get(i);
                        preView[k] = (double)(rangeA - rangeH)/100.0;
                        k++;
                    }                                
                }
            }
            else if(A_H.get(i) > 1.8){
                int rangeH = hRange(i);
                int rangeA = aRange(i);              
                if (A_H.get(i)*(double)(rangeA - rangeH)/100.0 > 0.1){
                    names1[k] = awayteam.get(i);
                    names2[k] = hometeam.get(i);
                    courses[k] = A_H.get(i);
                    preView[k] = (double)(rangeA - rangeH)/100.0;
                    k++;
                }
            if(H_A.get(i) > 1.8){
                    if (H_A.get(i)*(double)(rangeH - rangeA)/100.0 > 0.1){
                        names1[k] = hometeam.get(i);
                          names2[k] = awayteam.get(i);
                        courses[k] = H_A.get(i);
                        preView[k] = (double)(rangeH - rangeA)/100.0;
                        k++;
                    }
                }
            }
        }
        if(k!=0){
            X = false;
            this.names1 = new String[k];
            this.names2 = new String[k];
            this.courses = new double[k];
            this.preView = new double[k]; 
            while (k > 0){
                k--;
                this.names1[k] = names1[k];
                this.names2[k] = names2[k];
                this.courses[k] = courses[k];
                this.preView[k] = preView[k];                 
            }
        }
        else {return;}
    }    
    private int hRange(int i){
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
        else if(!a && !b && !c) rangeH-=25; //kiedyś będzie trzeba się temu przyjrzeć dokładniej. 
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
    private int hRangeT(int i){
        int q2 = hometeam1.size();        
        int rangeH = 50;
        boolean a = false,b = false,c = false;        
        int j = i+1;
        for(; j < q2; ++j){
            if(hometeam1.get(i).equals(hometeam1.get(j))){
                a = (H.get(j) > A.get(j));
                break;
            }
            else if(hometeam1.get(i).equals(awayteam1.get(j))){
                a = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(hometeam1.get(i).equals(hometeam1.get(j))){
                b = (H.get(j) > A.get(j));
                break;
            }
            else if(hometeam1.get(i).equals(awayteam1.get(j))){
                b = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(hometeam1.get(i).equals(hometeam1.get(j))){
                c = (H.get(j) > A.get(j));
                break;
            }
            else if(hometeam1.get(i).equals(awayteam1.get(j))){
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
            if(hometeam1.get(i).equals(hometeam1.get(j)) && awayteam1.get(i).equals(awayteam1.get(j))){
                a = (H.get(j) > A.get(j));
                break;
            }
            else if(hometeam1.get(i).equals(awayteam1.get(j)) && awayteam1.get(i).equals(hometeam1.get(j))){
                a = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(hometeam1.get(i).equals(hometeam1.get(j))){
                b = (H.get(j) > A.get(j));
                break;
            }
            else if(hometeam1.get(i).equals(awayteam1.get(j))){
                b = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(hometeam1.get(i).equals(hometeam1.get(j))){
                c = (H.get(j) > A.get(j));
                break;
            }
            else if(hometeam1.get(i).equals(awayteam1.get(j))){
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
    private int aRange(int i){
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
    private int aRangeT(int i){
        int q2 = hometeam1.size();        
        int rangeA = 50;
        boolean a = false,b = false,c = false;        
        int j = i+1;
        for(; j < q2; ++j){
            if(awayteam1.get(i).equals(hometeam1.get(j))){
                a = (H.get(j) > A.get(j));
                break;
            }
            else if(awayteam1.get(i).equals(awayteam1.get(j))){
                a = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(awayteam1.get(i).equals(hometeam1.get(j))){
                b = (H.get(j) > A.get(j));
                break;
            }
            else if(awayteam1.get(i).equals(awayteam1.get(j))){
                b = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(awayteam1.get(i).equals(hometeam1.get(j))){
                c = (H.get(j) > A.get(j));
                break;
            }
            else if(awayteam1.get(i).equals(awayteam1.get(j))){
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
            if(awayteam1.get(i).equals(hometeam1.get(j)) && hometeam1.get(i).equals(awayteam1.get(j))){
                a = (H.get(j) > A.get(j));
                break;
            }
            else if(awayteam1.get(i).equals(awayteam1.get(j)) && hometeam1.get(i).equals(hometeam1.get(j))){
                a = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(awayteam1.get(i).equals(hometeam1.get(j)) && hometeam1.get(i).equals(awayteam1.get(j))){
                b = (H.get(j) > A.get(j));
                break;
            }
            else if(awayteam1.get(i).equals(awayteam1.get(j)) && hometeam1.get(i).equals(hometeam1.get(j))){
                b = (H.get(j) < A.get(j));
                break;
            }
            else continue;
        }
        for(; j < q2; ++j){
            if(awayteam1.get(i).equals(hometeam1.get(j)) && hometeam1.get(i).equals(awayteam1.get(j))){
                c = (H.get(j) > A.get(j));
                break;
            }
            else if(awayteam1.get(i).equals(awayteam1.get(j)) && hometeam1.get(i).equals(hometeam1.get(j))){
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
