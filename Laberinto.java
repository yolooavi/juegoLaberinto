import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public abstract class Laberinto extends JFrame {
    protected final int tamano;
    protected final int tamanoCuadro;
    protected final int[][] laberinto;
    protected final boolean[][] visible;
    protected int jugadorX, jugadorY;

    public Laberinto(int tamano, int tamanoCuadro) {
        this.tamano = tamano;
        this.tamanoCuadro = tamanoCuadro;
        this.laberinto = new int[tamano][tamano];
        this.visible = new boolean[tamano][tamano];
        this.jugadorX = 1;
        this.jugadorY = 0;

        setSize(tamano * tamanoCuadro + 20, tamano * tamanoCuadro + 40);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        generarLaberinto();
        actualizarVisibilidad();

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int y = 0; y < tamano; y++) {
                    for (int x = 0; x < tamano; x++) {
                        if (visible[y][x]) {
                            if (laberinto[y][x] == 1) {
                                g.setColor(Color.BLACK);
                                g.fillRect(x * tamanoCuadro, y * tamanoCuadro, tamanoCuadro, tamanoCuadro);
                            } else {
                                g.setColor(Color.WHITE);
                                g.fillRect(x * tamanoCuadro, y * tamanoCuadro, tamanoCuadro, tamanoCuadro);
                            }
                        } else {
                            g.setColor(Color.DARK_GRAY);
                            g.fillRect(x * tamanoCuadro, y * tamanoCuadro, tamanoCuadro, tamanoCuadro);
                        }
                    }
                }
                g.setColor(Color.RED);
                g.fillOval(jugadorX * tamanoCuadro + 5, jugadorY * tamanoCuadro + 5, tamanoCuadro - 10, tamanoCuadro - 10);
            }
        };

        add(panel);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                moverJugador(e);
                panel.repaint();
                if (jugadorX == tamano - 2 && jugadorY == tamano - 1) {
                    JOptionPane.showMessageDialog(null, "¡Felicidades! Has ganado.");
                    dispose();
                }
            }
        });
    }

    protected abstract void generarLaberinto();

    protected void moverJugador(KeyEvent e) {
        int newX = jugadorX;
        int newY = jugadorY;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: newY--; break;
            case KeyEvent.VK_DOWN: newY++; break;
            case KeyEvent.VK_LEFT: newX--; break;
            case KeyEvent.VK_RIGHT: newX++; break;
        }
        if (laberinto[newY][newX] == 0) {
            jugadorX = newX;
            jugadorY = newY;
            actualizarVisibilidad();
        }
    }

    protected void actualizarVisibilidad() {
        for (int y = 0; y < tamano; y++)
            Arrays.fill(visible[y], false);

        for (int dy = -2; dy <= 2; dy++) {
            for (int dx = -2; dx <= 2; dx++) {
                int nx = jugadorX + dx;
                int ny = jugadorY + dy;
                if (nx >= 0 && ny >= 0 && nx < tamano && ny < tamano) {
                    visible[ny][nx] = true;
                }
            }
        }
    }
}
