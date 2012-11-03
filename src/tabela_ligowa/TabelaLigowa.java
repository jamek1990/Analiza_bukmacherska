package tabela_ligowa;
import analiza_bukmacherska.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Map;
import java.util.Observer;
import java.util.Observable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import wybierz_lige.WybranaLiga;



public class TabelaLigowa extends JLayeredPane implements Observer{
    Observable observable;
    WybranaDruzyna wybranaDruzyna;
    public void update(Observable obs, Object arg) {
        if (obs instanceof WybranaLiga) {
            WybranaLiga wybranaLiga = (WybranaLiga)obs;
            try {
                wybranaLigas= wybranaLiga.getWybranaliga();
                Tabelka_dane(wybranaLiga.getWybranaliga());
            } catch (SQLException ex) {
                Logger.getLogger(TabelaLigowa.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TabelaLigowa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    JTable jTable1;
    m1_okienko jP_OknoTabela;
    String wybranaLigas;
    SQL database;
    JLabel jButton4;
    JLabel jButton5;
    JLabel jButton6;
    private int[] tabela_ligowa_button;
    private int tabela_ligowa_button_akt;
    String columny[] ={"POZ", "DRU", "MEC", "ZWY", "REM", "POR", "GOL", "RÓŻ", "PUN", "   Z%          R%          P%"};
    private Map<Object, Icon> icons = null;
    public void IconListRenderer(Map<Object, Icon> icons) {
        this.icons = icons;
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
            Tabelka_dane(wybranaLigas);
            jP_OknoTabela.jL_tytul.setText("TABELA LIGOWA - DOM");
            
        }
    }
    private void jButton4MousePressed(java.awt.event.MouseEvent evt) throws  ClassNotFoundException, SQLException {
        if(tabela_ligowa_button_akt != 0){
            jButton5.setIcon(new javax.swing.ImageIcon("images/btok2.jpg")); 
            jButton4.setIcon(new javax.swing.ImageIcon("images/btok.jpg")); 
            jButton6.setIcon(new javax.swing.ImageIcon("images/btok2.jpg"));
            tabela_ligowa_button_akt=0; 
            Tabelka_dane(wybranaLigas);
            jP_OknoTabela.jL_tytul.setText("TABELA LIGOWA");
            
        }
    }
    private void jButton6MousePressed(java.awt.event.MouseEvent evt) throws SQLException, ClassNotFoundException {
        if(tabela_ligowa_button_akt != 2){
            jButton5.setIcon(new javax.swing.ImageIcon("images/btok2.jpg")); 
            jButton4.setIcon(new javax.swing.ImageIcon("images/btok2.jpg")); 
            jButton6.setIcon(new javax.swing.ImageIcon("images/btok.jpg"));
            tabela_ligowa_button_akt=2;
            Tabelka_dane(wybranaLigas);
            jP_OknoTabela.jL_tytul.setText("TABELA LIGOWA - WYJAZD");
            
        }
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
    class RowListener implements ListSelectionListener{  
        public void valueChanged(ListSelectionEvent e){  
            if(!e.getValueIsAdjusting()){  
                ListSelectionModel model = jTable1.getSelectionModel();  
                int lead = model.getLeadSelectionIndex();  
                if(lead!=-1)
                    displayRowValues(lead);
                  
            }  
        }
        private void displayRowValues(int rowIndex){  
           String team =jTable1.getValueAt(rowIndex, 1).toString();
            wybranaDruzyna.setWybranadruzyna(team);
            wybranaDruzyna.setWybranaliga(wybranaLigas);
            wybranaDruzyna.Wyslij();
        }  
    } 
    public TabelaLigowa(Observable observable,WybranaDruzyna wybranaDruzyna) throws SQLException, ClassNotFoundException{
        this.observable = observable;
        observable.addObserver(this);
        this.wybranaDruzyna = wybranaDruzyna;
        
        jP_OknoTabela = new  m1_okienko(770,371,3,3,"TABELA LIGOWA");
        tabela_ligowa_button = new int[3];
        tabela_ligowa_button[0]=1;
        tabela_ligowa_button[1]=0;
        tabela_ligowa_button[2]=0;
        tabela_ligowa_button_akt=0;
        jButton4 =new  JLabel();
        jButton5 =new  JLabel();
        jButton6 =new  JLabel();
        
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
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
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
        add(jP_OknoTabela);
        //770,371,3,3,
        setBounds(3, 3, 770, 371);
    }
}
