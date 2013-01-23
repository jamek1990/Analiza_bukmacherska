package wybierz_lige;

import analiza_bukmacherska.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WybierzLige extends JLayeredPane{
    
    WybranaLiga wybrana_liga;
    m1_okienko  jP_OknoWybierzLige;
    DefaultListModel model = new DefaultListModel();
    DefaultListModel listModel  = new DefaultListModel();
    JList list = new JList(listModel);
    Map<Object, Icon> icons = new HashMap<Object, Icon>();
     
    ListSelectionListener listSelectionListener = new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            boolean adjust = listSelectionEvent.getValueIsAdjusting();
            if (!adjust) {      
                //icons.put("Szkocja2",new javax.swing.ImageIcon("images/Scotland.gif"));
                //list = new JList(new Object[] {"Szkocja2", "Belgia", "Francja", "Grecja", "Hiszpania", "Holandia","Niemcy", "Portugalia", "Szkocja", "Turcja" , "Włochy"});
                //listModel.addElement("Szkocja2");
                //list.setCellRenderer(new m2_lista_ikonki(icons));
                //JList list = (JList) listSelectionEvent.getSource();
                int selections[] = list.getSelectedIndices();
                          Integer ind =selections[0];
                String s="";
                if(ind == 0)s="E0";
                if(ind == 1)s="E1";
                if(ind == 2)s="E2";
                if(ind == 3)s="E3";
                if(ind == 4)s="EC";
                if(ind == 5)s="B1";
                if(ind == 6)s="F1";
                if(ind == 7)s="F2";
                if(ind == 8)s="G1";
                if(ind == 9)s="SP1";
                if(ind == 10)s="SP2";
                if(ind == 11)s="N1";
                if(ind == 12)s="D1";
                if(ind == 13)s="D2";
                if(ind == 14)s="P1";
                if(ind == 15)s="SC0";
                if(ind == 16)s="SC1";
                if(ind == 17)s="SC2";
                if(ind == 18)s="SC3";
                if(ind == 19)s="T1";
                if(ind == 20)s="I1";
                wybrana_liga.setWybranaliga(s);
                wybrana_liga.Wyslij();
            }
        }
    };
    
    public WybierzLige(WybranaLiga wybrana_liga){
        this.wybrana_liga = wybrana_liga;
        jP_OknoWybierzLige = new  m1_okienko(230,408,0,0,"LIGA");
        wybrana_liga=new WybranaLiga();
        icons.put("Anglia",new javax.swing.ImageIcon("images/England.gif"));
        icons.put("Anglia1",new javax.swing.ImageIcon("images/England.gif"));
        icons.put("Anglia2",new javax.swing.ImageIcon("images/England.gif"));
        icons.put("Anglia3",new javax.swing.ImageIcon("images/England.gif"));
        icons.put("AngliaC",new javax.swing.ImageIcon("images/England.gif"));
        icons.put("Francja1",new javax.swing.ImageIcon("images/France.gif"));
        icons.put("Francja2",new javax.swing.ImageIcon("images/France.gif"));
        icons.put("Niemcy1",new javax.swing.ImageIcon("images/Germany.gif"));
        icons.put("Niemcy2",new javax.swing.ImageIcon("images/Germany.gif"));
        icons.put("Belgia",new javax.swing.ImageIcon("images/Belgium.gif"));
        icons.put("Portugalia",new javax.swing.ImageIcon("images/Portugal.gif"));
        icons.put("Turcja",new javax.swing.ImageIcon("images/Turkey.gif"));
        icons.put("Włochy",new javax.swing.ImageIcon("images/Italy.gif"));
        icons.put("Grecja",new javax.swing.ImageIcon("images/Greece.gif"));
        icons.put("Hiszpania1",new javax.swing.ImageIcon("images/Spain.gif"));
        icons.put("Hiszpania2",new javax.swing.ImageIcon("images/Spain.gif"));
        icons.put("Holandia",new javax.swing.ImageIcon("images/Netherlands.gif"));
        icons.put("Szkocja",new javax.swing.ImageIcon("images/Scotland.gif"));
        icons.put("Szkocja1",new javax.swing.ImageIcon("images/Scotland.gif"));
        icons.put("Szkocja2",new javax.swing.ImageIcon("images/Scotland.gif"));
        icons.put("Szkocja3",new javax.swing.ImageIcon("images/Scotland.gif"));
        listModel.addElement("Anglia");
        listModel.addElement("Anglia1");
        listModel.addElement("Anglia2");
        listModel.addElement("Anglia3");
        listModel.addElement("AngliaC");
        listModel.addElement("Belgia");
        listModel.addElement("Francja1");
        listModel.addElement("Francja2");
        listModel.addElement("Grecja");
        listModel.addElement("Hiszpania1");
        listModel.addElement("Hiszpania2");
        listModel.addElement("Holandia");
        listModel.addElement("Niemcy1");
        listModel.addElement("Niemcy2");
        listModel.addElement("Portugalia");
        listModel.addElement("Szkocja");
        listModel.addElement("Szkocja1");
        listModel.addElement("Szkocja2");
        listModel.addElement("Szkocja3");
        listModel.addElement("Turcja");
        listModel.addElement("Włochy");
        list.setCellRenderer(new m2_lista_ikonki(icons));
        list.addListSelectionListener(listSelectionListener);
        list.setBounds(0, 30, 230, 538);
        jP_OknoWybierzLige.add(list);
        System.out.println("LIGA");
        add(jP_OknoWybierzLige);
        setBounds(780,6,230,531);
    }
}
