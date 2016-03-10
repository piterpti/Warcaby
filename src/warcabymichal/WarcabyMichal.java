package warcabymichal;

import java.awt.*;

/*
@author Michał B
@blabla

*/

public class WarcabyMichal 
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            Okno okno = new Okno();
            okno.setVisible(true);
        });
        
    }
}
