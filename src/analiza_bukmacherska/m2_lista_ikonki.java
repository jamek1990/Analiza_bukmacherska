import java.awt.Component;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;

public class m2_lista_ikonki
extends DefaultListCellRenderer {
 
    private Map<Object, Icon> icons = null;

    public m2_lista_ikonki(Map<Object, Icon> icons) {
        this.icons = icons;
    }

    public Component getListCellRendererComponent(JList list, Object value, int index,boolean isSelected, boolean cellHasFocus) {
        JLabel label =(JLabel) super.getListCellRendererComponent(list,
        value, index, isSelected, cellHasFocus);
        Icon icon = icons.get(value);
        label.setIcon(icon);
        label.setBackground(new java.awt.Color(227, 225, 225));
        label.setForeground(new java.awt.Color(100, 103, 103));
        label.setBounds(20, 20, 230, 60);;
        if (isSelected){
             label.setBackground(new java.awt.Color(100, 103, 103));
             label.setForeground(new java.awt.Color(227, 225, 225));
        }
        return label;
    }
}