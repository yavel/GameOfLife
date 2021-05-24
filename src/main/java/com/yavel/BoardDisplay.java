package com.yavel;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BoardDisplay extends JPanel {

    public int rows, cols;
    private List<Point> fillCells;

    public BoardDisplay(int rows, int cols) {
        super();
        this.rows = rows;
        this.cols = cols;
        fillCells = new ArrayList<>(25);
    }

    public BoardDisplay() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point fillCell : fillCells) {
            int cellX = 10 + (fillCell.x * 20);
            int cellY = 10 + (fillCell.y * 20);
            g.setColor(Color.RED);
            g.fillRect(cellX, cellY, 20, 20);
        }

        // Draw the outer square
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, rows*20, cols*20);

        // Draw the vertical lines
        for (int i = 10; i <= rows*20; i += 20) {
            g.drawLine(i, 10, i, rows*20+10);
        }

        // Draw the horizontal lines
        for (int i = 10; i <= cols*20; i += 20) {
            g.drawLine(10, i, cols*20+10, i);
        }
    }

    public void clearCells() {
        fillCells.clear();
    }

    public void fillCell(int x, int y) {
        fillCells.add(new Point(x, y));
        repaint();
    }

    public void initialize() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame window = new JFrame();
                window.setSize(1200, 1200);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.add(BoardDisplay.this);
                window.setVisible(true);
                fillCell(0, 0);
                fillCell(0, 1);
                fillCell(3, 0);
            }
        });
    }

    public static void main(String[] a) {
        BoardDisplay b = new BoardDisplay(40, 40);
        b.initialize();
    }
}