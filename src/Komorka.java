import java.awt.*;
import java.util.Random;

public class Komorka extends Rectangle {
    private int bok;
    private int punktyZycia;
    private int poczatkowePunktyZycia;
    private Random random;
    private double predkoscY;
    private double szybkosc = 0.2;
    private double x;
    private double y;
    private int idKoloni;
    private String [] kolory = new String[] {"#CEC4C4", "#45F1FE", "#29AEFB", "#4475C8", "#4200DA",
                                                "#6F41B2", "#9510AC", "#B24172", "#A82626"};

    Komorka(int zakresX1, int zakresX2, int zakresY1, int zakresY2, int bok, boolean kirunek, int idKoloni) {
        random = new Random();
        this.idKoloni = idKoloni;
        this.bok = bok;
        x = random.nextInt(zakresX2 - zakresX1 + 1) + zakresX1;
        y = random.nextInt(zakresY2 - bok - zakresY1+ 1) + zakresY1;
        punktyZycia = poczatkowePunktyZycia = random.nextInt(9) + 1;
        if(kirunek)
            ustawKierunekY(-1);
        else
            ustawKierunekY(1);


    }
    public void draw(Graphics g) {
        g.setColor(Color.decode(kolory[punktyZycia-1]));
        g.fill3DRect((int)Math.round(x), (int)Math.round(y), bok, bok, true);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 20));
        g.drawString(String.valueOf(punktyZycia), (int)Math.round(x) + 5, (int)Math.round(y) + 16);
    }

    private void ustawKierunekY(int kierunekY) {
        predkoscY = kierunekY * szybkosc;
    }
    public void zmienKierunek() {
        predkoscY = -predkoscY;
    }
    public void zmienPozycje() {
        y = y + predkoscY;
    }
    public boolean czyNachodzi(int celX, int celY, int bok) {
        if((this.x < celX + bok)  && (this.x + bok > celX) && (this.y < celY + bok) && (this.y + bok > celY))
            return true;
        return false;
    }
    boolean wspolnaSciana(int objektX, int objektY, int bok) {
        if((this.x == objektX + bok && this.y == objektY) || (this.x == objektX && this.y == objektY + bok) ||
                (this.x + bok == objektX && this.y == objektY) || (this.x == objektX && this.y + bok == objektY))
            return true;
        return false;

    }
    public int wezX() {
        return (int)Math.round(x);
    }
    public int wezY() {
        return (int)Math.round(y);
    }
    public int wezPunktyZycia() {
        return punktyZycia;
    }
    public void zmniejszZycie() {
        punktyZycia --;
    }
    public int wezPoczatkowePunktyZycia() {
        return poczatkowePunktyZycia;
    }
    public int wezID() {
        return idKoloni;
    }

}
