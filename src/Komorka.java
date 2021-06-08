import java.awt.*;
import java.util.Random;

public class Komorka extends Rectangle {
    private int fontSize = 20;
    private int bok;
    private int xStringShift = 5;
    private int yStringShift = 16;
    private int punktyZycia;
    private int poczatkowePunktyZycia;
    private Random random;
    private double predkoscY;
    private double szybkosc = 0.2;
    private int kierunek;
    private int x;
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
    Komorka(int X, int Y, int bok, boolean kirunek, int idKoloni) {
        random = new Random();
        this.idKoloni = idKoloni;
        this.bok = bok;
        x = X;
        y = y;
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
        g.setFont(new Font("Consolas", Font.PLAIN, fontSize));
        g.drawString(String.valueOf(punktyZycia), (int)Math.round(x) + xStringShift, (int)Math.round(y) + yStringShift);
    }

    private void ustawKierunekY(int kierunekY) {
        predkoscY = kierunekY * szybkosc;
        this.kierunek = kierunekY;
    }
    public void zmienKierunek() {
        predkoscY = -predkoscY;
    }
    public void zmienPozycje() {
        y = y + predkoscY;
    }
    public boolean czyNachodzi(int celX, int celY, int bok) {
        if(((this.x < celX + bok )  && (this.x + bok > celX ) && (this.y < celY + bok ) && (this.y + bok  > celY )))
          //  && (this.x != celX) && this.y != celY)
            return true;
        return false;
    }
    boolean wspolnaSciana(int objektX, int objektY, int bok) {
        if((this.x == objektX + bok && this.y == objektY) || (this.x == objektX && this.y == objektY + bok) ||
                (this.x == objektX - bok && this.y == objektY) || (this.x == objektX && this.y == objektY - bok))
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
    public void zmienPredkosc() {
        predkoscY = predkoscY * 1.1;
    }
    public void zmniejszBok() {
        bok = (int) Math.round(bok * 0.95);
        fontSize = (int) Math.round(fontSize * 0.95);
        xStringShift = (int) Math.round(xStringShift * 0.95);
        yStringShift = (int) Math.round(yStringShift * 0.95);
    }

    public void zwiekszZycie() {
        if(punktyZycia != 9) {
            punktyZycia ++;
        }
    }
    public void zmienIDKoloni() {
        idKoloni --;
    }


}
