package wybierz_lige;

import analiza_bukmacherska.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WybierzLige extends JLayeredPane{
    
    WybranaLiga wybrana_liga;
    m1_okienko  jP_OknoWybierzLige;
    
    ListSelectionListener listSelectionListener = new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            boolean adjust = listSelectionEvent.getValueIsAdjusting();
            if (!adjust) {
                JList list = (JList) listSelectionEvent.getSource();
                int selections[] = list.getSelectedIndices();
                Integer ind =selections[0];
                String s="";
                if(ind == 0)s="E0";
                if(ind == 1)s="B1";
                if(ind == 2)s="F1";
                if(ind == 3)s="G1";
                if(ind == 4)s="SP1";
                if(ind == 5)s="N1";
                if(ind == 6)s="D1";
                if(ind == 7)s="P1";
                if(ind == 8)s="SC0";
                if(ind == 9)s="T1";
                if(ind == 10)s="I1";
                wybrana_liga.setWybranaliga(s);
                wybrana_liga.Wyslij();
            }
        }
    };
    
    public WybierzLige(WybranaLiga wybrana_liga){
        this.wybrana_liga = wybrana_liga;
        jP_OknoWybierzLige = new  m1_okienko(230,371,0,0,"LIGA");
        wybrana_liga=new WybranaLiga();
        JList list = new JList(new Object[] {"Anglia", "Belgia", "Francja", "Grecja", "Hiszpania", "Holandia","Niemcy", "Portugalia", "Szkocja", "Turcja" , "Włochy"});
        Map<Object, Icon> icons = new HashMap<Object, Icon>();
        icons.put("Anglia",new javax.swing.ImageIcon("images/England.gif"));
        icons.put("Francja",new javax.swing.ImageIcon("images/France.gif"));
        icons.put("Niemcy",new javax.swing.ImageIcon("images/Germany.gif"));
        icons.put("Belgia",new javax.swing.ImageIcon("images/Belgium.gif"));
        icons.put("Portugalia",new javax.swing.ImageIcon("images/Portugal.gif"));
        icons.put("Turcja",new javax.swing.ImageIcon("images/Turkey.gif"));
        icons.put("Włochy",new javax.swing.ImageIcon("images/Italy.gif"));
        icons.put("Grecja",new javax.swing.ImageIcon("images/Greece.gif"));
        icons.put("Hiszpania",new javax.swing.ImageIcon("images/Spain.gif"));
        icons.put("Holandia",new javax.swing.ImageIcon("images/Netherlands.gif"));
        icons.put("Szkocja",new javax.swing.ImageIcon("images/Scotland.gif"));
        list.setCellRenderer(new m2_lista_ikonki(icons));
        list.addListSelectionListener(listSelectionListener);
        list.setBounds(0, 30, 230, 198);
        jP_OknoWybierzLige.add(list);
        System.out.println("LIGA");
        add(jP_OknoWybierzLige);
        setBounds(780,6,230,371);
    }
}
