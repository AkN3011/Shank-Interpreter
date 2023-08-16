import java.util.ArrayList;
// Custom linked list
public class LinkedList extends  NODE {
// head node declared
 static iFNode head = null;


    // insert node method
    public static void inserdata(BooleanCompareNode bool, ArrayList<StatementNode> statements)
    {


       iFNode node = new iFNode(bool, statements) ;



       // Inserts the data into the node
        node.setStatementNode(statements);
        node.setBoolcompare(bool);
        node.nextif = null;
   // If the head is null then it's the first node and head is equals that node
        if(head == null){
            head  = node;
        } else {
             // sets the "newnode" the head node of the list
            iFNode newnode = head;
            // This iterates until it reaches the last node with a null
            while (newnode.nextif != null){
                 // This moves on to the next node
               newnode = newnode.nextif;

            }
            // This appends the address of the new node to the previous last node
            newnode.nextif = node;


        }



    }




    @Override
    public String toString() {
        return null;
    }
}


