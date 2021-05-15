import javax.swing.*;
import java.awt.*;

public class Wynik extends JPanel {

    static int GAME_WIDTH;
    private int player1 = 0;
    private int player2 = 0;
    private Image image;
    private Graphics2D graphics;

    Wynik(int GAME_WIDTH) {
        this.GAME_WIDTH = GAME_WIDTH;

    }
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        g.drawLine(0, 80, GAME_WIDTH, 80);
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, 80);

        g.drawString(String.valueOf(player1/10) + String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
        g.drawString(String.valueOf(player2/10) + String.valueOf(player2%10), (GAME_WIDTH)/2+20, 50);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = (Graphics2D) image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void zwiekszPunkty(int komu, int ile) {
        switch(komu) {
            case 1:
                player1 += ile;
                break;
            case 2:
                player2 += ile;
                break;
        }
    }

}