package warcabymichal;

import java.awt.*;

/*
@author Michał B

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
