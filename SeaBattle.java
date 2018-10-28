package MyBattleShip;

import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;


public class SeaBattle implements MyControlListener {
	// Тут добавть обработчик события ПРоверитьЧтоИграКОнчилась.isGameOver(
	// в этом обработчике пробегаемся по списку кораблей, и если все isFloating = false;
	// Точнее, если хотя бы один isFloating == true, значит игра не кончилась

	final int fieldLength = 10;
	final int fieldSize = fieldLength * fieldLength;

	int fromX = 0;
	int fromY = 0;
	int toX = 0;
	int toY = 0;

	int[][] battleField = new int[fieldLength][fieldLength];
	Cell[][] battleField2 = new Cell[fieldLength][fieldLength];

	// ArrayList<String> coordinateShip = new ArrayList<String>();
	ArrayList<Ship> ships = new ArrayList<Ship>();


	// ArrayList<Cell> testCellSurroundingShip = new ArrayList<Cell>();
	// ArrayList<Ship> <<< Nado sdelat +

	public static void main(String[] args) {
		SeaBattle seaBattle = new SeaBattle();
		seaBattle.go();

	}

	public void onDataChanged(MyEvent dataAddedCount) {
		int finishGameSteps = 0;
		String steps = dataAddedCount.getMessage();
		// finishGameSteps = Integer.parseInt(steps);
		// System.out.println("" + steps);
		for (Ship shipTest : ships) {
			if (shipTest.isFloating == false) return;
		}

		// System.out.println("получил событие проверить конец игры");
		// int incr = 0;
		// incr++;
		// finishGame = this.isGameOver();
		// System.out.println("isGameOver - " + finishGame);
		// if (finishGame == true) {
		// System.out.println("Вы потопили все корабли за " + incr + " хода(ов)");
		
		System.exit(0);
		// }
	}

	// public boolean isGameOver() {
	// 	for (Ship ship : ships) {
	// 		if (ship.isFloating == false) {
	// 			return false;
	// 		}
	// 	}
	// 	return true;
	// }

	public void go() {
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

				battleField2[i][j] = new Cell();
				battleField2[i][j].addMyEventListener(this);
				tempButton.addActionListener(battleField2[i][j]);

				battleField2[i][j].button = tempButton;

			}
		}
		int sizeShip = 4;
		// TO DO: написать метод который оптимизирует данный цикл
		for (int i = sizeShip; i > 0; i--) {
			if (i == 4) ships.add(randomPlaceShip(i));
			else if (i == 3) {
				ships.add(randomPlaceShip(i));
				ships.add(randomPlaceShip(i));
			} else if (i == 2) {
				ships.add(randomPlaceShip(i));
				ships.add(randomPlaceShip(i));
				ships.add(randomPlaceShip(i));
			} else if (i == 1) {
				ships.add(randomPlaceShip(i));
				ships.add(randomPlaceShip(i));
				ships.add(randomPlaceShip(i));
				ships.add(randomPlaceShip(i));
			}
		}
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setBounds(570, 300, fieldLength * 75, fieldLength * 65);
		frame.setVisible(true);
	}

	public Ship randomPlaceShip(int shipSize) {

		int incr = 1;
		int row = 0;
		int column = 0;
		boolean result = false;
		boolean verticalShip = false;
		boolean gorizontalShip = false;

		ArrayList<Cell> locationShip = new ArrayList<Cell>();
		ArrayList<Cell> surroundCellShip = new ArrayList<Cell>();

		while (result == false) {
			//Рандомиим направление корабля, 0 - горизонтальный, 1 - вертикальный
			//Рандомим начальные координаты корабля в зависимости от его направления
			int vertOrGorizont = (int) (Math.random() * 2);

			if (vertOrGorizont == 1) {
				// incr = fieldLength;
				verticalShip = true;
				row = (int) (Math.random() * (fieldLength - shipSize));
				column = (int) (Math.random() * fieldLength);
				// System.out.println("Рандомим вертикальный корабль: " + shipSize + " палубы " + row + " - " + column);
			} else {
				gorizontalShip = true;
				column = (int) (Math.random() * (fieldLength - shipSize));
				row = (int) (Math.random() * fieldLength);
				// System.out.println("Рандомим горизонтальный корабль: " + shipSize + " палубы " + row + " - " + column);
			}
			result = this.checkLocationShip(row, column, verticalShip,
			                                gorizontalShip, shipSize);
		}
		// System.out.println("Нарандомили точку: " + row + " - " + column);
		// System.out.println("");
		//создаем экземпляр класса корабль и присваеваем ему его местоположение и запоминаем область вокруг корабля

		Ship randomShip = new Ship();

		for (int i = fromX; i <= toX; i++) {
			for (int j = fromY; j <= toY; j++) {
				if (battleField[i][j] == 1) {
					battleField2[i][j].hasShip = true;
					battleField2[i][j].setShip(randomShip);
					locationShip.add(battleField2[i][j]);
				} else if (battleField[i][j] == 2) {
					battleField2[i][j].shipField = true;
					surroundCellShip.add(battleField2[i][j]);
				}
			}
		}
		randomShip.setLocationCells(locationShip);
		randomShip.setSurroundingCells(surroundCellShip);
		System.out.println("korabl narandomlen");
		return randomShip;
	}

