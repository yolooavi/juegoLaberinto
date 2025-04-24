import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    public static void main(String[] var0) {
        new Menu();
     }
    public Menu() {
        super("Laberinto");
        this.initComponents();
        this.setSize(800, 800);
        this.setVisible(true);
    }

    void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLabel text = new JLabel("Elige el nivel de dificultad:");
        text.setFont(new Font("Arial", Font.BOLD, 28)); 
        text.setAlignmentX(CENTER_ALIGNMENT);

        JPanel botonesPanel = new JPanel();
        JButton botonFácil = new JButton("Fácil");
        JButton botonMedio = new JButton("Medio");
        JButton botonDifícil = new JButton("Difícil");

        Font botonFont = new Font("Arial", Font.PLAIN, 24);
        botonFácil.setFont(botonFont);
        botonMedio.setFont(botonFont);
        botonDifícil.setFont(botonFont);

        botonFácil.addActionListener(e -> {
            LaberintoFacil labyrinth = new LaberintoFacil();
            labyrinth.setVisible(true);
        });
        botonMedio.addActionListener(e -> {
            LaberintoMedio labyrinth = new LaberintoMedio();
            labyrinth.setVisible(true);
        });
        botonDifícil.addActionListener(e -> {
            LaberintoDificil labyrinth = new LaberintoDificil();
            labyrinth.setVisible(true);
        });        
        
        mainPanel.add(Box.createVerticalStrut(200));
        mainPanel.add(text);
        mainPanel.add(Box.createVerticalStrut(30));
        botonesPanel.add(botonFácil);
        botonesPanel.add(botonMedio);
        botonesPanel.add(botonDifícil);
        mainPanel.add(botonesPanel);

        this.add(mainPanel, "Center");
    }
}