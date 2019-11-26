package ui;


import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LoadingScreen {
    private JFrame frame;
    private JLabel label;
    private ImageIcon imageIcon;

    public LoadingScreen() throws InterruptedException {
        final Runnable runnable =
                (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.hand");

        frame = new JFrame("Debt Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setPreferredSize(new Dimension(500, 500));
        imageIcon = new ImageIcon(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "data" + System.getProperty("file.separator") + "loadingscreen.jpg");
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg); // taken from : https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
        label = new JLabel(imageIcon);
        frame.add(label);
        frame.pack();
        frame.setIconImage(imageIcon.getImage());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        runnable.run();
        Thread.sleep(2500);
        frame.setVisible(false);
        frame.setResizable(false);  //taken from D11 example
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

}