// TODO: Создать корабль и передать в него список клеток где находится корабль и список окружающих клеток +
// TODO2: Всем клеткам из массива нужно сделать setShip(createdShip) +

	public boolean checkLocationShip(int x, int y, boolean verticalShip,
	                                 boolean gorizontalShip, int shipSize) {
		// int fromX, fromY;
		// int toX = 0;
		// int toY = 0;


		//формируем индексы начала и конца цикла для строк,
		//для проверки области расположения корабля
		//если координата "х" равна нулю, то это значит, что
		//палуба находится у верхней стенки поля
		//значит началом цикла будет х = 0, в противном случае х - 1
		fromX = (x == 0) ? x : x - 1;
		//Если условие истинно, то корабль расположен вертикально и последняя его
		//палуба находится у нижней границы поля, из этого следует,
		//координата "х" последней палубы будет индексом конца цикла
		if (x + shipSize == fieldLength && verticalShip == true) toX = x + (shipSize - 1);
		//корабль расположен вертикально и между ним и нижней границей
		//есть как минимум одна клетка, то она и будет являтся индексом конца цикла
		else if (x + shipSize < fieldLength && verticalShip == true) toX = x + shipSize;
		//корабль расположен горизонтально и находится у нижней границы поля
		else if (x == fieldLength - 1 && gorizontalShip == true) toX = x;
		//корабль расположен горизонтально и не касается стенок
		else if (x < fieldLength && gorizontalShip == true) toX = x + 1;

		//формируем индексы начала и конца для столбцов
		//принцип тот же
		fromY = (y == 0) ? y : y - 1;
		if (y + shipSize == fieldLength && gorizontalShip == true) toY = y + (shipSize - 1);
		else if (y + shipSize < fieldLength && gorizontalShip == true) toY = y + shipSize;
		else if (y == fieldLength - 1 && verticalShip == true) toY = y;
		else if (y < fieldLength && verticalShip == true) toY = y + 1;
		// System.out.println("fromX: " + fromX + " toX: " + toX + " fromY: " + fromY + " toY: " + toY);
		//проверяем наши найденные области на наличие кораблей
		for (int i = fromX; i <= toX; i++) {
			for (int j = fromY; j <= toY; j++) {
				if (battleField[i][j] == 1) {
					// System.out.println("Точка: " + i + " - " + j + " занята");
					// System.out.println("");
					return false;
				}
			}
		}
		//отмечаем область вокруг корабля "2ой"
		for (int i = fromX; i <= toX; i++) {
			for (int j = fromY; j <= toY; j++) {
				battleField[i][j] = 2;
				// System.out.println("Точка: " + i + "-" + j + "занята");
			}
		}
		//отмечаем расположение корабля "1ой"
		//проверить что в 254 строке выходит за границы массива
		int r = x;
		int c = y;
		for (int k = 0; k < shipSize; k++) {
			battleField[r][c] = 1;
			if (verticalShip == true) {
				r++;
			} else {
				c++;
			}
		}
		return true;
	}
}