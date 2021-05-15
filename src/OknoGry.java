import javax.swing.*;
import java.awt.*;

public class OknoGry extends JFrame {
    PoleGry panel;
    Wynik wynik;
    private static final int SZEROKOSC_GRY = 1000;
    private static final int WYSOKOSC_OKNA = 555 + 150;
    private static final int WYSOKOSC_GRY = 555;


    OknoGry() {
        this.setSize(SZEROKOSC_GRY, WYSOKOSC_OKNA);
        wynik = new Wynik(1000);
        wynik.setBounds(0, 0, SZEROKOSC_GRY, 100);
        panel = new PoleGry(SZEROKOSC_GRY, WYSOKOSC_GRY, wynik);
        panel.setBounds(0, 100, SZEROKOSC_GRY, WYSOKOSC_GRY);

        this.add(panel);
        this.add(wynik);
        this.setTitle("gra w czołgi");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
