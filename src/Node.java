import java.util.Stack;

public class Node {

	Stack<Integer> stack;
	Node next;
	String name;
	
	Node(Node next, String name){
		this.next= next;
		this.name = name;
		this.stack = new Stack<>();
	}
	
}
