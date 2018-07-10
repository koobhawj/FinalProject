/*
 * Program description: Tic Tac Toe Game Application
 * Author: ABRAHAM KETEMA, MADA MIDTLING, KONG HER
 * Assignment: final project
 * Date: July, 10 2018
 * Class: CSCI 1082 
 */
package edu.century.game.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import edu.century.game.file.GameFile;

public class GameBoard extends JFrame implements ActionListener {
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JFrame mainFrame;
	private JMenuItem saveMenu;
	private JMenuItem loadMenu;
	private JLabel winner;
	private int currentPlayer = 1;
	private Boolean gameOver = false;
	// create an array to hold the buttons
	private JButton[] buttons = new JButton[9];
	private JButton[] pressedButtons = new JButton[9];
	// to hold the board results
	private String[] plays = new String[9];
	private int currentButton = 0;

	public GameBoard() {
		prepareBoard();
	}

	public void prepareBoard() {
		mainFrame = new JFrame("Tic Tac Toe Game");
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(600, 340);

		// Create the menu bar.
		JMenuBar menuBar = new JMenuBar();
		saveMenu = new JMenuItem("SAVE");
		loadMenu = new JMenuItem("LOAD");
		saveMenu.addActionListener(this);
		loadMenu.addActionListener(this);
		menuBar.add(saveMenu);
		menuBar.add(loadMenu);
		mainFrame.setJMenuBar(menuBar);

		winner = new JLabel();
		mainFrame.add(winner, BorderLayout.NORTH);

		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(3, 3, 2, 2));
		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();
		button5 = new JButton();
		button6 = new JButton();
		button7 = new JButton();
		button8 = new JButton();
		button9 = new JButton();

		buttons[0] = button1;
		buttons[1] = button2;
		buttons[2] = button3;
		buttons[3] = button4;
		buttons[4] = button5;
		buttons[5] = button6;
		buttons[6] = button7;
		buttons[7] = button8;
		buttons[8] = button9;

		for (JButton button : buttons) {
			button.addActionListener(this);
			boardPanel.add(button);
		}

		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		mainFrame.add(boardPanel, BorderLayout.CENTER);

		mainFrame.setVisible(true);
	}

	private Boolean isPressed(JButton button) {
		for (JButton b : pressedButtons) {
			if (b == button) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loadMenu) {
			loadGame();
			return;
		}
		if (e.getSource() == saveMenu) {
			GameFile.saveGame(plays);
			return;
		}
		if (gameOver) {
			return;
		}
		for (JButton button : buttons) {
			if (e.getSource() == button) {
				markSquare(button);
			}
		}

	}
	//load plays onto board
	private void loadGame() {
		plays = GameFile.loadGame();
		//resetting the game before loading
		currentPlayer = 1;
		currentButton = 0;
		pressedButtons = new JButton[9];
		for(int i = 0; i <=8; i++) {
			//remove new lines
			String play = plays[i];
			
			if(play == null || play.equals("")) {

				continue;
			}
			JButton button = buttons[i];
			if(play.equals("X")) {
				button.setText("X");
				currentPlayer = 2;
			}
			else if(play.equals("O")) {
				button.setText("O");
				currentPlayer = 1;
			}
			currentButton++;
		}
		showWinner();
	}
	// Mark the button as X or O
	private void markSquare(JButton button) {
		// check if button is pressed or all buttons have been pressed
		if (isPressed(button) || currentButton > 8) {
			return;
		}
		for (int i = 0; i <= 8; i++) {
			if (buttons[i] == button) {
				
				if (currentPlayer == 1) {
					button.setText("X");
					plays[i] = "X";
					currentPlayer = 2;
				} else {
					button.setText("O");
					plays[i] = "O";
					currentPlayer = 1;
				}
			}
		}

		pressedButtons[currentButton] = button;
		currentButton++;
		showWinner();
		
	}
	private void showWinner() {

		String win = CheckWinner.checkWinner(plays);
		System.out.println(String.format("win %s currentButton %s", win, currentButton));
		if(currentButton == 9 && win.equals("")) {
			winner.setText("Game has ended as a draw");
		}
		if (win.equals(""))
			return;
		gameOver = true;
		if (win.equals("X")) {
			winner.setText("Player 1 wins");
		}

		else if (win.equals("O")) {
			winner.setText("Player 2 wins");
		}
	}

}
