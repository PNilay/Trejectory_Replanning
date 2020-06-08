import javax.swing.*;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame{

public Gridworld grid;
public node temp;
public GUI(Gridworld gr)
{
  this.setTitle("A* Algorithm");
  this.setSize(500,500);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setVisible(true);
  this.setResizable(true);
  Grid b = new Grid();
  this.setContentPane(b);
  this.grid = gr;
  this.temp = null;
}

public GUI(Gridworld gr, node t)
{
  this.setTitle("A* Algorithm");
  this.setSize(500,500);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setVisible(true);
  this.setResizable(true);
  Grid b = new Grid();
  this.setContentPane(b);
  this.grid = gr;
  this.temp = t;
}

public class Grid extends JPanel{
  public void paint(Graphics g)
  {
    System.out.println("Graphics!!!! CHANGED2");
  /*  for(int i =0; i<grid.row; i++)
    {
      for(int j = 0; j<grid.column; j++)
      {
        if(grid.get_node_at(i,j).blocked == 'X')
        {
          g.setColor(Color.black);
          g.fillRect((500/(grid.row-1))*i, (500/(grid.column-1))*j,(500/(grid.row-1)),(500/(grid.column-1)));
        }
        else if(grid.get_node_at(i,j).blocked == 'S')
        {
          g.setColor(Color.red);
          g.fillRect((500/(grid.row-1))*i, (500/(grid.column-1))*j,(500/(grid.row-1)),(500/(grid.column-1)));
        }
        else if(grid.get_node_at(i,j).blocked == 'G')
        {
          g.setColor(Color.green);
          g.fillRect((500/(grid.row-1))*i, (500/(grid.column-1))*j,(500/(grid.row-1)),(500/(grid.column-1)));
        }
        else
        {
          g.setColor(Color.white);
          g.fillRect((500/(grid.row-1))*i, (500/(grid.column-1))*j,(500/(grid.row-1)),(500/(grid.column-1)));
        }
        }
      }*/

      int goalRow = -1;
  	  int goalCol = -1;
  	  int startRow = -1;
  	  int startCol = -1;

      for(int i =0, c= 0; i<grid.row; i++, c++)
      {
        for(int j = 0, r = 0; j<grid.column; j++, r++)
        {
          if(grid.get_node_at(i,j).blocked == 'X')
          {
            g.setColor(Color.black);
            g.fillRect((500/(grid.row-1))*r, (500/(grid.column-1))*c,(500/(grid.row-1)),(500/(grid.column-1)));
          }
          else if(grid.get_node_at(i,j).blocked == 'S')
          {
          	startRow = i;
          	startCol = j;
            g.setColor(Color.red);
            g.fillRect((500/(grid.row-1))*r, (500/(grid.column-1))*c,(500/(grid.row-1)),(500/(grid.column-1)));
          }
          else if(grid.get_node_at(i,j).blocked == 'G')
          {
          	goalRow = i;
          	goalCol = j;
            g.setColor(Color.green);
            g.fillRect((500/(grid.row-1))*r, (500/(grid.column-1))*c,(500/(grid.row-1)),(500/(grid.column-1)));
          }
          else
          {
            g.setColor(Color.white);
            g.fillRect((500/(grid.row-1))*r, (500/(grid.column-1))*c,(500/(grid.row-1)),(500/(grid.column-1)));
          }

          }
        }
        //Print path on environment
              if(temp != null)
              {
            	  //System.out.println("Start: "+ startRow + ", " + startCol);
                  //System.out.println("Goal: "+ goalRow + ", " + goalCol);
                node end = temp;
                end = end.A_star_parent;

                  while(end.A_star_parent != null)
                  {
                    g.setColor(Color.blue);
                    g.fillRect((500/(grid.column-1))*end.column,(500/(grid.row-1))*end.row,(500/(grid.column-1)),(500/(grid.row-1)));
                    end = end.A_star_parent;
                  }
                /*
               for(int i =0; i<grid.row; i++)
                  {
                    for(int j = 0; j<grid.column; j++)
                    {
                      if(grid.get_node_at(i,j).isVisited == true && grid.get_node_at(i,j) !=temp)
                      {
                        g.setColor(Color.yellow);
                        g.fillRect((500/(grid.column-1))*end.column,(500/(grid.row-1))*end.row,(500/(grid.column-1)),(500/(grid.row-1)));
                      }
                    }
                  }*/
              }

//Print path on environment
      /*if(temp != null)
      {
        for(int i =0; i<grid.row; i++)
        {
          for(int j = 0; j<grid.column; j++)
          {
            if(grid.get_node_at(i,j).isVisited == true && grid.get_node_at(i,j) !=temp)
            {
              g.setColor(Color.yellow);
              g.fillRect((500/(grid.row-1))*i, (500/(grid.column-1))*j,(500/(grid.row-1)),(500/(grid.column-1)));
            }
          }
        }

     for(int i =0; i<grid.row; i++)
        {
          for(int j = 0; j<grid.column; j++)
          {
            if(grid.get_node_at(i,j).isExpanded == true && grid.get_node_at(i,j) !=temp)
            {
              g.setColor(Color.pink);
              g.fillRect((500/(grid.row-1))*i, (500/(grid.column-1))*j,(500/(grid.row-1)),(500/(grid.column-1)));
            }
          }
        }

        node end = temp;
        end = end.A_star_parent;

          while(end.A_star_parent != null)
          {
            g.setColor(Color.blue);
            g.fillRect((500/(grid.row-1))*end.row,(500/(grid.column-1))*end.column,(500/(grid.row-1)),(500/(grid.column-1)));
            end = end.A_star_parent;
          }
      }*/
    }
  }}
