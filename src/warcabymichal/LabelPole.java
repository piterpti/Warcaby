package warcabymichal;

import java.awt.*;
import javax.swing.*;

public class LabelPole extends JLabel
{
    public Color pionKolor;
    int x = -1;
    int y = -1;
    
    public LabelPole()
    {
        super();
        setOpaque(true);
        setBackground(new Color(200, 200, 200));  
    }
    
    public LabelPole(int aX, int aY, Color c)
    {
        super();
        setOpaque(true);
        setBackground(c);
        addMouseListener(new ClickHandler());
        x = aX;
        y = aY;
        setText(x + " " + y);
        setHorizontalAlignment(JLabel.CENTER);
        setForeground(Color.RED);
    }
    
    public void zmienPion(Color c)
    {
        pionKolor = c;
    }    
    
    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if(pionKolor == null)
            return;
        g2.setColor(pionKolor);
        
        int srodekX = this.getWidth();
        int srodekY = this.getHeight();
        
        int rozmiarX = (int) (srodekX / 1.2);
        int rozmiarY = (int) (srodekY / 1.2);
        
        g2.fillOval( (srodekX - rozmiarX) /2, (srodekY - rozmiarY) / 2, rozmiarX, rozmiarY);
    }
}
