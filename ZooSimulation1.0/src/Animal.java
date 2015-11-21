/*************************************************************************** Class:  AnimalAuthor:  Greg King  Date:  October 21, 2004This class is intended to be a super class used to create simulated animalsin for a zoo or similar environment.  Date		Modification10-21-2004	Main Coding10-22-2004	Updated one argument constructor, added a two argument constructor,			added accessor methods12-03-2004	Added amIDead and idNumber			amIDead is used to keep Animals in the list of all animals given to			TheGUIPart from acting if they are killed before they get a chance 			to act, idNumber is used to positively ID any given Animal11-13-2005	Added genetics to the Animal class****************************************************************************/import java.awt.*;import java.util.*;public class Animal{	protected static int idCounter=0;		protected Position myPos;	protected Color myColor;	protected Cage myCage;	protected static Random generator = new Random();	protected boolean amIDead;	protected int idNumber;		protected int geneA;	protected int geneB;	protected int[] possibleGenes = {2,3,5,7,11,13,17,19,23,29};		/**	*	Constructor creates an animal with Position 0,0.  Animal	*	has no cage in which to live.	*/	public Animal()	{		myPos.setPosition(0,0);		myColor = Color.black;		amIDead = false;		idNumber = idCounter;		idCounter++;		randomGenes();	}		/**	*	Constructor creates an animal in a random empty spot in	*	the given cage.	*	@param cage  the cage in which animal will be created.	*/	public Animal (Cage cage)	{		myCage = cage;		myColor = Color.black;		if(myCage.isFull() == false)		{			myPos = myCage.randomEmpty();		}		amIDead = false;		idNumber = idCounter;		idCounter++;		randomGenes();	}		/**	*	Constructor creates an animal in a random empty spot in	*	the given cage with the specified Color.	*	@param cage  the cage in which animal will be created.	*	@param color  the color of the animal	*/	public Animal(Cage cage, Color color)	{		myCage = cage;		myColor = color;		if(myCage.isFull() == false)		{			myPos = myCage.randomEmpty();		}		amIDead = false;		idNumber = idCounter;		idCounter++;	}		/**	*	Constructor creates an animal in the given Position	*	the given cage with the specified Color.	*	@param cage  the cage in which animal will be created.	*	@param color  the color of the animal	*	@param pos	the position of the animal	*/	public Animal(Cage cage, Color color, Position pos)	{		myCage = cage;		myColor = color;		if(myCage.isEmptyAt(pos))		{			myPos = pos;		}		amIDead = false;		idNumber = idCounter;		idCounter++;		randomGenes();	}			/**	*	Accessor method returns the Animal's Position.	*	@return returns Animal's position	*/	public Position getPosition()	{		return myPos;	}		/**	*	Accessor method returns the Animal's Color.	*	@return returns Animal's color	*/	public Color getColor()	{		return myColor;	}		/**	*	Method causes the Animal to act.  This may include 	*	any number of behaviors (moving, eating, etc.).	*	@return	true if animal did act, false otherwise	*/	public boolean act()	{		boolean didAct = false;				/*  Stores current Position and checks for open spots */		Position[] possible = myCage.emptyNeighbors(myPos);		if (possible.length==0)			return didAct;		Position oldPos = myPos;				/* Moves the Animal */		int index = (int)(generator.nextDouble()*possible.length);		myPos = possible[index];		myCage.moveAnimal(oldPos, this);		/*  End of moving the Animal */				didAct = true;		return didAct;			}		/**	*	Method returns true if Animal is dead, false if it is alive.	*	@return true if Animal is dead, false otherwise	*/	public boolean isDead()	{		return amIDead;	}		/**	*	Method "kills" animal by telling the Animal it is dead.	*/	public void kill()	{		amIDead = true;	}		/**	*	Method returns the identification number (unique for each 	*	Animal) of the Animal.	*	@return returns id number of Animal	*/	public int getID()	{		return idNumber;	}		/**	*	Returns String form of Animal, which is its position	*	and its type.	*	@return String form of Animal	*/	public String toString()	{		return (myPos.toString() + " is an Animal.  ");	}		/**	*	Method returns the String form of the Animal's	*	species, in this case "Animal"	*	@return	the String "Animal"	*/	public String getSpecies()	{		return "Animal";	}			/**	*	Method assigns random values to Animals genes.	*/	public void randomGenes()	{		geneA = 2;		if(Math.random()<.5)			geneB = 2;		else			geneB = 3;	}			public int getGeneA()	{		return geneA;	}			public int getGeneB()	{		return geneB;	}}