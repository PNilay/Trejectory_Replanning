public class DFS_node{

public node data;
public DFS_node next;

public DFS_node()
{
  this.data = null;
  this.next = null;
}

public DFS_node(node Data)
{
  this.data = Data;
  this.next = null;
}

public DFS_node(node Data, DFS_node Next)
{
  this.data = Data;
  this.next = Next;
}
}
