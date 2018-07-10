/*
 * Program description: Tic Tac Toe Game Application
 * Author: ABRAHAM KETEMA, MADA MIDTLING, KONG HER
 * Assignment: final project
 * Date: July, 10 2018
 * Class: CSCI 1082 
 */
package edu.century.game.gui;

public class CheckWinner {
	public static String checkWinner(String[] plays) {
		String column_1 = plays[0] + plays[3] + plays[6];
		String column_2 = plays[1] + plays[4] + plays[7];
		String column_3 = plays[2] + plays[5] + plays[8];
		String cross_1 = plays[0] + plays[4] + plays[8];
		String cross_2 = plays[2] + plays[4] + plays[6];
		if (column_1.equals("XXX") || column_2.equals("XXX") || column_3.equals("XXX") || cross_1.equals("XXX")
				|| cross_2.equals("XXX")) {
			return "X";
		}

		else if (column_1.equals("OOO") || column_2.equals("OOO") || column_3.equals("OOO") || cross_1.equals("OOO")
				|| cross_2.equals("OOO")) {
			return "O";
		}

		for (int a = 0; a < 8; a++) {
			String line = null;
			switch (a) {
			case 0:
				line = plays[0] + plays[1] + plays[2];
				break;
			case 1:
				line = plays[3] + plays[4] + plays[5];
				break;
			case 2:
				line = plays[6] + plays[7] + plays[8];
				break;
			case 3:
				line = plays[0] + plays[3] + plays[6];
				break;
			case 4:
				line = plays[1] + plays[4] + plays[7];
				break;
			case 5:
				line = plays[2] + plays[5] + plays[8];
				break;
			case 6:
				line = plays[0] + plays[4] + plays[8];
				break;
			case 7:
				line = plays[2] + plays[4] + plays[6];
				break;
			}
			if (line.equals("XXX")) {
				return "X";
			} else if (line.equals("OOO")) {
				return "O";
			}
		}
		return "";
	}
}
