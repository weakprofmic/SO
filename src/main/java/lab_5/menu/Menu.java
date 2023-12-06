package lab_5.menu;

import java.util.ArrayList;
import java.util.Scanner;

import lab_5.utils.ConsoleUtils;

public class Menu {

	private class MenuItem {

		private MenuCallback _mc;
		private String _text;

		public MenuItem(String text, MenuCallback mc) {
			_mc = mc;
			_text = text;
		}

		public MenuCallback get_mc() {
			return _mc;
		}

		public String get_text() {
			return _text;
		}

	}

	private ArrayList<MenuItem> Items = new ArrayList<MenuItem>();

	public boolean add(String text, MenuCallback mc) {
		return Items.add(new MenuItem(text, mc));
	}

	public void show() {
		int choosen = 0;
		Scanner in = new Scanner(System.in);

		for (int i = 0; i < Items.size(); ++i) {
			MenuItem mi = Items.get(i);
			System.out.printf(" [%d] %s \n", i + 1, mi.get_text());
		}

		System.out.println(); // add a line

		try {
			choosen = in.nextInt();
		} catch (Exception e1) {
			/* Ignore non numeric and mixed */ }

		ConsoleUtils.clearConsole();

		if (choosen > Items.size() || choosen < 1) {
			System.out.println("Invalid option.\nPress enter to continue...");
			in.nextLine();
			in.nextLine();
		} else {
			MenuItem mi = Items.get(choosen - 1);
			MenuCallback mc = mi.get_mc();
			mc.Invoke();
		}
	}
}
