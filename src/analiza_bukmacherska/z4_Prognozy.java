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
        myMoneyText.setText("Posiadana kwota:");
        myMoneyText.setBounds(10, 80, 115, 35);
        add(myMoneyText);
        
        myMoney = new JTextField();
        myMoney.setText("");
        myMoney.setBounds(125, 75, 290, 35);
        add(myMoney);        
        /////////////////////////////////////////////////////////czesc dwa i pol          
        JLabel strAgr = new JLabel();
        strAgr.setText("Metoda agresywna");
        strAgr.setBounds(10, 380, 140, 35);
        
        JLabel strOpt = new JLabel();
        strOpt.setText("Metoda optymalna");
        strOpt.setBounds(150, 380, 140, 35);
       
        JLabel strLam = new JLabel();
        strLam.setText("Metoda zachowawcza");
        strLam.setBounds(290, 380, 140, 35);
        
        add(strLam);
        add(strAgr);
        add(strOpt);
        ///////////////////////////////////////////////////////////czesc trzecia        
        okienko = new m1_okienko(400,200,10,206,"Wybierz strategię gry");
        add(okienko);      
        results = new m1_okienko(500,80,420,10,"Proponowana strategia gry w najbliższym czasie");
        add(results);
        textFrame = new JTextArea();   
        jP_Prognozy = new m1_okienko(400,200,10,10,"Prognozy");
        add(jP_Prognozy);        
    }
    private void setResult(Vector<mecz> strategy, int S){
        if(strategy.isEmpty()){
            opcja = new NastepnyWynik[0];
            textFrame.setLineWrap(true);
            textFrame.setText("Niestety, ale obecnie \nnie ma ciekawych \nmeczów  w których \nopłacałoby \nsię ryzykować.");
            textFrame.setForeground(Color.RED);
            textFrame.setBackground(getBackground());
            textFrame.setEditable(false);
            textFrame.setFont(new Font("sansserif", Font.BOLD, 32));
            textFrame.setBounds(460, 120, 400, 300);
            add(textFrame);
            return;
        }
        else{            
            int N = strategy.size();
            System.out.println(N);
            textFrame.setText("");
            opcja = new NastepnyWynik[N];
            double K = 1.0;
            try{
                K = Double.parseDouble(myMoney.getText());
            }       
            catch(Exception ex){}  
            for(int i = 0; i < N; i++){
                System.out.println(strategy.get(i).wypisz());
                opcja[i] = new NastepnyWynik(""+strategy.get(i).getStawka()*K,strategy.get(i).getTeam1(),strategy.get(i).getTeam2(),"" + (strategy.get(i).getData()%10000)/100 + "." +strategy.get(i).getData()%100,S);
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
