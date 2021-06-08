import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wynik extends JPanel implements ActionListener {

    static int GAME_WIDTH;
    final int TIME1 = 20000;
    final int TIME2 = 300000;
    private int player1 = 0;
    private int player2 = 0;
    private Image image;
    private Graphics2D graphics;
    JLabel timeLabel = new JLabel();
    int elapsedTime = TIME1;
    int seconds = 10;
    int minutes = 0;
    boolean timeEnd = false;
    String seconds_string =  String.format("%02d", seconds);
    String minutes_string =  String.format("%02d", minutes);


    int elapsedTime2 = TIME2;
    int seconds2 = 0;
    int minutes2 = 10;
    boolean timeEnd2 = false;
    String seconds_string2 =  String.format("%02d", seconds2);
    String minutes_string2 =  String.format("%02d", minutes2);

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime -= 1000;
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            seconds_string =  String.format("%02d", seconds);
            minutes_string =  String.format("%02d", minutes);
            timeLabel.setText(minutes_string + ":" + seconds_string);
            if(elapsedTime == 1000) {
                timeEnd = true;
            }
        }
    });
    Timer timer2 = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime2 -= 1000;
            minutes2 = (elapsedTime2/60000) % 60;
            seconds2 = (elapsedTime2/1000) % 60;
            seconds_string2 =  String.format("%02d", seconds2);
            minutes_string2 =  String.format("%02d", minutes2);
            timeLabel.setText(minutes_string2 + ":" + seconds_string2);
            if(elapsedTime2 == 1000) {
                timeEnd2 = true;
            }
        }
    });

    Wynik(int GAME_WIDTH) {
        this.GAME_WIDTH = GAME_WIDTH;
        timer.start();
        timer2.start();


    }
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        g.drawLine(0, 80, GAME_WIDTH, 80);
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, 80);

        g.drawString(String.valueOf(player1/10) + String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
        g.drawString(String.valueOf(player2/10) + String.valueOf(player2%10), (GAME_WIDTH)/2+20, 50);

        g.drawString(minutes_string + ":" + seconds_string, 20, 50);
        g.drawString(minutes_string2 + ":" + seconds_string2, GAME_WIDTH - 200, 50);
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
    public boolean czyNowyTryb() {
        return timeEnd;
    }
    public void ustawTimer() {
        timeEnd = false;
        elapsedTime = TIME1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}