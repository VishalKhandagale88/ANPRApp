package org.anpr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class ANPRApp extends JFrame {
    private final JLabel imageLabel;
    private final JTextArea resultTextArea;
    public ANPRApp( ){
        setTitle("Automatic Number Plate Recognition : ");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 223, 186)); // Replace with your preferred RGB color

        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        add(scrollPane, BorderLayout.SOUTH);

        JButton openButton = new JButton("Open Image");
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openImage();
            }
        });

        // Style the button
        openButton.setBackground(new Color(46, 139, 87)); // Background color
        openButton.setForeground(Color.WHITE); // Text color
        openButton.setFont(new Font("Arial", Font.BOLD, 14)); // Font

        add(openButton, BorderLayout.NORTH); // North represents "Top of the frame"
    }

    private BufferedImage processImage(File fileImage) {
        try {
            BufferedImage originalImage = ImageIO.read(fileImage);
            int targetWidth = 600;
            int targetHeight = 400;

            BufferedImage resizeImg = new BufferedImage(targetWidth, targetHeight, originalImage.getType());
            Graphics2D graphics = resizeImg.createGraphics();
            graphics.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
            graphics.dispose();

            return resizeImg;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
