import java.util.*;;
public class main {

    public static void main(String[] args) {
        
        Node start = new Node(null, "Start");
        Node node1 = new Node(null, "Aux1");
        Node node2 = new Node(null, "Aux2");
        Node node3 = new Node(null, "Aux3");
        Node node4 = new Node(null, "Aux4");
        Node dest = new Node(null, "Dest");

        
        start.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node1;
        
        Stack<String> output=new Stack<>();
  
        
        Node[] node = {start, node1, node2, node3, node4, dest};

        
        for (int i = 1; i < 11; i++){
        	movetower(i, node,output);
            node[5].stack.clear();
        }
    }

    
    public static void move(Node start, Node finish,Stack<String> output){
        int disk = start.stack.pop();
        finish.stack.push(disk);
        output.push("Move disk #" + disk + " from " + start.name + " to " + finish.name);
        
    }

   
    public static void movetwo(int disks, Node home,Stack<String> output){
        if (disks == 1){
            move(home,home.next,output);
            move(home.next,home.next.next,output);

   
        }
        else if (disks > 1) {
            movetwo(disks - 1, home,output);
            move(home, home.next,output);
            movetwo(disks - 1, home.next.next,output);
            move(home.next, home.next.next,output);
            movetwo(disks - 1, home,output);
        }
    }

  
    public static void movetower(int disks,  Node[] node, Stack<String> output){
        System.out.println("Playing game for " + disks + " disks:");
        for (int i = 0; i < disks; i++){
            node[0].stack.push(disks-i);
        }
        load(node,output);
        unload(node,output);
        print(output);
        
    }

   
    private static void load(Node[] node,Stack<String> output){
        while (!node[0].stack.isEmpty()){
            if(node[0].stack.size()==1){
                move(node[0],node[1],output);
                move(node[1],node[2],output);
                move(node[2],node[3],output);
                move(node[3],node[5],output);
            }
            else {
                move(node[0],node[1],output);
                move(node[1],node[2],output);
                move(node[2],node[3],output);
                movetwo(node[4].stack.size(), node[4],output);
                move(node[3],node[4],output);
     
                movetwo(node[2].stack.size(), node[2],output);
            }
        }
    }

    private static void unload(Node[] node,Stack<String> output) {
        while (node[4].stack.size() != 0) {
            movetwo(node[4].stack.size() - 1, node[4],output);
            move(node[4], node[1],output);
            movetwo(node[2].stack.size(), node[2],output);
            move(node[1],node[2],output);
            move(node[2],node[3],output);
            move(node[3],node[5],output);
            movetwo(node[2].stack.size(), node[2],output);
        }
        
    }
    private static void print(Stack<String> output) {
    	if(output.size()<=200) {
    		int temp =output.size();
    		for (int i = 0; i < temp; i++){
        		System.out.println(output.elementAt(0));
        		output.remove(0);
            }	
    	}
    	else {
    		int minus =100;
    		for (int i = 0; i < 100; i++){
    			System.out.println(output.elementAt(0));
        		output.remove(0);
            }
    		for (int i = 0; i<100; i++){
    			int pos= output.size()-minus;
    			System.out.println(output.elementAt(pos));
    			minus--;
            }
    	
    	
    	}
    }
    
}