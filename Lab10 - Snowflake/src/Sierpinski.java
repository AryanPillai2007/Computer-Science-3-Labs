import javax.swing.*;
public class Sierpinski {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SierpinskiPanel());
        frame.pack();
        frame.setVisible(true);
    }
}