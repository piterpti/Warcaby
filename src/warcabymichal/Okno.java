package warcabymichal;

import java.awt.*;
import javax.swing.*;

public class Okno extends JFrame
{
    public static final int PION_BIALY = 2;
    public static final int PION_CZARNY = 3;
    public static final int PUSTE_POLE = 1;
    public static int gracz = 2; // 2 - biały | 3 - czarny
    
    public static Plansza plansza;
    public static PlanszaPanel panel;
    
    public static JLabel labelGracz;
    
    public static final String RUCH_BIALY = "<html>GRACZ: <font color = \"white\">BIAŁY</font></html>";
    public static final String RUCH_CZARNY = "<html>GRACZ: <font color = \"black\">CZARNY</font></html>";
    
    public static JCheckBox komputerWlacznik;
    
    public Okno()
    {
        setTitle("Warcaby");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        plansza = new Plansza();
        panel = new PlanszaPanel();
        labelGracz = new JLabel();
        komputerWlacznik = new JCheckBox();
        
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 2));
        komputerWlacznik.setText("Przeciwnik komputerowy");
        komputerWlacznik.setBackground(new Color(40, 190, 40));
        komputerWlacznik.setFont(new Font(labelGracz.getName(), Font.BOLD, 16));
        komputerWlacznik.setSelected(true);
        
        add(panel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        southPanel.add(labelGracz);
        southPanel.add(komputerWlacznik);
        labelGracz.setFont(new Font(labelGracz.getName(), Font.BOLD, 16));
        labelGracz.setText(RUCH_BIALY);
        labelGracz.setOpaque(true);
        labelGracz.setBackground(new Color(190, 40, 40));
        labelGracz.setHorizontalAlignment(JLabel.CENTER);
        panel.odswiez(plansza);
        
        pack();
        setSize(500, 500);
    }
}
