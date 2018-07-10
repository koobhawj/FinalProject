/*
 * Program description: Tic Tac Toe Game Application
 * Author: ABRAHAM KETEMA, MADA MIDTLING, KONG HER
 * Assignment: final project
 * Date: July, 10 2018
 * Class: CSCI 1082 
 */
package edu.century.game.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * Saves plays to file
 * Reads plays from file
 *
 */
public class GameFile {
	//filename to save to
	private static final String FILENAME = "game_file.txt";
	/**
	 * 
	 * @param plays String[] the plays from the board
	 * Save to file
	 */
	public static void saveGame(String[] plays) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			for (String str : plays) {
				if (str != null) {
					bw.write(str+"\n");
				} else {
					bw.write("\n");
				}
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	/**
	 * 
	 * Reads file into a String[]
	 * @return String[]
	 */
	public static String[] loadGame() {
		BufferedReader br;
		String[] plays = new String[9];
		try {
			br = new BufferedReader(new FileReader(FILENAME));
		} catch (FileNotFoundException e) {
			return plays;
		}
		try {
			String line = br.readLine();
			int i = 0;
			while (line != null) {
				if( i >=9)
					break;
				line = line.replace("\n", "");
				plays[i] = line;
				i++;
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return plays;
	}
}
