import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

class SierpinskiPanel extends JPanel {
    private static final int MIN_SIZE = 5;

    public SierpinskiPanel() {
        super.setPreferredSize(new Dimension(400, 400));
        super.setBackground(Color.WHITE);
    }
    public void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        int size = Math.min(width, height);
        drawSierpinski(g, 0, 0, size);
    }
    private void drawSierpinski(Graphics g, int x, int y, int size) {
        if (size < MIN_SIZE) {
            return;
        }
        g.drawLine(x, y, x + size, y);
        g.drawLine(x, y, x, y + size);
        g.drawLine(x, y + size, x + size, y);
        int newSize = size / 2;
        drawSierpinski(g, x, y, newSize);
        drawSierpinski(g, x + newSize, y, newSize);
        drawSierpinski(g, x, y + newSize, newSize);
    }
}