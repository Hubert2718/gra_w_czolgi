import java.awt.*;
import java.awt.event.KeyEvent;

public class Czolg extends Rectangle {
    private int id;
    private int predkoscY;
    private int szybkosc = 2;

    Czolg(int x, int y, int szerokosc, int wysokosc, int id) {
        super(x, y, szerokosc, wysokosc);
        this.id = id;
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
        }

    }
    public void ustawKierunekY(int kierunekY) {
        predkoscY = kierunekY;
    }
    public void zmienPozycje() {
        y = y + predkoscY;
    }
    public void narysuj(Graphics g) {
        if(id == 1)
            g.setColor(Color.decode("#37034f"));
        else
            g.setColor(Color.decode("#8e032c"));
        g.fill3DRect(x, y, width, height, true);
        g.setColor(Color.white);
        g.drawOval(x+width/2 - 10, y + height/2 - 10, 20, 20);
    }
    public int wezPredkoscY() {
        return predkoscY;
    }

    public int wezSzybkosc() {
        return szybkosc;
    }

}
