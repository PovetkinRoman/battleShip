package MyBattleShip;

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Ship {
	private ArrayList<Cell> locationCells = new ArrayList<Cell>();
	private ArrayList<Cell> surroundingCells = new ArrayList<Cell>();

	boolean isFloating = false;

	private String name;

	public void setLocationCells(ArrayList<Cell> loc) {
		// ArrayList<Cell> locationTest = new ArrayList<Cell>;
		// locationTest = loc;
		locationCells = loc;
	}

	// public void setNullLocationCells() {
	// 	locationCells.clear();
	// }

	public ArrayList<Cell> getLocationCells() {
		return locationCells;
	}

	public void setSurroundingCells(ArrayList<Cell> sur) {
		surroundingCells = sur;
	}

	// public void setNullSurroundingCells() {
	// 	surroundingCells.clear();
	// }

	public ArrayList<Cell> getSurroundCells() {
		return surroundingCells;
	}

	public void setName(String n) {
		name = n;
	}

	// public boolean isShipFloating() {
	// 	System.out.println("is floating true!!!");
	// 	return isFloating = true;
	// }

	public void сheckYourself() {
		// System.out.println(locationCells);
		for (Cell cellToTest : locationCells) {
			if (cellToTest.shooted == false) {
				return;
			}
		}
		// System.out.println("i have setBackground color");
		isFloating = true;
		for (Cell cellToSet : surroundingCells) {
			cellToSet.shooted = true;
			cellToSet.button.setBackground(Color.blue);
		}
		// если корабль потонул, то во всех клетках locationCell и всех клетках surroundinCell нужно
		// установить что в них попали +
		// и если он потонул, то isFloatin = false +

		// return true;
	}
}