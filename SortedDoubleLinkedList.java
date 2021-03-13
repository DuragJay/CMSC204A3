import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SortedDoubleLinkedList <T> extends BasicDoubleLinkedList <T>{

	private Comparator comparCar;
	//create a comparator 
	public SortedDoubleLinkedList(java.util.Comparator<T> comparator) {
		// TODO Auto-generated constructor stub
		
		comparCar = comparator;
		
	}

	public void add(T data) {
		//new node to carry the data 
		InnerDoubleLink midNode = new InnerDoubleLink(data);
        InnerDoubleLink head = headReference;
        InnerDoubleLink end = null;
       //if there is nothing in the list then add to it, but we dont want too add to the entries since we added at top
        if(numOfEntries == 0) 
        {
           super.addToFront(data); 
           super.numOfEntries--;
        }
        else 
        {
        	//iterator through the the list until you find a proper spot to add the new data 
            while(head != null) 
            {
                if(comparCar.compare(data, head.getData()) > 0) 
                {
                    end = head;
                    head = head.getNextData();
                }
                else 
                {
                    break;
                }
            }
            //if the head is null then you would add to the end of the list 
            if(head == null) 
            { // add to end
                super.addToEnd(data);
                super.numOfEntries--;
               
            }
            //if the end is null then you would add to thefront
            else if(end == null) 
            { // add to front of list
                super.addToFront(data);
                super.numOfEntries--;
        
            } 
            //else you would create a new link to add the new node in the middle
            else 
            {
                midNode.setNextData(head);;
                head.setPrevData(midNode);
                midNode.setPrevData(end);
                end.setNextData(midNode); 
            }
        }
        //add the number of items at the end
        
		super.numOfEntries++;
	}
		

	//iterator instance 
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
        return super.iterator();
    }
	//add to end and add to front not supported 
	public void addToEnd(T data)
	{
		 throw new UnsupportedOperationException();
	}
	public void addToFront(T data)
	{
		 throw new UnsupportedOperationException();
	}

}
