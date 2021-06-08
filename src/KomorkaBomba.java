import java.awt.*;

public class KomorkaBomba extends Rectangle {
    private int fontSize = 20;
    private int bokX;
    private int bokY;
    private int punktyZycia = 1;
    private int poczatkowePunktyZycia = 1;
    private int x;
    private double y;
    private int idKoloni;


    KomorkaBomba(int X, int Y, int bokKomorki) {
        bokX = 2 * bokKomorki;
        bokY =  bokKomorki;
        x = X - bokX/2;
        y = Y;

    }
    public void draw(Graphics g) {
        g.setColor(Color.decode("#201f27"));
        g.fill3DRect((int)Math.round(x), (int)Math.round(y), bokX, bokY, true);
    }

    public boolean czyNachodzi(int celX, int celY, int bok) {
        if(((this.x < celX + bok )  && (this.x + bok > celX ) && (this.y < celY + bok ) && (this.y + bok  > celY )))
            //  && (this.x != celX) && this.y != celY)
            return true;
        return false;
    }

    boolean czyTeSameWsp(int objektX, int objektY) {
        if(this.wezX() == objektX && this.wezY() == objektY)
            return true;
        return false;
    }
    public int wezX() {
        return (int)Math.round(x);
    }
    public int wezY() {
        return (int)Math.round(y);
    }
    public int wezID() {
        return idKoloni;
    }

}

