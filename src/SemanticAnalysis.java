

import java.util.ArrayList;

public class SemanticAnalysis {

// This method accepts a program node
    //The list of statements and list of variables are used to ensure that type mismatches do not occur by mistake.
    public void CheckAssignments (NODE node){
        ArrayList<VariableNode> variableNode = ((FunctionNode)node).getVariables();


    // Iterating through the list of statments and depending on the node type the same method is called
        // Alot of instance checking is used
        for(StatementNode Node : ((FunctionNode)node).getStatements() ){

            if(Node instanceof AssignmentNode){



                Assignment(Node, variableNode);

            }else if(Node instanceof iFNode){
            Assignment(((iFNode) Node), variableNode);




            } else if (Node instanceof WhileNODE) {
              Assignment((WhileNODE)Node,variableNode);


            }else if(Node instanceof ForNODE){

                Assignment((ForNODE)Node,variableNode);
            } else if (Node instanceof RepeatNODE) {
                Assignment((RepeatNODE)Node,variableNode);

            }

        }



    }

    public void Assignment(StatementNode node, ArrayList<VariableNode> variableNode ){

        String variablename;
        NODE right_side_type = null;


 if(node instanceof AssignmentNode ) {
     //This traverses the variable declaration list in search of the variable name, then extracts the variable's defined type.

     String node_left = ((AssignmentNode)node).getTarget().getName();




     for(int i = 0; i < variableNode.size(); i++){

         if( variableNode.get(i).getName().equals(node_left)){

             right_side_type = variableNode.get(i).getType();





         }

     }




     if (right_side_type instanceof IntegerNode) {



         // t initially determines the variable's type by traversing the variable declaration types and comparing if the initialized type equals the declared type.
         // else a type mismatch error will occur
         //MathOpNode is also handled in this section by verifying the type of the left and right sides of the math opnode. If they do not match, an exception is thrown.
         if (((AssignmentNode) node).getValue() instanceof IntegerNode) {


         } else if (((AssignmentNode)node).getValue() instanceof MathOpNode) {

             NODE left = ((MathOpNode) ((AssignmentNode) node).getValue()).getLeft();




             NODE right = ((MathOpNode) ((AssignmentNode) node).getValue()).getRight();



             if(left instanceof IntegerNode && right instanceof IntegerNode){

             } else if (left instanceof VariableReferenceNode || right instanceof VariableReferenceNode) {
                  Boolean typecheck = false;

                 for(int i = 0; i < variableNode.size(); i++){
                     if(variableNode.get(i).getType().equals(((AssignmentNode) node).getTarget().getName())){
                         typecheck = true;
                     }else {
                         typecheck = false;
                     }


                 }

                 if(typecheck){
                     throw new RuntimeException(  left +" "+ ((MathOpNode) ((AssignmentNode) node).getValue()).getOperator() + " "+ right + " (Datatype Mismatch!)"  );

                 }


             }

              else {
                 throw new RuntimeException(  left +" "+ ((MathOpNode) ((AssignmentNode) node).getValue()).getOperator() + " "+ right + " (Datatype Mismatch!)"  );
             }

         } else if (((AssignmentNode) node).getValue() instanceof VariableReferenceNode) {

             Boolean typecheck = false;

             for(int i = 0; i < variableNode.size(); i++){
                 if(variableNode.get(i).getType().equals(((AssignmentNode) node).getTarget().getName())){
                     typecheck = true;
                 }else {
                     typecheck = false;
                 }


             }

             if(typecheck){
                 throw new RuntimeException("Type mismatch" + ((VariableReferenceNode) ((AssignmentNode) node).getValue()).getName() + "Not an Integer type" );
             }



         } else {
             throw new RuntimeException("Type mismatch! Declared Variable type: " + right_side_type + " " + " Initialized variable type: " + ((AssignmentNode) node).getValue());
         }
         // it initially determines the variable's type by traversing through the variable declaration list  and comparing it with the initialized type and the declared type.
         // else a type mismatch error will occur
        // MathOpNode is also handled in this section by verifying the type of the left and right sides of the math opnode. If they do not match, an exception is thrown.
     } else if (right_side_type instanceof RealNode) {
         if (((AssignmentNode) node).getValue() instanceof RealNode) {

         }else if (((AssignmentNode)node).getValue() instanceof MathOpNode) {

             NODE left = ((MathOpNode) ((AssignmentNode) node).getValue()).getLeft();
             NODE right = ((MathOpNode) ((AssignmentNode) node).getValue()).getRight();

             if(left instanceof RealNode && right instanceof RealNode){

             } else if (left instanceof VariableReferenceNode || right instanceof VariableReferenceNode) {
                 Boolean typecheck = false;

                 for(int i = 0; i < variableNode.size(); i++){
                     if(variableNode.get(i).getType().equals(((AssignmentNode) node).getTarget().getName())){
                         typecheck = true;
                     }else {
                         typecheck = false;
                     }


                 }

                 if(typecheck){
                     throw new RuntimeException(  left +" "+ ((MathOpNode) ((AssignmentNode) node).getValue()).getOperator() + " "+ right + " (Datatype Mismatch!)"  );

                 }


             } else if (((AssignmentNode) node).getValue() instanceof VariableReferenceNode) {

                 Boolean typecheck = false;

                 for(int i = 0; i < variableNode.size(); i++){
                     if(variableNode.get(i).getType().equals(((AssignmentNode) node).getTarget().getName())){
                         typecheck = true;
                     }else {
                         typecheck = false;
                     }


                 }

                 if(typecheck){
                     throw new RuntimeException("Type mismatch" + ((VariableReferenceNode) ((AssignmentNode) node).getValue()).getName() + "Not an real type" );
                 }



             }

             else {
                 throw new RuntimeException(  left +" "+ ((MathOpNode) ((AssignmentNode) node).getValue()).getOperator() + " "+ right + " (Datatype Mismatch!)"  );
             }

         }





         else {
             throw new RuntimeException("Type mismatch! Declared Variable type: " + right_side_type + " " + " Initialized variable type: " + ((AssignmentNode) node).getValue());

         }

     } else if (right_side_type instanceof CharacterNode) {

         if (((AssignmentNode) node).getValue() instanceof CharacterNode) {

         }

         else if (((AssignmentNode)node).getValue() instanceof MathOpNode) {

             NODE left = ((MathOpNode) ((AssignmentNode) node).getValue()).getLeft();
             NODE right = ((MathOpNode) ((AssignmentNode) node).getValue()).getRight();

             if(left instanceof RealNode && right instanceof RealNode){

             } else if (left instanceof VariableReferenceNode || right instanceof VariableReferenceNode) {
                 Boolean typecheck = false;

                 for(int i = 0; i < variableNode.size(); i++){
                     if(variableNode.get(i).getType().equals(((AssignmentNode) node).getTarget().getName())){
                         typecheck = true;
                     }else {
                         typecheck = false;
                     }


                 }

                 if(typecheck){
                     throw new RuntimeException(  left +" "+ ((MathOpNode) ((AssignmentNode) node).getValue()).getOperator() + " "+ right + " (Datatype Mismatch!)"  );

                 }


             } else if (((AssignmentNode) node).getValue() instanceof VariableReferenceNode) {

                 Boolean typecheck = false;

                 for(int i = 0; i < variableNode.size(); i++){
                     if(variableNode.get(i).getType().equals(((AssignmentNode) node).getTarget().getName())){
                         typecheck = true;
                     }else {
                         typecheck = false;
                     }


                 }

                 if(typecheck){
                     throw new RuntimeException("Type mismatch" + ((VariableReferenceNode) ((AssignmentNode) node).getValue()).getName() + "Not an character type" );
                 }



             }

             else {
                 throw new RuntimeException(  left +" "+ ((MathOpNode) ((AssignmentNode) node).getValue()).getOperator() + " "+ right + " (Datatype Mismatch!)"  );
             }

         }




         else {
             throw new RuntimeException("Type mismatch! Declared Variable type: " + right_side_type + " " + " Initialized variable type: " + ((AssignmentNode) node).getValue());
         }
         // It first identifies the type of the variable by traversing the variable declaration types and comparing whether the initialized type equals the declared type.
         // else a type mismatch error will occur

     } else if (right_side_type instanceof StringNode) {
         if (((AssignmentNode) node).getValue() instanceof StringNode) {

         } else if (((AssignmentNode) node).getValue() instanceof VariableReferenceNode) {

             Boolean typecheck = false;

             for(int i = 0; i < variableNode.size(); i++){
                 if(variableNode.get(i).getType().equals(((AssignmentNode) node).getTarget().getName())){
                     typecheck = true;
                 }else {
                     typecheck = false;
                 }


             }

             if(typecheck){
                 throw new RuntimeException("Type mismatch" + ((VariableReferenceNode) ((AssignmentNode) node).getValue()).getName() + "Not a string type" );
             }



         }




         else {
             throw new RuntimeException("Type mismatch! Declared Variable type: " + right_side_type + " " + " Initialized variable type: " + ((AssignmentNode) node).getValue());
         }
         // t initially determines the variable's type by traversing the variable declaration types and comparing if the initialized type equals the declared type.
         // else a type mismatch error will occur

     } else if (right_side_type instanceof BooleanNode) {
         if (((AssignmentNode) node).getValue() instanceof BooleanNode) {

         } else {
             throw new RuntimeException("Type mismatch! Declared Variable type: " + right_side_type + " " + " Initialized variable type: " + ((AssignmentNode) node).getValue());
         }
     }


 } else if (node instanceof ForNODE) {
     // ForNode has a list of statement nodes
    // Recursion calls the method again when an assignment node is encountered inside the bloc
     for (StatementNode Node: ((ForNODE)node).getStatementNode()){


      Assignment(Node, variableNode);

     }

 } else if (node instanceof WhileNODE) {
     // while node has a list of statement nodes
     // While iterating through the statement nodes, it is checking for assignment nodes
     // Recursion is used to deal with assignment nodes

     for (StatementNode Node: ((WhileNODE)node).getStatementNode()){


         Assignment(Node, variableNode);


     }

 } else if (node instanceof iFNode) {
     // Because IF nodes are connected, they must be iterated one at a time, extracting statements from each if node until it hits null.

     while (LinkedList.head != null){

         for(StatementNode Node: LinkedList.head.getStatementNode()){
             if(Node instanceof AssignmentNode){

                 Assignment(Node,variableNode);
             }
         }
         
         // After recursion, it moves on to the next node

        LinkedList.head = LinkedList.head.nextif;




     }

 } else if (node instanceof RepeatNODE) {
     // same as for node
     // Recursion is used
     for (StatementNode Node: ((RepeatNODE)node).getStatementNode()){


         Assignment(Node, variableNode);

     }

 }


    }


}
