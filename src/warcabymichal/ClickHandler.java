package warcabymichal;

import static warcabymichal.Okno.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.scripts.JO;


public class ClickHandler extends MouseAdapter
{
    public static Pionek poprz = null;
    
    @Override
    public void mouseReleased(MouseEvent e) 
    {
        LabelPole pole = (LabelPole) e.getSource();
        int x = pole.x;
        int y = pole.y;
        
        {
            if(plansza.pole[x][y] > PUSTE_POLE)
            {
                Pionek p = ruchyPionka(x, y);
                poprz = p;
            }
            else if(poprz != null)
            {
                for(Ruch r : poprz.ruchy)
                {
                    if(r.equals(new Ruch(x ,y)))
                    {                    
                        plansza.pole[poprz.x][poprz.y] = 1;
                        plansza.pole[x][y] = gracz;
                        if(r.bicie)
                        {
                            plansza.pole[r.zbityX][r.zbityY] = PUSTE_POLE;
                        }
                        poprz = null;
                        if(!komputerWlacznik.isSelected())
                        {
                            gracz = gracz == PION_BIALY ? PION_CZARNY : PION_BIALY;
                        }
                        else 
                        {
                            gracz = PION_CZARNY;
                            if(gracz == PION_CZARNY)
                            {
                                ruchKomputera();
                            }
                        }
                    }
                }
            }        
        }
        panel.odswiez(plansza);
    }

    private Pionek ruchyPionka(int x, int y) 
    {
        Pionek pionek = new Pionek(x, y);
        if((sprawdzBicia(x, y)).ruchy.size() > 0)
        {
            pionek = sprawdzBicia(x, y);
            return pionek;
        }
        
        if(plansza.pole[x][y] == PION_BIALY && gracz == PION_BIALY)
        {
            if(y > 0)
            {
                if(x > 0 && x < 7 )
                {
                    if(plansza.pole[x + 1][y - 1] == 1)
                    {
                        pionek.ruchy.add(new Ruch(x + 1, y - 1));
                    }
                    if(plansza.pole[x - 1][y - 1] == 1)
                    {
                        pionek.ruchy.add(new Ruch(x - 1, y - 1));
                    }
                }
                else if(x == 0 && plansza.pole[x + 1][y - 1] == 1)
                {
                    pionek.ruchy.add(new Ruch(x + 1, y - 1));
                }
                else if(x == 7 && plansza.pole[x - 1][y - 1] == 1)
                {
                    pionek.ruchy.add(new Ruch(x - 1, y - 1));
                }
            }
        }
        else if(plansza.pole[x][y] == PION_CZARNY && gracz == PION_CZARNY)
        {
            if(y < 7)
            {  
                if(x > 0 && x < 7 )
                {
                    if(plansza.pole[x + 1][y + 1] == 1)
                    {
                        pionek.ruchy.add(new Ruch(x + 1, y + 1));
                    }
                    if(plansza.pole[x - 1][y + 1] == 1)
                    {
                        pionek.ruchy.add(new Ruch(x - 1, y + 1));
                    }
                }
                else if(x == 0 && plansza.pole[x + 1][y + 1] == 1)
                {
                    pionek.ruchy.add(new Ruch(x + 1, y + 1));
                }
                else if(x == 7 && plansza.pole[x - 1][y + 1] == 1)
                {
                    pionek.ruchy.add(new Ruch(x - 1, y + 1));
                }
            }
        }
        return pionek;
    }

