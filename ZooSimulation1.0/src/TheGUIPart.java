// Legal Notice// This program was written using the BreezyGUI platform, written by Kenneth A. // Lambert and Martin Osborne.  The BreezyGUI package can be found at:// http://faculty.cs.wwu.edu/martin/Software%20Packages/BreezyGUI/breezyhome.htm// // The program may be use or modified as you wish, as long as you obey all of the// restrictions from the BreezyGUI site and maintain the reference to the author.// There is no actual or implied warranty and the program is to be used at the // user's own risk./******************************************************************** Class:  TheGUIPartAuthor:  Greg King  Date:  December 1, 2004This class provides the main interactive window for a user to control the simulation.The simulation is a Zoo with animals in a cage.Date			Modification12-01-2004	Main coding on this class started12-01-2004	Added Threaded (Runnable) operations12-03-2004	Attempting to correct the not quite dying animal problem, solved by			implementing a kill method in Animal class12-04-2004	Modified programs so Gazelles can either more intelligently (by running			away from Lions) or randomly.  Created a new class called HungryLion			with different behavior and characteristics than a Lion.11-01-2005	Cleaned up the user interface, allowed initialize button to clear simulation11-10-2005  	Added Agents to the mix of available Animals			Made Agents capable of "realistic" breeding and dying behavior with			genetics03-22-2006	Changed the way Predators find Prey*********************************************************************/import java.awt.*;//import javax.swing.*;import BreezyGUI.*;;// This class extends the BreezySwing GBFrame class and implements// the Runnable interface in order to create a threadpublic class TheGUIPart extends GBFrame implements Runnable{	private Cage myCage;	private Animal[] list;	private int numAnimals;	private Display displayWindow;		private IntegerField heightField;			// y value of cage	private IntegerField widthField;			// x value of cage	private IntegerField animalsField;	private IntegerField addAnimalsField;		private Label heightLabel;	private Label widthLabel;	private Label animalsLabel;	private Label addAnimalsLabel;		private Button initializeButton;	private Button oneStepButton;	private Button checkButton;	private Button runButton;	private Button stopButton;	private Button addAnimalsButton;	private Button addOneAnimalButton;	private Button printAnimalsButton;		private Choice animalTypes;	private TextArea outputArea;		public TheGUIPart( )	{		heightField = addIntegerField(50,1,2,1,1);		widthField = addIntegerField(50,2,2,1,1);		animalsField = addIntegerField(0,3,2,1,1);		addAnimalsField = addIntegerField(1,4,2,1,1);				heightLabel = addLabel("Height of Cage",1,1,1,1);		widthLabel = addLabel("Width of Cage",2,1,1,1);		animalsLabel = addLabel("Current # of Animals",3,1,1,1);		addAnimalsLabel = addLabel("# of Animals to add",4,1,1,1);				initializeButton = addButton("Initialize",5,1,1,1);		oneStepButton = addButton("One Step",5,2,1,1);		runButton = addButton("Run Sim",6,1,1,1);		stopButton = addButton("Stop",6,2,1,1);		addAnimalsButton = addButton("Add Animals",7,1,1,1);		addOneAnimalButton = addButton("Add One Animal",7,2,1,1);		printAnimalsButton = addButton("Show Animals",8,1,1,1);				animalTypes = addChoice(10,1,1,1);     	animalTypes.addItem("Standard Gazelles");     	animalTypes.addItem("Smart Gazelles");      	animalTypes.addItem("Standard Lions");      	animalTypes.addItem("Hungry Lions");      	      	outputArea = addTextArea("",11,1,2,2);	}		public void buttonClicked(Button buttonObj)	{		checkButton = buttonObj;		if(buttonObj == initializeButton)		{			initialize();		}		if(buttonObj == oneStepButton)		{			step();			animalsField.setNumber(myCage.getNumAnimals());			displayWindow.refresh(myCage);		}		if(buttonObj == runButton)		{			Thread go = new Thread(this);			go.start();		}		if(buttonObj == stopButton)		{			update();		}		if(buttonObj == addAnimalsButton)		{			int numAnimals = addAnimalsField.getNumber();			addAnimals(numAnimals);		}		if(buttonObj == addOneAnimalButton)		{			addAnimals(1);		}		if(buttonObj == printAnimalsButton)		{			Animal[] list = myCage.allAnimals();			for(int n=0; n<list.length; n++)			{				outputArea.append(list[n].toString() + "\n");			}		}	}			public void initialize()	{		int x = widthField.getNumber();		// int x = 750;		int y = heightField.getNumber();		myCage = new Cage(x, y);				animalsField.setNumber(0);		addAnimalsField.setNumber(1);		numAnimals = 0;				// makes the new display window (and close the old one)		if(displayWindow != null)			displayWindow.setVisible(false);		displayWindow = new Display(myCage);		displayWindow.refresh(myCage);	}			public void step()	{		list = myCage.allAnimals();		animalsField.setNumber(list.length);		for(int n=0; n<list.length; n++)		{			if(list[n].isDead() == false)				list[n].act();		}	}			public void run()	{		while (checkButton == runButton)		{			step();			animalsField.setNumber(myCage.getNumAnimals());			displayWindow.refresh(myCage);						// This code delays the thread execution for 100 milliseconds			// which makes the simulation slow enough to view			try			{				Thread.sleep(100);			}			catch(Exception e){}		}	}			public void update()	{		// no code currently	}			public void addAnimals(int numAnimals)	{		if(myCage==null)		{			initialize();		}				Animal animal;		boolean check;				for(int n=0; n<numAnimals; n++)		{			if(animalTypes.getSelectedIndex()==0)				animal = new Gazelle(myCage, Color.blue);			else if (animalTypes.getSelectedIndex()==1)				animal = new SmartGazelle(myCage, Color.green);			else if(animalTypes.getSelectedIndex()==2)				animal = new Lion(myCage, Color.yellow);			else if (animalTypes.getSelectedIndex()==3)				animal = new HungryLion(myCage, Color.magenta);			else				animal = new Animal(myCage,Color.pink);			check = myCage.addAnimal(animal);			if(check == false)			{				System.out.println("Failed to add Animal # " + n + ", a " + animal.getSpecies());			}		}				displayWindow.refresh(myCage);			}  // End method} // End class