public class GraWCzolgi {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        while(true) {
            System.out.println(menu.czyZaczynac());
            if (menu.czyZaczynac()) {
                OknoGry frame = new OknoGry();
                menu.ustawStart();
            }
        }
    }
}
