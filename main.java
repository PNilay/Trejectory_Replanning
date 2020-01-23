import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class main{

public static void main(String[] args) throws IOException {

		//Uncomment following commands to Create 50 Gridworlds of 101X101 (Save them as .txt file)
		 // createEnvironments();
			int option = main_menu();
			switch(option){
				case 1: //Set up Enviornments
					createEnvironments();
					System.out.println("Enviornments Created");
					break;
				case 2://repeated forward A* algorithm ( select random g-value if their f-values are equal)
					Gridworld p1 = askAndLoad();
					A_Star A1 = new A_Star(p1.startRow,p1.startCol,p1.goleRow, p1.goleCol);
					A1.Initialize_Repeted_Forward_Astar(p1,0);
					break;
				case 3://repeated forward A* algorithm ( priortize larger g-value if their f-values are equal)
					Gridworld p2 = askAndLoad();
					A_Star A2 = new A_Star(p2.startRow,p2.startCol,p2.goleRow, p2.goleCol);
					A2.Initialize_Repeted_Forward_Astar(p2,1);
					break;
				case 4://repeated forward A* algorithm ( priortize smaller g-value if their f-values are equal)
					Gridworld p3 = askAndLoad();
					A_Star A3 = new A_Star(p3.startRow,p3.startCol,p3.goleRow, p3.goleCol);
					A3.Initialize_Repeted_Forward_Astar(p3,2);
					break;
				case 5://repeated backward A* algorithm ( select random g-value if their f-values are equal)
					Gridworld p4 = askAndLoad();
					A_Star A4 = new A_Star(p4.startRow,p4.startCol,p4.goleRow, p4.goleCol);
					A4.Initialize_Repeted_Backward_Astar(p4,0);
					break;
				case 6://repeated backward A* algorithm ( priortize larger g-value if their f-values are equal)
					Gridworld p5 = askAndLoad();
					A_Star A5 = new A_Star(p5.startRow,p5.startCol,p5.goleRow, p5.goleCol);
					A5.Initialize_Repeted_Backward_Astar(p5,1);
					break;
				case 7://repeated backward A* algorithm ( priortize smaller g-value if their f-values are equal)
					Gridworld p6 = askAndLoad();
					A_Star A6 = new A_Star(p6.startRow,p6.startCol,p6.goleRow, p6.goleCol);
					A6.Initialize_Repeted_Backward_Astar(p6,2);
					break;
				case 8://adaptive A* algorithm ( select random g-value if their f-values are equal)
					Gridworld p7 = askAndLoad();
					A_Star A7 = new A_Star(p7.startRow,p7.startCol,p7.goleRow, p7.goleCol);
					A7.Initialize_Adaptive_Astar(p7,0);
					break;
				case 9://adaptive A* algorithm ( priortize larger g-value if their f-values are equal)
					Gridworld p8 = askAndLoad();
					A_Star A8 = new A_Star(p8.startRow,p8.startCol,p8.goleRow, p8.goleCol);
					A8.Initialize_Adaptive_Astar(p8,1);
					break;
				case 10://Perform adaptive A* algorithm ( priortize smaller g-value if their f-values are equal)
					Gridworld p9 = askAndLoad();
					A_Star A9 = new A_Star(p9.startRow,p9.startCol,p9.goleRow, p9.goleCol);
					A9.Initialize_Adaptive_Astar(p9,2);
					break;
			}

		//	Gridworld p1 = askAndLoad();
			//System.out.println("+\n\n" + "Loaded World: ");
			//p1.Print_List();
			//A_Star A = new A_Star(p1.startRow,p1.startCol,p1.goleRow, p1.goleCol);
			//A.Initialize_Repeted_Forward_Astar(p1,0);
		//	A.Initialize_Repeted_Forward_Astar(P1,1);
		//	A.Initialize_Repeted_Forward_Astar(P1,2);

		//  A.Initialize_Adaptive_Astar(p1,0);
		//  A.Initialize_Adaptive_Astar(p1,1);
		//  A.Initialize_Adaptive_Astar(P1,2);

		// A.Initialize_Repeted_Backward_Astar(p1,0);
	  // A.Initialize_Repeted_Backward_Astar(p1,1);
		// A.Initialize_Repeted_Backward_Astar(p1,2);



		/*
		A_Star A = new A_Star(p1.startRow,p1.startCol,p1.goleRow, p1.goleCol);

			long StartTime = System.currentTimeMillis();
		 A.Initialize_Repeted_Backward_Astar(p1,0);
//A.Initialize_Repeted_Forward_Astar(p1,0);
		 	//A.Initialize_Adaptive_Astar(p1,0);
		 long EndTime = System.currentTimeMillis();
		 System.out.println("Time: "+ (EndTime - StartTime));


			//A.Initialize_Adaptive_Astar(p1,1);
			//A.Initialize_Repeted_Backward_Astar(p1,0);
			*/
=	}

public static int main_menu()
{
	System.out.println("MENU: Select one of the options:");
	System.out.println("1. Create gridworlds of size 101X101, and save them in .txt files for later use");
	System.out.println("2. Perform repeated forward A* algorithm ( select random g-value if their f-values are equal)");
	System.out.println("3. Perform repeated forward A* algorithm ( priortize larger g-value if their f-values are equal)");
	System.out.println("4. Perform repeated forward A* algorithm ( priortize smaller g-value if their f-values are equal)");

	System.out.println("5. Perform repeated backward A* algorithm ( select random g-value if their f-values are equal)");
	System.out.println("6. Perform repeated backward A* algorithm ( priortize larger g-value if their f-values are equal)");
	System.out.println("7. Perform repeated backward A* algorithm ( priortize smaller g-value if their f-values are equal)");

	System.out.println("8. Perform adaptive  A* algorithm ( select random g-value if their f-values are equal)");
	System.out.println("9. Perform adaptive A* algorithm ( priortize larger g-value if their f-values are equal)");
	System.out.println("10. Perform adaptive A* algorithm ( priortize smaller g-value if their f-values are equal)");

	System.out.println("Which option? (Enter 1-10) (enter any other key to exit): ");

	Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	String input = myObj.nextLine();  // Read user input

	int id;
	try {
		 id = Integer.parseInt(input);
	}catch (NumberFormatException e){
		 id = 0;
	}
	return id;
}

	//picks a random node in the grid to mark as the start state 'S'
	//picks a random node in the grid to mark as the goal state 'G'
	public static void createStartingPoints(Gridworld p1, int gridID) throws IOException {

		int startRow = (int) (Math.random() * (101));
		int startCol = (int) (Math.random() * (101));

		System.out.println("Start Coordinates:");
		System.out.print(startRow + "\t");
		System.out.println(startCol);

		//choose random node for goal
		int goalRow = (int) (Math.random() * (101));
		int goalCol = (int) (Math.random() * (101));

		System.out.println("Goal Coordinates:");
		System.out.print(goalRow + "\t");
		System.out.println(goalCol);


		p1.get_node_at(startRow, startCol).blocked = 'S'; 	//starting node

		p1.get_node_at(goalRow, goalCol).blocked = 'G'; 	//goal node

		createObstacles(p1, p1.get_node_at(startRow, startCol), null);

    p1.Assign_Start_End(startRow,startCol,goalRow, goalCol);
		p1.Print_And_Save(gridID);

    //A_Star A = new A_Star(startRow,startCol,goalRow, goalCol);
    //A.Initialize_Forward_Astar(p1,0);

	}



//searches all nodes through dfs
//starts at p1's start node which is denoted by 'S'
//30% chance of marking the visited node as blocked
//visited statuses are kept under node.visitState where 'U' marks unvisited and 'V' marks visited
//For now, X's denote blockages and O's denoted open spaces
public static void createObstacles(Gridworld p1, node head, node parent) {
	//start dfs
	boolean control = true;
	node temp = new node();
	double possibleBlock;



	while(true) {

		possibleBlock = Math.random();
		boolean leftNeighbor = false;
		boolean rightNeighbor = false;
		boolean upNeighbor = false;
		boolean downNeighbor = false;

		if(head.dfsVisit != false && possibleBlock <= 0.30) {
			head.dfsVisit = true;
			if(head.blocked != 'G' && head.blocked != 'S') { //the potential block isnt already the start or goal state
				head.blocked = 'X';
			}else {
				System.out.println("already head or goal state");
			}
		}else {
			head.dfsVisit = true;
		}


		if(head.left != null && head.left.dfsVisit == false) {
			leftNeighbor = true;
		}else if(head.right != null && head.right.dfsVisit == false) {
			rightNeighbor = true;
		}else if(head.up != null && head.up.dfsVisit == false) {
			upNeighbor = true;
		}else if(head.down != null && head.down.dfsVisit == false) {
			downNeighbor = true;
		}

		int nextNeighbor = -1;
		String randomNeigh = "";


		if(leftNeighbor && rightNeighbor && upNeighbor && downNeighbor){
			nextNeighbor = (int) (Math.random() * 4);

			if(nextNeighbor == 0) {
				randomNeigh = "left";
			}else if(nextNeighbor == 1) {
				randomNeigh = "right";
			}else if(nextNeighbor == 2) {
				randomNeigh = "up";
			}else if(nextNeighbor == 3) {
				randomNeigh = "down";
			}

		}else if(!leftNeighbor && rightNeighbor && upNeighbor && downNeighbor) {
			nextNeighbor = (int) (Math.random() * 3);

			 if(nextNeighbor == 0) {
				randomNeigh = "right";
			}else if(nextNeighbor == 1) {
				randomNeigh = "up";
			}else if(nextNeighbor == 2) {
				randomNeigh = "down";
			}

		}else if(leftNeighbor && !rightNeighbor && upNeighbor && downNeighbor) {
			nextNeighbor = (int) (Math.random() * 3);

			if(nextNeighbor == 0) {
				randomNeigh = "left";
			}else if(nextNeighbor == 1) {
				randomNeigh = "up";
			}else if(nextNeighbor == 2) {
				randomNeigh = "down";
			}

		}else if(leftNeighbor && rightNeighbor && !upNeighbor && downNeighbor) {
			nextNeighbor = (int) (Math.random() * 3);

			 if(nextNeighbor == 0) {
				randomNeigh = "left";
			}else if(nextNeighbor == 1) {
				randomNeigh = "right";
			}else if(nextNeighbor == 2) {
				randomNeigh = "down";
			}

		}else if(leftNeighbor && rightNeighbor && upNeighbor && !downNeighbor) {
			nextNeighbor = (int) (Math.random() * 3);

			if(nextNeighbor == 0) {
				randomNeigh = "left";
			}else if(nextNeighbor == 1) {
				randomNeigh = "right";
			}else if(nextNeighbor == 2) {
				randomNeigh = "up";
			}

		}else if(!leftNeighbor && !rightNeighbor && upNeighbor && downNeighbor){
			nextNeighbor = (int) (Math.random() * 2);

			if(nextNeighbor == 0) {
				randomNeigh = "up";
			}else if(nextNeighbor == 1) {
				randomNeigh = "down";
			}

		}else if(!leftNeighbor && rightNeighbor && !upNeighbor && downNeighbor) {
			nextNeighbor = (int) (Math.random() * 2);

			if(nextNeighbor == 0) {
				randomNeigh = "right";
			}else if(nextNeighbor == 1) {
				randomNeigh = "down";
			}

		}else if(!leftNeighbor && rightNeighbor && upNeighbor && !downNeighbor) {
			nextNeighbor = (int) (Math.random() * 2);

			if(nextNeighbor == 0) {
				randomNeigh = "right";
			}else if(nextNeighbor == 1) {
				randomNeigh = "up";
			}

		}else if(leftNeighbor && !rightNeighbor && !upNeighbor && downNeighbor) {
			nextNeighbor = (int) (Math.random() * 2);

			if(nextNeighbor == 0) {
				randomNeigh = "left";
			}else if(nextNeighbor == 1) {
				randomNeigh = "down";
			}

		}else if(leftNeighbor && !rightNeighbor && upNeighbor && !downNeighbor) {
			nextNeighbor = (int) (Math.random() * 2);

			if(nextNeighbor == 0) {
				randomNeigh = "left";
			}else if(nextNeighbor == 1) {
				randomNeigh = "up";
			}

		}else if(leftNeighbor && rightNeighbor && !upNeighbor && !downNeighbor) {
			nextNeighbor = (int) (Math.random() * 2);

			if(nextNeighbor == 0) {
				randomNeigh = "right";
			}else if(nextNeighbor == 1) {
				randomNeigh = "left";
			}

		}else if(!leftNeighbor && !rightNeighbor && !upNeighbor && downNeighbor){
			randomNeigh = "down";
		}else if(!leftNeighbor && !rightNeighbor && upNeighbor && !downNeighbor){
			randomNeigh = "up";
		}else if(leftNeighbor && !rightNeighbor && !upNeighbor && !downNeighbor) {
			randomNeigh = "left";
		}else if(!leftNeighbor && rightNeighbor && !upNeighbor && !downNeighbor) {
			randomNeigh = "right";
		}

		//System.out.println(randomNeigh);


		if(randomNeigh == "left" && head.left.dfsVisit == false) {
			//leftNeighbor = true;
			head.dfsVisit = true;
			temp = head;
			head = head.left;
			head.parent = temp;
			createObstacles(p1, head, head.parent);
		}else if(randomNeigh == "right" && head.right.dfsVisit == false) {
			head.dfsVisit = true;
			temp = head;
			head = head.right;
			head.parent = temp;
			createObstacles(p1, head, head.parent);
		}else if(randomNeigh == "up" && head.up.dfsVisit == false) {
			head.dfsVisit = true;
			temp = head;
			head = head.up;
			head.parent = temp;
			createObstacles(p1, head, head.parent);
		}else if(randomNeigh == "down" && head.down.dfsVisit == false) {
			head.dfsVisit = true;
			temp = head;
			head = head.down;
			head.parent = temp;
			createObstacles(p1, head, head.parent);
		}else if(head.parent != null) {
			head.dfsVisit = true;
			temp = head;
			head = head.parent;

			if(head.left != null && head.left.dfsVisit == false) {
				head = head.left;
			}else if(head.right != null && head.right.dfsVisit == false) {
				head = head.right;
			}else if(head.up != null && head.up.dfsVisit == false) {
				head = head.up;
			}else if(head.down != null && head.down.dfsVisit == false) {
				head = head.down;
			}

			createObstacles(p1, head, head.parent);
		}

		break;
	}


	return;
}
/*
	//searches all nodes through dfs
	//starts at p1's start node which is denoted by 'S'
	//30% chance of marking the visited node as blocked
	//visited statuses are kept under node.visitState where false marks unvisited and true marks visited
	//For now, X's denote blockages and O's denoted open spaces
	public static void createObstacles(Gridworld p1, node head, node parent) {
		//start dfs
		boolean control = true;
		node temp = new node();
		double possibleBlock;

		while(true) {
			//head.visitState = true;

			possibleBlock = Math.random();

			//System.out.println(possibleBlock);

			if(possibleBlock <= 0.20) {
				if(head.blocked != 'G' && head.blocked != 'S') { //the potential block isnt already the start or goal state
					head.blocked = 'X';
					head.visitState = true;
				}else {
					System.out.println("already head or goal state");
				}
			}

			if(head.left != null && head.left.visitState == false) {
				head.visitState = true;
				temp = head;
				head = head.left;
				head.parent = temp;
				createObstacles(p1, head, head.parent);
			}else if(head.right != null && head.right.visitState == false) {
				head.visitState = true;
				temp = head;
				head = head.right;
				head.parent = temp;
				createObstacles(p1, head, head.parent);
			}else if(head.up != null && head.up.visitState == false) {
				head.visitState = true;
				temp = head;
				head = head.up;
				head.parent = temp;
				createObstacles(p1, head, head.parent);
			}else if(head.down != null && head.down.visitState == false) {
				head.visitState = true;
				temp = head;
				head = head.down;
				head.parent = temp;
				createObstacles(p1, head, head.parent);
			}else if(head.parent != null) {
				head.visitState = true;
				temp = head;
				head = head.parent;

				if(head.left != null && head.left.visitState == false) {
					head = head.left;
				}else if(head.right != null && head.right.visitState == false) {
					head = head.right;
				}else if(head.up != null && head.up.visitState == false) {
					head = head.up;
				}else if(head.down != null && head.down.visitState == false) {
					head = head.down;
				}




				createObstacles(p1, head, head.parent);
			}

			break;
		}

		return;
	}
*/
  public static Gridworld askAndLoad() throws FileNotFoundException, IOException {
			int id;
			while(true) {
  			System.out.println("Which environment? (Enter 0-49): ");

  			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
  			String input = myObj.nextLine();  // Read user input

  			try {
  			   id = Integer.parseInt(input);
  			   //System.out.println(id);

  			}catch (NumberFormatException e){
  				//System.out.println(id);
  			   id = 0;
  			}

  			if(id < 0 || id >=50) {
  				System.out.print("Not a valid environment! ");
  				continue;
  			}

  			break;
  		}

  		String loadFile = id + ".txt";
  		Gridworld loadedWorld = new Gridworld(101, 101);
  		loadedWorld.Create_Grid();

  		String[] fileLines = new String[200];
  		int i = 0;

  		//System.out.println("\n" + loadFile + "holds:");
  		try (BufferedReader br = new BufferedReader(new FileReader(loadFile))) {
  			   String line = null;
  			   while ((line = br.readLine()) != null && i<199) {
  				   if(line.equals("")) {

  				   }else {
  					   fileLines[i] = line;
  					   i++;
  				   }
  				   //System.out.println(line);

  			   }
  		}catch(FileNotFoundException ex){
				System.out.println("Enviornment enterd is not available in System (Run Comman 1 of menu first)");
				System.exit(0);
			}

  		int goalRow = -1;
  		int goalCol = -1;

  		int startRow = -1;
  		int startCol = -1;

  		for(int r = 0; r < 101; r++) {
  			for(int c = 0; c < 101; c++) {

  				//System.out.print(fileLines[r].charAt(c));

  				if(fileLines[r].charAt(c) == 'O') { //unblocked node
  					loadedWorld.get_node_at(r, c).blocked = 'O';
  				}else if(fileLines[r].charAt(c) == 'X'){ //blocked node
  					loadedWorld.get_node_at(r, c).blocked = 'X';
  				}else if(fileLines[r].charAt(c) == 'G') { //goal node
  					loadedWorld.get_node_at(r, c).blocked = 'G';
  					goalRow = r;
  					goalCol = c;
  				}else if(fileLines[r].charAt(c) == 'S' ) { //start node
  					loadedWorld.get_node_at(r, c).blocked = 'S';
  					startRow = r;
  					startCol = c;
  				}
  			}
  		}

			loadedWorld.Assign_Start_End(startRow, startCol,goalRow,goalCol);
  		return loadedWorld;
  	}

  	public static void createEnvironments() throws IOException {

  		for(int i = 0; i < 50; i++) {

  			Gridworld p1 = new Gridworld(101, 101); // this generate grid of row X column (48X48)
  			p1.Create_Grid();

  			//will create initial grid and populate with
  			//start node: denoted with S
  			//goal node: denoted with G
  			//beginning of obstacles node: denoted with X
				DFS n =  new DFS();
				n.createStartingPoints(p1);
				p1.Print_And_Save(i);

  			//createStartingPoints(p1,i);
  		}
  	}


  }
