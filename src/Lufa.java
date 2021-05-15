import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import static java.lang.Math.round;
import static java.lang.Math.toRadians;

public class Lufa extends Rectangle{
    private final int id;
    private int predkoscY;
    private int szybkosc = 10;
    private double theta = 0;
    private int predkoscRuchuObrotowego;
    private final int szybkoscZmianyKata = 2;

    Lufa(int x, int y, int szerokosc, int wysokosc, int id, int predkoscY, int szybkosc) {
        super(x, y, szerokosc, wysokosc);
        this.id = id;
        this.predkoscY = predkoscY;
        this.szybkosc = szybkosc;
    }
    public void nasisnietyPrzycisk(KeyEvent e) {
        switch(id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    ustawKierunekY(-szybkosc);
                    zmienPozycje();
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    ustawKierunekY(szybkosc);
                    zmienPozycje();
                }
                if(e.getKeyCode()==KeyEvent.VK_A ) {
                    ustawKierunekZmianyKata(-szybkoscZmianyKata);
                    zmienKat();
                }
                if(e.getKeyCode()==KeyEvent.VK_D) {
                    ustawKierunekZmianyKata(szybkoscZmianyKata);
                    zmienKat();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    ustawKierunekY(-szybkosc);
                    zmienPozycje();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    ustawKierunekY(szybkosc);
                    zmienPozycje();
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT ) {
                    ustawKierunekZmianyKata(-szybkoscZmianyKata);
                    zmienKat();
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT ) {
                    ustawKierunekZmianyKata(szybkoscZmianyKata);
                    zmienKat();
                }
        }
    }
    public void zwolnionyPrzycisk(KeyEvent e) {
        switch(id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    ustawKierunekY(0);
                    zmienPozycje();
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    ustawKierunekY(0);
                    zmienPozycje();
                }
                if(e.getKeyCode()==KeyEvent.VK_A) {
                    ustawKierunekZmianyKata(0);
                    zmienKat();
                }
                if(e.getKeyCode()==KeyEvent.VK_D) {
                    ustawKierunekZmianyKata(0);
                    zmienKat();
                }

                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    ustawKierunekY(0);
                    zmienPozycje();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    ustawKierunekY(0);
                    zmienPozycje();
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT) {
                    ustawKierunekZmianyKata(0);
                    zmienKat();
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
                    ustawKierunekZmianyKata(0);
                    zmienKat();
                }
                break;
        }

    }
    private void ustawKierunekY(int kierunekY) {
        predkoscY = kierunekY;
    }
    public void zmienPozycje() {
        y = y + predkoscY;
    }
    private void ustawKierunekZmianyKata(int szybkoscZmianyKata) {
        predkoscRuchuObrotowego = szybkoscZmianyKata;
    }
    public void zmienKat() {
        if( Math.abs(theta) <= 60 )
             theta = theta + predkoscRuchuObrotowego;
        else {
            if(theta < 0)
                theta++;
            else
                theta--;
        }
    }
    public void narysuj(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        if(id == 1) {
            g2= (Graphics2D)g.create();
            g2.rotate(Math.toRadians(theta), x, y + this.height/2);
            g2.setColor(Color.decode("#37037b"));
            g2.fill3DRect(x, y, width, height, true);
        }

        else {
            g2 = (Graphics2D) g.create();
            g2.rotate(Math.toRadians(theta), x+this.getWidth(), y+this.height/2);
            g2.setColor(Color.decode("#b83b36"));
            g2.fill3DRect(x, y, width, height, true);
        }

        //dispose Graphics2D object
        g2.dispose();

    }
    public int wezXPozycje(){
        int xPozycja = 0;
        switch(id) {
            case 1:
                xPozycja = (int)((this.getLocation()).getX() + (this.getWidth() * Math.cos(toRadians(theta))));
                break;
            case 2:
                xPozycja = (int)((this.getLocation()).getX() + (this.getWidth() -
                        ((int)Math.round(Math.cos(Math.toRadians(theta)) * this.getWidth()))));

        }
        return xPozycja;
    }
    public int wezYPozycje(){
        int yPozycja = 0;
        switch(id) {
            case 1:
                yPozycja = y +  ((int)Math.round(Math.sin(Math.toRadians(theta)) * this.getWidth()));
                break;
            case 2:
                yPozycja = (int)(this.getLocation()).getY() -
                        ((int)Math.round(Math.sin(Math.toRadians(theta)) * this.getWidth()));

        }
        return yPozycja;
    }
    public double wezKat() {
        return theta;
    }

}
