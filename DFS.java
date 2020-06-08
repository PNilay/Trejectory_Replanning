import java.util.Random;

// 'false' = unvisited
// 'true' = visited

//'O' =unblocked
//'X' = blocked
//'S' = starting
//'G' = ending

public class DFS{

//Using Depth first search generate maze/corridor like structure.
  //Create stack using dfs node, where we add in front and also remove element from front:

  public DFS_node head; //Head of the DFS class
  public int Total_Visited =0;//To keep track of how many cells are visited how many more to visit
//  public int row; //Total number of rows in grid
  //public int column;//Total numbers of columns in  grid

  public DFS()
  {
    this.head = null;
  }
  public void Set_DFS(int row, int column)
  {
    this.head = null;
    //grid.row = row;
    //grid.column = column;
  }

public void Print_Visit_List(Gridworld grid)
{
      System.out.println("total visited: "+this.Total_Visited);
  for (int r = 0; r < grid.row; r++) {
      for (int c = 0; c < grid.column; c++) {
    	  System.out.print(grid.get_node_at(r,c).visitState);
       }
      System.out.println("\n");
  }
 }

//Implementing stack to keep track of parent  node:
  public boolean isEmpty()// check if stack is empty or not
  {
    if(this.head == null)
    { return true;}
    else
    {return false;}
  }

  public void Prepend(node data)//add new node  in front of the list
  {
    if(this.isEmpty() == true)
    {
      this.head = new DFS_node(data);
    }
    else
    {
      DFS_node newhead = new DFS_node(data, this.head);
      this.head = newhead;
    }
  }

  public DFS_node Deque_Front()//remove last added item from stack
  {
    if(this.isEmpty() == true)
    {
      return null;
    }
    else
    {
      DFS_node newhead = head.next;
      this.head = newhead;
      return this.head;
    }
  }

  public void Iterate() //Iterte through whole stack
  {
    DFS_node cur = this.head;
    while(cur != null)
    {
      System.out.println("cur.data.blocked : "+cur.data.blocked);
      cur = cur.next;
    }
  }
  public DFS_node Get_unvisitedNode(Gridworld grid) // Return unvisited DFS_node
  {
    int index = -1;
    for(int i =0; i<grid.row*grid.column; i++)
    {
      if(grid.gridworld[i].visitState == false)
      {
        index = i;
        break;
      }
    }
    DFS_node newnode =new DFS_node(grid.gridworld[index]);
    return newnode;
  }


  public int Get_Index(int row, int column, Gridworld grid) //Given row  and column returns index
  {
    return row*grid.column + column;
  }

