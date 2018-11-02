package MyBattleShip;

public class ShipRandomizer {

	int fromX = 0;
	int fromY = 0;
	int toX = 0;
	int toY = 0;

	int[][] battleField = new int[fieldLength][fieldLength];

	

	public Ship randomPlaceShip(int shipSize, Cell[][] battleField2) {

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
		return randomShip;
	}

	public boolean checkLocationShip(int x, int y, boolean verticalShip,
	                                 boolean gorizontalShip, int shipSize) {
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
		//отмечаем область вокруг корабля "2-кой"
		for (int i = fromX; i <= toX; i++) {
			for (int j = fromY; j <= toY; j++) {
				battleField[i][j] = 2;
				// System.out.println("Точка: " + i + "-" + j + "занята");
			}
		}
		//отмечаем расположение корабля "1-кой"
		for (int k = 0; k < shipSize; k++) {
			battleField[x][y] = 1;
			if (verticalShip == true) {
				x++;
			} else {
				y++;
			}
		}
		return true;
	}
}