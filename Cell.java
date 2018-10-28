package MyBattleShip;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class Cell implements ActionListener {

	// TODO: create getters and setters
	Boolean shooted = false;
	JButton button = null;
	Boolean hasShip = false;
	Boolean shipField = false; //нужен ли?
	Ship owner = null; // setship
	private String name = "nope";
	String steps;
	int incr = 0;
	// boolean isFloating = false;

	private ArrayList<MyControlListener> listeners = new ArrayList<MyControlListener>();
	//привязываем каждой клетке определенный корабль
	public void setShip(Ship ship) {
		owner = ship;
	}

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void actionPerformed(ActionEvent a) {
		JButton from = (JButton) a.getSource();


		// если кнопку уже стреляли - просто выйти из функции
		if (shooted == true) {
			System.out.println("ЭЭЭ в эту кнопку уже стреляли");
			return;
			//организовать выход из функции
		} else {
			shooted = true;
			// incr++;

		}

		// здесь нужно проверять, принадлежит ли эта точка кораблю
		// if(owner != null) {

		// }
		// если да, то в переменной owner можно узнать, потоплен корабль или нет
		if (hasShip && owner != null) {
			// from.setText("X");
			from.setBackground(Color.red);
			owner.сheckYourself();
			// owner.checkYourself()
		} else {
			// from.setText("o");
			from.setBackground(Color.blue);
		}
		// выпускает событие(ПроверьЧтоИгра кончилась)
		// steps = Integer.toString(incr);
		fireMyEvent("hh");
	}

	public void addMyEventListener(MyControlListener listener) {
		listeners.add(listener);
	}
	public MyControlListener[] getMyEventListeners() {
		return listeners.toArray(new MyControlListener[listeners.size()]);
	}
	public void removeMyEventListener(MyControlListener listener) {
		listeners.remove(listener);
	}
	protected void fireMyEvent(String message) {
		MyEvent ev = new MyEvent(this, message);
		for (MyControlListener listener : listeners) {
			listener.onDataChanged(ev);
		}
	}
}