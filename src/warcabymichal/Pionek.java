package warcabymichal;

import java.util.*;

public class Pionek 
{
    public int x;
    public int y;
    public ArrayList<Ruch> ruchy;

    public Pionek(int x, int y) 
    {
        this.x = x;
        this.y = y;
        ruchy = new ArrayList<>();
    }

    @Override
    public String toString() 
    {
        String s = "Pionek: " + x + " " + y + "\nRuchy: ";
        for(Ruch ruch : ruchy)
            s += ruch.x + " " + ruch.y + " | ";
        s = s.substring(0, s.length() - 2);
        return s;
    }
}