  public void createStartingPoints(Gridworld grid)
  {
    this.Initialize_DFS(grid);
    this.createObstacles(grid);
    System.out.println("Create obstacles done");
    //System.out.println("done making grid");
  }
  public void Initialize_DFS(Gridworld grid) // This function randomlly set starting position and gole position
  {
    //Randomaly select one cell as starting point and mark it as visited and unblocked
      this.Set_DFS(grid.row, grid.column); // constructor for DFS

      //choose random node for start
  		//Random rand = new Random();

  		grid.startRow = (int) (Math.random() * (grid.row));
  		grid.startCol = (int) (Math.random() * (grid.column));

  		System.out.println("Start Coordinates:");
  		System.out.print(grid.startRow + "\t");
  		System.out.println(grid.startCol);

  		//choose random node for goal
  		grid.goleRow = (int) (Math.random() * (grid.row));
  		grid.goleCol = (int) (Math.random() * (grid.column));

  		System.out.println("Goal Coordinates:");
  		System.out.print(grid.goleRow + "\t");
  		System.out.println(grid.goleCol);

  	//	p1.get_node_at(startRow, startCol).blocked = 'S'; 	//starting node
      DFS_node StartNode = new DFS_node(grid.get_node_at(grid.startRow,grid.startCol));
      this.head = StartNode;
      this.head.data.blocked = 'S';//start node
      this.head.data.visitState = true;
      this.Total_Visited = this.Total_Visited +1;

  		//p1.get_node_at(goleRow, goleCol).blocked = 'G'; 	//goal node
      grid.get_node_at(grid.goleRow,grid.goleCol).blocked = 'G';
      grid.get_node_at(grid.goleRow,grid.goleCol).visitState = true;
      this.Total_Visited = this.Total_Visited +1;

  }
  public void createObstacles(Gridworld grid)
  {
//Check which neighbors are unvisited and unblocked
      int possibilities =0;
      String binary = "";

      //Decide if next block shoulde be blocked or unblocked with 30% and 70% probability:
      double decide = Math.random(); // if decide < 0.3 then next block should be blocked otherwise unblocked
      //first check how many negighbors does head cell have, and how many of them is unvisited

      if(this.isEmpty() != true) // Iif head is not null
      {
        if(this.head.data.up != null)
        {
          if(this.head.data.up.visitState == false)
          {
            binary = binary + "1";
            possibilities = possibilities+1;
          }
          else
          {
            binary = binary +"0";
          }

        }
        else{binary = binary +"0";}

        if(this.head.data.right != null)
        {
          if(this.head.data.right.visitState == false)
          {
            binary = binary +"1";
            possibilities = possibilities+1;
          }
          else
          {
            binary = binary +"0";
          }
        }else{binary = binary +"0";}

      if(this.head.data.down != null)
      {
        if(this.head.data.down.visitState == false)
        {
          binary = binary +"1";
          possibilities = possibilities+1;
        }
        else
        {
          binary = binary +"0";
        }
      }else{binary = binary +"0";}

      if(this.head.data.left != null)
      {
        if(this.head.data.left.visitState == false)
        {
          binary = binary +"1";
          possibilities = possibilities+1;
        }
        else
        {
          binary = binary +"0";
        }
      }else{binary = binary +"0";}
    }
    else
    {
      binary = "0000";
      possibilities =0;
    }

      switch(possibilities)
      {
        case 0://If head cell has no unvisited negighbors, then back track to parent node, if there are still unvisted nodes
            if(this.Total_Visited == grid.row*grid.column) // this is last node
            {
              if(decide < 0.3)
              {
              this.head.data.blocked = 'X';
              this.Deque_Front();
              }
              else
              {
                this.head.data.blocked = 'O';
                this.Deque_Front();
              }
            }
            else if(this.Total_Visited < grid.row*grid.column && this.isEmpty() == true)//if stack is empty and there are still unvisited
            //nodes then look for new unvisted node and set it as head node.
            {
              this.head = this.Get_unvisitedNode(grid);
              this.head.data.visitState = true;
              this.Total_Visited = this.Total_Visited +1;
              this.createObstacles(grid);
            }
            else if(this.Total_Visited < grid.row*grid.column && this.isEmpty() == false)//Back track to parent node
            {
                this.Deque_Front();
                this.createObstacles(grid);
            }

            break;
        case 1:// if head cell has only one neighbor
            if(binary.equals("1000") == true) // it has only up neighbor
            {
              if(decide < 0.3)
              {
                this.head.data.up.blocked = 'X';// assign 1 to set cell as blocked cell
                this.head.data.up.visitState = true;
                this.Total_Visited = this.Total_Visited +1;
                this.createObstacles(grid);
              }
              else
              {
                this.Prepend(this.head.data.up);
                this.head.data.visitState = true;
                this.Total_Visited = this.Total_Visited +1;
                this.createObstacles(grid);
              }
            }

            else if(binary.equals("0100") == true) // it has only right neighbor
            {
              if(decide < 0.3)
              {
                this.head.data.right.blocked = 'X';// assign 1 to set cell as blocked cell
                this.head.data.right.visitState = true;
                this.Total_Visited = this.Total_Visited +1;
                this.createObstacles(grid);
              }
              else
              {
                this.Prepend(this.head.data.right);
                this.head.data.visitState = true;
                this.Total_Visited = this.Total_Visited +1;
               this.createObstacles(grid);
              }
            }
            else if(binary.equals("0010") == true) // it has only down neighbor
            {
              if(decide < 0.3)
              {
                this.head.data.down.blocked = 'X';// assign 1 to set cell as blocked cell
                this.head.data.down.visitState = true;
                this.Total_Visited = this.Total_Visited +1;

                this.createObstacles(grid);
              }
              else
              {
                this.Prepend(this.head.data.down);
                this.head.data.visitState = true;
                this.Total_Visited = this.Total_Visited +1;

                this.createObstacles(grid);
              }
            }
            else // it has only left neighbor
            {
              if(decide < 0.3)
              {
                this.head.data.left.blocked = 'X';// assign 1 to set cell as blocked cell
                this.head.data.left.visitState = true;
                this.Total_Visited = this.Total_Visited +1;

                this.createObstacles(grid);
              }
              else
              {
                this.Prepend(this.head.data.left);
                this.head.data.visitState = true;
                this.Total_Visited = this.Total_Visited +1;

                this.createObstacles(grid);
              }
            }

            break;
        case 2:// if head cell has two neighbors, now randomlly choose which node to visit next
            double random_no = Math.random(); // if up_or_right<0.5 --> up else right
            if(binary.equals("1100") == true) // it has up and right neighbors
            {
              if(decide<0.3)
              {
                if(random_no<0.5)
                {
                  this.head.data.up.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.up.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.head.data.right.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.right.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
              else
              {
                if(random_no<0.5)
                {
                  this.Prepend(this.head.data.up);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.Prepend(this.head.data.right);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
            }

            else if(binary.equals("0110") == true) // it has up and right neighbors
            {
              if(decide<0.3)
              {
                if(random_no<0.5)
                {
                  this.head.data.right.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.right.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.head.data.down.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.down.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
              else
              {
                if(random_no<0.5)
                {
                  this.Prepend(this.head.data.right);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.Prepend(this.head.data.down);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
            }

            else if(binary.equals("0011") == true) // it has up and right neighbors
            {
              if(decide<0.3)
              {
                if(random_no<0.5)
                {
                  this.head.data.down.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.down.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.head.data.left.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.left.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
              else
              {
                if(random_no<0.5)
                {
                  this.Prepend(this.head.data.down);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.Prepend(this.head.data.left);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
            }

            else if(binary.equals("1001") == true) // it has up and right neighbors
            {
              if(decide<0.3)
              {
                if(random_no<0.5)
                {
                  this.head.data.up.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.up.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.head.data.left.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.left.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
              else
              {
                if(random_no<0.5)
                {
                  this.Prepend(this.head.data.up);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.Prepend(this.head.data.left);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
            }

            else if(binary.equals("1010") == true) // it has up and right neighbors
            {
              if(decide<0.3)
              {
                if(random_no<0.5)
                {
                  this.head.data.up.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.up.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.head.data.down.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.down.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
              else
              {
                if(random_no<0.5)
                {
                  this.Prepend(this.head.data.up);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.Prepend(this.head.data.down);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
            }

            else //0101
            {
              if(decide<0.3)
              {
                if(random_no<0.5)
                {
                  this.head.data.right.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.right.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.head.data.left.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.left.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
              else
              {
                if(random_no<0.5)
                {
                  this.Prepend(this.head.data.right);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.Prepend(this.head.data.right);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
            }
            break;
        case 3:// if head has three neighbors
            double three_random = Math.random();
            if(binary.equals("1110") == true)
            {
              if(decide<0.3)
              {
                if(three_random<0.33)
                {
                  this.head.data.up.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.up.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else if(three_random >0.33 && three_random <0.66)
                {
                  this.head.data.right.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.right.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.head.data.down.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.down.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
              else
              {
                if(three_random<0.33)
                {
                  this.Prepend(this.head.data.up);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else if(three_random >0.33 & three_random< 0.66)
                {
                  this.Prepend(this.head.data.right);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.Prepend(this.head.data.down);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
            }

            else if(binary.equals("0111") == true)
            {
              if(decide<0.3)
              {
                if(three_random<0.33)
                {
                  this.head.data.right.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.right.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else if(three_random >0.33 && three_random <0.66)
                {
                  this.head.data.down.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.down.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.head.data.left.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.left.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
              else
              {
                if(three_random<0.33)
                {
                  this.Prepend(this.head.data.right);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else if(three_random >0.33 & three_random< 0.66)
                {
                  this.Prepend(this.head.data.down);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.Prepend(this.head.data.left);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
            }

            else if(binary.equals("1011") == true)
            {
              if(decide<0.3)
              {
                if(three_random<0.33)
                {
                  this.head.data.up.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.up.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else if(three_random >0.33 && three_random <0.66)
                {
                  this.head.data.left.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.left.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.head.data.down.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.down.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                this.createObstacles(grid);
                }
              }
              else
              {
                if(three_random<0.33)
                {
                  this.Prepend(this.head.data.up);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else if(three_random >0.33 & three_random< 0.66)
                {
                  this.Prepend(this.head.data.left);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                 this.createObstacles(grid);
                }
                else
                {
                  this.Prepend(this.head.data.down);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
            }

            else // 1101
            {
              if(decide<0.3)
              {
                if(three_random<0.33)
                {
                  this.head.data.up.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.up.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else if(three_random >0.33 && three_random <0.66)
                {
                  this.head.data.right.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.right.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.head.data.left.blocked = 'X';// assign 1 to set cell as blocked cell
                  this.head.data.left.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
              else
              {
                if(three_random<0.33)
                {
                  this.Prepend(this.head.data.up);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else if(three_random >0.33 & three_random< 0.66)
                {
                  this.Prepend(this.head.data.right);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
                else
                {
                  this.Prepend(this.head.data.left);
                  this.head.data.visitState = true;
                  this.Total_Visited = this.Total_Visited +1;

                  this.createObstacles(grid);
                }
              }
            }
            break;
        case 4://if head has all four neighbors unvisited (only possible at starting)
            double four_random = Math.random();
            if(decide<0.3)
            {
              if(four_random<0.25)
              {
                this.head.data.up.blocked = 'X';// assign 1 to set cell as blocked cell
                this.head.data.up.visitState = true;
                this.Total_Visited = this.Total_Visited +1;
                this.createObstacles(grid);
              }
              else if(four_random >0.25 && four_random <0.50)
              {
                this.head.data.right.blocked = 'X';// assign 1 to set cell as blocked cell
                this.head.data.right.visitState = true;
                this.Total_Visited = this.Total_Visited +1;
                this.createObstacles(grid);
              }
              else if(four_random >0.50 && four_random<0.75)
              {
                this.head.data.down.blocked = 'X';// assign 1 to set cell as blocked cell
                this.head.data.down.visitState = true;
                this.Total_Visited = this.Total_Visited +1;
                this.createObstacles(grid);
              }
              else
              {
                this.head.data.left.blocked = 'X';// assign 1 to set cell as blocked cell
                this.head.data.left.visitState = true;
                this.Total_Visited = this.Total_Visited+1;
                this.createObstacles(grid);
              }
            }
            else
            {
              if(four_random<0.25)
              {
                this.Prepend(this.head.data.up);
                this.head.data.visitState = true;
                this.Total_Visited = this.Total_Visited +1;
                this.createObstacles(grid);
              }
              else if(four_random >0.25 & four_random< 0.50)
              {
                this.Prepend(this.head.data.right);
                this.head.data.visitState = true;
                this.Total_Visited =this.Total_Visited +1;
                this.createObstacles(grid);
              }
              else if(four_random>0.50 && four_random<0.75)
              {
                this.Prepend(this.head.data.down);
                this.head.data.visitState = true;
                this.Total_Visited = this.Total_Visited +1;
                this.createObstacles(grid);
              }
              else
              {
                this.Prepend(this.head.data.left);
                this.head.data.visitState = true;
                this.Total_Visited = this.Total_Visited+1;
                this.createObstacles(grid);
              }
            }
            break;
        default:
            System.out.println("DEFAULT: =================================");
            break;
      }

    }

}
