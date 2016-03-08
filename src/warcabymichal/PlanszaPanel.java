package warcabymichal;

import static warcabymichal.Okno.*;
import java.awt.*;
import javax.swing.*;

public class PlanszaPanel extends JPanel
{    
    private static final Color DARK_FIELD = new Color(80 ,80, 80);
    
    public PlanszaPanel()
    {
        super();
        setLayout(new GridLayout(8, 8));
    }
    
    public void odswiez(Plansza p)
    {
        removeAll();
        revalidate();
        
        for(int y = 0; y < 8; y++)
        {
            for(int x = 0; x < 8; x++)
            {
                LabelPole pole = null;
                if(plansza.pole[x][y] == 0)
                {
                    pole = new LabelPole();
                }
                else
                {
                    pole = new LabelPole(x, y, DARK_FIELD);
                    if(p.pole[x][y] == PION_BIALY)
                    {
                        pole.zmienPion(Color.WHITE);
                    }
                    else if(p.pole[x][y] == PION_CZARNY)
                    {
                        pole.zmienPion(Color.BLACK);
                    }
                }
                add(pole);
            }
        }
        if(gracz == 2)
            labelGracz.setText(RUCH_BIALY);
        else 
            labelGracz.setText(RUCH_CZARNY);
    }
}
