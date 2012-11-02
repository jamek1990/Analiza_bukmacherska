import analiza_bukmacherska.z2_Tabele_Forma.Forma;
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
    JLabel forma;
    JLabel forma2;
    m1_okienko jP_OknoTabela;
    m1_okienko  jP_OknoWybierzLige;
    m1_okienko  jP_WskaznikiFormy;
    m1_okienko  jP_StatystykiMeczow;
    m1_okienko  jP_OstatnieWyniki;
    m1_okienko  jP_Archiwum;
    JLabel jLabel3;
    String wybrana_liga;
    Forma form;
    
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
        String query="";
        Integer columnCount=10;
        Vector data = new Vector(columnCount);
        Vector row = new Vector(columnCount);
        Vector columnNames = new Vector(columnCount);
        stat = database.con.createStatement(); 
        Integer dr = 1;
        if(tabela_ligowa_button_akt==0){
            query = "SELECT DRU,SUM(MEC),SUM(ZWY),SUM(REM),SUM(POR),SUM(GOL),SUM(ROZ),SUM(PUN) AS PUNN "+
            "FROM( "+
            "SELECT  HomeTeam AS DRU,COUNT(FTR) AS MEC , sum(CASE WHEN FTR = 'H' THEN 1 ELSE 0 END) AS ZWY ,sum(CASE WHEN FTR = 'D' THEN 1 ELSE 0 END) AS REM,sum(CASE WHEN FTR = 'A' THEN 1 ELSE 0 END) AS POR,SUM(FTHG) AS GOL ,SUM(FTHG)-SUM(FTAG)  AS ROZ, sum(CASE WHEN FTR = 'H' THEN 3 ELSE 0 END) +SUM(CASE WHEN FTR = 'D' THEN 1 ELSE 0 END) AS PUN "+
            "FROM MECZE_STATYSTYKI WHERE DIV = '"+ liga + "' AND DATA>'20120701' "+
            "GROUP BY  HomeTeam "+
            "UNION "+
            "SELECT  AwayTeam  AS DRU,COUNT(FTR) AS MEC , sum(CASE WHEN FTR = 'A' THEN 1 ELSE 0 END) AS ZWY ,sum(CASE WHEN FTR = 'D' THEN 1 ELSE 0 END) AS REM,sum(CASE WHEN FTR = 'H' THEN 1 ELSE 0 END) AS POR,SUM(FTAG) AS GOL ,SUM(FTAG)-SUM(FTHG)  AS ROZ, sum(CASE WHEN FTR = 'A' THEN 3 ELSE 0 END) +SUM(CASE WHEN FTR = 'D' THEN 1 ELSE 0 END) AS PUN "+
            "FROM MECZE_STATYSTYKI WHERE DIV = '"+ liga + "' AND DATA>'20120701' "+
            "GROUP BY  AwayTeam "+
            ") "+
            "GROUP BY DRU "+
            "ORDER BY PUNN DESC ";
        }else if(tabela_ligowa_button_akt==1){
            query = "SELECT  HomeTeam AS DRU,COUNT(FTR) AS MEC , sum(CASE WHEN FTR = 'H' THEN 1 ELSE 0 END) AS ZWY ,sum(CASE WHEN FTR = 'D' THEN 1 ELSE 0 END) AS REM,sum(CASE WHEN FTR = 'A' THEN 1 ELSE 0 END) AS POR,SUM(FTHG) AS GOL ,SUM(FTHG)-SUM(FTAG)  AS ROZ, sum(CASE WHEN FTR = 'H' THEN 3 ELSE 0 END) +SUM(CASE WHEN FTR = 'D' THEN 1 ELSE 0 END) AS PUN  FROM MECZE_STATYSTYKI WHERE DIV = '"+ liga + "' AND DATA>'20120701' GROUP BY  HomeTeam ORDER BY PUN DESC";
        }else if(tabela_ligowa_button_akt==2){
            query = "SELECT  AwayTeam AS DRU,COUNT(FTR) AS MEC , sum(CASE WHEN FTR = 'A' THEN 1 ELSE 0 END) AS ZWY ,sum(CASE WHEN FTR = 'D' THEN 1 ELSE 0 END) AS REM,sum(CASE WHEN FTR = 'H' THEN 1 ELSE 0 END) AS POR,SUM(FTAG) AS GOL ,SUM(FTAG)-SUM(FTHG)  AS ROZ, sum(CASE WHEN FTR = 'A' THEN 3 ELSE 0 END) +SUM(CASE WHEN FTR = 'D' THEN 1 ELSE 0 END) AS PUN  FROM MECZE_STATYSTYKI WHERE DIV = '"+ liga + "' AND DATA>'20120701' GROUP BY  AwayTeam ORDER BY PUN DESC";
        }
        ResultSet rs = stat.executeQuery(query);
        while (rs.next()) {
            row.addElement(new Integer(dr));
            row.addElement(rs.getString(1));
            row.addElement(new Integer(rs.getInt(2)));
            row.addElement(new Integer(rs.getInt(3)));
            row.addElement(new Integer(rs.getInt(4)));
            row.addElement(new Integer(rs.getInt(5)));
            row.addElement(new Integer(rs.getInt(6)));
            row.addElement(new Integer(rs.getInt(7)));
            row.addElement(new Integer(rs.getInt(8)));       
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
        stat.close();
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
                  //  jTable1.setRowSelectionAllowed(false);
                    Tabelka_dane(s);
                    //jTable1.setRowSelectionAllowed(false);
                } catch (SQLException ex) {
                    Logger.getLogger(z2_Tabele.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(z2_Tabele.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
    class RowListener implements ListSelectionListener{  
        public void valueChanged(ListSelectionEvent e){  
            if(!e.getValueIsAdjusting()){  
                ListSelectionModel model = jTable1.getSelectionModel();  
                 try { 
                     int lead = model.getLeadSelectionIndex();  
                
                    try {
                        if(lead!=-1)
                            displayRowValues(lead);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(z2_Tabele.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(z2_Tabele.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
        }
        private void displayRowValues(int rowIndex) throws SQLException, ClassNotFoundException{  
            SQL database =new SQL();
            Statement stat;
            stat = database.con.createStatement();
            String team =jTable1.getValueAt(rowIndex, 1).toString();
            String query = "SELECT   HOMETEAM, AWAYTEAM ,FTR,FTHG,FTAG, DATA FROM MECZE_STATYSTYKI  WHERE DIV ='" + wybrana_liga + "' AND (HOMETEAM = '" + team + "' OR AWAYTEAM = '" + team +"') ORDER BY DATA DESC LIMIT 6";
            ResultSet rs = stat.executeQuery(query);
            form.update_data(rs,team);
            stat.close();
        }  
    }  
    
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
        jP_Archiwum= new  m1_okienko(230,371,780,235,"ARCHIWUM");
        jButton4 =new  JLabel();
        jButton5 =new  JLabel();
        jButton6 =new  JLabel();
        jTable1 = new JTable(){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
        Component returnComp = super.prepareRenderer(renderer, row, column);
        Color alternateColor = new Color(232,232,236);
        Color alternateColor2 = new Color(242,245,255);
        if (!returnComp.getBackground().equals(getSelectionBackground())){
            Color bg = (row % 2 == 0 ? alternateColor : alternateColor2);
            returnComp .setBackground(bg);
            bg = null;
        }
        return returnComp;
        }};
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
      //  Tabelka_dane("I1");
        
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
        jTable1.setBackground(new java.awt.Color(209, 210, 211));
        jTable1.setOpaque(true);
        ListSelectionModel selectionModel = jTable1.getSelectionModel();  
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        selectionModel.addListSelectionListener(new RowListener()); 
        jP_OknoTabela.setBackground(new java.awt.Color(209, 210, 211));
        jP_OknoTabela.setOpaque(true);
        jScrollPane1.setBackground(new java.awt.Color(209, 210, 211));
        jScrollPane1.setOpaque(true);
        Dodaj_Paski();
       // jTable1.setRowSelectionInterval(0, 1);
        //setLocation(0,0);
        setBounds(0, 0, 1024, 530);
        jP_WskaznikiFormy = new  m1_okienko(400,201,3,375,"WSKAŹNIKI FORMY");
        jP_StatystykiMeczow = new  m1_okienko(250,201,410,375,"WSKAŹNIKI FORMY");
        jP_OstatnieWyniki= new  m1_okienko(344,201,667,375,"OSTATNIE WYNIKI");
        
       // forma = new JLabel();
        forma2 = new JLabel();
        //forma.setIcon(new javax.swing.ImageIcon("images/forma.png"));
        forma2.setIcon(new javax.swing.ImageIcon("images/forma2.png"));
        jP_StatystykiMeczow.add(forma2);

        forma2.setBounds(0, 20, 261, 125);
        
        
        form = new Forma();
        jP_WskaznikiFormy.add(form);
        jP_WskaznikiFormy.dodajPodzial();
        form.setBounds(0, 0, 541, 150);
        jP_WskaznikiFormy.setBackground(new java.awt.Color(209, 210, 211));
        jP_WskaznikiFormy.setOpaque(true);
        add(jP_WskaznikiFormy);
        
        add(jP_OknoTabela);
        
        
        add(jP_StatystykiMeczow);
        
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
        
        setBackground(new java.awt.Color(209, 210, 211));
        setOpaque(true);
        
    }
}
