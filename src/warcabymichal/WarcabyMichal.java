package warcabymichal;

import java.awt.*;

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
