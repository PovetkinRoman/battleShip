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
		locationCells = loc;
	}

	public ArrayList<Cell> getLocationCells() {
		return locationCells;
	}

	public void setSurroundingCells(ArrayList<Cell> sur) {
		surroundingCells = sur;
	}

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
	}
}