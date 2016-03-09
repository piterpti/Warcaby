package warcabymichal;

import static warcabymichal.Okno.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;


public class ClickHandler extends MouseAdapter
{
    public static Pionek poprz = null;
    
    @Override
    public void mouseReleased(MouseEvent e) 
    {
        LabelPole pole = (LabelPole) e.getSource();
        int x = pole.x;
        int y = pole.y;
        
        ArrayList<Pionek> ruchy = dodajDostepneRuchy(gracz, plansza);
        
        if(plansza.pole[x][y] > PUSTE_POLE)
        {
            for(int i = 0; i < ruchy.size(); i++)
            {
                Pionek p = ruchy.get(i);
                if(x == p.x && y == p.y)
                {
                    poprz = p;                    
                }
            }
        }
        else if(poprz != null)
        {
            for(Ruch r : poprz.ruchy)
            {
                if(r.equals(new Ruch(x ,y)))
                {
                    gracz = wykonajRuch(plansza, poprz, r, gracz);
                    poprz = null;
                    if(komputerWlacznik.isSelected())
                    {
                        ruchKomputera();
                    }
                }
            }
        }
        panel.odswiez(plansza);
    }
    
    public ArrayList<Pionek> dodajDostepneRuchy(int GRACZ, Plansza plansza)
    {
        boolean czyBicie = false;
        ArrayList<Pionek> pionkiZRuchami = new ArrayList<>();
        for(int x = 0; x < 8; x++)
        {
            for(int y = 0; y < 8; y++)
            {
                if(plansza.pole[x][y] == GRACZ)
                {
                    Pionek p = ruchyPionka(x, y, GRACZ,plansza);
                    if(!p.ruchy.isEmpty())
                    {
                        pionkiZRuchami.add(p);
                        if(!czyBicie)
                            for(Ruch r : p.ruchy)
                                if(r.bicie)
                                    czyBicie = true;
                    }
                }
            }
        }
        
        ArrayList<Pionek> nowePionki = new ArrayList<>();
        if(czyBicie)
        {
            for(int i = 0; i < pionkiZRuchami.size(); i++)
            {
                Pionek pion = pionkiZRuchami.get(i);
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
            nowePionki = pionkiZRuchami;
        }
        return nowePionki;        
    }

    private Pionek ruchyPionka(int x, int y, int GRACZ, Plansza plansza) 
    {
        Pionek pionek = new Pionek(x, y);
        if((sprawdzBicia(x, y, GRACZ, plansza)).ruchy.size() > 0)
        {
            pionek = sprawdzBicia(x, y, GRACZ, plansza);
            return pionek;
        }
        
        if(plansza.pole[x][y] == PION_BIALY && GRACZ == PION_BIALY)
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
        else if(plansza.pole[x][y] == PION_CZARNY && GRACZ == PION_CZARNY)
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

    private Pionek sprawdzBicia(int x, int y, int GRACZ, Plansza plansza)
    {
        Pionek p = new Pionek(x, y);
        
        if(GRACZ == PION_BIALY && y > 1)
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
        else if(GRACZ == PION_CZARNY && y < 6)// PION_CZARNY 
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
    
    private int wykonajRuch(Plansza p, Pionek pion, Ruch r, int aGracz)
    {
        p.pole[pion.x][pion.y] = PUSTE_POLE;
        p.pole[r.x][r.y] = aGracz;
        if(r.bicie)
        {
            p.pole[r.zbityX][r.zbityY] = PUSTE_POLE;
            pion = sprawdzBicia(r.x, r.y, aGracz, plansza);
            while(!pion.ruchy.isEmpty())
            {
                p.pole[r.x][r.y] = PUSTE_POLE;
                p.pole[pion.ruchy.get(0).zbityX][pion.ruchy.get(0).zbityY] = PUSTE_POLE;
                p.pole[pion.ruchy.get(0).x][pion.ruchy.get(0).y] = gracz;
                pion = sprawdzBicia(pion.ruchy.get(0).x, pion.ruchy.get(0).y, aGracz, plansza);
            }
        }
        return aGracz = aGracz == PION_BIALY ? PION_CZARNY : PION_BIALY;
    }

    private void ruchKomputera(){       
        
        ArrayList<Pionek> pionkiZRuchami = dodajDostepneRuchy(gracz, plansza);
        if(pionkiZRuchami.isEmpty())
        {
            JOptionPane.showMessageDialog(panel, "Brak dostępnych ruchów", "Koniec gry", JOptionPane.CANCEL_OPTION);
            System.exit(0);
        }
        
        Pionek p = znajdzRuch(pionkiZRuchami);
        Ruch r = p.ruchy.get(0);
        gracz = wykonajRuch(plansza, p, r, gracz);
    }
    
    public Pionek znajdzRuch(ArrayList<Pionek> pionki) // pionki - pionki z dostepnymi ruchami 
    {
        int minKoszt = Integer.MAX_VALUE;
        Pionek najPionek = pionki.get(0);
        for(int i = 0; i < pionki.size(); i++)
        {
            Pionek p = pionki.get(i);
            for(int z = 0; z < p.ruchy.size(); z++)
            {
                Ruch r = p.ruchy.get(z);
                pionki.get(i).ruchy.get(z).koszt = kosztRuchu(p, r); 
                if(pionki.get(i).ruchy.get(z).koszt == minKoszt)
                {
                    Random generator = new Random();
                    if(generator.nextBoolean())
                    {
                        minKoszt = pionki.get(i).ruchy.get(z).koszt;
                        najPionek = new Pionek(pionki.get(i).x, pionki.get(i).y);
                        najPionek.ruchy.add(pionki.get(i).ruchy.get(z));
                    }
                    
                }
                if(pionki.get(i).ruchy.get(z).koszt < minKoszt)
                {
                    najPionek = new Pionek(pionki.get(i).x, pionki.get(i).y);
                    najPionek.ruchy.add(pionki.get(i).ruchy.get(z));
                    minKoszt = pionki.get(i).ruchy.get(z).koszt;
                }                
            }
        }
        return najPionek;
    }    
    
    private int kosztRuchu(Pionek p, Ruch r)
    {
        int stanPrzed = 0;
        int stanPo = 0;      
        int minKoszt = Integer.MAX_VALUE;
        
        Plansza tPlansza = new Plansza();  
        kopiujPlansze(tPlansza, plansza);
        stanPrzed = roznicaPionkow(tPlansza);
        wykonajRuch(tPlansza, p, r, PION_CZARNY);
        
        ArrayList<Pionek> pionki = dodajDostepneRuchy(PION_BIALY, tPlansza);
        
        for(int i = 0; i < pionki.size(); i++)
        {            
            for(int z = 0; z < pionki.get(i).ruchy.size(); z++)
            {                 
                kopiujPlansze(tPlansza, plansza);
                wykonajRuch(tPlansza, p, r, PION_CZARNY);
                wykonajRuch(tPlansza, pionki.get(i), pionki.get(i).ruchy.get(z), gracz);
                stanPo = roznicaPionkow(tPlansza);
                if(minKoszt > stanPrzed - stanPo)
                {
                    minKoszt = stanPrzed - stanPo;
                }
            }
        }
        return minKoszt;
    }
    
    private int roznicaPionkow(Plansza p)
    {
        int czarne = 0;
        int biale = 0;
        for(int y = 0; y < 8; y++)
        {
            for(int x = 0; x < 8; x++)
            {
                if(p.pole[x][y] == PION_CZARNY)
                    czarne++;
                else if(p.pole[x][y] == PION_BIALY)
                    biale++;
            }
        }
        return czarne - biale;
    }
    
    private void kopiujPlansze(Plansza temp, Plansza src)
    {
        for(int y = 0; y < 8; y++)
        {
            for(int x = 0; x < 8; x++)
            {
                temp.pole[x][y] = plansza.pole[x][y];
            }
        }
    }

}
