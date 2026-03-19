package com.PingPone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PingPongGame extends JPanel implements ActionListener, KeyListener {

    Timer timer;

    int ballX = 300, ballY = 200;
    int ballXSpeed = 3, ballYSpeed = 3;

    int paddle1Y = 150;
    int paddle2Y = 150;

    int score1 = 0, score2 = 0;

    public PingPongGame() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.black);

        timer = new Timer(10, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Ball
        g.setColor(Color.white);
        g.fillOval(ballX, ballY, 15, 15);

        // Paddles
        g.fillRect(20, paddle1Y, 10, 80);
        g.fillRect(560, paddle2Y, 10, 80);

        // Score
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("P1: " + score1, 100, 30);
        g.drawString("P2: " + score2, 450, 30);
    }

    public void actionPerformed(ActionEvent e) {

        // Move ball
        ballX += ballXSpeed;
        ballY += ballYSpeed;

        // Top & bottom collision
        if (ballY <= 0 || ballY >= getHeight() - 15) {
            ballYSpeed *= -1;
        }

        // Paddle collision
        if (ballX <= 30 && ballY >= paddle1Y && ballY <= paddle1Y + 80) {
            ballXSpeed *= -1;
        }

        if (ballX >= 550 && ballY >= paddle2Y && ballY <= paddle2Y + 80) {
            ballXSpeed *= -1;
        }

        // Score update
        if (ballX < 0) {
            score2++;
            resetBall();
        }

        if (ballX > getWidth()) {
            score1++;
            resetBall();
        }

        repaint();
    }

    public void resetBall() {
        ballX = 300;
        ballY = 200;
    }

    // Controls
    public void keyPressed(KeyEvent e) {

        // Player 1 (W/S)
        if (e.getKeyCode() == KeyEvent.VK_W && paddle1Y > 0) {
            paddle1Y -= 15;
        }
        if (e.getKeyCode() == KeyEvent.VK_S && paddle1Y < getHeight() - 80) {
            paddle1Y += 15;
        }

        // Player 2 (UP/DOWN)
        if (e.getKeyCode() == KeyEvent.VK_UP && paddle2Y > 0) {
            paddle2Y -= 15;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && paddle2Y < getHeight() - 80) {
            paddle2Y += 15;
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {

        JFrame frame = new JFrame("🏓 Ping Pong Game");

        PingPongGame game = new PingPongGame();

        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}