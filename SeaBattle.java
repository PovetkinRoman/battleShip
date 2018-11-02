package MyBattleShip;

import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;


public class SeaBattle implements MyControlListener {

	final int fieldLength = 10;
	final int fieldSize = fieldLength * fieldLength;

	Cell[][] battleField2 = new Cell[fieldLength][fieldLength];

	ArrayList<Ship> ships = new ArrayList<Ship>();

	public static void main(String[] args) {
		SeaBattle seaBattle = new SeaBattle();
		seaBattle.fieldInitialization();

	}

	public void fieldInitialization() {
		JFrame frame = new JFrame("Battle Ship by Roman Povetkin");
		JPanel panel = new JPanel();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout grid = new GridLayout(fieldLength, fieldLength);
		// grid.setVgap(1);
		// grid.setHgap(2);
		panel = new JPanel(grid);

		for (int i = 0; i < fieldLength; i++) {
			for (int j = 0; j < fieldLength; j++) {

				JButton tempButton = new JButton(String.valueOf(i) + "-" + String.valueOf(j));
				panel.add(tempButton);
				tempButton.addActionListener(battleField2[i][j]);

				battleField2[i][j] = new Cell();
				battleField2[i][j].addMyEventListener(this);
				battleField2[i][j].button = tempButton;
			}
		}
		ShipRandomizer sr = new ShipRandomizer();
		// int sizeShip = 4;
		// // TO DO: написать метод который оптимизирует данный цикл
		// for (int i = sizeShip; i > 0; i--) {
		// 	if (i == 4) ships.add(randomPlaceShip(i));
		// 	else if (i == 3) {
		// 		ships.add(randomPlaceShip(i));
		// 		ships.add(randomPlaceShip(i));
		// 	} else if (i == 2) {
		// 		ships.add(randomPlaceShip(i));
		// 		ships.add(randomPlaceShip(i));
		// 		ships.add(randomPlaceShip(i));
		// 	} else if (i == 1) {
		// 		ships.add(randomPlaceShip(i));
		// 		ships.add(randomPlaceShip(i));
		// 		ships.add(randomPlaceShip(i));
		// 		ships.add(randomPlaceShip(i));
		// 	}
		// }
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setBounds(570, 300, fieldLength * 75, fieldLength * 65);
		frame.setVisible(true);
	}

	public void onDataChanged(MyEvent dataAddedCount) {
		int finishGameSteps = 0;
		String steps = dataAddedCount.getMessage();
		for (Ship shipTest : ships) {
			if (shipTest.isFloating == false) return;
		}
		//Добавить подсчет ходов и вывод их на экран
		System.exit(0);
	}


}