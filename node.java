public class node{

public char blocked;
public boolean visitState;

public node parent;
public node left;
public node right;
public node up;
public node down;
public node A_star_parent;
public int index;
public int row;
public int column;
public int g;
public int h;
public boolean isExpanded;
public boolean isVisited;
public boolean dfsVisit;

//constructor, which assign blocked as 0 and adreess of negighbors as null
public node()
{
  this.blocked = 'O';
  this.visitState = false;
  this.left = null;
  this.right = null;
  this.up = null;
  this.down = null;
  this.parent = null;
  this.A_star_parent = null;
  this.isExpanded = false;
  int g =0;
  int h =0;
}

}
