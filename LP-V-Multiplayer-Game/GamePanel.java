import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {
    private final Timer timer = new Timer(15, this);
    private final Tank player1 = new Tank(100, 600, Color.RED);
    private final Tank player2 = new Tank(1000, 600, Color.BLUE);
    private final Player[] players = {new Player("Player 1"), new Player("Player 2")};
    private int currentPlayerIndex = 0;

    private Missile currentMissile = null;

    private final JTextField angleInput = new JTextField("45", 5);
    private final JTextField powerInput = new JTextField("50", 5);
    private final JButton fireButton = new JButton("FIRE!");
    private final JLabel turnLabel = new JLabel();

    public GamePanel() {
        setLayout(null);

        // GUI controls panel
        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(0, 620, 1200, 80);
        controlPanel.setBackground(new Color(230, 230, 250));
        controlPanel.setLayout(null);

        JLabel angleLabel = new JLabel("Angle:");
        JLabel powerLabel = new JLabel("Power:");

        angleLabel.setBounds(50, 20, 50, 25);
        angleInput.setBounds(100, 20, 50, 25);
        powerLabel.setBounds(170, 20, 60, 25);
        powerInput.setBounds(230, 20, 50, 25);
        fireButton.setBounds(300, 20, 100, 30);
        turnLabel.setBounds(450, 20, 400, 30);

        turnLabel.setFont(new Font("Arial", Font.BOLD, 18));
        turnLabel.setForeground(Color.DARK_GRAY);

        controlPanel.add(angleLabel);
        controlPanel.add(angleInput);
        controlPanel.add(powerLabel);
        controlPanel.add(powerInput);
        controlPanel.add(fireButton);
        controlPanel.add(turnLabel);

        add(controlPanel);

        fireButton.addActionListener(e -> {
            if (currentMissile == null) {
                try {
                    int angle = Integer.parseInt(angleInput.getText());
                    int power = Integer.parseInt(powerInput.getText());

                    Tank currentTank = (currentPlayerIndex == 0) ? player1 : player2;
                    currentMissile = new Missile(currentTank.x + 25, currentTank.y, angle, power, currentPlayerIndex == 0);
                } catch (NumberFormatException ignored) {}
            }
        });

        // Key listener to control tank movement
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (currentPlayerIndex == 0) {
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        player1.moveLeft();
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        player1.moveRight();
                    }
                } else {
                    if (e.getKeyCode() == KeyEvent.VK_A) {
                        player2.moveLeft();
                    } else if (e.getKeyCode() == KeyEvent.VK_D) {
                        player2.moveRight();
                    }
                }
                repaint();
            }
        });

        setFocusable(true);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        // Draw ground
        g.setColor(new Color(120, 180, 100));
        g.fillRect(0, 630, 1200, 80);

        // Draw tanks
        player1.draw(g);
        player2.draw(g);

        // Draw missile
        if (currentMissile != null) {
            currentMissile.draw(g);
        }

        turnLabel.setText("Current Turn: " + players[currentPlayerIndex].name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentMissile != null) {
            currentMissile.update();

            if (currentMissile.getBounds().intersects(player1.getBounds()) && currentPlayerIndex == 1) {
                JOptionPane.showMessageDialog(this, "💥 Player 2 hit Player 1!");
                reset();
            } else if (currentMissile.getBounds().intersects(player2.getBounds()) && currentPlayerIndex == 0) {
                JOptionPane.showMessageDialog(this, "💥 Player 1 hit Player 2!");
                reset();
            } else if (currentMissile.y > 700) {
                switchTurn();
            }

            repaint();
        }
    }

    private void switchTurn() {
        currentMissile = null;
        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
    }

    private void reset() {
        currentMissile = null;
        player1.x = 100;
        player2.x = 1000;
        currentPlayerIndex = 0;
        repaint();
    }
}
