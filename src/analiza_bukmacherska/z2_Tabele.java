import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
public class z2_Tabele extends JLayeredPane{
    JLayeredPane panel2;
    JLabel baza2;
    JLabel baza3;
    JTable jTable1;
    JLabel jButton4;
    JLabel jButton5;
    JLabel jButton6;
    m1_okienko jP_OknoTabela;
    m1_okienko  jP_OknoWybierzLige;
    m1_okienko  jP_WskaznikiFormy;
    m1_okienko  jP_OstatnieWyniki;
    m1_okienko  jP_Archiwum;
    JLabel jLabel3;
    String wybrana_liga;
    
    private int[] tabela_ligowa_button;
    private int tabela_ligowa_button_akt;
    String columny[] ={"POZ", "DRU", "MEC", "ZWY", "REM", "POR", "GOL", "RÓŻ", "PUN", "   Z%          R%          P%"};
    SQL database;
    
    private Map<Object, Icon> icons = null;
    public void IconListRenderer(Map<Object, Icon> icons) {
        this.icons = icons;
    }
    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {                                      
        if(tabela_ligowa_button_akt != 1){    
            jButton5.setIcon(new javax.swing.ImageIcon("images/btok3.jpg")); 
        }
    }                                     
    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {                                      
        if(tabela_ligowa_button_akt != 2){
            jButton6.setIcon(new javax.swing.ImageIcon("images/btok3.jpg"));
        }
    }                                     

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {                                     
        if(tabela_ligowa_button_akt != 1){
            jButton5.setIcon(new javax.swing.ImageIcon("images/btok2.jpg")); 
        }
    }                                    

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {                                     
        if(tabela_ligowa_button_akt != 2){
            jButton6.setIcon(new javax.swing.ImageIcon("images/btok2.jpg"));
        }
    }                                    

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {                                      
        if(tabela_ligowa_button_akt != 0){
            jButton4.setIcon(new javax.swing.ImageIcon("images/btok3.jpg"));
        }
    }                                     

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {                                     
        if(tabela_ligowa_button_akt != 0){
            jButton4.setIcon(new javax.swing.ImageIcon("images/btok2.jpg")); 
        }
    }
    private void jButton5MousePressed(java.awt.event.MouseEvent evt) throws SQLException, ClassNotFoundException {
        if(tabela_ligowa_button_akt != 1){
            jButton5.setIcon(new javax.swing.ImageIcon("images/btok.jpg")); 
            jButton4.setIcon(new javax.swing.ImageIcon("images/btok2.jpg")); 
            jButton6.setIcon(new javax.swing.ImageIcon("images/btok2.jpg"));
            tabela_ligowa_button_akt=1; 
            Tabelka_dane(wybrana_liga);
            jP_OknoTabela.jL_tytul.setText("TABELA LIGOWA - DOM");
            
        }
    }
    private void jButton4MousePressed(java.awt.event.MouseEvent evt) throws  ClassNotFoundException, SQLException {
        if(tabela_ligowa_button_akt != 0){
            jButton5.setIcon(new javax.swing.ImageIcon("images/btok2.jpg")); 
            jButton4.setIcon(new javax.swing.ImageIcon("images/btok.jpg")); 
            jButton6.setIcon(new javax.swing.ImageIcon("images/btok2.jpg"));
            tabela_ligowa_button_akt=0; 
            Tabelka_dane(wybrana_liga);
            jP_OknoTabela.jL_tytul.setText("TABELA LIGOWA");
            
        }
    }
    private void jButton6MousePressed(java.awt.event.MouseEvent evt) throws SQLException, ClassNotFoundException {
        if(tabela_ligowa_button_akt != 2){
            jButton5.setIcon(new javax.swing.ImageIcon("images/btok2.jpg")); 
            jButton4.setIcon(new javax.swing.ImageIcon("images/btok2.jpg")); 
            jButton6.setIcon(new javax.swing.ImageIcon("images/btok.jpg"));
            tabela_ligowa_button_akt=2;
            Tabelka_dane(wybrana_liga);
            jP_OknoTabela.jL_tytul.setText("TABELA LIGOWA - WYJAZD");
            
        }
    }
    class ImageRenderer extends DefaultTableCellRenderer {
        JLabel lbl = new JLabel();
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            lbl.setIcon((ImageIcon)value);
            return lbl;
        }
    }
    public Component getListCellRendererComponent(JList list, Object value, int index,
        boolean isSelected, boolean cellHasFocus) {
        JLabel label =new JLabel();
        Icon icon = icons.get(value); 
        label.setIcon(icon);
        return label;
    }
    private void Dodaj_Paski() {                                         
        try {
            File file1 = new File("images/wygrana2.jpg");
            File file2 = new File("images/remis2.jpg");
            File file3 = new File("images/przegrana2.jpg");
            BufferedImage img1 =ImageIO.read(file1);
            BufferedImage img2 = ImageIO.read(file2);
            BufferedImage img3 = ImageIO.read(file3);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            for(int i=0; i<10; i++)
            jTable1.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            jTable1.getColumnModel().getColumn(9).setCellRenderer(new ImageRenderer());
            DefaultTableModel Tmodel=(DefaultTableModel) jTable1.getModel();
            Tmodel.fireTableDataChanged();
            for(int i =0; i<jTable1.getRowCount();i++){
                int a = Integer.parseInt(jTable1.getValueAt(i,3).toString());
                int b=  Integer.parseInt(jTable1.getValueAt(i,4).toString());
                int d=  Integer.parseInt(jTable1.getValueAt(i,2).toString());
                BufferedImage image = new BufferedImage(135, img1.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = (Graphics2D) image.getGraphics();
                g2.drawImage(img1, 0,0,null);
                g2.drawImage(img2, a*135/d,0,null);
                g2.drawImage(img3, (a+b)*135/d,0,null);
                g2.dispose();
                ImageIcon newImg = new ImageIcon(image);
                Tmodel.setValueAt(newImg, i, 9);
            }
        } catch (IOException ex) {}
    } 
    
    public void Tabelka_dane(String liga) throws SQLException, ClassNotFoundException{
        database =new SQL();
        Statement stat;
        Statement stat2;
        //database.createDB();
        String query="SELECT FTHG,FTAG,FTR,HomeTeam,AwayTeam, DATA FROM MECZE_STATYSTYKI WHERE DIV = '"+ liga +"'  AND substr(DATA,4,2)+ substr(DATA,7,2)*2012  >2012*12+7";
        String query2="SELECT DISTINCT(HomeTeam)FROM MECZE_STATYSTYKI3 WHERE DIV = '"+ liga +"'  AND length(DATA) = 8 AND substr(DATA,4,2)+ substr(DATA,7,2)*2012  >2012*12+7 ORDER BY HOMETEAM";
        Integer columnCount=10;
        Vector data = new Vector(columnCount);
        Vector row = new Vector(columnCount);
        Vector columnNames = new Vector(columnCount);
        stat = database.con.createStatement(); 
        stat2 = database.con.createStatement(); 
        ResultSet rs = stat.executeQuery(query2);
        Integer dr = 1;
        while (rs.next()) {
            Integer Punkty = 0;
            Integer Mecze = 0;
            Integer Wygrane = 0;
            Integer Remisy = 0;
            Integer Przegrane = 0;
            Integer Gole_strzelone = 0;
            Integer Gole_stracone = 0;
            Integer d1,d2;
            if(tabela_ligowa_button_akt==0)
                query="SELECT FTHG,FTAG,FTR,HomeTeam,AwayTeam, DATA FROM MECZE_STATYSTYKI3 WHERE DIV = '"+ liga +"'  AND length(DATA) = 8 AND substr(DATA,4,2)+ substr(DATA,7,2)*2012  >2012*12+7 AND (HomeTeam='" +rs.getString(1) + "' OR AWAYTEAM='"+rs.getString(1) + "')" ;
            else if(tabela_ligowa_button_akt==1)
                query="SELECT FTHG,FTAG,FTR,HomeTeam,AwayTeam, DATA FROM MECZE_STATYSTYKI3 WHERE DIV = '"+ liga +"'  AND length(DATA) = 8 AND substr(DATA,4,2)+ substr(DATA,7,2)*2012  >2012*12+7 AND (HomeTeam='" +rs.getString(1) + "')" ;
            else if(tabela_ligowa_button_akt==2)
                query="SELECT FTHG,FTAG,FTR,HomeTeam,AwayTeam, DATA FROM MECZE_STATYSTYKI3 WHERE DIV = '"+ liga +"'  AND length(DATA) = 8 AND substr(DATA,4,2)+ substr(DATA,7,2)*2012  >2012*12+7 AND (AWAYTEAM='"+rs.getString(1) + "')" ;
           
            ResultSet rs2 = stat2.executeQuery(query);
            while (rs2.next()) {
                Mecze = Mecze + 1;
                if (rs2.getString(4).contentEquals(rs.getString(1))==true){
                    d1 = rs2.getInt(1);
                    d2 = rs2.getInt(2);
                    Gole_strzelone = Gole_strzelone + d1;
                    Gole_stracone = Gole_stracone + d2;
                    if(rs2.getString(3).contentEquals("H")){
                        Wygrane = Wygrane + 1;
                        Punkty = Punkty + 3;
                    }else if(rs2.getString(3).contentEquals("D")){
                        Remisy = Remisy + 1;
                        Punkty = Punkty + 1;
                    }else{
                        Przegrane = Przegrane + 1;
                    }
                }else{
                    d1 = rs2.getInt(2);
                    d2 = rs2.getInt(1);
                    Gole_strzelone = Gole_strzelone + d1;
                    Gole_stracone = Gole_stracone + d2;
                    if(rs2.getString(3).contentEquals("A")){
                        Wygrane = Wygrane + 1;
                        Punkty = Punkty + 3;
                    }
                    else if(rs2.getString(3).contentEquals("D")){
                        Remisy = Remisy + 1;
                        Punkty = Punkty + 1;
                    }else{
                        Przegrane = Przegrane + 1;
                    }
                } 
            }
            row.addElement(new Integer(dr));
            row.addElement(rs.getString(1));
            row.addElement(new Integer(Mecze));
            row.addElement(new Integer(Wygrane));
            row.addElement(new Integer(Remisy));
            row.addElement(new Integer(Przegrane));
            row.addElement(new Integer(Gole_strzelone));
            row.addElement(new Integer(Gole_strzelone-Gole_stracone));
            row.addElement(new Integer(Punkty));       
            data.addElement(row);
            row = new Vector(columnCount);
            dr = dr + 1;
        }
        
        for (int i = 1; i <= columnCount; i++) {
            columnNames.addElement(columny[i-1]);
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        jTable1.setModel( model ); 
        
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(207, 205, 205));
        jTable1.setRequestFocusEnabled(false);
        jTable1.setSelectionBackground(new java.awt.Color(83, 81, 83));
        jTable1.setSelectionForeground(new java.awt.Color(0, 174, 239));
        jTable1.getTableHeader().setReorderingAllowed(false);
        for(int i=0;i<10;i++){
            jTable1.getColumnModel().getColumn(i).setResizable(false);
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(20);
        }
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(100);
        TableRowSorter trs = new TableRowSorter(model);
        for(int i=0;i<=8;i++){
            if(i!=1){
                trs.setComparator(i, new sort());
                jTable1.setRowSorter(trs);
            }
        }
        jTable1.setAutoCreateRowSorter(false);
        Dodaj_Paski();

        /*jTable1.setAutoCreateColumnsFromModel(false);
        sortAllRowsBy(model, 8, false);
        jTable1.setAutoCreateRowSorter(true);*/
        stat.close();
        stat2.close();
        database.con.close();
        
    }
    
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
                wybrana_liga = s;
                try {
                    Tabelka_dane(s);
                } catch (SQLException ex) {
                    Logger.getLogger(z2_Tabele.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(z2_Tabele.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
    
    public z2_Tabele() throws SQLException, ClassNotFoundException{
        wybrana_liga="E0";
        tabela_ligowa_button = new int[3];
        tabela_ligowa_button[0]=1;
        tabela_ligowa_button[1]=0;
        tabela_ligowa_button[2]=0;
        tabela_ligowa_button_akt=0;
        JLabel  jLabel2 =new  JLabel();
        jLabel3 =new  JLabel();
        
        jP_OknoTabela = new  m1_okienko(770,371,3,3,"TABELA LIGOWA");
        jP_OknoWybierzLige = new  m1_okienko(230,371,780,3,"LIGA");
        jP_WskaznikiFormy = new  m1_okienko(500,371,3,375,"WSKAŹNIKI FORMY");
        jP_OstatnieWyniki= new  m1_okienko(500,371,510,375,"OSTATNIE WYNIKI");
        jP_Archiwum= new  m1_okienko(230,371,780,235,"ARCHIWUM");
        jButton4 =new  JLabel();
        jButton5 =new  JLabel();
        jButton6 =new  JLabel();
        jTable1 = new JTable();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        jButton4.setIcon(new javax.swing.ImageIcon("images/btok.jpg"));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {jButton4MouseEntered(evt);}
            public void mouseExited(java.awt.event.MouseEvent evt)  {jButton4MouseExited(evt);}
            public void mousePressed(java.awt.event.MouseEvent evt) {try {
                    jButton4MousePressed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(z2_Tabele.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(z2_Tabele.class.getName()).log(Level.SEVERE, null, ex);
                }
        }});        jP_OknoTabela.add(jButton4,1);
        jButton4.setBounds(700, 5, 16, 16);
        jButton5.setIcon(new javax.swing.ImageIcon("images/btok2.jpg"));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {jButton5MouseEntered(evt);}
            public void mouseExited(java.awt.event.MouseEvent evt)  {jButton5MouseExited(evt); }
            public void mousePressed(java.awt.event.MouseEvent evt) {try {
                    jButton5MousePressed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(z2_Tabele.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(z2_Tabele.class.getName()).log(Level.SEVERE, null, ex);
                }
}
        });
        jP_OknoTabela.add(jButton5,1);
        jButton5.setBounds(720, 5, 16, 16);
        jButton6.setIcon(new javax.swing.ImageIcon("images/btok2.jpg"));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {jButton6MouseEntered(evt);}
            public void mouseExited(java.awt.event.MouseEvent evt)  {jButton6MouseExited(evt);}
            public void mousePressed(java.awt.event.MouseEvent evt) {try {
                    jButton6MousePressed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(z2_Tabele.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(z2_Tabele.class.getName()).log(Level.SEVERE, null, ex);
                }
}           
        });
        jP_OknoTabela.add(jButton6,1);
        jButton6.setBounds(740, 5, 16, 16);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "POZ", "DRU", "MEC", "ZWY", "REM", "POR", "GOL", "RÓŻ", "PUN", "   Z%          R%          P%"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabelka_dane("I1");
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(207, 205, 205));
        jTable1.setRequestFocusEnabled(false);
        jTable1.setSelectionBackground(new java.awt.Color(83, 81, 83));
        jTable1.setSelectionForeground(new java.awt.Color(0, 174, 239));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        for(int i=0;i<10;i++){
            jTable1.getColumnModel().getColumn(i).setResizable(false);
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(20);
        }
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(100);
        
        jP_OknoTabela.add(jScrollPane1);
        jScrollPane1.setBounds(0, 30, 770, 350);
        Dodaj_Paski();
        //setLocation(0,0);
        setBounds(0, 0, 1024, 500);
        add(jP_OknoTabela);
        add(jP_WskaznikiFormy);
        jP_OstatnieWyniki.dodajPodzial();
        add(jP_OstatnieWyniki);
        add(jP_Archiwum);
        
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
        jP_OknoWybierzLige.add(list);
        list.setBounds(0, 30, 230, 198);
        add(jP_OknoWybierzLige);
        
    }
}
