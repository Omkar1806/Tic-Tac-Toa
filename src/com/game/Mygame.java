package com.game;

import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.*;  
import java.awt.event.*;  
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Mygame extends JFrame implements ActionListener {
	JLabel heading,clocklabel;
	Font font = new Font("",Font.BOLD,40);
	JPanel mainPanel;
	
	JButton []btns = new JButton[9];
	
	//game instance variable 
	int gameChances[]= {2,2,2,2,2,2,2,2,2};
	int activePlayer=0;
	
	int wps[][]= {
			{0,1,2},
			{3,4,5},
			{6,7,8},
			{0,3,6},
			{1,4,7},
			{2,5,8},
			{0,4,8},
			{2,4,6}
	};
	
	int winner=2;
	
	boolean gameOver= false;
	Mygame(){
		setTitle("Tic tac toe");
		setSize(850,850);
		ImageIcon icon = new ImageIcon("src/images/logo.png");
		setIconImage(icon.getImage());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createGui();
		setVisible(true);
		
		
	}
	private void createGui() {
		this.getContentPane().setBackground(Color.decode("#2196f3"));
		this.setLayout(new BorderLayout());
		heading = new JLabel("Tic Tac Toe");
//		heading.setIcon(new ImageIcon("src/images/logo.png"));
		heading.setFont(font);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setForeground(Color.WHITE);
//		heading.setHorizontalTextPosition(SwingConstants.CENTER);

		this.add(heading,BorderLayout.NORTH);
		
		clocklabel = new JLabel("Clock");
		clocklabel.setForeground(Color.WHITE);
		clocklabel.setFont(font);
		clocklabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(clocklabel, BorderLayout.SOUTH);
		
		Thread t = new Thread() {
			public void run() {
				try {
					while(true) {
						String datetime = new Date().toLocaleString();
						clocklabel.setText(datetime);
						Thread.sleep(1000);
							
						}
					}catch(Exception e) {
					e.printStackTrace();
						
					
					}
		
					
				}
			};
			t.start();
			
			mainPanel= new JPanel();
			mainPanel.setLayout(new GridLayout(3,3));
			
			for(int i=1;i<=9;i++) {
				JButton btn = new JButton();
			

				btn.setIcon(new ImageIcon(""));
				btn.setBackground(Color.decode("#90caf9"));
				btn.setFont(font);
				mainPanel.add(btn);
				btns[i - 1] = btn;
				btn.addActionListener(this);
			 	btn.setName(String.valueOf(i-1));
				
				 
			}
			this.add(mainPanel,BorderLayout.CENTER);
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton currentButton = (JButton)e.getSource(); 
		String namestr=currentButton.getName();
		int name =Integer.parseInt(namestr.trim());
		
		if(gameOver==true) {
			JOptionPane.showMessageDialog(this,"Game Already Over...");
			return;
			
		}
		
		if(gameChances[name]==2) {
			if(activePlayer==1) {
				ImageIcon imageIcon = new ImageIcon("src/images/cross.png");
				Image image = imageIcon.getImage();
				Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
				currentButton.setIcon(imageIcon);
//				currentButton.setIcon(new ImageIcon("src/images/cross.png"));
				
				gameChances[name]= activePlayer;
				activePlayer=0;
			}else {
				ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("src/images/zero.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
				currentButton.setIcon(imageIcon1);
//				currentButton.setIcon(new ImageIcon("src/images/My project(1).png"));
				gameChances[name]=activePlayer;
				activePlayer=1;
		
		}
			for(int []temp:wps) {
				if((gameChances[temp[0]]==gameChances[temp[1]])&&(gameChances [temp[1]]== gameChances[temp[2]]) && gameChances[temp[2]]!=2) {
					winner= gameChances[temp[0]];
					gameOver=true;
					JOptionPane.showMessageDialog(null,"Player"+winner+"has won the game..");
					int i = JOptionPane.showConfirmDialog(this,"do you want to play more ??");
					if(i==0) {
						this.setVisible(false);
						new Mygame();
					}else if(i==1 ) {
						System.exit(34234);
						
					}
					else {
						
					}

					break;
					
				}
				
			}
			
			int c=0;
			for(int x:gameChances) {
				if(x==2) {
					c++;
					break;
				}
			}
			if(c==0&&gameOver==false) {
				JOptionPane.showMessageDialog(null,"Its draw...");
				int i=JOptionPane.showConfirmDialog(this,"Play More??");
				if(i==0) {
					this.setVisible(false);
					
					new Mygame();
					
				}else if(i==1) {
					System.exit(1289);
				}else {
					
				}
				
				gameOver=true;
			}
	}
	else {
		JOptionPane.showMessageDialog(this, "Position already occupied");
	}
}
}
