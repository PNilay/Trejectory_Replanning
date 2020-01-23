import java.lang.Math;
import java.util.ArrayList;
import java.io.IOException;

public class A_Star{

public int Start_Row;
public int Start_Column;
public int Gole_Row;
public int Gole_Column;

public int temp =0;

public A_Star(int SR, int SC, int GR, int GC)
{
  this.Start_Row = SR;
  this.Start_Column = SC;
  this.Gole_Row = GR;
  this.Gole_Column = GC;
}

public void Manhattan_Distances(Gridworld grid) // This Function assign h values to all cells
{
  for(int i=0; i<grid.row; i++){
    for(int j=0; j<grid.column; j++){
    grid.get_node_at(i,j).h = Math.abs(i-this.Gole_Row)+Math.abs(j-this.Gole_Column);
  }}
}

//g_priority = 0 or  any other integer// Select random g value node if both f are equal
//g_priority =1 //give more priority to max g value node
//g_priority = 2//give priority to low g value node


//Forward Astar Algorithm:
public void Initialize_Repeted_Forward_Astar(Gridworld grid, int g_priority)
{
  //Create Empty gridworld to store Robot's Observations:
  Gridworld Brain = new Gridworld(grid.row, grid.column);
  Brain.Create_Grid();

  //Assign Goal Row and column:
  Brain.Assign_End(grid.goleRow, grid.goleCol);
  Brain.get_node_at(grid.goleRow, grid.goleCol).blocked = 'G';

  //Assign  h values to each node in grid:
  this.Manhattan_Distances(grid);
  this.Manhattan_Distances(Brain);//For Empty gridworld.

  //start by setting g value of starting node:
  node current = grid.get_node_at(this.Start_Row, this.Start_Column);
  if(current != null)
  {
  current.g = 0;
  Binary_heap heap = new Binary_heap();

  if(g_priority == 1)
  {
    heap.Insert_LG(current);
  }
  else if(g_priority == 2)
  {heap.Insert_SG(current);}
  else
  {heap.Insert(current);}

  boolean t = this.Repeted_Forward_Astar(grid, heap, g_priority, Brain, true);

  System.out.println("SUCESS OR FAILUER:    :" + t);
  node end = grid.get_node_at(this.Gole_Row, this.Gole_Column);
  if(t == true)
  {
      GUI gui = new GUI(grid,end);
    while(end.A_star_parent != null)
    {
      System.out.print("( "+end.row+","+ end.column+" ) <--");
      end = end.A_star_parent;
    }
  }
  else
  {
    System.out.println("Path not available!");
  }
  }
  else
  {System.out.println("NULL current!!");}
}

//Forward_Astar with Estimation:
//Forward_or_backward == true --> forward A*
//Forward_or_backward == false --> backward A*
public boolean Repeted_Forward_Astar(Gridworld grid,Binary_heap heap, int g_priority, Gridworld Brain, boolean Forward_or_backward)
{
  if(heap.isEmpty() == true)
  {
    return false;
  }
  node current;
  if(g_priority == 1)
  {
    current = heap.Remove_min_LG();
  }
  else if(g_priority == 2)
  {current = heap.Remove_min_SG();}
  else
  {current = heap.Remove_min();}// remove minimum f value node

  //check if one of the neighbor cell is gole state or not:
    if(current.up != null)
    {
    if(current.up.blocked == 'G')
    {
      current.up.g = current.g+1;
      current.up.isVisited =true;
      current.up.A_star_parent = current;

      if(g_priority == 1)
      {
        heap.Insert_LG(current.up);
      }
      else if(g_priority == 2)
      {heap.Insert_SG(current.up);}
      else
      {heap.Insert(current.up);}

      return true;
    }
    if(current.up.blocked == 'X')
    {
      Brain.gridworld[current.up.index].blocked = 'X';
      Brain.Total_Blocked = Brain.Total_Blocked +1;
    }


  }

  if(current.down != null)
  {
    if(current.down.blocked == 'G')
    {
      current.down.g = current.g+1;
      current.down.isVisited =true;
      current.down.A_star_parent = current;
      if(g_priority == 1)
      {
         heap.Insert_LG(current.down);
      }
      else if(g_priority == 2)
      {heap.Insert_SG(current.down);}
      else
      {heap.Insert(current.down);}

      return true;
    }
    if(current.down.blocked == 'X')
    {
      Brain.gridworld[current.down.index].blocked = 'X';
      Brain.Total_Blocked = Brain.Total_Blocked +1;
    }

  }

  if(current.left != null)
  {
    if(current.left.blocked == 'G')
    {
      current.left.g = current.g+1;
      current.left.isVisited =true;
      current.left.A_star_parent = current;

      if(g_priority == 1)
      {
        heap.Insert_LG(current.left);
    }
      else if(g_priority == 2)
      {heap.Insert_SG(current.left);}
      else
      {heap.Insert(current.left);}

      return true;
    }
    if(current.left.blocked == 'X')
    {
      Brain.gridworld[current.left.index].blocked = 'X';
      Brain.Total_Blocked = Brain.Total_Blocked +1;

    }
  }

  if(current.right != null)
  {
    if(current.right.blocked == 'G')
    {
      current.right.g = current.g+1;
      current.right.isVisited =true;
      current.right.A_star_parent = current;

      if(g_priority == 1)
      {
         heap.Insert_LG(current.right);}
      else if(g_priority == 2)
      {heap.Insert_SG(current.right);}
      else
      {heap.Insert(current.right);}

      return true;
    }
    if(current.right.blocked == 'X')
    {
      Brain.gridworld[current.right.index].blocked = 'X';
      Brain.Total_Blocked = Brain.Total_Blocked +1;

    }
  }

  //add all the unexpanded, unvisited, and unblocked negighbors to Heap, after changing it's g value
  if(current.up != null && current.up.blocked != 'X' && current.up.isExpanded != true && current.up.isVisited != true)
  {
    current.up.g = current.g+1;
    current.up.isVisited =true;
    current.up.A_star_parent = current;
    if(g_priority == 1)
    {
      heap.Insert_LG(current.up);}
    else if(g_priority == 2)
    {heap.Insert_SG(current.up);}
    else
    {heap.Insert(current.up);}
  }
  if(current.right != null && current.right.blocked != 'X' && current.right.isExpanded != true && current.right.isVisited != true)
  {

    current.right.g = current.g+1;
    current.right.isVisited =true;
    current.right.A_star_parent = current;
    if(g_priority == 1)
    {
      heap.Insert_LG(current.right);}
    else if(g_priority == 2)
    {heap.Insert_SG(current.right);}
    else
    {heap.Insert(current.right);}

  }
  if(current.down != null && current.down.blocked != 'X' && current.down.isExpanded != true && current.down.isVisited != true)
  {

    current.down.g = current.g+1;
    current.down.isVisited =true;
    current.down.A_star_parent = current;
    if(g_priority == 1)
    {
      heap.Insert_LG(current.down);
    }
    else if(g_priority == 2)
    {heap.Insert_SG(current.down);}
    else
    {heap.Insert(current.down);}

  }
  if(current.left != null && current.left.blocked != 'X' && current.left.isExpanded != true && current.left.isVisited != true)
  {
    current.left.g = current.g+1;
    current.left.isVisited =true;
    current.left.A_star_parent = current;
    if(g_priority == 1)
    {
      heap.Insert_LG(current.left);}
    else if(g_priority == 2)
    {heap.Insert_SG(current.left);}
    else
    {heap.Insert(current.left);}
  }
  current.isExpanded = true;

 this.Estimated_Path(Brain,current.index,g_priority, Forward_or_backward);
  if(this.Repeted_Forward_Astar(grid, heap, g_priority,Brain, Forward_or_backward) == true){
  return true;}
  else
  {
    return false;
  }

}

//Estimation path generater for Forward_Astar:
public void Estimated_Path(Gridworld Brain, int index, int g_priority, boolean Forward_or_backward)
{
  //First assign Start Row and Column:
  int row = (int)(index / Brain.column);
  int col = index % (Brain.column);

  Brain.Assign_Start(row,col);
  Brain.get_node_at(row,col).blocked = 'S';

  this.Initialize_Forward_Astar(Brain,g_priority,row,col);

  if(Brain.Prev_Blocked<Brain.Total_Blocked && this.temp<5)
  {//Brain.Print_List();
  Brain.Prev_Blocked = Brain.Total_Blocked;
  this.temp = this.temp+1;

  node end = Brain.get_node_at(Brain.goleRow, Brain.goleCol);
  //Brain.gridworld[end.row*Brain.column+end.column].blocked = '*';
    end = end.A_star_parent;
    while(end.A_star_parent != null)
    {
      Brain.gridworld[end.row*Brain.column+end.column].blocked = '*';
      //System.out.print("( "+end.row+","+ end.column+" ) <--");
      end = end.A_star_parent;
    }
    try {
      if(Forward_or_backward == true)
      {
      Brain.Save_Estimated("Forward",this.temp);}
      else{ Brain.Save_Estimated("Backward",this.temp);}
    }catch (IOException e){  }

  end = Brain.get_node_at(Brain.goleRow, Brain.goleCol);
  Brain.gridworld[end.row*Brain.column+end.column].blocked = 'G';
  end = end.A_star_parent;
  while(end.A_star_parent != null)
  {
    Brain.gridworld[end.row*Brain.column+end.column].blocked = 'O';
    //System.out.print("( "+end.row+","+ end.column+" ) <--");
    end = end.A_star_parent;
  }



  }
  Brain.SetIsVisited();
  Brain.get_node_at(row,col).blocked = 'O';
}

//SR: start row
//SC: start column
public void Initialize_Forward_Astar(Gridworld grid, int g_priority, int SR, int SC)
{
  node current = grid.get_node_at(SR, SC);
  current.isVisited = true;
  if(current != null)
  {
  current.g = 0;
  Binary_heap heap = new Binary_heap();

  if(g_priority == 1)
  {heap.Insert_LG(current);}
  else if(g_priority == 2)
  {heap.Insert_SG(current);}
  else
  {heap.Insert(current);}

  boolean t = this.Forward_Astar(grid, heap, g_priority);
  //System.out.println("SUCESS OR FAILUER:    :" + t);
  //node end = grid.get_node_at(this.Gole_Row, this.Gole_Column);
  /*if(t == true)
  {
    //  GUI gui = new GUI(grid,end);
    while(end.A_star_parent != null)
    {
      System.out.print("( "+end.row+","+ end.column+" ) <--");
      end = end.A_star_parent;
    }
  }
  else
  {
    System.out.println("Path not available!");
  }*/
}
  else
  {
    System.out.println("NULL current!!");
  }
}

//Basic Function without Estimation path at each step
public boolean Forward_Astar(Gridworld grid, Binary_heap heap, int g_priority)
{
  if(heap.isEmpty() == true)
  {
    return false;
  }
  node current;
  if(g_priority == 1)
  {
    current = heap.Remove_min_LG();}
  else if(g_priority == 2)
  {current = heap.Remove_min_SG();}
  else
  {current = heap.Remove_min();}// remove minimum f value node

  //check if one of the neighbor cell is gole state or not:
    if(current.up != null)
    {
    if(current.up.blocked == 'G')
    {
      current.up.g = current.g+1;
      current.up.isVisited =true;
      current.up.A_star_parent = current;

      if(g_priority == 1)
      {  heap.Insert_LG(current.up);}
      else if(g_priority == 2)
      {heap.Insert_SG(current.up);}
      else
      {heap.Insert(current.up);}

      return true;
    }
  }

  if(current.down != null)
  {
    if(current.down.blocked == 'G')
    {
      current.down.g = current.g+1;
      current.down.isVisited =true;
      current.down.A_star_parent = current;
      if(g_priority == 1)
      {  heap.Insert_LG(current.down);}
      else if(g_priority == 2)
      {heap.Insert_SG(current.down);}
      else
      {heap.Insert(current.down);}

      return true;
    }
  }

  if(current.left != null)
  {
    if(current.left.blocked == 'G')
    {
      current.left.g = current.g+1;
      current.left.isVisited =true;
      current.left.A_star_parent = current;

      if(g_priority == 1)
      {  heap.Insert_LG(current.left);}
      else if(g_priority == 2)
      {heap.Insert_SG(current.left);}
      else
      {heap.Insert(current.left);}

      return true;
    }
  }

  if(current.right != null)
  {
    if(current.right.blocked == 'G')
    {
      current.right.g = current.g+1;
      current.right.isVisited =true;
      current.right.A_star_parent = current;

      if(g_priority == 1)
      {  heap.Insert_LG(current.right);}
      else if(g_priority == 2)
      {heap.Insert_SG(current.right);}
      else
      {heap.Insert(current.right);}

      return true;
    }
  }

  //add all the unexpanded, unvisited, and unblocked negighbors to Heap, after changing it's g value
  if(current.up != null && current.up.blocked != 'X' && current.up.isExpanded != true && current.up.isVisited != true)
  {
    current.up.g = current.g+1;
    current.up.isVisited =true;
    current.up.A_star_parent = current;
    if(g_priority == 1)
    {  heap.Insert_LG(current.up);}
    else if(g_priority == 2)
    {heap.Insert_SG(current.up);}
    else
    {heap.Insert(current.up);  }
  }
  if(current.right != null && current.right.blocked != 'X' && current.right.isExpanded != true && current.right.isVisited != true)
  {
    current.right.g = current.g+1;
    current.right.isVisited =true;
    current.right.A_star_parent = current;
    if(g_priority == 1)
    {  heap.Insert_LG(current.right);}
    else if(g_priority == 2)
    {heap.Insert_SG(current.right);}
    else
    {heap.Insert(current.right);
    }
  }
  if(current.down != null && current.down.blocked != 'X' && current.down.isExpanded != true && current.down.isVisited != true)
  {
    current.down.g = current.g+1;
    current.down.isVisited =true;
    current.down.A_star_parent = current;
    if(g_priority == 1)
    {  heap.Insert_LG(current.down);}
    else if(g_priority == 2)
    {heap.Insert_SG(current.down);}
    else
    {heap.Insert(current.down);}
  }
  if(current.left != null && current.left.blocked != 'X' && current.left.isExpanded != true && current.left.isVisited != true)
  {
    current.left.g = current.g+1;
    current.left.isVisited =true;
    current.left.A_star_parent = current;
    if(g_priority == 1)
    {  heap.Insert_LG(current.left);}
    else if(g_priority == 2)
    {heap.Insert_SG(current.left);}
    else
    {heap.Insert(current.left);}
    }

  if(this.Forward_Astar(grid, heap, g_priority) == true){
  return true;}
  else
  {
    return false;
  }
}

//Backward Repeted Astar:
//Backward Repeted Astar is similar to Repeted_Forward_Astar
public void Initialize_Repeted_Backward_Astar(Gridworld grid, int g_priority)
{
  //Create Empty gridworld to store Robot's Observations:
  Gridworld Brain = new Gridworld(grid.row, grid.column);
  Brain.Create_Grid();

//For backward A* change startRow with goleRow, and start column with gole column
grid.Assign_Start_End(this.Gole_Row,this.Gole_Column, this.Start_Row, this.Start_Column);
//Change Blocked status at start and end of grid:
grid.gridworld[grid.startRow*grid.column+grid.startCol].blocked = 'S';
grid.gridworld[grid.goleRow*grid.column+grid.goleCol].blocked = 'G';

  //Assign Goal Row and column:
  Brain.Assign_End(grid.goleRow, grid.goleCol);
  Brain.get_node_at(grid.goleRow, grid.goleCol).blocked = 'G';

  //Assign  h values to each node in grid:
  this.Manhattan_Distances(grid);
  this.Manhattan_Distances(Brain);//For Empty gridworld.

  //start by setting g value of starting node:
  node current = grid.get_node_at(this.Gole_Row, this.Gole_Column);
  if(current != null)
  {
  current.g = 0;
  Binary_heap heap = new Binary_heap();

  if(g_priority == 1)
  {heap.Insert_LG(current);}
  else if(g_priority == 2)
  {heap.Insert_SG(current);}
  else
  {heap.Insert(current);}

  boolean t = this.Repeted_Forward_Astar(grid, heap, g_priority, Brain, false);

  System.out.println("SUCESS OR FAILUER:    :" + t);
  node end = grid.get_node_at(this.Start_Row, this.Start_Column);
  if(t == true)
  {
      GUI gui = new GUI(grid,end);
    while(end.A_star_parent != null)
    {
      System.out.print("( "+end.row+","+ end.column+" ) <--");
      end = end.A_star_parent;
    }
  }
  else
  {
    System.out.println("Path not available!");
  }
  }
  else
  {System.out.println("NULL current!!");}
}


//ADAPTIVE A*:
public void Initialize_Adaptive_Astar(Gridworld grid, int g_priority)
{
  Gridworld Brain = new Gridworld(grid.row, grid.column);
  Brain.Create_Grid();

  //Assign Goal Row and column:
  Brain.Assign_End(grid.goleRow, grid.goleCol);
  Brain.get_node_at(grid.goleRow, grid.goleCol).blocked = 'G';

  //Assign  h values to each node in grid:
  this.Manhattan_Distances(grid);
  this.Manhattan_Distances(Brain);//For Empty gridworld.

  //start by setting g value of starting node:
  node current = grid.get_node_at(this.Start_Row, this.Start_Column);
  if(current != null)
  {
  current.g = 0;
  Binary_heap heap = new Binary_heap();

  if(g_priority == 1)
  {heap.Insert_LG(current);}
  else if(g_priority == 2)
  {heap.Insert_SG(current);}
  else
  {heap.Insert(current);}

  boolean t = this.Adaptive_Astar(grid, heap, g_priority, Brain);

  System.out.println("SUCESS OR FAILUER:    :" + t);
  node end = grid.get_node_at(this.Gole_Row, this.Gole_Column);
  if(t == true)
  {
      GUI gui = new GUI(grid,end);
    while(end.A_star_parent != null)
    {
      System.out.print("( "+end.row+","+ end.column+" ) <--");
      end = end.A_star_parent;
    }
  }
  else
  {
    System.out.println("Path not available!");
  }
  }
  else
  {System.out.println("NULL current!!");}
}

public boolean Adaptive_Astar(Gridworld grid,Binary_heap heap, int g_priority, Gridworld Brain)
{
  if(heap.isEmpty() == true)
  {
    return false;
  }
  node current;
  if(g_priority == 1)
  {  current = heap.Remove_min_LG();}
  else if(g_priority == 2)
  {current = heap.Remove_min_SG();}
  else
  {current = heap.Remove_min();}// remove minimum f value node

  //check if one of the neighbor cell is gole state or not:
    if(current.up != null)
    {
    if(current.up.blocked == 'G')
    {
      current.up.g = current.g+1;
      current.up.isVisited =true;
      current.up.A_star_parent = current;

      if(g_priority == 1)
      {  heap.Insert_LG(current.up);}
      else if(g_priority == 2)
      {heap.Insert_SG(current.up);}
      else
      {heap.Insert(current.up);}

      return true;
    }
    if(current.up.blocked == 'X')
    {
      Brain.gridworld[current.up.index].blocked = 'X';
      Brain.Total_Blocked = Brain.Total_Blocked +1;
    }
  }

  if(current.down != null)
  {
    if(current.down.blocked == 'G')
    {
      current.down.g = current.g+1;
      current.down.isVisited =true;
      current.down.A_star_parent = current;
      if(g_priority == 1)
      {  heap.Insert_LG(current.down);}
      else if(g_priority == 2)
      {heap.Insert_SG(current.down);}
      else
      {heap.Insert(current.down);}

      return true;
    }
    if(current.down.blocked == 'X')
    {
      Brain.gridworld[current.down.index].blocked = 'X';
      Brain.Total_Blocked = Brain.Total_Blocked +1;
    }
  }

  if(current.left != null)
  {
    if(current.left.blocked == 'G')
    {
      current.left.g = current.g+1;
      current.left.isVisited =true;
      current.left.A_star_parent = current;

      if(g_priority == 1)
      {  heap.Insert_LG(current.left);}
      else if(g_priority == 2)
      {heap.Insert_SG(current.left);}
      else
      {heap.Insert(current.left);}

      return true;
    }
    if(current.left.blocked == 'X')
    {
      Brain.gridworld[current.left.index].blocked = 'X';
      Brain.Total_Blocked = Brain.Total_Blocked +1;

    }
  }

  if(current.right != null)
  {
    if(current.right.blocked == 'G')
    {
      current.right.g = current.g+1;
      current.right.isVisited =true;
      current.right.A_star_parent = current;

      if(g_priority == 1)
      {  heap.Insert_LG(current.right);}
      else if(g_priority == 2)
      {heap.Insert_SG(current.right);}
      else
      {heap.Insert(current.right);}

      return true;
    }
    if(current.right.blocked == 'X')
    {
      Brain.gridworld[current.right.index].blocked = 'X';
      Brain.Total_Blocked = Brain.Total_Blocked +1;

    }
  }

  //add all the unexpanded, unvisited, and unblocked negighbors to Heap, after changing it's g value
  if(current.up != null && current.up.blocked != 'X' && current.up.isExpanded != true && current.up.isVisited != true)
  {
    current.up.g = current.g+1;
    current.up.isVisited =true;
    current.up.A_star_parent = current;
    if(g_priority == 1)
    {  heap.Insert_LG(current.up);}
    else if(g_priority == 2)
    {heap.Insert_SG(current.up);}
    else
    {heap.Insert(current.up);}
  }
  if(current.right != null && current.right.blocked != 'X' && current.right.isExpanded != true && current.right.isVisited != true)
  {

    current.right.g = current.g+1;
    current.right.isVisited =true;
    current.right.A_star_parent = current;
    if(g_priority == 1)
    {  heap.Insert_LG(current.right);}
    else if(g_priority == 2)
    {heap.Insert_SG(current.right);}
    else
    {heap.Insert(current.right);}
  }
  if(current.down != null && current.down.blocked != 'X' && current.down.isExpanded != true && current.down.isVisited != true)
  {

    current.down.g = current.g+1;
    current.down.isVisited =true;
    current.down.A_star_parent = current;
    if(g_priority == 1)
    {  heap.Insert_LG(current.down);}
    else if(g_priority == 2)
    {heap.Insert_SG(current.down);}
    else
    {heap.Insert(current.down);}

  }
  if(current.left != null && current.left.blocked != 'X' && current.left.isExpanded != true && current.left.isVisited != true)
  {
    current.left.g = current.g+1;
    current.left.isVisited =true;
    current.left.A_star_parent = current;
    if(g_priority == 1)
    {  heap.Insert_LG(current.left);}
    else if(g_priority == 2)
    {heap.Insert_SG(current.left);}
    else
    {heap.Insert(current.left);}
  }
  current.isExpanded = true;

 this.Estimated_Path_Adaptive_Astar(Brain,current.index,g_priority,grid);

    if(this.Adaptive_Astar(grid, heap, g_priority,Brain) == true){
      return true;}
      else
      {
        return false;
      }

}


public void Estimated_Path_Adaptive_Astar(Gridworld Brain, int index, int g_priority, Gridworld grid)
{
//First assign Start Row and Column:
int row = (int)(index / Brain.column);
int col = index % (Brain.column);

Brain.Assign_Start(row,col);
Brain.get_node_at(row,col).blocked = 'S';

ArrayList<Integer> Expanded = this.Estimated_Adaptive_Astar(Brain,g_priority,row,col);
if(Expanded != null)
{
if(Brain.Prev_Blocked<Brain.Total_Blocked && this.temp<5)
{//Brain.Print_List();
Brain.Prev_Blocked = Brain.Total_Blocked;
this.temp = this.temp+1;


node end = Brain.get_node_at(this.Gole_Row, this.Gole_Column);

//Brain.gridworld[end.row*Brain.column+end.column].blocked = '*';
  end = end.A_star_parent;
  while(end.A_star_parent != null)
  {
    Brain.gridworld[end.row*Brain.column+end.column].blocked = '*';
    //System.out.print("( "+end.row+","+ end.column+" ) <--");
    end = end.A_star_parent;
  }
  try {
    Brain.Save_Estimated("A_star",this.temp);
  }catch (IOException e){
    //System.out.println(id);
  }
    //Brain.Print_List();

  end = Brain.get_node_at(this.Gole_Row, this.Gole_Column);
Brain.gridworld[end.row*Brain.column+end.column].blocked = 'G';
end = end.A_star_parent;
while(end.A_star_parent != null)
{
  Brain.gridworld[end.row*Brain.column+end.column].blocked = 'O';
  //System.out.print("( "+end.row+","+ end.column+" ) <--");
  end = end.A_star_parent;
}
}
//Change h values of all Expanded node to new h value:
int s_goal = Brain.get_node_at(Brain.goleRow, Brain.goleCol).g;//g(s_gole)
//System.out.println("S_goal: "+s_goal);
while(Expanded.isEmpty() != true)
{
  int temp = Expanded.remove(0);
  //System.out.println("Before inital: Brain.gridworld[temp].h : "+Brain.gridworld[temp].h);
  Brain.gridworld[temp].h = s_goal - Brain.gridworld[temp].g;
  //System.out.println("s-goal - Brain.gridworld[temp]: "+ Brain.gridworld[temp].h);
  //System.out.println("Before inital: grid.gridworld[temp].h : "+grid.gridworld[temp].h);
  grid.gridworld[temp].h = s_goal - Brain.gridworld[temp].g;
  //System.out.println("s-goal - grid.gridworld[temp]: "+ grid.gridworld[temp].h);

}

}
Brain.SetIsVisited();
Brain.get_node_at(row,col).blocked = 'O';
}

public ArrayList<Integer> Estimated_Adaptive_Astar(Gridworld grid, int g_priority, int SR, int SC)
{
  node current = grid.get_node_at(SR, SC);
  current.isVisited = true;
  if(current != null)
  {
  current.g = 0;
  Binary_heap heap = new Binary_heap();

  if(g_priority == 1)
  {heap.Insert_LG(current);}
  else if(g_priority == 2)
  {heap.Insert_SG(current);}
  else
  {heap.Insert(current);}
  ArrayList<Integer> Expanded = new ArrayList<Integer>();

  boolean t = this.Estimated_Astar(grid, heap, g_priority,Expanded);
  if(t == true)
  {
    return (Expanded);
  }
  else{return null;}
}
else
{
  System.out.println("NULL current!!");
  return null;
}
}


public boolean Estimated_Astar(Gridworld grid, Binary_heap heap, int g_priority, ArrayList<Integer> Expanded)
{

//  System.out.println("Estimated_Astar");
  if(heap.isEmpty() == true)
  {
        return false;
  }
  node current;
  if(g_priority == 1)
  {  current = heap.Remove_min_LG();}
  else if(g_priority == 2)
  {current = heap.Remove_min_SG();}
  else
  {current = heap.Remove_min();}// remove minimum f value node

  //check if one of the neighbor cell is gole state or not:
    if(current.up != null)
    {
    if(current.up.blocked == 'G')
    {
      current.up.g = current.g+1;
      current.up.isVisited =true;
      current.up.A_star_parent = current;

      if(g_priority == 1)
      {  heap.Insert_LG(current.up);
        //System.out.println("Insert");
}
      else if(g_priority == 2)
      {heap.Insert_SG(current.up);}
      else
      {heap.Insert(current.up);}

      return true;
    }
  }

  if(current.down != null)
  {
    if(current.down.blocked == 'G')
    {
      current.down.g = current.g+1;
      current.down.isVisited =true;
      current.down.A_star_parent = current;
      if(g_priority == 1)
      {  heap.Insert_LG(current.down);
      //  System.out.println("Insert");
}
      else if(g_priority == 2)
      {heap.Insert_SG(current.down);}
      else
      {heap.Insert(current.down);}

      return true;
    }
  }

  if(current.left != null)
  {
    if(current.left.blocked == 'G')
    {
      current.left.g = current.g+1;
      current.left.isVisited =true;
      current.left.A_star_parent = current;

      if(g_priority == 1)
      {  heap.Insert_LG(current.left);
        //System.out.println("Insert");
}
      else if(g_priority == 2)
      {heap.Insert_SG(current.left);}
      else
      {heap.Insert(current.left);}

      return true;
    }
  }

  if(current.right != null)
  {
    if(current.right.blocked == 'G')
    {
      current.right.g = current.g+1;
      current.right.isVisited =true;
      current.right.A_star_parent = current;

      if(g_priority == 1)
      {  heap.Insert_LG(current.right);
      //  System.out.println("Insert");
}
      else if(g_priority == 2)
      {heap.Insert_SG(current.right);}
      else
      {heap.Insert(current.right);}

      return true;
    }
  }

  //add all the unexpanded, unvisited, and unblocked negighbors to Heap, after changing it's g value
  if(current.up != null && current.up.blocked != 'X' && current.up.isExpanded != true && current.up.isVisited != true)
  {
    current.up.g = current.g+1;
    current.up.isVisited =true;
    current.up.A_star_parent = current;
    if(g_priority == 1)
    {  heap.Insert_LG(current.up);
    //  System.out.println("Insert");
}
    else if(g_priority == 2)
    {heap.Insert_SG(current.up);}
    else
    {heap.Insert(current.up);  }
  }
  if(current.right != null && current.right.blocked != 'X' && current.right.isExpanded != true && current.right.isVisited != true)
  {
    current.right.g = current.g+1;
    current.right.isVisited =true;
    current.right.A_star_parent = current;
    if(g_priority == 1)
    {  heap.Insert_LG(current.right);
      //System.out.println("Insert");
}
    else if(g_priority == 2)
    {heap.Insert_SG(current.right);}
    else
    {heap.Insert(current.right);
    }
  }
  if(current.down != null && current.down.blocked != 'X' && current.down.isExpanded != true && current.down.isVisited != true)
  {
    current.down.g = current.g+1;
    current.down.isVisited =true;
    current.down.A_star_parent = current;
    if(g_priority == 1)
    {  heap.Insert_LG(current.down);
    //  System.out.println("Insert");
}
    else if(g_priority == 2)
    {heap.Insert_SG(current.down);}
    else
    {heap.Insert(current.down);}
  }
  if(current.left != null && current.left.blocked != 'X' && current.left.isExpanded != true && current.left.isVisited != true)
  {
    current.left.g = current.g+1;
    current.left.isVisited =true;
    current.left.A_star_parent = current;
    if(g_priority == 1)
    {  heap.Insert_LG(current.left);
    //  System.out.println("Insert");
}
    else if(g_priority == 2)
    {heap.Insert_SG(current.left);}
    else
    {heap.Insert(current.left);}
    }
  //Determine Estimated path after discovering neighbors of current cell:
  //Assume that the grid is only is which
  Expanded.add(current.index);
  if(this.Estimated_Astar(grid, heap, g_priority, Expanded) == true){
  return true;}
  else
  {
    return false;
  }

}
}
