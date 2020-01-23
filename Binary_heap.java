public class Binary_heap{
//Binary heap to implement openlist:
public node HeapArray[];
public int last_index;

public Binary_heap()
{
  this.HeapArray = new node[20];
  this.last_index = -1;
}

public void Double_Heap()//Increases the size of Heap
{
   node temp[] = new node[2*(this.HeapArray).length];
  for (int i = 0; i < (this.HeapArray).length; i++){
     temp[i] = this.HeapArray[i];
  }
  this.HeapArray = temp;
}

public boolean isEmpty()
{
  if(this.last_index == -1){return true;}
  else{return false;}
}
public void Print_HeapArray()
{
  for(int i =0;i<this.last_index+1;i++)
  {
    System.out.println((this.HeapArray[i].g)+(this.HeapArray[i].h));
  }
}

//*****************************************************************************
//Randomally gives priority to g value if both f are equal:

public void Swap(int a, int b)// Swap node at index a with node at index b
{
  node temp = this.HeapArray[a];
  this.HeapArray[a] = this.HeapArray[b];
  this.HeapArray[b] = temp;
}



public void Swim(int a)
{
  while(a >= 1 && (this.HeapArray[(a-1)/2].g)+(this.HeapArray[(a-1)/2].h) > this.HeapArray[a].g + this.HeapArray[a].h)
{
    this.Swap(a,(a-1)/2);
    a = (a-1)/2;// update a for next iteration
}
}

public void Insert(node data)
{
    if(this.last_index >= -1 && this.last_index < (this.HeapArray).length-1) // Check if there is a space in the array or not
    {
        this.HeapArray[++this.last_index] = data;
        this.Swim(this.last_index);// if value entered is smaller than parent's value, then swim it up unit parent's value is smaller than child.
    }
    else // increase the size of the array
    {
        this.Double_Heap();
        this.Insert(data);
    }
}

public void Sink(int k)
{
    int index = k+1; // incrementing k so the first index 0 --> 1, 1-->2, 2-->3 ...
    while(((2*index - 1)<= this.last_index)) // while(there are no children to check)
    {   int j = (2*index - 1);
        if(j<this.last_index && (this.HeapArray[j].g)+(this.HeapArray[j].h)>(this.HeapArray[j+1].g)+(this.HeapArray[j+1].h))
        {
            j++;
        }
        if((this.HeapArray[index-1].g)+(this.HeapArray[index-1].h)<= (this.HeapArray[j].h)+(this.HeapArray[j].g))
        {
            break;
        }
        this.Swap(index -1, j);
        index =j;
    }
}

public node Remove_min()
{
  
  //first check if there is element in heap array, if not then return null:
  if(this.last_index == -1)
  {
    return null;
  }
    node Min = this.HeapArray[0]; // first element is the lowest
    this.HeapArray[0] = this.HeapArray[this.last_index];
    this.last_index =this.last_index-1;
    this.Sink(0);
    return Min;
}


//******************************************************************************************************
//Heap Metohds, that priortize larger g values if their f values are equal:
public void Swim_larger_g(int a)
{

  while(a >= 1 && (this.HeapArray[(a-1)/2].g)+(this.HeapArray[(a-1)/2].h) > this.HeapArray[a].g + this.HeapArray[a].h)
{
    this.Swap(a,(a-1)/2);
    a = (a-1)/2;// update a for next iteration
}
if(a>=1)
{
  if((this.HeapArray[(a-1)/2].g)+(this.HeapArray[(a-1)/2].h) == (this.HeapArray[a].g + this.HeapArray[a].h))
  {
    if(this.HeapArray[(a-1)/2].g < this.HeapArray[a].g)
    {
      this.Swap(a,(a-1)/2);
    }
  }
}
}

public void Insert_LG(node data)// Gives more priority to larger g value
{

    if(this.last_index >= -1 && this.last_index < (this.HeapArray).length-1) // Check if there is a space in the array or not
    {
        this.HeapArray[++this.last_index] = data;
        this.Swim_larger_g(this.last_index);// if value entered is smaller than parent's value, then swim it up unit parent's value is smaller than child.
    }
    else // increase the size of the array
    {
        this.Double_Heap();
        this.Insert_LG(data);
    }
}

public void Sink_LG(int k)//Gives more priority to larger g vaues when both f are equal
{
    int index = k+1; // incrementing k so the first index 0 --> 1, 1-->2, 2-->3 ...
    while(((2*index - 1)<= this.last_index)) // while(there are no children to check)
    {   int j = (2*index - 1);


      if(j<this.last_index && (this.HeapArray[j].g)+(this.HeapArray[j].h)!=(this.HeapArray[j+1].g)+(this.HeapArray[j+1].h))
      {
      //  System.out.println("NOT EQUAl");
          if(j< this.last_index && (this.HeapArray[j].g)+(this.HeapArray[j].h)>(this.HeapArray[j+1].g)+(this.HeapArray[j+1].h))
          {
            //j is not the last  index and current f is greater then next f (therefore incrementing j value)
              j++;
          }

          if(j<= this.last_index &&(this.HeapArray[index-1].g)+(this.HeapArray[index-1].h) == (this.HeapArray[j].h)+(this.HeapArray[j].g))
          {
            if((this.HeapArray[index-1].g) >(this.HeapArray[j].g))
            {
              break;
            }
          }
          if(j<= this.last_index && (this.HeapArray[index-1].g)+(this.HeapArray[index-1].h) < (this.HeapArray[j].h)+(this.HeapArray[j].g))
          {
              break;
          }
          this.Swap(index -1, j);
          index =j+1;
      }
    else{
      //System.out.println("EQUAL");
     if(j<this.last_index && (this.HeapArray[j].g)<(this.HeapArray[j+1].g))
      {
        j++;
      }

      //Now compare j and index-1
      if(j<= this.last_index &&(this.HeapArray[index-1].g)+(this.HeapArray[index-1].h) == (this.HeapArray[j].h)+(this.HeapArray[j].g))
      {
        if((this.HeapArray[index-1].g) >(this.HeapArray[j].g))
        {
          break;
        }
      }

      if(j<= this.last_index && (this.HeapArray[index-1].g)+(this.HeapArray[index-1].h) < (this.HeapArray[j].h)+(this.HeapArray[j].g))
      {
          break;
      }

      this.Swap(index -1, j);
      index =j+1;

    }
  }
}
public node Remove_min_LG()//larger g priority
{
  //first check if there is element in heap array, if not then return null:
  if(this.last_index == -1)
  {
    return null;
  }
    node Min = this.HeapArray[0]; // first element is the lowest
    this.HeapArray[0] = this.HeapArray[this.last_index];
    this.last_index =this.last_index-1;
    this.Sink_LG(0);
    return Min;
}

//******************************************************************************************************
//Heap Metohds, that priortize smaller g values if their f values are equal:
public void Swim_smaller_g(int a)
{
  while(a >= 1 && (this.HeapArray[(a-1)/2].g)+(this.HeapArray[(a-1)/2].h) > this.HeapArray[a].g + this.HeapArray[a].h)
{
    this.Swap(a,(a-1)/2);
    a = (a-1)/2;// update a for next iteration
}
if(a>=1)
{
  if((this.HeapArray[(a-1)/2].g)+(this.HeapArray[(a-1)/2].h) == this.HeapArray[a].g + this.HeapArray[a].h)
  {
    if(this.HeapArray[(a-1)/2].g > this.HeapArray[a].g)
    {
      this.Swap(a,(a-1)/2);
    }
  }
}

}

public void Insert_SG(node data)// Gives more priority to smaller g value
{
    if(this.last_index >= -1 && this.last_index < (this.HeapArray).length-1) // Check if there is a space in the array or not
    {
        this.HeapArray[++this.last_index] = data;
        this.Swim_smaller_g(this.last_index);// if value entered is smaller than parent's value, then swim it up unit parent's value is smaller than child.
    }
    else // increase the size of the array
    {
        this.Double_Heap();
        this.Insert_SG(data);
    }

}

public void Sink_SG(int k)//Gives more priority to smaller g vaues when both f are equal
{
  /*int index = k+1; // incrementing k so the first index 0 --> 1, 1-->2, 2-->3 ...
  while(((2*index - 1)<= this.last_index)) // while(there are no children to check)
  {   int j = (2*index - 1);
      if(j<this.last_index && (this.HeapArray[j].g)+(this.HeapArray[j].h)>(this.HeapArray[j+1].g)+(this.HeapArray[j+1].h))
      {
          j++;
      }
      if((this.HeapArray[index-1].g)+(this.HeapArray[index-1].h)<= (this.HeapArray[j].h)+(this.HeapArray[j].g))
      {
          break;
      }
      this.Swap(index -1, j);
      index =j;
  }


    int index = k+1; // incrementing k so the first index 0 --> 1, 1-->2, 2-->3 ...
    while(((2*index - 1)<= this.last_index)) // while(there are no children to check)
    {   int j = (2*index - 1);
        if(j<this.last_index && (this.HeapArray[j].g)+(this.HeapArray[j].h)>(this.HeapArray[j+1].g)+(this.HeapArray[j+1].h))
        {
            j++;
        }
        if((this.HeapArray[index-1].g)+(this.HeapArray[index-1].h) == (this.HeapArray[j].h)+(this.HeapArray[j].g))
        {
          if((this.HeapArray[index-1].g) <(this.HeapArray[j].g))
          {
            break;
          }
        }
        if((this.HeapArray[index-1].g)+(this.HeapArray[index-1].h) < (this.HeapArray[j].h)+(this.HeapArray[j].g))
        {
            break;
        }
        this.Swap(index -1, j);
        index =j;
    }

    */



    int index = k+1; // incrementing k so the first index 0 --> 1, 1-->2, 2-->3 ...
    while(((2*index - 1)<= this.last_index)) // while(there are no children to check)
    {   int j = (2*index - 1);


      if(j<this.last_index && (this.HeapArray[j].g)+(this.HeapArray[j].h)!=(this.HeapArray[j+1].g)+(this.HeapArray[j+1].h))
      {
      //  System.out.println("NOT EQUAl");
          if(j< this.last_index && (this.HeapArray[j].g)+(this.HeapArray[j].h)>(this.HeapArray[j+1].g)+(this.HeapArray[j+1].h))
          {
            //j is not the last  index and current f is greater then next f (therefore incrementing j value)
              j++;
          }

          if(j<= this.last_index &&(this.HeapArray[index-1].g)+(this.HeapArray[index-1].h) == (this.HeapArray[j].h)+(this.HeapArray[j].g))
          {
            if((this.HeapArray[index-1].g) <(this.HeapArray[j].g))
            {
              break;
            }
          }
          if(j<= this.last_index && (this.HeapArray[index-1].g)+(this.HeapArray[index-1].h) < (this.HeapArray[j].h)+(this.HeapArray[j].g))
          {
              break;
          }
          this.Swap(index -1, j);
          index =j+1;
      }
    else{
      //System.out.println("EQUAL");
     if(j<this.last_index && (this.HeapArray[j].g)>(this.HeapArray[j+1].g))
      {
        j++;
      }

      //Now compare j and index-1
      if(j<= this.last_index &&(this.HeapArray[index-1].g)+(this.HeapArray[index-1].h) == (this.HeapArray[j].h)+(this.HeapArray[j].g))
      {
        if((this.HeapArray[index-1].g) <(this.HeapArray[j].g))
        {
          break;
        }
      }

      if(j<= this.last_index && (this.HeapArray[index-1].g)+(this.HeapArray[index-1].h) < (this.HeapArray[j].h)+(this.HeapArray[j].g))
      {
          break;
      }

      this.Swap(index -1, j);
      index =j+1;

    }
  }


}
public node Remove_min_SG()//larger g priority
{
  //first check if there is element in heap array, if not then return null:
  if(this.last_index == -1)
  {
    return null;
  }
    node Min = this.HeapArray[0]; // first element is the lowest
    this.HeapArray[0] = this.HeapArray[this.last_index];
    this.last_index =this.last_index-1;
    this.Sink_SG(0);
    return Min;
}

}
