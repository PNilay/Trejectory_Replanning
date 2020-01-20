import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Gridworld{

public node gridworld[];
public int row;
public int column;
public int startRow;
public int startCol;
public int goleRow;
public int goleCol;
public int Total_Blocked;
public int Prev_Blocked;

public Gridworld(int row, int column)
{
  this.row = row;
  this.column = column;
  this.gridworld = new node[row*column];
  this.goleRow = -1;
  this.goleCol =-1;
  this.startCol = -1;
  this.startRow = -1;
  this.Total_Blocked = 0;
  this.Prev_Blocked =-1;

int i;
  for(i =0; i<row*column; i++)
  {
    this.gridworld[i] = new node();
  }
}

public void Print_h()
{
  for (int r = 0; r < this.row; r++) {
      for (int c = 0; c < this.column; c++) {
    	  System.out.print(this.get_node_at(r,c).h);
      }
      System.out.println("\n");
  }
}


public void SetIsVisited()
{
  int i;
    for(i =0; i<row*column; i++)
    {
      this.gridworld[i].isVisited = false;
      this.gridworld[i].A_star_parent = null;
      this.gridworld[i].isExpanded = false;
    }
}
public void Assign_Start_End(int SR, int SC, int GR, int GC)
{
  this.goleRow = GR;
  this.goleCol = GC;
  this.startCol = SC;
  this.startRow = SR;
}

public void Assign_Start(int SR, int SC)
{
  this.startRow = SR;
  this.startCol = SC;
}

public void Assign_End(int GR, int GC)
{
  this.goleRow = GR;
  this.goleCol = GC;
}
public node get_node_at(int Row, int Column)
{
  if(Row <0 || Column<0)
  {return null;}
  return this.gridworld[Row*this.column+Column];
}

public void Assign_Index()
{
  int i;
    for(i =0; i<row*column; i++)
    {
      this.gridworld[i].index = i;
    }
}

public void Assign_neighbors()
{
  int Row, Column ;
  for (Row = 0; Row < this.column; Row++) {
       for (Column = 0; Column < this.row; Column++) {
           node current = get_node_at(Row,Column);

           current.row = Row;
           current.column = Column;

           if (Row > 0) {     // it has up neighbor
               current.up = get_node_at(Row-1,Column);
           }
           if (Row < (this.row - 1)) { // it has down neighbor
               current.down = get_node_at(Row+1,Column);
           }
           if (Column > 0) {     // it has left neighbor
               current.left = get_node_at(Row, Column-1);
           }
           if (Column < (this.column - 1)) { // has right neighbor
               current.right = get_node_at(Row,Column+1);
           }
       }
   }
}



public void Print_List()
{
  for (int r = 0; r < this.row; r++) {
      for (int c = 0; c < this.column; c++) {
    	  System.out.print(get_node_at(r,c).blocked);
      }
      System.out.println("\n");
  }
}

public void Print_And_Save(int gridID) throws IOException
{

	FileWriter fileWriter = new FileWriter(gridID + ".txt", true); //Set true for append mode
	PrintWriter printWriter = new PrintWriter(fileWriter);

	for (int r = 0; r < this.row; r++) {
		for (int c = 0; c < this.column; c++) {
	    	//System.out.print(get_node_at(r,c).blocked);

	    	//printWriter = new PrintWriter(fileWriter);
	    	//printWriter = new PrintWriter(fileWriter);
			printWriter.print(get_node_at(r,c).blocked);  //New line

		}
	   // System.out.println("\n");
	    printWriter.println("\n");
	}

	printWriter.close();

}

public void Save_Estimated(String name, int gridID) throws IOException
{

	FileWriter fileWriter = new FileWriter(name + "_"+ gridID + ".txt", true); //Set true for append mode
	PrintWriter printWriter = new PrintWriter(fileWriter);

	for (int r = 0; r < this.row; r++) {
		for (int c = 0; c < this.column; c++) {
//	    	System.out.print(get_node_at(r,c).blocked);

	    	//printWriter = new PrintWriter(fileWriter);
	    	//printWriter = new PrintWriter(fileWriter);
			printWriter.print(get_node_at(r,c).blocked);  //New line

		}
	    //System.out.println("\n");
	    printWriter.println("\n");
	}

	printWriter.close();

}


public void Print_Visit_List()
{
  for (int r = 0; r < this.row; r++) {
      for (int c = 0; c < this.column; c++) {
    	  System.out.print(get_node_at(r,c).visitState);
      }
      System.out.println("\n");
  }
}

public void Create_Grid()
{
  this.Assign_Index();
  this.Assign_neighbors();
  //System.out.println("Initial Grid:");
  //this.Print_List();
}


}
