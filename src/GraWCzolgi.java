import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GraWCzolgi  {
    public static void main(String[] args) {
        OknoGry frame = null;
        boolean a = false;
        MainMenu menu = new MainMenu();
        while(true) {
            System.out.println(menu.czyZaczynac());
            if (menu.czyZaczynac()) {
                frame = new OknoGry();
                menu.ustawStart();
            }
            if (frame != null && frame.czyOtworzycMenu()) {
                frame.dispose();
                frame = null;
                menu = new MainMenu();
            }
        }
    }

}
