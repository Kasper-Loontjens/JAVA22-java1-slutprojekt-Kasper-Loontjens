package CalenderPackage;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


class Day {
	//Datum passerat från veckan.
	LocalDate localDate;
	//Gui element, Text för att visa datumet och knapp för att kunna lägga till "Event" i en textruta.
	JLabel label = new JLabel("");
	JLabel labelMonth = new JLabel("");
	JTextArea textArea = new JTextArea(); 
	JTextField textField = new JTextField();
	JPanel panel = new JPanel();
	JButton button = new JButton("Add");
	String textInArea = "";
	
	int axisX;

	Day(LocalDate localDate, JPanel container, int axisX){
		this.localDate = localDate;
		this.axisX = axisX;

		add(container);
		
		positionAndStyle();
	}
	
	void positionAndStyle() {
		//Positionerar gui elementen.
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		textArea.setMaximumSize(new Dimension(120, 200));
		textArea.setEditable(false);
		
		textField.setMaximumSize(new Dimension(120, 20));
		textField.setToolTipText("Add an event");

		button.setAlignmentX(button.CENTER_ALIGNMENT);
		button.addActionListener(actionListener);
		
		label.setText(localDate.getDayOfWeek().name());
		label.setAlignmentX(label.CENTER_ALIGNMENT);
		
		labelMonth.setText(localDate.getMonth().name().toLowerCase() + " " + localDate.getDayOfMonth());
		labelMonth.setAlignmentX(labelMonth.CENTER_ALIGNMENT);
			
		panel.setBounds(axisX+10, 50, 120, 300);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		if (localDate.getDayOfYear() == LocalDate.now().getDayOfYear()) {
			// Märkerar dagens datum med rosa bakgrund.
			panel.setBackground(Color.PINK);
		}
	}
	ActionListener actionListener = new ActionListener() {
		// När man trycker på knappen kollas det om användaren skrivit något, har dem det så läggs det till i textrutan.
		public void actionPerformed(ActionEvent e) {
			if (!textField.getText().isBlank()) {
				textInArea = textInArea + textField.getText() + "\n";
				textArea.setText(textInArea);
				textField.setText("");
			}
		}
	};
	void add(JPanel container) {
		//För att visa denna dagen.

		container.add(panel);
		panel.add(label);	
		panel.add(labelMonth);
		panel.add(textArea);
		panel.add(textField);
		panel.add(button);
		
	}
	void remove(JPanel container) {
		// För att sluta visa denna dagen.
		panel.remove(label);	
		panel.remove(labelMonth);
		panel.remove(textArea);
		panel.remove(textField);
		panel.remove(button);
		panel.removeAll();
		container.remove(panel);
	}
}
