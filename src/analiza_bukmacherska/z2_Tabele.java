import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class z2_Tabele extends JLayeredPane{
    JLayeredPane panel2;
    JLabel baza2;
    JLabel baza3;
    JTable jTable1;
    JLabel jButton4;
    JLabel jButton5;
    JLabel jButton6;
    JPanel jP_OknoTabela;
    m1_okienko  jP_OknoWybierzLige;
    JLabel jLabel3;
    private int[] tabela_ligowa_button;
    private int tabela_ligowa_button_akt;
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
    class ImageRenderer extends DefaultTableCellRenderer {
        JLabel lbl = new JLabel();
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            lbl.setIcon((ImageIcon)value);
            return lbl;
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
            for(int i =0; i<20;i++){
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
    public z2_Tabele(){
        tabela_ligowa_button = new int[3];
        tabela_ligowa_button[0]=1;
        tabela_ligowa_button[1]=0;
        tabela_ligowa_button[2]=0;
        tabela_ligowa_button_akt=0;
        JLabel  jLabel2 =new  JLabel();
        jLabel3 =new  JLabel();
        jP_OknoTabela =new  JPanel();
        jP_OknoWybierzLige = new  m1_okienko(230,371,780,3,"LIGA");
        jButton4 =new  JLabel();
        jButton5 =new  JLabel();
        jButton6 =new  JLabel();
        jTable1 = new JTable();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        jP_OknoTabela.setBackground(new java.awt.Color(0, 153, 153));
        jP_OknoTabela.setLayout(null);
        //jP_OknoWybierzLige.setBackground(new java.awt.Color(0, 153, 153));
        //jP_OknoWybierzLige.setLayout(null);
        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 10));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TABELA LIGOWA - DOM");
        jP_OknoTabela.add(jLabel3);
        jLabel3.setBounds(0, 0, 770, 26);
        jP_OknoTabela.setBounds(0, 0, 770, 500);
        
        
        jButton4.setIcon(new javax.swing.ImageIcon("images/btok.jpg")); // NOI18N
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {jButton4MouseEntered(evt);}
            public void mouseExited(java.awt.event.MouseEvent evt)  {jButton4MouseExited(evt);}
            public void mousePressed(java.awt.event.MouseEvent evt) {//jButton4MousePressed(evt);}
            }});
        jP_OknoTabela.add(jButton4);
        jButton4.setBounds(700, 5, 16, 16);
        jButton5.setIcon(new javax.swing.ImageIcon("images/btok2.jpg")); // NOI18N
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {jButton5MouseEntered(evt);}
            public void mouseExited(java.awt.event.MouseEvent evt)  {jButton5MouseExited(evt); }
            public void mousePressed(java.awt.event.MouseEvent evt) {//jButton5MousePressed(evt);
            }});
        jP_OknoTabela.add(jButton5);
        jButton5.setBounds(720, 5, 16, 16);
        jButton6.setIcon(new javax.swing.ImageIcon("images/btok2.jpg")); // NOI18N
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {jButton6MouseEntered(evt);}
            public void mouseExited(java.awt.event.MouseEvent evt)  {jButton6MouseExited(evt);}
            public void mousePressed(java.awt.event.MouseEvent evt) {//jButton6MousePressed(evt);}
            }});
        jP_OknoTabela.add(jButton6);
        jButton6.setBounds(740, 5, 16, 16);
        
        
        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon("images/TABELA_NAZWA2.jpg")); // NOI18N
        jP_OknoTabela.add(jLabel2);
        jLabel2.setBounds(0, 0, 770, 26);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "FC Barcelona", "7", "6", "1", "0", "19-7", "12", "19", ""},
                {"2", "Atletico Madryt", "7", "6", "1", "0", "18-8", "10", "19", ""},
                {"3", "FC Malaga", "7", "4", "2", "1", "11-4", "7", "14", ""},
                {"4", "Betis Sewilla", "7", "4", "0", "3", "12-13", "-1", "12", ""},
                {"5", "FC Sevilla", "7", "3", "2", "2", "8-7", "1", "11", ""},
                {"6", "RCD Mallorca", "7", "3", "2", "2", "8-6", "2", "11", ""},
                {"7", "Real Madryt", "7", "3", "2", "2", "14-7", "7", "11", ""},
                {"8", "Real Valladolid", "7", "3", "1", "3", "11-7", "4", "10", ""},
                {"9", "FC Getafe", "7", "3", "1", "3", "8-10", "-2", "10", ""},
                {"10", "UD Levante", "7", "3", "1", "3", "8-13", "-5", "10", ""},
                {"11", "Rayo Vallecano", "7", "3", "1", "3", "9-14", "-5", "10", ""},
                {"12", "Real Sociedad", "7", "3", "0", "4", "8-11", "-3", "9", ""},
                {"13", "Celta de Vigo", "7", "3", "0", "4", "9-8", "1", "9", ""},
                {"14", "FC Valencia", "7", "2", "2", "3", "8-9", "-1", "8", ""},
                {"15", "Granada CF", "7", "2", "2", "3", "6-10", "-4", "8", ""},
                {"16", "Athletic Bilbao", "7", "2", "2", "3", "9-14", "-5", "8", ""},
                {"17", "Real Saragossa", "7", "2", "0", "5", "5-9", "-4", "6", ""},
                {"18", "Deprotivo La Coruna", "7", "1", "3", "3", "9-14", "-5", "6", ""},
                {"19", "Osasuna", "7", "1", "1", "5", "7-11", "-4", "4", ""},
                {"20", "Espanyol Barcelona", "7", "0", "2", "5", "8-13", "-5", "4", ""}
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
        jP_OknoTabela.setBounds(3, 3, 770, 371);
        Dodaj_Paski();
        //setLocation(0,0);
        setBounds(0, 0, 1024, 500);
        add(jP_OknoTabela);
        //jP_OknoWybierzLige.se
        add(jP_OknoWybierzLige);
    }
}
