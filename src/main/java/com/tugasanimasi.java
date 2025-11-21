package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class tugasanimasi extends JPanel implements ActionListener {

    private int xPosition = 0;
    private int groundLevel = 320;
    private Timer timer;
    private int[] starX = new int[50];
    private int[] starY = new int[50];
    private int[] starSize = new int[50];
    private Image img1;
    private Image img2;

    public tugasanimasi() {
        //image bamjji dan lemyo
        img1 = new ImageIcon(getClass().getResource("/bamjji.png")).getImage();
        img2 = new ImageIcon(getClass().getResource("/lemyo.png")).getImage();

        //untuk bintang-bintang
        Random rand = new Random();
        for (int i = 0; i < starX.length; i++) {
            starX[i] = rand.nextInt(900);
            starY[i] = rand.nextInt(200);
            starSize[i] = rand.nextInt(4) + 1;}

        //timer kiri ke kanan
        timer = new Timer(20, this);
        timer.start();}

        //background
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //langit
            GradientPaint nightSky = new GradientPaint(
                    0, 0, new Color(10, 10, 50),
                    0, groundLevel, new Color(25, 25, 112));
            g2d.setPaint(nightSky);
            g2d.fillRect(0, 0, getWidth(), groundLevel);
            //tanah
            g2d.setColor(new Color(34, 139, 34));
            g2d.fillRect(0, groundLevel, getWidth(), getHeight() - groundLevel);
            //bintang
            drawSkyObjects(g2d);
            //gambar
            drawPicture(g2d, img1, xPosition, groundLevel);
            drawPicture(g2d, img2, xPosition + 150, groundLevel);}

        //warna
        private void drawSkyObjects(Graphics2D g) {
            g.setColor(Color.WHITE);
            for (int i = 0; i < starX.length; i++) {
                g.fillOval(starX[i], starY[i], starSize[i], starSize[i]);}
            // Bulan
            g.setColor(new Color(255, 255, 224));
            g.fillOval(650, 50, 60, 60);
            g.setColor(new Color(10, 10, 50));
            g.fillOval(635, 45, 60, 60);}

    //ukuran gambar
    private void drawPicture(Graphics2D g, Image img, int x, int baseY) {
        int width = 120;
        int height = 120;
        //nyentuh ground
        int y = baseY - height + 30;
        g.drawImage(img, x, y, width, height, this);}
    
    //aksi
    @Override
    public void actionPerformed(ActionEvent e) {
        xPosition += 3;
        if (xPosition > getWidth()) {
            xPosition = -300;}
        repaint();}

    //main
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animasi Kiri ke Kanan - Bamjji dan Lemyo");
        tugasanimasi animation = new tugasanimasi();
        frame.add(animation);
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);}
}
