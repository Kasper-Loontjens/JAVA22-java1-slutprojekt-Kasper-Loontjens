package CalenderPackage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.swing.JPanel;

class Week {
	//En array med dagar klasser. 
	Day daysOfWeek[] = new Day[7];
	//För att kunna positionera dagarna.
	int axisX = 0;
	//För att hålla koll på veckans datum, Datumet rör sig mellan alla sju dagar i veckan och passeras sen in i dagarnas klass.
	LocalDate weekDate;
	//Containern som dagarna kommer visas i.
	JPanel container;
	
	Week(JPanel container, LocalDate weekDate){
		//Skapar veckan 
		this.weekDate = weekDate;
		this.container = container;
	}
	void createWeek() {
		//återsttäller utifall veckan nån gång skulle skapas om.
		axisX = 0;
		makeItMonday();
		//För skapar dem sju dagarna och lägger dem i en array, varje dag får nytt datum och Gui elementen flyttas till höger om förra dagen.
		for (int i = 0; i < 7; i++) {
			daysOfWeek[i] = new Day(weekDate,container,axisX);
			weekDate = weekDate.plusDays(1);
			axisX+=140;
		}
	}
	void addThisWeek() {
		//Visar vajre dag i denna veckan.
		for (int i = 0; i < 7; i++) {
			daysOfWeek[i].add(container);

		}
	}
	void removeThisWeek() {
		//slutar visa varje dag i denna veckan.
		for (int i = 0; i < 7; i++) {
			daysOfWeek[i].remove(container);
		}
	}
	void makeItMonday() {
		//Gör det till måndag.
		LocalDate monday = weekDate;
	    while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
	      monday = monday.minusDays(1);
	      weekDate = monday;
	    }
	}
	
}
