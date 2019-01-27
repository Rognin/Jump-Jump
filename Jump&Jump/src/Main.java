import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Main {

	static ArrayList<Rabbit> rabbits;

	public static class Drawing extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			Drawable.drawAll(g2d);
		}

	}

	public static void main(String[] args) throws IOException {
		Level level = new Level();
		level.load("settings.txt");
		JFrame window = new JFrame();
		window.setSize(level.LEVEL_WIDTH, level.LEVEL_HEIGHT);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setSize(window.getSize());
		panel.setLayout(new BorderLayout());
		Drawing draw = new Drawing();
		panel.add(draw, BorderLayout.CENTER);
		panel.setFocusable(true);
		window.add(panel, BorderLayout.CENTER);
		panel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
//				level.keyTyped(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				level.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				level.keyReleased(e);
			}
		});

		Timer timer = new Timer(20, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					level.update();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel.repaint();
				Toolkit.getDefaultToolkit().sync();
			}
		});
		timer.start();

		window.setVisible(true);
	}

}
