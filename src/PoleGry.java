import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class PoleGry extends JPanel  {
    private static int SZEROKOSC_GRY;
    private static int WYSOKOSC_GRY;
    private static final Dimension ROZMIAR_OKNA = new Dimension(SZEROKOSC_GRY, WYSOKOSC_GRY);
    private static int srednicaPocisku = 10;
    private static final int SZEROKOSC_CZOLGU = 50;
    private static final int WYSOKOSC_CZOLGU = 40;
    private static final int SZEROKOSC_LUFY = 60;
    private static final int WYSOKOSC_LUFY = 10;
    private static final int LICZBA_POJEDYNCZYCH_KOMOREK = 10;
    private static final int LICZBA_KOLONI = 20;
    private static int bokKomorki = 20;
    private static Wynik wynik;
    private int liczbaPociskow1;
    private int liczbaPociskow2;
    private final int limitPociskow = 7;
    private Image image;
    private Czolg czolg1;
    private Czolg czolg2;
    private Lufa lufa1;
    private Lufa lufa2;
    private ArrayList<Pocisk> pociski1= new ArrayList<Pocisk>();
    private ArrayList<Pocisk> pociski2= new ArrayList<Pocisk>();
    private ArrayList<Komorka> komorki= new ArrayList<Komorka>();
    private ArrayList<Kolonia> kolonie = new ArrayList<Kolonia>();

    PoleGry(int SZEROKOSC_GRY, int WYSOKOSC_GRY, Wynik wynik) {
        PoleGry.wynik = wynik;
        PoleGry.SZEROKOSC_GRY = SZEROKOSC_GRY;
        PoleGry.WYSOKOSC_GRY = WYSOKOSC_GRY;
        noweCzolgi();
        noweKolonie();
        noweKomorki();

        /* generowanie nowych komórek */
        /**/

        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(ROZMIAR_OKNA);

    }


    public void sprawdzKolizje() {
        // czolgi i lufy nie wychodza poza krawedzie
        if (czolg1.y <= 0) {
            czolg1.y = 0;
            lufa1.y = WYSOKOSC_CZOLGU / 2 - WYSOKOSC_LUFY / 2;
        }
        if (czolg1.y >= (WYSOKOSC_GRY - WYSOKOSC_CZOLGU)) {
            czolg1.y = WYSOKOSC_GRY - WYSOKOSC_CZOLGU;
            lufa1.y = WYSOKOSC_GRY - WYSOKOSC_CZOLGU / 2 - WYSOKOSC_LUFY / 2;
        }
        if (czolg2.y <= 0) {
            czolg2.y = 0;
            lufa2.y = WYSOKOSC_CZOLGU / 2 - WYSOKOSC_LUFY / 2;
        }
        if (czolg2.y >= (WYSOKOSC_GRY - WYSOKOSC_CZOLGU)) {
            czolg2.y = WYSOKOSC_GRY - WYSOKOSC_CZOLGU;
            lufa2.y = WYSOKOSC_GRY - WYSOKOSC_CZOLGU / 2 - WYSOKOSC_LUFY / 2;
         }

        // pociski wychodza poza pole gry lub trafiaja
        for (int i = 0; i < pociski1.size(); i++) {
            if(pociski1.get(i).wezX() > SZEROKOSC_GRY || pociski1.get(i).wezY() > WYSOKOSC_GRY || pociski1.get(i).wezY() < 0) {
                pociski1.remove(i);
                liczbaPociskow1--;
                i--;
                continue;
            }
                                                                                                           //mozna sprobowac zrobic w jednej petli iterujacej po komorkach
            for (int j = 0; j < komorki.size(); j++) {                                                    //i dwóch pentlach iterujących po pociskach
                if (pociski1.get(i).trafiony(komorki.get(j).wezX(), komorki.get(j).wezY(), bokKomorki)) {
                    komorki.get(j).zmniejszZycie();
                    pociski1.remove(i);
                    liczbaPociskow1--;
                    if(komorki.get(j).wezPunktyZycia() == 0 && komorki.get(j).wezID() == 999) {
                        wynik.zwiekszPunkty(1, komorki.get(j).wezPoczatkowePunktyZycia());
                        wynik.repaint();
                        komorki.remove(j);
                    }
                    else if(komorki.get(j).wezPunktyZycia() == 0 && komorki.get(j).wezID() != 999) {
                        kolonie.get(komorki.get(j).wezID()).zabijKomorke();
                        if(kolonie.get(komorki.get(j).wezID()).ileZywych() == 0) {
                            wynik.zwiekszPunkty(1, kolonie.get(komorki.get(j).wezID()).wezPunktyZycia());
                            wynik.repaint();
                            komorki.remove(j);
                        }
                        komorki.remove(j);
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < pociski2.size(); i++) {
            if(pociski2.get(i).wezX() < 0 || pociski2.get(i).wezY() > WYSOKOSC_GRY || pociski2.get(i).wezY() < 0) {
                pociski2.remove(i);
                liczbaPociskow2--;
                i--;
                continue;
            }
            for (int j = 0; j < komorki.size(); j++) {
                if (pociski2.get(i).trafiony(komorki.get(j).wezX(), komorki.get(j).wezY(), bokKomorki)) {
                    komorki.get(j).zmniejszZycie();
                    pociski2.remove(i);
                    liczbaPociskow2--;
                    if(komorki.get(j).wezPunktyZycia() == 0 && komorki.get(j).wezID() == 999) {
                        wynik.zwiekszPunkty(2, komorki.get(j).wezPoczatkowePunktyZycia());
                        wynik.repaint();
                        komorki.remove(j);
                    }
                    else if(komorki.get(j).wezPunktyZycia() == 0 && komorki.get(j).wezID() != 999) {
                        kolonie.get(komorki.get(j).wezID()).zabijKomorke();
                        if(kolonie.get(komorki.get(j).wezID()).ileZywych() == 0) {
                            wynik.zwiekszPunkty(2, kolonie.get(komorki.get(j).wezID()).wezPunktyZycia());
                            wynik.repaint();
                            komorki.remove(j);
                        }
                        komorki.remove(j);
                    }
                    break;
                }
            }
        }
        //komorki nie wychodza poza krawedzie ekranu
        for(Komorka k : komorki) {
            if (k.wezY() <= 0 || k.wezY() >= (WYSOKOSC_GRY - bokKomorki))
                k.zmienKierunek();
        }

    }
    public void paint(Graphics g) {
        try
        {
            image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("background.png"), "background.png"));
        }
        catch (Exception e) { /*handled in paintComponent()*/ }
        Graphics2D graphics = (Graphics2D)image.getGraphics();
        narysuj(graphics);
        g.drawImage(image, 0, 0, null);
    }
    public void narysuj(Graphics g) {
        czolg1.narysuj(g);
        czolg2.narysuj(g);
        lufa1.narysuj(g);
        lufa2.narysuj(g);
        for (Pocisk value : pociski1) {
            value.draw(g);
        }
        for (Pocisk pocisk : pociski2) {
            pocisk.draw(g);
        }
        for (Komorka komorka : komorki) {
            komorka.draw(g);
        }


    }
    public void aktualizujPozycje() {
        czolg1.zmienPozycje();
        czolg2.zmienPozycje();
        lufa1.zmienPozycje();
        lufa1.zmienKat();
        lufa2.zmienPozycje();
        lufa2.zmienKat();
        for(Komorka k : komorki)
            k.zmienPozycje();
        for (Pocisk pocisk : pociski1) {
            pocisk.zmienPozycje();
        }
        for (Pocisk pocisk : pociski2) {
            pocisk.zmienPozycje();
        }
    }
    public void zwiekszPoziomTrudnosci() {
        srednicaPocisku -= 0.25;
        bokKomorki -= 1;
        for(Komorka k : komorki) {
            k.zmienPredkosc();
            k.zmniejszBok();
            k.zwiekszZycie();
        }
    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            czolg1.nasisnietyPrzycisk(e);
            czolg2.nasisnietyPrzycisk(e);
            lufa1.nasisnietyPrzycisk(e);
            lufa2.nasisnietyPrzycisk(e);

            if(e.getKeyCode() == KeyEvent.VK_ENTER && liczbaPociskow2 != limitPociskow) {
                pociski2.add(new Pocisk(lufa2.wezXPozycje(), lufa2.wezYPozycje(), srednicaPocisku, srednicaPocisku, 2, lufa2.wezKat()));
                liczbaPociskow2++;
            }
            if(e.getKeyCode() == KeyEvent.VK_SPACE && liczbaPociskow1 != limitPociskow) {
                pociski1.add(new Pocisk(lufa1.wezXPozycje(), lufa1.wezYPozycje(), srednicaPocisku, srednicaPocisku, 1, lufa1.wezKat()));
                liczbaPociskow1++;
            }

        }
        public void keyReleased(KeyEvent e) {
            czolg1.zwolnionyPrzycisk(e);
            czolg2.zwolnionyPrzycisk(e);
            lufa1.zwolnionyPrzycisk(e);
            lufa2.zwolnionyPrzycisk(e);
        }
    }
    private void noweCzolgi() {
        czolg1 = new Czolg(0, (WYSOKOSC_GRY/2)-(WYSOKOSC_CZOLGU/2), SZEROKOSC_CZOLGU, WYSOKOSC_CZOLGU, 1);
        lufa1 = new Lufa(SZEROKOSC_CZOLGU/2, (WYSOKOSC_GRY/2)-(WYSOKOSC_LUFY/2), SZEROKOSC_LUFY, WYSOKOSC_LUFY,
                1, czolg1.wezPredkoscY(), czolg1.wezSzybkosc());
        czolg2 = new Czolg(SZEROKOSC_GRY-SZEROKOSC_CZOLGU - 15, (WYSOKOSC_GRY/2)-(WYSOKOSC_CZOLGU/2),
                SZEROKOSC_CZOLGU, WYSOKOSC_CZOLGU, 2);
        lufa2 = new Lufa(SZEROKOSC_GRY-SZEROKOSC_LUFY-SZEROKOSC_CZOLGU/2 - 15, (WYSOKOSC_GRY/2)-(WYSOKOSC_LUFY/2),
                SZEROKOSC_LUFY, WYSOKOSC_LUFY, 2, czolg1.wezPredkoscY(), czolg1.wezSzybkosc());

    }
    private void noweKolonie() {
        boolean nachodzi = false;
        for(int i = 0; kolonie.size() != LICZBA_KOLONI; i++) {
            kolonie.add(new Kolonia(SZEROKOSC_CZOLGU + SZEROKOSC_LUFY, SZEROKOSC_GRY - SZEROKOSC_CZOLGU - SZEROKOSC_LUFY - 15, WYSOKOSC_GRY, bokKomorki, i));
            for(Komorka kom : komorki) {
                for(Komorka komN : kolonie.get(i).wezKolonie()) {
                    if(komN.czyNachodzi(kom.wezX(), kom.wezY(), bokKomorki)) {
                        kolonie.remove(i);
                        i--;
                        nachodzi = true;
                        break;
                    }
                }
                if(nachodzi)
                    break;

            }
            if(nachodzi) {
                nachodzi = false;
                continue;
            }
            komorki.addAll(kolonie.get(i).wezKolonie());

        }
    }
    private void noweKomorki() {
        int aktualnyRozmiar = komorki.size();
        Random random = new Random();
        for( int i = aktualnyRozmiar; komorki.size() != aktualnyRozmiar+ LICZBA_POJEDYNCZYCH_KOMOREK; i++) {
            komorki.add(new Komorka(SZEROKOSC_CZOLGU + SZEROKOSC_LUFY ,SZEROKOSC_GRY - SZEROKOSC_CZOLGU - SZEROKOSC_LUFY - 15,0, WYSOKOSC_GRY, bokKomorki, random.nextBoolean(), 999));
            for (int j = 0; j < komorki.size() && i!=j; j++) {
                if(komorki.get(i).czyNachodzi(komorki.get(j).wezX(), komorki.get(j).wezY(), bokKomorki)) {
                    komorki.remove(i);
                    i--;
                }
            }
        }
    }

}
