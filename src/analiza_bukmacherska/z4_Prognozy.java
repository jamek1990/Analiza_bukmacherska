package analiza_bukmacherska;
import java.awt.*;
import javax.swing.*;
import java.util.Vector;

        

public class z4_Prognozy extends JLayeredPane{
    m1_okienko jP_Prognozy;
    m1_okienko okienko;
    m1_okienko results;
    JTextField myMoney;
    JTextField chooseMyLeague;
    
    JPanel myResults;
    m1_okienko samplePathUp; 
    m1_okienko samplePathDown;
    JTextArea textFrame;
    JLabel buttonLM;
    JLabel buttonOM;
    JLabel buttonAM;
    NastepnyWynik[] opcja;
    int money;    
    Test test;
    
    
    public z4_Prognozy(){      
        setLocation(0,0);
        setBounds(0, 0, 1024, 600);
        test = new Test(true);
        setWindows();
    }    
    private void setWindows(){
        /////////////////////////////////////////////Funkcjonalności przycisków:
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
        buttonLM.setBounds(30,260,80,80);                
        buttonOM.setBounds(160,260,80,80);        
        buttonAM.setBounds(300,260,80,80);
        add(buttonLM);
        add(buttonOM);                
        add(buttonAM);                            
        /////////////////////////////////////////////////////////Ramka na kwotę:        
        JLabel myMoneyText = new JLabel();
        myMoneyText.setText("POSIADANA KWOTA:");
        myMoneyText.setBounds(10, 80, 125, 35);
        add(myMoneyText);
        
        myMoney = new JTextField();
        myMoney.setText("");
        myMoney.setBounds(135, 75, 270, 35);
        add(myMoney);        
        /////////////////////////////////////////////////////////czesc dwa i pol          
        JLabel strAgr = new JLabel();
        strAgr.setText("METODA SPOKOJNA");
        strAgr.setBounds(10, 380, 140, 35);
        
        JLabel strOpt = new JLabel();
        strOpt.setText("METODA OPTYMALNA");
        strOpt.setBounds(150, 380, 140, 35);
       
        JLabel strLam = new JLabel();
        strLam.setText("METODA AGRESYWNA");
        strLam.setBounds(290, 380, 140, 35);
        
        add(strLam);
        add(strAgr);
        add(strOpt);
        ///////////////////////////////////////////////////////////czesc trzecia        
        okienko = new m1_okienko(400,200,10,206,"WYBIERZ STARTEGIĘ GRY");
        add(okienko);      
        results = new m1_okienko(500,80,420,10,"PROPONOWANA TAKTYKA NA NAJBLIŻSZĄ KOLEJKĘ");
        add(results);
        textFrame = new JTextArea();   
        jP_Prognozy = new m1_okienko(400,200,10,10,"PROGNOZY");
        add(jP_Prognozy); 
        
        opcja = new NastepnyWynik[0];
            textFrame = new JTextArea();
            textFrame.setLineWrap(true);
            textFrame.setText("Niestety, ale obecnie \nnie ma ciekawych \nmeczów  w których \nopłacałoby \nsię ryzykować.");
            textFrame.setForeground(Color.RED);
            textFrame.setBackground(new Color(238,238,238));
            textFrame.setEditable(false);
            textFrame.setFont(new Font("sansserif", Font.BOLD, 32));
            textFrame.setBounds(460, 120, 400, 300);
            textFrame.setVisible(false);
            add(textFrame);  
    }
    private void setResult(Vector<mecz> strategy, int S){
        if(strategy.isEmpty()){
            int k = opcja.length-1;
            while (k >= 0){opcja[k].setVisible(false);--k;}
            opcja = new NastepnyWynik[0];            
            textFrame.setVisible(true);
        }
        else{
            int N = strategy.size();
            System.out.println(N);
            textFrame.setVisible(false);
            int k = opcja.length-1;
            while (k >= 0){opcja[k].setVisible(false);--k;}
            opcja = new NastepnyWynik[N];
            double K = 1.0;
            try{
                K = Double.parseDouble(myMoney.getText());
            }       
            catch(Exception ex){}  
            for(int i = 0; i < N; i++){
                System.out.println(strategy.get(i).wypisz());
                int a = (strategy.get(i).getData()%10000)/100;
                String A = "", B = "";
                if(a > 9) {
                    A += a;
                }
                else {

                    A = A + "0" + a;
                }
                int b = strategy.get(i).getData()%100;                
                if(b > 9) {
                    B += b;
                }
                else{
                    B = B + "0" + b;
                }
                opcja[i] = new NastepnyWynik(""+strategy.get(i).getStawka()*K,strategy.get(i).getTeam1().toUpperCase(),strategy.get(i).getTeam2().toUpperCase(), A + "." + B,S);
                opcja[i].setLocation(460, i*60+60);   
                add(opcja[i]);
            }
        }
    }
    public void lameMethode(){     
        setResult(test.getLameStrategy(),1);
    }  
    public void optimalMethode(){         
        setResult(test.getOptimalStrategy(),0);
    } 
    public void agresiveMethode(){         
        setResult(test.getAgresiveStrategy(),2);
    } 
}
