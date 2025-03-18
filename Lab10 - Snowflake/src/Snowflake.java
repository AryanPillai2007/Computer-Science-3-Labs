import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;

class SnowFlakePanel extends JPanel {
    private static final double TWO_PI = 2.0 * Math.PI;
    private static final int MIN_LENGTH = 5;
    private Random random = new Random();

    public SnowFlakePanel() {
        super.setPreferredSize(new Dimension(400, 400));
        super.setBackground(Color.WHITE);
    }
    
    public void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        super.paintComponent(g);

        for (int i = 0; i < 30; i++) {
            int centerX = random.nextInt(width);
            int centerY = random.nextInt(height);
            int size = 10 + random.nextInt(20);
            Color randomColor = new Color
                    (random.nextInt(256), random.nextInt(256), random.nextInt(256));
            g.setColor(randomColor);
            drawSnowflake(g, centerX, centerY, size);
        }
    }

    private void drawSnowflake(Graphics g, int centerX, int centerY, int size) {
        if (size < MIN_LENGTH) {
            return;
        }
        for (int i = 0; i < 6; i++) {
            double angle = i * TWO_PI / 6;
            int endX = centerX + (int)(size * Math.cos(angle));
            int endY = centerY + (int)(size * Math.sin(angle));

            g.drawLine(centerX, centerY, endX, endY);
            drawSnowflake(g, endX, endY, size / 3);
        }
    }

    public class Snowflake {
        public void main(String[] args) {
            JFrame frame = new JFrame("Snowflake");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new SnowFlakePanel());
            frame.pack();
            frame.setVisible(true);
        }
    }
}
