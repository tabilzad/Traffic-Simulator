package myproject.ui;

import javax.swing.JOptionPane;

public final class PopupUI implements UI {
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void displayError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	public void processMenu(UIMenu menu) {
		StringBuilder b = new StringBuilder();
		b.append(menu.getHeading());
		b.append("\n");
		b.append("Enter choice by number:");
		b.append("\n");

		for (int i = 1; i < menu.size(); i++) {
			b.append("  " + i + ". " + menu.getPrompt(i));
			b.append("\n");
		}

		String response = JOptionPane.showInputDialog(b.toString());
		if (response == null) {
			response = "";
		}
		int selection;
		try {
			selection = Integer.parseInt(response, 10);
			if ((selection < 0) || (selection >= menu.size()))
				selection = 0;
		} catch (NumberFormatException e) {
			selection = 0;
		}

		menu.runAction(selection);
	}

	public String[] processForm(UIForm form) {
		// TODO
		String[] r = new String[form.size()];
		for (int i = 0; i < form.size(); i++) {
			String response = JOptionPane.showInputDialog(form.getPrompt(i));
			r[i] = response;
		}

		return r;

	}
}