package game;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

public class MainMenuPanel extends JPanel {
    protected GameFrame gameFrame;

    public MainMenuPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.setPreferredSize(new Dimension(960, 640)); // Match game size
        this.setLayout(null); // Absolute positioning for buttons

        // Start button
        JButton startButton = new JButton("Start Game");
        startButton.setBounds(380, 300, 200, 50);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setBackground(Color.DARK_GRAY);
        startButton.setForeground(Color.CYAN);
        startButton.setFocusPainted(false);
        startButton.setOpaque(true);
        startButton.setBorderPainted(true);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.startGame();
            }
        });

        // Quit button
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(380, 370, 200, 50);
        quitButton.setFont(new Font("Arial", Font.BOLD, 20));
        quitButton.setBackground(Color.DARK_GRAY);
        quitButton.setForeground(Color.RED);
        quitButton.setFocusPainted(false);
        quitButton.setOpaque(true);
        quitButton.setBorderPainted(true);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(startButton);
        this.add(quitButton);
        
        // Force the buttons to be visible
        this.revalidate();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Enable anti-aliasing for smoother rendering
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Gradient background
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(31, 37, 38),
                960, 640, new Color(58, 90, 95));
        g2.setPaint(gradient);
        g2.fillRect(0, 0, 960, 640);

        // Load custom font
        Font titleFont;
        try {
            // Try to load the custom font
            InputStream is = getClass().getResourceAsStream("/fonts/chiller_regular.ttf");
            if (is != null) {
                titleFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, 100f);
                is.close();
            } else {
                // Fallback to a similar font if Chiller isn't available
                titleFont = new Font("Serif", Font.BOLD, 80);
            }
        } catch (Exception e) {
            // Fallback to a similar font if there's an error
            titleFont = new Font("Serif", Font.BOLD, 80);
            System.err.println("Error loading custom font: " + e.getMessage());
        }

        // Title
        g2.setColor(new Color(66, 217, 200));
        g2.setFont(titleFont);
        g2.drawString("Shadow Escape", 280, 200);

        // Subtitle
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setColor(Color.WHITE);
        g2.drawString("Navigate the maze, avoid your shadow!", 330, 250);
        
        // Don't dispose the graphics context as it's needed for button rendering
        // g2.dispose(); - Remove this line
    }
}
