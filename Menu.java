import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Menu::new);
    }

    public Menu() {
        super("Laberinto");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new BackgroundPanel());
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = new ImageIcon(getClass().getResource("/images/laberinto_background.png")).getImage();
            } catch (Exception e) {
                System.err.println("Error al cargar el fondo: " + e.getMessage());
            }

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JLabel text = new JLabel("Elige el nivel de dificultad:");
            text.setFont(new Font("Arial", Font.BOLD, 28)); 
            text.setForeground(Color.WHITE);
            text.setAlignmentX(CENTER_ALIGNMENT);

            JPanel botonesPanel = new JPanel();
            botonesPanel.setOpaque(false);
            JButton botonFacil = new JButton("Fácil");
            JButton botonMedio = new JButton("Medio");
            JButton botonDificil = new JButton("Difícil");

            Font botonFont = new Font("Arial", Font.PLAIN, 24);
            botonFacil.setFont(botonFont);
            botonMedio.setFont(botonFont);
            botonDificil.setFont(botonFont);

            botonFacil.addActionListener(e -> new LaberintoFacil().setVisible(true));
            botonMedio.addActionListener(e -> new LaberintoMedio().setVisible(true));
            botonDificil.addActionListener(e -> new LaberintoDificil().setVisible(true));

            botonesPanel.add(botonFacil);
            botonesPanel.add(botonMedio);
            botonesPanel.add(botonDificil);

            add(Box.createVerticalStrut(200));
            add(text);
            add(Box.createVerticalStrut(30));
            add(botonesPanel);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
