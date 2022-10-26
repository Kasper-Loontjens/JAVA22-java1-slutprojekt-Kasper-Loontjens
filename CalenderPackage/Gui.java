package CalenderPackage;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Gui {
	//Gui element
	JFrame frame = new JFrame("Calender");
	JPanel groupContainer = new JPanel();
	JButton nextWeekButton = new JButton("Next");
	JButton previousWeekButton = new JButton("Previous");
	//En tom lista som kommer fyllas med veckor när man klickar på knappar.
	ArrayList<Week> weeks = new ArrayList<>();
	//För att hålla koll på den nuvarande veckan.
	int weekNr = 0;
	//För att börja datum på dagens datum. Används sen för att skapa veckor med nya datum.
	LocalDate aDayInWeek = LocalDate.now();
	
	 void createAndDisplay() {
		 //Gör första veckan.
		 	weeks.add(new Week(groupContainer, aDayInWeek));
		 	weeks.get(0).createWeek();
		 	positionAndStyle();
		 //För att kunna positionera fönstren som jag vill.
			groupContainer.setLayout(null);
		
		//Lägger till element.
			frame.setSize(1000,600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(previousWeekButton);
			frame.add(nextWeekButton);
			frame.add(groupContainer);
			frame.setVisible(true);
	 }
	 void positionAndStyle() {
		//Positionerar knapparna och gör så att dem går att klicka på.
		 nextWeekButton.setBounds(800, 400, 100, 30);
		 nextWeekButton.addActionListener(nextWeekAction);

		 previousWeekButton.setBounds(100, 400, 100, 30);
		 previousWeekButton.addActionListener(previousWeekAction);
	 }
	 
	 ActionListener nextWeekAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		//Om den nuvarande veckan är sist i listan så skapas en ny vecka med nya datum som läggs till i listan sen visas.
			if (weekNr == weeks.size() -1 ) {
				weeks.get(weekNr).removeThisWeek();
			 	aDayInWeek = aDayInWeek.plusDays(7);
			 	weeks.add(new Week(groupContainer, aDayInWeek));
				weekNr ++;
			 	weeks.get(weekNr).createWeek();
				weeks.get(weekNr).addThisWeek();
		//Annars bytar den till nästa vecka.
			}else {
				weeks.get(weekNr).removeThisWeek();
				weekNr ++;
			 	aDayInWeek = aDayInWeek.plusDays(7);
				weeks.get(weekNr).addThisWeek();
			}
		//Målar om alla Gui element.
			frame.repaint();
			frame.revalidate();
		}
	};
	ActionListener previousWeekAction = new ActionListener() {
	 //Om nuvarande vecka är först i listan skapas en ny vecka i början av listan.
		public void actionPerformed(ActionEvent e) {
			if (weekNr == 0) {
				weeks.get(0).removeThisWeek();
			 	aDayInWeek = aDayInWeek.minusDays(7);
			 	weeks.add(0, new Week(groupContainer, aDayInWeek));
			 	weeks.get(0).createWeek();
				weeks.get(0).addThisWeek();

			}else {
				weeks.get(weekNr).removeThisWeek();
				weekNr --;
			 	aDayInWeek = aDayInWeek.minusDays(7);
				weeks.get(weekNr).addThisWeek();
			}
			frame.repaint();
			frame.revalidate();
		}
	};	
}