    private Pionek sprawdzBicia(int x, int y)
    {
        Pionek p = new Pionek(x, y);
        
        if(gracz == PION_BIALY && y > 1)
        {
            if(x > 0 && x < 7)
            {
                if(plansza.pole[x - 1][y - 1] == PION_CZARNY && (x - 2 >= 0) && plansza.pole[x - 2][y - 2] == PUSTE_POLE)
                {
                    p.ruchy.add(new Ruch(x - 2, y - 2, true, x - 1, y - 1));
                }
                if(plansza.pole[x + 1][y - 1] == PION_CZARNY && (x + 2 <= 7) && plansza.pole[x + 2][y - 2] == PUSTE_POLE)
                {
                    p.ruchy.add(new Ruch(x + 2, y - 2, true, x + 1, y - 1));
                }
                
            }
            else if(x == 0)
            {
                if(plansza.pole[x + 1][y - 1] == PION_CZARNY && (x + 2 <= 7) && plansza.pole[x + 2][y - 2] == PUSTE_POLE)
                {
                    p.ruchy.add(new Ruch(x + 2, y - 2, true, x + 1, y - 1));
                }
            }
            else if(x == 7)
            {
                if(plansza.pole[x - 1][y - 1] == PION_CZARNY && (x - 2 >= 0) && plansza.pole[x - 2][y - 2] == PUSTE_POLE)
                {
                    p.ruchy.add(new Ruch(x - 2, y - 2, true, x - 1, y - 1));
                }
            }
        }
        else if(gracz == PION_CZARNY && y < 6)// PION_CZARNY 
        {
            if(x > 0 && x < 7)
            {               
                if(plansza.pole[x - 1][y + 1] == PION_BIALY && (x - 2 >= 0) && plansza.pole[x - 2][y + 2] == PUSTE_POLE)
                {
                    p.ruchy.add(new Ruch(x - 2, y + 2, true, x - 1, y + 1));
                }
                if(plansza.pole[x + 1][y + 1] == PION_BIALY && (x + 2 <= 7) && plansza.pole[x + 2][y + 2] == PUSTE_POLE)
                {
                    p.ruchy.add(new Ruch(x + 2, y + 2, true, x + 1, y + 1));
                }
            }
            else if(x == 0)
            {
                if(plansza.pole[x + 1][y + 1] == PION_BIALY && (x + 2 <= 7) && plansza.pole[x + 2][y + 2] == PUSTE_POLE)
                {
                    p.ruchy.add(new Ruch(x + 2, y + 2, true, x + 1, y + 1));
                }
            }
            else if(x == 7)
            {
                if(plansza.pole[x - 1][y + 1] == PION_BIALY && (x - 2 >= 0) && plansza.pole[x - 2][y + 2] == PUSTE_POLE)
                {
                    p.ruchy.add(new Ruch(x - 2, y + 2, true, x - 1, y + 1));
                }
            }
        }
        return p;
    }

    private void ruchKomputera(){
        
        ArrayList<Pionek> pionkiZRuchami = new ArrayList<>();
        boolean czyBicia = false;
        for(int y = 0; y < 8; y++) // pobranie wszystkich pionków z ruchami
        {
            for(int x = 0; x < 8; x++)
            {
                if(plansza.pole[x][y] == PION_CZARNY)
                {
                    Pionek p = ruchyPionka(x, y);
                    if(!p.ruchy.isEmpty())
                    {
                        pionkiZRuchami.add(p);   
                        for(Ruch r : p.ruchy)
                            if(r.bicie)
                                czyBicia = true;
                    }
                }
            }
        }   
        
        if(pionkiZRuchami.isEmpty())
        {
            JOptionPane.showMessageDialog(panel, "Brak dostępnych ruchów", "Koniec gry", JOptionPane.CANCEL_OPTION);
            System.exit(0);
        }
        
        
        
        Pionek p = znajdzRuch(pionkiZRuchami, czyBicia);
        Ruch r = p.ruchy.get(0);
        plansza.pole[p.x][p.y] = 1;
        plansza.pole[r.x][r.y] = gracz;
        if(r.bicie)
        {
            plansza.pole[r.zbityX][r.zbityY] = PUSTE_POLE;
        }
//        gracz = gracz == PION_BIALY ? PION_CZARNY : PION_BIALY;
        gracz = PION_BIALY;
    }
    
    public Pionek znajdzRuch(ArrayList<Pionek> pionki, boolean czyBicia)
    {
        ArrayList<Pionek> nowePionki = new ArrayList<>();
        if(czyBicia)
        {
            for(int i = 0; i < pionki.size(); i++)
            {
                Pionek pion = pionki.get(i);
                Pionek nowyPion = new Pionek(pion.x, pion.y);
                for(int n = 0; n < pion.ruchy.size(); n++)
                {
                    Ruch r = pion.ruchy.get(n);
                    if(r.bicie)
                    {
                        nowyPion.ruchy.add(r);
                    }
                }
                if(!nowyPion.ruchy.isEmpty())
                nowePionki.add(nowyPion);
            }
        }
        else
        {
            nowePionki = pionki;
        }
        // nowePionki - zawiera wszystkie możliwe ruchy KOMPUTERA
        // teraz trzeba wybrać najlepszy ruch
        
        
        
        
        return nowePionki.get(0);
    }
    
}
