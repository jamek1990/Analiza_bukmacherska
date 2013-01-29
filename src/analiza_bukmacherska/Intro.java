package analiza_bukmacherska;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.LayerUI;

public class Intro {//implements ActionListener {
    
    private JLabel jB_aktualizacja;
    private JLabel jB_program;
    private JButton mOrderButton;
    private JLabel jB_exit;
    private JFrame f;
    public SQL sql;
    public Robot rob;
    public void createUI() throws IOException
    {
        f = new JFrame();
        f.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        f.setTitle("Analiza Bukmacherska");
        f.setLayout(null);
        
        
        f.setUndecorated(true);
        
        final WaitLayerUI layerUI = new WaitLayerUI();
        ImagePanel panel = createPanel();
        JLayer<JPanel> jlayer = new JLayer<JPanel>(panel, layerUI);
     
        final Timer stopper = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                layerUI.stop();
            }
        });
        stopper.setRepeats(false);
        
        jB_aktualizacja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jB_aktualizacja.setVisible(false);
                jB_program.setVisible(false);
                jB_exit.setVisible(false);
                rob = new Robot();
                try {
                    //rob.RefreshNewKurs();
                    //rob.RefreshDataBase();
                   // rob.grzes();
                } catch (Exception ex) {
                    Logger.getLogger(Intro.class.getName()).log(Level.SEVERE, null, ex);
                }
                layerUI.start();
                if (!stopper.isRunning()) {
                    stopper.start();
                }
                jB_aktualizacja.setVisible(true);
                jB_program.setVisible(true);
                jB_exit.setVisible(true);
            }
        });
        
        jB_program.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
               
                    try {
                        A_B.rysuj();
                    } catch (Exception ex) {
                        Logger.getLogger(Intro.class.getName()).log(Level.SEVERE, null, ex);
                    }
              
                f.dispose();
            }
        });
        
        jB_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                f.dispose();
            }
        });
        
        f.add(jlayer);
        jlayer.setBounds(0,0,500,300);
        f.setSize(new Dimension(500,300));
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    private ImagePanel createPanel() throws IOException {
        ImagePanel p = new ImagePanel(new ImageIcon("images/intro2.png").getImage());
        
        p.setLayout(null);
        jB_aktualizacja = new JLabel("           ");
        jB_program = new JLabel("          ");
        jB_exit = new JLabel("        ");
        jB_exit.setBounds(90, 155, 230, 30);
        jB_aktualizacja.setBounds(200, 155, 100, 30);
        jB_program.setBounds(320, 155, 230, 30);
        //jB_aktualizacja.setBounds(360, 195, 230, 30);
        //jB_program.setBounds(360, 230, 230, 30);
        //jB_exit.setBounds(360, 265, 230, 30);
        //jB_program.addActionListener(this);
        //jB_program.setActionCommand("open");

        //jB_aktualizacja.addActionListener(this);
        //jB_aktualizacja.setActionCommand("update");
        
        p.add(jB_aktualizacja);
        p.add(jB_program);
        p.add(jB_exit);
        return p;
    
    }
    
    /*@Override
    public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();

        if(cmd.equals("open"))
        {
            try {
                A_B.rysuj();
            } catch (SQLException ex) {
                Logger.getLogger(Intro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Intro.class.getName()).log(Level.SEVERE, null, ex);
            }
            f.dispose();
        }
    */
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Intro n = new Intro();
        n.createUI();
    }
}

class Background extends JComponent {
    private Image image;
    
    public Background(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}

class WaitLayerUI extends LayerUI<JPanel> implements ActionListener {
  private boolean mIsRunning;
  private boolean mIsFadingOut;
  private Timer mTimer;
 
  private int mAngle;
  private int mFadeCount;
  private int mFadeLimit = 15;
 
  @Override
  public void paint (Graphics g, JComponent c) {
    int w = c.getWidth();
    int h = c.getHeight();
 
    // Paint the view.
    super.paint (g, c);
 
    if (!mIsRunning) {
      return;
    }
 
    Graphics2D g2 = (Graphics2D)g.create();
 
    float fade = (float)mFadeCount / (float)mFadeLimit;
    //cień
    Composite urComposite = g2.getComposite();
    g2.setComposite(AlphaComposite.getInstance(
        AlphaComposite.SRC_OVER, .5f * fade));
    g2.fillRect(0, 0, w, h);
    g2.setComposite(urComposite);
 
    // Paint the wait indicator.
    int s = Math.min(w, h) / 5;
    int cx = w / 2;
    int cy = h / 2;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setStroke(
        new BasicStroke(s / 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    g2.setPaint(Color.white);
    g2.rotate(Math.PI * mAngle / 180, cx, cy);
    for (int i = 0; i < 12; i++) {
      float scale = (11.0f - (float)i) / 11.0f;
      g2.drawLine(cx + s, cy, cx + s * 2, cy);
      g2.rotate(-Math.PI / 6, cx, cy);
      g2.setComposite(AlphaComposite.getInstance(
          AlphaComposite.SRC_OVER, scale * fade));
    }
 
    g2.dispose();
  }
 
  public void actionPerformed(ActionEvent e) {
    if (mIsRunning) {
      firePropertyChange("tick", 0, 1);
      mAngle += 3;
      if (mAngle >= 360) {
        mAngle = 0;
      }
      if (mIsFadingOut) {
        if (--mFadeCount == 0) {
          mIsRunning = false;
          mTimer.stop();
        }
      }
      else if (mFadeCount < mFadeLimit) {
        mFadeCount++;
      }
    }
  }
 
  public void start(){
    if (mIsRunning) {
      return;
    }
     
    // Run a thread for animation.
    mIsRunning = true;
    mIsFadingOut = false;
    mFadeCount = 0;
    int fps = 24;
    int tick = 1000 / fps;
    mTimer = new Timer(tick, this);
    mTimer.start();
    
    
  }
 
  public void stop() {
    
    mIsFadingOut = true;
  }
 
  @Override
  public void applyPropertyChange(PropertyChangeEvent pce, JLayer l) {
    if ("tick".equals(pce.getPropertyName())) {
      l.repaint();
    }
  }
}