package Diablo;

public class Node {
	
	int x;
	int y;
	double distance;
	
	Node head;
	Node tail;
	
	public Node(final int x, final int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Node(final int x, final int y, double distance)
	{
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
	
	public void addTail(Node newNode)
	{
		tail = newNode;
	}
	
	public void addNode(final int x, final int y)
	{
		tail = new Node( x, y);
		tail.head = this;
	}
	
	public Node nextNode()
	{
		return tail;
	}
	
	public Node previousNode()
	{
		return head;
	}
	
	public Node lastNode()
	{
		
		Node temp = this;
		while(temp != null)
		{
			
			if(temp.nextNode() == null)
			{
				break;
			}
			else
			{
				temp = temp.nextNode();
			}
		}
		return temp;
	}
	
	public void replaceXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
