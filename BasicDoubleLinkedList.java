import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class BasicDoubleLinkedList <T>{
	
	//node instamces for the first and last addresses of list 
	protected InnerDoubleLink headReference;
	
	protected InnerDoubleLink tailReference;
	//variable for the number of elements in the list 
	int numOfEntries = 0;

	//constructor will set the head and tail to null
		public BasicDoubleLinkedList()
		{
			headReference = null;
			
			tailReference = null;

	}
		//goes through the list and prints it out, used this for debugging 
	public void displayList()
	{
		InnerDoubleLink itearorNode = headReference;
		
		while(itearorNode != null)
		{
		
			System.out.println();
			itearorNode = itearorNode.getNextData();
			
		}
	}
	//getters and setters for head and tail
	public InnerDoubleLink getHead()
	{
		return headReference;
	}
	
	public InnerDoubleLink getTail()
	{
		return tailReference;
	}
	public void setHead (InnerDoubleLink h)
	{
		headReference = h;
	}
	public void setTail (InnerDoubleLink t)
	{
		tailReference = t;
	}
	//add element  to the end of list
	public void addToEnd(T d) {
		
		InnerDoubleLink newList = new InnerDoubleLink(d);
		// if the node list is empty then set both the tail and end to the new entry
		if(headReference == null)
		{
			headReference = newList;
			
			tailReference = newList;
		}
		else
		{
			//link the new node as the after the tail 
			newList.prevAddress = tailReference;
			// set the new tail into the new node
			tailReference.setNextData(newList);
		}
		//set the tail equal the end
		tailReference = newList;
		
		//add to the number of items on the list
		numOfEntries++;
	}
	//get number of items on the list 
	public int  getSize() {
		return numOfEntries;
	}
    // get the data of the tail 
	public T getLast() {
		
		return tailReference.getData();
		
	}

	//get the data of the head 
	public T getFirst() {
		
		return headReference.getData();
		
	}

	public void addToFront(T string) {
		
		InnerDoubleLink newList = new InnerDoubleLink(string);
		// if the node list is empty then set both the tail and end to the new entry
		if(headReference == null)
		{
			headReference = newList;
			
			tailReference = newList;
		}
		else
		{
			//put the new list element on the top
			newList.nextAddress = headReference;
			//set the head equal to the new node
			headReference.setPrevData(newList);
		
		}
		headReference = newList;
		numOfEntries++;
		
	}

	public ArrayList<T> toArrayList() {
		
		ArrayList<T> nodeArray = new ArrayList<T>();
		
		InnerDoubleLink itearorNode = headReference;
		//while we are not at the end of the list 
		while(itearorNode != null)
		{
			//add elements of list too the list then go to next 
			nodeArray.add(itearorNode.getData());
			
			itearorNode = itearorNode.getNextData();
			
		}
		// TODO Auto-generated method stub
		
		return nodeArray;
		
	}
//instance of iterator 
	public ListIterator<T> iterator() {
		
		listIteratiorInner listIter;
		
		listIter = new listIteratiorInner();
		
		return listIter;
		// TODO Auto-generated method stub
		
	}

	public void remove(T a, java.util.Comparator<T> comparatorCar) {
		//node you want to delete
		InnerDoubleLink delNode = headReference;
		
		
		//if the node you want to delete is the headference then push the head to next and set prev to null
		if(headReference.getData() == a)
		{
			headReference = headReference.getNextData();
			
			headReference.setPrevData(null);
			return;
		}
		
		delNode = headReference;
		//while we cant find the node we want to delete iterate through the list 
		while(comparatorCar.compare(a, delNode.getData()) != 0)
		{
			
			delNode = delNode.getNextData();
			
		}
		//create new links that disconnect node we want to delete
		if(delNode.prevAddress != null)
		{
			delNode.prevAddress.nextAddress = delNode.nextAddress;
		}
		if(delNode.nextAddress != null)
		{
			delNode.nextAddress.prevAddress = delNode.prevAddress;
		}
		//if the tail is the node we want too delete than then push it backwards and set the next equal too null
		if(tailReference.getData() == a)
		{
			tailReference = tailReference.getPrevData();
			
			tailReference.setNextData(null);
		}
		numOfEntries--;
		
		return;
	
	
	
	}
    //
	public T retrieveFirstElement() {
		
		java.util.Comparator<T> comparatorCar = null;
		//save the head and then remove it
		T firstEle = headReference.getData();
		remove(headReference.getData(),comparatorCar);
		//decrement
		numOfEntries--;
		return firstEle;
		// TODO Auto-generated method stub
		
	}

	public T retrieveLastElement() {
		// TODO Auto-generated method stub
		//save the tail and then remove it(couldnt use remove for some reason)
		T lastEle = tailReference.getData();
		tailReference = tailReference.getPrevData();
		
		tailReference.setNextData(null);
		numOfEntries--;
		return lastEle;
	}
	
	public class listIteratiorInner implements ListIterator<T> 
	{
		//instances of node class for pointers to next & prev adress 
		private  InnerDoubleLink nextNode;
		
		private InnerDoubleLink prevNode;
		
		//constructors sets the next pointer to the head of the list and prev to null
		private  listIteratiorInner ()
		{
			nextNode = headReference;
			
			prevNode = null;
		}


		@Override
		//if the next pointer is not empty then it would have something next
		public boolean hasNext() {
			
			return nextNode != null ;
			

			
		}
		

		@Override
		public T next() {
			T nextData = null;
			//if it does have something next
			if(hasNext())
			{
				//it will save the next in a variable
				nextData =  nextNode.getData();
				//it will then move the next foward
				prevNode = nextNode;
						
				nextNode = nextNode.getNextData();
				
				
			}
			else
			{
				throw new NoSuchElementException();
			}
			
			//return next you saved
			return nextData;
		}

		@Override
		//if the prev pointer is not empty then it would have something prev
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return prevNode != null;
		}

		@Override
		public T previous() {
			// TODO Auto-generated method stub
			T prevData = null;
			//if it has previous, 
			if(hasPrevious())
			{
				//it will save the current previous address
				prevData = prevNode.getData();
				
				//then move backwards in the list
				nextNode = prevNode;
				
				prevNode = prevNode.getPrevData();
			}	
			else
				throw new NoSuchElementException();
			
			//finally return first previous address that was saved 
			return prevData;
		}
		
		//these next methods are not supported
		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Operation Not Suppoerted");
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Operation Not Suppoerted");
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Operation Not Suppoerted");
		}

		@Override
		public void set(T e) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Operation Not Suppoerted");
		}

		@Override
		public void add(T e) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Operation Not Suppoerted");
		}
		
	}
	public class InnerDoubleLink 
	{
		//inner node class
		private T dataElement; // this generic  variable will keep the data of the node
		
		//these varaibles will point to the next and previous nodes in the list 
		protected InnerDoubleLink nextAddress;
		
		protected  InnerDoubleLink prevAddress;
		
		//constructor, sets data and sets the next and prev too null
		protected  InnerDoubleLink(T dataPortion)
		{
			this(dataPortion, null, null);
		}
		//constructor, intializes the data, the next address and prev addreess
		private InnerDoubleLink(T dataPortion, InnerDoubleLink nextNode, InnerDoubleLink prevNode)
		{
			dataElement = dataPortion;
			
			nextAddress = nextNode;
			
			prevAddress = prevNode;
			
			
		}
		
		//getters & setters, get and set the data and addresses
		public T getData()
		{
			return dataElement;
		}
		
		public void setData(T data)
		{
			dataElement = data;
		}
		
		public InnerDoubleLink getNextData()
		{
			return nextAddress;
		}
		
		public InnerDoubleLink getPrevData()
		{
			return prevAddress;
		}
		
		public void setNextData(InnerDoubleLink nextPoint)
		{
			nextAddress = nextPoint;
		}
		public void setPrevData(InnerDoubleLink prevPoint)
		{
			prevAddress = prevPoint;
		}
	}

}
