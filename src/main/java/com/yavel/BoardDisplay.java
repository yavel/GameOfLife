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

public class BoardDisplay {

    public static class Grid extends JPanel {
        public int rows, cols;
        private List<Point> fillCells;

        public Grid(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            fillCells = new ArrayList<>(25);
        }

        public Grid() {
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

        public void fillCell(int x, int y) {
            fillCells.add(new Point(x, y));
            repaint();
        }
    }

    public static void main(String[] a) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                Grid grid = new Grid(40, 40);
                JFrame window = new JFrame();
                window.setSize(1200, 1200);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.add(grid);
                window.setVisible(true);
                grid.fillCell(0, 0);
                grid.fillCell(0, 1);
                grid.fillCell(3, 0);
//                grid.fillCell(79, 0);
//                grid.fillCell(0, 49);
//                grid.fillCell(79, 49);
//                grid.fillCell(39, 24);
            }
        });
    }
}