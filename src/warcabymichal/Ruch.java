package warcabymichal;

public class Ruch
{
    public int x;
    public int y;
    public boolean bicie = false;
    public int zbityX;
    public int zbityY;
    public int koszt = 999;

    public Ruch(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Ruch(int x, int y, boolean bicie, int zbityX, int zbityY) {
        this.x = x;
        this.y = y;
        this.bicie = bicie;
        this.zbityX = zbityX;
        this.zbityY = zbityY;
    }

    @Override
    public boolean equals(Object obj) 
    {
        Ruch ruch = (Ruch) obj;
        return x == ruch.x && y == ruch.y;
    }
}
