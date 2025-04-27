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
    protected int movimientosJugador;

    public Laberinto(int tamano, int tamanoCuadro) {
        this.tamano = tamano;
        this.tamanoCuadro = tamanoCuadro;
        this.laberinto = new int[tamano][tamano];
        this.visible = new boolean[tamano][tamano];
        this.jugadorX = 1;
        this.jugadorY = 0;
        this.movimientosJugador = 0;

        setSize(tamano * tamanoCuadro + 20, tamano * tamanoCuadro + 40);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        generarLaberinto();
        actualizarVisibilidad();

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
        
                g.setColor(new Color(10, 10, 30)); 
                g.fillRect(0, 0, getWidth(), getHeight());
        
                for (int y = 0; y < tamano; y++) {
                    for (int x = 0; x < tamano; x++) {
                        if (visible[y][x]) {
                            if (laberinto[y][x] == 1) {
                                g.setColor(new Color(50, 50, 60)); 
                                g.fillRect(x * tamanoCuadro, y * tamanoCuadro, tamanoCuadro, tamanoCuadro);
                            } else {
                                g.setColor(new Color(20, 30, 60)); 
                                g.fillRect(x * tamanoCuadro, y * tamanoCuadro, tamanoCuadro, tamanoCuadro);
                            }
                        } else {
                            g.setColor(new Color(10, 10, 20)); 
                            g.fillRect(x * tamanoCuadro, y * tamanoCuadro, tamanoCuadro, tamanoCuadro);
                        }
                    }
                }
        
                g.setColor(new Color(100, 150, 255));
                g.fillOval(jugadorX * tamanoCuadro + 5, jugadorY * tamanoCuadro + 5, tamanoCuadro - 10, tamanoCuadro - 10);

                if (visible[tamano - 1][tamano - 2]) {
                    int metaX = (tamano - 2) * tamanoCuadro;
                    int metaY = (tamano - 1) * tamanoCuadro;
            
                    g.setColor(new Color(173, 216, 230));
                    g.fillRect(metaX, metaY, tamanoCuadro, tamanoCuadro);
                }
            }
        }; 

        add(panel);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                moverJugador(e);
                panel.repaint();
                if (jugadorX == tamano - 2 && jugadorY == tamano - 1) {
                    int movimientosMinimos = 20;

                    if (movimientosJugador < movimientosMinimos) {
                        JOptionPane.showMessageDialog(null, "¡Felicidades! Has ganado. Has tardado menos de " + movimientosMinimos + " movimientos.");
                    } else if (movimientosJugador == movimientosMinimos) {
                        JOptionPane.showMessageDialog(null, "¡Felicidades! Has ganado con el número mínimo de movimientos.");
                    } else {
                        JOptionPane.showMessageDialog(null, "¡Felicidades! Has ganado en " + movimientosJugador + " movimientos.");
                    }

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
            default: throw new IllegalArgumentException("Tecla no válida para mover al jugador");
        }
        if (isColision(newX, newY)) {
            jugadorX = newX;
            jugadorY = newY;
            movimientosJugador++;
            actualizarVisibilidad();
        } else {
            throw new IllegalArgumentException("Intento de mover fuera del área permitida");
        }
    }

    protected boolean isColision(int x, int y) {
        if (x < 0 || y < 0 || x >= tamano || y >= tamano || laberinto[y][x] == 1) {
            return false;
        }
        return true;
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
    
        visible[tamano - 1][tamano - 2] = true;
    }
}
