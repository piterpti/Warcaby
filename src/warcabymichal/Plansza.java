package warcabymichal;

import static warcabymichal.Okno.*;

public class Plansza 
{
    public int pole [][];
    
    public Plansza()
    {
        pole = new int[8][8];
        zerowanie();
        ustawPionki();
    }
    
    public void zerowanie()
    {
        int k = 0;
        for(int y = 0; y < 8; y++) 
        {
            k = (k + 1) % 2;
            for(int x = 0; x < 8; x++)
            {
                if(k == 1)
                    pole[y][x] = 1;
                else
                    pole[y][x] = 0;
                   
                k = (k + 1) % 2;
            }
        }
    }
    
    public void ustawPionki()
    {
        for(int y = 0; y < 8; y++)
        {
            for(int x = 0; x < 8; x++)
            {
                if(y < 3 && pole[x][y] == PUSTE_POLE)
                    pole[x][y] = PION_CZARNY;
                else if(y > 4 && pole[x][y] == PUSTE_POLE)
                    pole[x][y] = PION_BIALY;
            }
        }
    }
    
    public void wyswietl()
    {
        for(int y = 0; y < 8; y++)
        {
            for(int x = 0; x < 8; x++)
            {
                System.out.print(pole[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
