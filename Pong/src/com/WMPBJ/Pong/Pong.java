package com.WMPBJ.Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Pong extends JFrame {

	int ballX, ballY;
	int playerX, playerY;
	int playerSpeed;
	int playerScore;
	int ballDir;
	int ballSpeed;
	int enemyX;
	int enemyY;
	int enemyScore;
	private Image dbImage;
	private Graphics dbg;

	public class AL extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			int keyCode = e.getKeyCode();

			// Player Movement

			if (keyCode == e.VK_W) {

				playerY -= playerSpeed;

			}
			if (keyCode == e.VK_S) {

				playerY += playerSpeed;

			}

		}

		public void keyReleased(KeyEvent e) {

		}

	}

	public Pong() {

		addKeyListener(new AL());
		setTitle("Rabbits");
		setSize( 800, 600);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		
		ballX = 450;
		ballY = 50;
		ballDir = 3;
		ballSpeed = 10;

		playerX = 20;
		playerY = 300 - 64;
		playerSpeed = 5;
		playerScore = 0;

		enemyX = 800 - 20 - 20;
		enemyY = 300 - 64;
		enemyScore = 0;
	}

	public void paint(Graphics g) {

		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);

	}

	public void paintComponent(Graphics g) {
		
		g.setColor(Color.MAGENTA);
		g.drawString("Score: " + enemyScore, 500, 50);
		
		g.setColor(Color.MAGENTA);
		g.drawString("Score: " + playerScore, 50, 50);
		
		g.setColor(Color.GREEN);
		g.fillRect(enemyX, enemyY, 20, 128);
		
		g.setColor(Color.BLUE);
		g.fillRect(playerX, playerY, 20, 128);
		
		g.setColor(Color.ORANGE);
		g.fillRect(ballX, ballY, 20, 20);
		
		// Enemy Movement
		
		if( ballY > enemyY -20) {
			
			enemyY += 2;
			
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} else if(ballY < enemyY - 148){
			
			enemyY -= 2;
			
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		//Enemy Collision Detection
		
		// Top Collision
				if (enemyY <= 30)
					enemyY = 30;

				// Bottom Collision
				if (enemyY >= 600 - 131)
					enemyY = 600 - 131;
		
		
		// Player Collision Detection

		// Top Collision
		if (playerY <= 30)
			playerY = 30;

		// Bottom Collision
		if (playerY >= 600 - 131)
			playerY = 600 - 131;

		// Ball Collision Detection
		
		//Player Collision
		
		if ( ballX > playerX && ballX < playerX + 20 ) {
			if ( ballY > playerY && ballY < playerY + 128 ) {
				if ( ballDir == 1 ) {
					
					ballDir = 4;
					
				} else if ( ballDir == 2 ) {
					
					ballDir = 3;
					
				} 
			}
		}

		// Enemy Collision
		
		if ( ballX > enemyX && ballX < enemyX + 20) {
			if ( ballY - 20 > enemyY && ballY < enemyY + 128 ) {
				if ( ballDir == 4 ) {
					
					ballDir = 1;
					
				} else if ( ballDir == 3 ) {
					
					ballDir = 2;
					
				} 
			}
		}


		// Right Collision
		if (ballX >= 600 - 23 && ballDir == 4){
			ballDir = 1;
			playerScore++;
			}	
		else if (ballX >= 600 - 23 && ballDir == 3){
			ballDir = 2;
			playerScore++;
		}
		// Left Collision
		if (ballX <= 3 && ballDir == 1) {
			ballDir = 4;
			enemyScore++;
			}
		else if (ballX <= 3 && ballDir == 2) {
			ballDir = 3;
			enemyScore++;
		}
		// Top Collision
		if (ballY <= 30 && ballDir == 1)
			ballDir = 2;
		else if (ballY <= 30 && ballDir == 4)
			ballDir = 3;

		// Bottom Collision
		if (ballY >= 600 - 23 && ballDir == 2)
			ballDir = 1;
		else if (ballY >= 600 - 23 && ballDir == 3)
			ballDir = 4;

		// Ball Movement

		if (ballDir == 1) {

			ballX-=2;
			ballY-=2;

			try {
				Thread.sleep(ballSpeed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (ballDir == 2) {

			ballX-=2;
			ballY+=2;

			try {
				Thread.sleep(ballSpeed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (ballDir == 3) {

			ballX+=2;
			ballY+=2;

			try {
				Thread.sleep(ballSpeed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (ballDir == 4) {

			ballX+=2;
			ballY-=2;

			try {
				Thread.sleep(ballSpeed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		repaint();

	}

	public static void main(String[] args) {

		new Pong();

	}

}
