package main;

import entity.Bird;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main extends JFrame {

    private JPanel contentPane;
    private GamePanel gp;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Main frame = new Main();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public Main() {
        setTitle("FlappyBird");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 675);
        setSize(500, 675);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        gp = new GamePanel(new Bird(20/3.0 ,9,55,"bird.png"));
        gp.setBounds(0, 0, 500, 675);
        contentPane.add(gp);
        gp.setLayout(null);
        gp.startApp();

    }
}