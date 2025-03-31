import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PhotoMagic {
    public static Picture transform (Picture pic, LFSR lfsr) {
        int width = pic.width();
        int height = pic.height();
        Picture transformedPic = new Picture(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = pic.get(x, y);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int newRed = red ^ lfsr.generate(8);
                int newGreen = green ^ lfsr.generate(8);
                int newBlue = blue ^ lfsr.generate(8);

                Color newColor = new Color(newRed, newGreen, newBlue);
                transformedPic.set(x, y, newColor);
            }
        }
        return transformedPic;
    }

    public static void main(String[] args) {

        Picture pipe = new Picture("/Users/aryanpillai2701/Downloads/pipe.png");
        pipe.show();

        LFSR lfsr = new LFSR("01101000010100010000", 16);
        Picture encryptedPipe = transform(pipe, lfsr);

        encryptedPipe.show();

        LFSR lfsr2 = new LFSR("01101000010100010000", 16);
        Picture decryptedPipe = transform(encryptedPipe,lfsr2);
        decryptedPipe.show();
    }
}