import java.awt.*;
import java.util.Random;

public class Pocisk extends Rectangle{

    Random random;
    private double predkoscX;
    private double predkoscY;
    private double predkosc;
    private double szybkosc = 2;
    private  int id;
    private double theta;    //w radianach
    private double x;
    private double y;


    Pocisk(double x, double y, int width, int height, int id, double theta) {
        super(width, height);
        this.id = id;
        this.x = x;
        this.y = y;
        this.theta = theta;

        if(id == 2)
            predkosc = -szybkosc ;
        else
            predkosc = szybkosc;
        ustawPredkoscX(predkosc);
        ustawPredkoscY(predkosc);

    }

    private void ustawPredkoscX(double zwrotWektora) {
        predkoscX = zwrotWektora * Math.cos(Math.toRadians(theta)) ;
    }
    private void ustawPredkoscY(double zwrotWektora) {
        predkoscY = zwrotWektora * Math.sin(Math.toRadians(theta)) ;
    }
    public void zmienPozycje() {
        x += predkoscX;
        y += predkoscY;
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval((int)Math.round(x), (int)Math.round(y), width, height);
    }

    public boolean trafiony(int celX, int celY, int bok) {
        if((this.x + width >= celX) && (this.x <= celX + bok) && (this.y + this.height >= celY) && (this.y <= celY + bok))
            return true;
        return false;
    }
    public boolean trafionyBomba(int celX, int celY, int bok) {
        if((this.x + width >= celX) && (this.x <= celX + 2 * bok) && (this.y + this.height >= celY) && (this.y <= celY + bok))
            return true;
        return false;
    }

    public int wezX() {
        return (int)Math.round(x);
    }
    public int wezY() {
        return (int)Math.round(y);
    }
}
