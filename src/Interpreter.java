import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;



public class Interpreter {

    static HashMap<String, InterpreterDataType> local_Variables = new HashMap<>();
    static InterpreterDataType idt;



    // Interpret function
    public void interpretFunction(FunctionNode function, ArrayList<InterpreterDataType> param_idt ) throws Exception {




        FunctionNode functionnode = null;

// A function can include variables and constants, therefore a for loop is used to go traverse each and a new IDT is created depending on the node type.
        for (VariableNode constant : function.getConstants()) {
            if (constant.getType() instanceof RealNode) {

                // If constant is a RealNode then a new iDT is created
                // New constant node

                idt = new RealDataType(((RealNode) constant.getType()).getValue());

                // It is added to the hashmap
                local_Variables.put(constant.getName(), idt);
               // Since constants are initialized a new method isn't necessary, so the value is set to the IDT

                ((RealDataType)idt).setValue(((RealNode) constant.getType()).getValue());



            } else if (constant.getType() instanceof IntegerNode) {
                idt = new IntegerDatatype(((IntegerNode) constant.getType()).getInteger());


                local_Variables.put(constant.getName(), idt);
                ((IntegerDatatype)idt).setValue(((IntegerNode) constant.getType()).getInteger());
            } else if (constant.getType() instanceof BooleanNode) {
                idt = new BooleanDataType(((BooleanNode) constant.getType()).getbool());
                local_Variables.put(constant.getName(), idt);
                ((BooleanDataType)idt).setBoolean_value(((BooleanNode) constant.getType()).getbool());

            } else if (constant.getType() instanceof StringNode) {
                idt = new StringDataType(((StringNode) constant.getType()).getStrNode());
                local_Variables.put(constant.getName(), idt);
                ((StringDataType)idt).setString_value(((StringNode) constant.getType()).getStrNode());

            } else if (constant.getType() instanceof CharacterNode) {
                idt = new CharacterDataType(((CharacterNode) constant.getType()).getChanode());
                local_Variables.put(constant.getName(), idt);
                ((CharacterDataType)idt).setChar_value((((CharacterNode) constant.getType()).getChanode()));

            } else{
                 return;
            }

        } //A function can include variables and constants, therefore a for loop is used to go traverse each and a new IDT is created depending on the node type.
        for (VariableNode variable : function.getVariables()) {


            if (variable.getType() instanceof RealNode) {
                idt = new RealDataType(((RealNode) variable.getType()).getValue());

                local_Variables.put(variable.getName(), idt);
            } else if (variable.getType() instanceof IntegerNode) {

                idt = new IntegerDatatype(((IntegerNode) variable.getType()).getInteger());


                local_Variables.put(variable.getName(), idt);


            } else if (variable.getType() instanceof StringNode) {
                idt = new StringDataType(((StringNode) variable.getType()).getStrNode());
                local_Variables.put(variable.getName(), idt);

            } else if (variable.getType() instanceof CharacterNode) {
                idt = new CharacterDataType(((CharacterNode) variable.getType()).getChanode());
                local_Variables.put(variable.getName(), idt);

            } else if (variable.getType() instanceof BooleanNode) {
                idt = new BooleanDataType(((BooleanNode) variable.getType()).getbool());
                local_Variables.put(variable.getName(), idt);

            }


        }
        ArrayList<VariableNode> parameterNode  = function.getParameters();

             for(int i = 0; i < param_idt.size() && i < parameterNode.size(); i++) {
                 local_Variables.put(parameterNode.get(i).getName(),param_idt.get(i) );


             }




        interpretBlock(local_Variables, function.getStatements());


    }
    //interpretBlock deals with all sorts of nodes, including ifNode, whileNode, forNode, assignment node, and so on.
    public void interpretBlock(HashMap<String, InterpreterDataType> local_Variables, ArrayList<StatementNode> statementNodes) throws Exception {

     //In a loop, each statement is iterated through, and depending on the node type, a function is called to interpret it.
        for (NODE statement : statementNodes) {





            if (statement instanceof VariableReferenceNode) {

                InterpreterDataType returnvalue = variableReferenceNode(local_Variables, (VariableReferenceNode) statement);



            } else if (statement instanceof iFNode) {

                ifNode(((iFNode) statement) );
            } else if (statement instanceof ForNODE) {

                forNode((ForNODE) statement);
            } else if (statement instanceof MathOpNode) {
                mathOpNode((MathOpNode) statement);

            } else if (statement instanceof WhileNODE) {
                whileNode(((WhileNODE)statement));

            } else if (statement instanceof RepeatNODE) {



                repeatNode((RepeatNODE) statement);

            } else if(statement instanceof AssignmentNode){


                assignmentNodes((AssignmentNode) statement);


            } else if (statement instanceof  VariableNode) {
               VariableNode node;


            } else if(statement instanceof FunctionCallNode){


              FunctionNode functionNode =  FunctionLookup((FunctionCallNode) statement);

              if(functionNode.getClass().equals(BuiltinWrite.class)){

                  Functioncall(statement);

              } else if (functionNode.getClass().equals(BuiltinRead.class)) {
                  Functioncall(statement);

              } else if (functionNode.getClass().equals(Left.class)) {
                  Functioncall(statement);

              } else if (functionNode.getClass().equals(Right.class)) {
                  Functioncall(statement);


              } else if (functionNode.getClass().equals(IntegertoReal.class)) {
                  Functioncall(statement);

              } else if (functionNode.getClass().equals(RealtoInteger.class)) {
                  Functioncall(statement);
              } else if (functionNode.getClass().equals(getRandom.class)) {
                  Functioncall(statement);

              } else if (functionNode.getClass().equals(SquareRoot.class)) {

                  Functioncall(statement);

              } else if (functionNode.getClass().equals(Substring.class)) {
                  Functioncall(statement);

              } else if (functionNode.getClass().equals(Start.class)) {
                  Functioncall(statement);

              } else if (functionNode.getClass().equals(End.class)) {
                  Functioncall(statement);

              } else {
                  throw new Exception("Not a built-in function call node  ");
              }

            }
        }


    }

// The variable reference node determines if the name is contained within the hashmap; otherwise, an exception is thrown.
    public InterpreterDataType variableReferenceNode(HashMap<String, InterpreterDataType> local_Variables, VariableReferenceNode statement) throws Exception {


        if (local_Variables.get(statement.getName()) == null) {
            throw new Exception();
        }

          // It returns the data type of that variable
        return local_Variables.get(statement.getName());
    }
// IF node first call boolean compare to see if the expression is true
    public void ifNode(iFNode node) throws Exception {
        // Then it starts a while loop to iterate through all the if nodes until they all return null.
        // if the condition is true then interpret block is called





        boolean boolcompare_return = BooleanCompare(node.getBoolcompare());



        while (LinkedList.head != null) {
            if (boolcompare_return) {
                interpretBlock(local_Variables, node.getStatementNode());
            } else {
                boolcompare_return = BooleanCompare(LinkedList.head.nextif.getBoolcompare());


            }
            // Moves on to the next node
            LinkedList.head = LinkedList.head.nextif;
        }

    }
//
//
//For node, expression is used to extract the from and to, and an IDT is returned.

    public void forNode(ForNODE fornode) throws Exception {

        NODE from = fornode.getFrom();
        NODE to  = fornode.getTo();
          InterpreterDataType from_idt = expression(from);
          InterpreterDataType to_idt = expression(to);
          // The value from the "from" and "to" idts is for the beginning and finish of this for loop.
         for(int i = ((IntegerDatatype)from_idt).getValue(); i < ((IntegerDatatype)to_idt).getValue(); i++){

             interpretBlock(local_Variables,fornode.getStatementNode());
         }







    }
 // This calls expression which has MathOpNode
    public void mathOpNode(MathOpNode node) throws Exception {
        expression((MathOpNode)node);


    }

    public void whileNode(WhileNODE whilenode) throws Exception {

        boolean whileNode_Bool = BooleanCompare(whilenode.getBoolcompare());
        while (whileNode_Bool){


            interpretBlock(local_Variables,whilenode.getStatementNode());
            whileNode_Bool = BooleanCompare(whilenode.getBoolcompare());

        }

    }
// RepeatNode does a do while node

    public void repeatNode(RepeatNODE repeatnode ) throws Exception {

        boolean repeatNode;
// It calls interpret block and then uses boolean compare to check the condition.



            do {

                // Reaptnodes statement nodes are accepted by the Block.
                interpretBlock(local_Variables, repeatnode.getStatementNode());

                repeatNode = BooleanCompare(repeatnode.getBoolcompare());


            } while (repeatNode);



    }



// This deals with boolean compare nodes
    public Boolean BooleanCompare(BooleanCompareNode BooleanCompare) throws Exception {

        boolean return_bool = false;

// Since Boolean Compare has a left and a right, the left and right must be interpreted first by using expression, and then bool opertors are used to compare the left and the right.



        InterpreterDataType Left  = expression(BooleanCompare.getLeft());





        InterpreterDataType Right = expression(BooleanCompare.getRight());
       

    // Expression either returns an Integer, Real, or String idt so depending on the idt type these blocks are called to deal with boolean operators.
        // Depending on the outcome, this method returns a true or false value.


        if(Left instanceof IntegerDatatype && Right instanceof IntegerDatatype) {
            if (BooleanCompare.getOperator() == BooleanCompareNode.BooleanCompare.GREATERTHAN) {
                return_bool = ((IntegerDatatype)Left).getValue() > ((IntegerDatatype)Right).getValue();



            } else if (BooleanCompare.getOperator() == BooleanCompareNode.BooleanCompare.GREATERTHANOREQUALTO) {
                return_bool = ((IntegerDatatype)Left).getValue() >= ((IntegerDatatype)Right).getValue();

            } else if (BooleanCompare.getOperator() == BooleanCompareNode.BooleanCompare.LESSTHAN) {
                return_bool = ((IntegerDatatype)Left).getValue() < ((IntegerDatatype)Right).getValue();


            }else if(BooleanCompare.getOperator() == BooleanCompareNode.BooleanCompare.LESSTHANOREQUALTO){
                return_bool = ((IntegerDatatype)Left).getValue() <= ((IntegerDatatype)Right).getValue();
            } else if (BooleanCompare.getOperator() == BooleanCompareNode.BooleanCompare.NOTEQUAL) {
                return_bool = ((IntegerDatatype)Left).getValue() != ((IntegerDatatype)Right).getValue();

            }else if(BooleanCompare.getOperator() == BooleanCompareNode.BooleanCompare.EQUAL){
                return_bool = ((IntegerDatatype)Left).getValue() == ((IntegerDatatype)Right).getValue();


            }


        } else if (Left instanceof RealDataType && Right instanceof RealDataType) {

            if (BooleanCompare.getOperator() == BooleanCompareNode.BooleanCompare.GREATERTHAN) {
                return_bool = ((RealDataType)Left).getValue() > ((RealDataType)Right).getValue();
            } else if (BooleanCompare.getOperator() == BooleanCompareNode.BooleanCompare.GREATERTHANOREQUALTO) {
                return_bool = ((RealDataType)Left).getValue() >= ((RealDataType)Right).getValue();

            } else if (BooleanCompare.getOperator() == BooleanCompareNode.BooleanCompare.LESSTHAN) {
                return_bool = ((RealDataType)Left).getValue() < ((RealDataType)Right).getValue();

            }else if(BooleanCompare.getOperator() == BooleanCompareNode.BooleanCompare.LESSTHANOREQUALTO){
                return_bool = ((RealDataType)Left).getValue() <= ((RealDataType)Right).getValue();
            } else if (BooleanCompare.getOperator() == BooleanCompareNode.BooleanCompare.NOTEQUAL) {
                return_bool = ((RealDataType)Left).getValue() != ((RealDataType)Right).getValue();

            }else if(BooleanCompare.getOperator() == BooleanCompareNode.BooleanCompare.EQUAL){
                return_bool = ((RealDataType)Left).getValue() == ((RealDataType)Right).getValue();
            }

        } else if (Left instanceof StringDataType && Right instanceof StringDataType) {
            if(BooleanCompare.getOperator() == BooleanCompareNode.BooleanCompare.EQUAL){
                return_bool = ((StringDataType)Left).getString_value().equals(((StringDataType)Right).getString_value());
            }

        }


        return return_bool;
    }

    public InterpreterDataType expression(NODE node) throws Exception {
        InterpreterDataType return_NODE = null;
        InterpreterDataType Left_side;


     

     // expression deals with data types, variable reference, and mathOpNode

        if (node instanceof MathOpNode) {

            // MathOpNode has a left and Right side same a booleancompare node
            NODE Left = ((MathOpNode) node).getLeft();


            NODE Right = ((MathOpNode) node).getRight();





            InterpreterDataType Left_IDT = null;
            InterpreterDataType Right_IDT = null;


            int int_value;
            float Real_value;
            String String_value;


            // TThere are several possibilities, but the basic implementation is the same throughout all mathOpnode
            // Depending on what left and right are, they are called and return a kind of node, from which a new IDT is generated.


            if (Left instanceof IntegerNode && Right instanceof IntegerNode) {


                int_value = ((IntegerNode) Left).getInteger();
                Left_IDT = new IntegerDatatype(int_value);


                int_value = ((IntegerNode) Right).getInteger();
                Right_IDT = new IntegerDatatype(int_value);


            }


            // There are several possibilities, but the basic implementation is the same throughout all mathOpnode
            else if (Left instanceof RealNode && Right instanceof RealNode) {

                Real_value = ((RealNode) Left).getValue();
                Left_IDT = new RealDataType(Real_value);


                Real_value = ((RealNode) Left).getValue();
                Right_IDT = new RealDataType(Real_value);

            } else if (Left instanceof StringNode || Right instanceof StringNode) {

                if (Left instanceof StringNode) {
                    String_value = ((StringNode) Left).getStrNode();
                    Left_IDT = new StringDataType(String_value);

                } else {
                    String_value = ((StringNode) Left).getStrNode();
                    Right_IDT = new StringDataType(String_value);

                }


            } else if (Left instanceof VariableReferenceNode && Right instanceof VariableReferenceNode) {



                    InterpreterDataType type = local_Variables.get(((VariableReferenceNode) Left).getName());

                    Left_IDT = type;




                    type = local_Variables.get(((VariableReferenceNode) Right).getName());

                    Right_IDT = type;


            } else if (Left instanceof VariableReferenceNode && Right instanceof IntegerNode || Left instanceof IntegerNode && Right instanceof VariableReferenceNode) {
                 if(Left instanceof VariableReferenceNode && Right instanceof IntegerNode) {
                     InterpreterDataType type = local_Variables.get(((VariableReferenceNode) Left).getName());

                     Left_IDT = type;


                     int_value = ((IntegerNode) Right).getInteger();
                     Right_IDT = new IntegerDatatype(int_value);

                 } else if(Left instanceof IntegerNode && Right instanceof VariableReferenceNode){

                     InterpreterDataType type = local_Variables.get(((VariableReferenceNode) Right).getName());

                     Left_IDT = type;


                     int_value = ((IntegerNode) Left).getInteger();
                     Right_IDT = new IntegerDatatype(int_value);



                 }




            } else if (Left instanceof MathOpNode && Right instanceof MathOpNode) {
                NODE Left_left = ((MathOpNode) Left).getLeft();
                NODE Left_Right = ((MathOpNode) Left).getRight();
                int L_value = 0;
                int R_right = 0;
                float F_Left = 0;
                float F_Right = 0;

                NODE Right_left = ((MathOpNode) Right).getLeft();
                NODE Right_Right = ((MathOpNode) Right).getRight();

                if (Left_left instanceof IntegerNode || Left_Right instanceof IntegerNode && Right_left instanceof IntegerNode && Right_Right instanceof IntegerNode) {

                    if (((MathOpNode) Left).getOperator() == MathOpNode.MATHOPERATION.ADD || ((MathOpNode) Right).getOperator() == MathOpNode.MATHOPERATION.ADD) {
                        assert Left_left instanceof IntegerNode;
                        L_value = ((IntegerNode) Left_left).getInteger() + ((IntegerNode) Left_Right).getInteger();
                        R_right = ((IntegerNode) Right_left).getInteger() + ((IntegerNode) Right_Right).getInteger();


                    } else if (((MathOpNode) Left).getOperator() == MathOpNode.MATHOPERATION.MULTIPLY || ((MathOpNode) Right).getOperator() == MathOpNode.MATHOPERATION.MULTIPLY) {
                        assert Left_left instanceof IntegerNode;
                        L_value = ((IntegerNode) Left_left).getInteger() * ((IntegerNode) Left_Right).getInteger();
                        R_right = ((IntegerNode) Right_left).getInteger() * ((IntegerNode) Right_Right).getInteger();
                    } else if (((MathOpNode) Left).getOperator() == MathOpNode.MATHOPERATION.DIVISION || ((MathOpNode) Right).getOperator() == MathOpNode.MATHOPERATION.DIVISION) {

                        assert Left_left instanceof IntegerNode;
                        L_value = ((IntegerNode) Left_left).getInteger() / ((IntegerNode) Left_Right).getInteger();
                        R_right = ((IntegerNode) Right_left).getInteger() / ((IntegerNode) Right_Right).getInteger();
                    } else if (((MathOpNode) Left).getOperator() == MathOpNode.MATHOPERATION.MINUS || ((MathOpNode) Right).getOperator() == MathOpNode.MATHOPERATION.MINUS) {
                        assert Left_left instanceof IntegerNode;
                        L_value = ((IntegerNode) Left_left).getInteger() - ((IntegerNode) Left_Right).getInteger();
                        R_right = ((IntegerNode) Right_left).getInteger() - ((IntegerNode) Right_Right).getInteger();
                    } else if (((MathOpNode) Left).getOperator() == MathOpNode.MATHOPERATION.MOD || ((MathOpNode) Right).getOperator() == MathOpNode.MATHOPERATION.MOD) {
                        assert Left_left instanceof IntegerNode;
                        L_value = ((IntegerNode) Left_left).getInteger() % ((IntegerNode) Left_Right).getInteger();
                        R_right = ((IntegerNode) Right_left).getInteger() % ((IntegerNode) Right_Right).getInteger();
                    } else {
                        throw new RuntimeException("Not a valid operator");
                    }


                    if (((MathOpNode) node).getOperator() == MathOpNode.MATHOPERATION.ADD) {
                        int_value = L_value + R_right;
                        return_NODE = new IntegerDatatype(int_value);
                    } else if (((MathOpNode) node).getOperator() == MathOpNode.MATHOPERATION.MULTIPLY) {
                        int value = L_value * R_right;
                        return_NODE = new IntegerDatatype(value);
                    } else if (((MathOpNode) node).getOperator() == MathOpNode.MATHOPERATION.DIVISION) {
                        int value = L_value / R_right;
                        return_NODE = new IntegerDatatype(value);
                    } else if (((MathOpNode) node).getOperator() == MathOpNode.MATHOPERATION.MINUS) {
                        int value = L_value - R_right;
                        return_NODE = new IntegerDatatype(value);

                    } else if (((MathOpNode) node).getOperator() == MathOpNode.MATHOPERATION.MOD) {
                        int value = L_value % R_right;
                        return_NODE = new IntegerDatatype(value);

                    }
                } else if (Left_left instanceof RealNode || Left_Right instanceof RealNode && Right_left instanceof RealNode && Right_Right instanceof RealNode) {

                    if (((MathOpNode) Left).getOperator() == MathOpNode.MATHOPERATION.ADD || ((MathOpNode) Right).getOperator() == MathOpNode.MATHOPERATION.ADD) {
                        F_Left = ((RealNode) Left_left).getValue() + ((IntegerNode) Left_Right).getInteger();
                        F_Right = ((RealNode) Right_left).getValue() + ((RealNode) Right_Right).getValue();


                    } else if (((MathOpNode) Left).getOperator() == MathOpNode.MATHOPERATION.MULTIPLY || ((MathOpNode) Right).getOperator() == MathOpNode.MATHOPERATION.MULTIPLY) {
                        assert Left_left instanceof RealNode;
                        assert Left_Right instanceof RealNode;
                        F_Left = ((RealNode) Left_left).getValue() * ((RealNode) Left_Right).getValue();
                        F_Right = ((RealNode) Right_left).getValue() * ((RealNode) Right_Right).getValue();
                    } else if (((MathOpNode) Left).getOperator() == MathOpNode.MATHOPERATION.DIVISION || ((MathOpNode) Right).getOperator() == MathOpNode.MATHOPERATION.DIVISION) {

                        F_Right = ((RealNode) Right_left).getValue() / ((RealNode) Right_Right).getValue();
                    } else if (((MathOpNode) Left).getOperator() == MathOpNode.MATHOPERATION.MINUS || ((MathOpNode) Right).getOperator() == MathOpNode.MATHOPERATION.MINUS) {
                        assert Left_left instanceof RealNode;
                        assert Left_Right instanceof RealNode;
                        F_Left = ((RealNode) Left_left).getValue() - ((RealNode) Left_Right).getValue();
                        F_Right = ((RealNode) Right_left).getValue() - ((RealNode) Right_Right).getValue();
                    } else if (((MathOpNode) Left).getOperator() == MathOpNode.MATHOPERATION.MOD || ((MathOpNode) Right).getOperator() == MathOpNode.MATHOPERATION.MOD) {
                        assert Left_left instanceof RealNode;
                        F_Left = ((RealNode) Left_left).getValue() % ((RealNode) Left_Right).getValue();
                        F_Right = ((RealNode) Right_left).getValue() % ((RealNode) Right_Right).getValue();
                    } else {
                        throw new RuntimeException("Not a valid operator");
                    }

                }


            } else {
                throw new RuntimeException("Not a valid expression");
            }


            if (Left_IDT instanceof IntegerDatatype && Right_IDT instanceof IntegerDatatype) {



                int value;

                if (((MathOpNode) node).getOperator() == MathOpNode.MATHOPERATION.ADD) {

                    value = ((IntegerDatatype) Left_IDT).getValue() + ((IntegerDatatype) Right_IDT).getValue();


                    return_NODE = new IntegerDatatype((value));



                } else if (((MathOpNode) node).getOperator() == MathOpNode.MATHOPERATION.MINUS) {
                    value = ((IntegerDatatype) Left_IDT).getValue() - ((IntegerDatatype) Right_IDT).getValue();
                    return_NODE = new IntegerDatatype((value));

                } else if (((MathOpNode) node).getOperator() == MathOpNode.MATHOPERATION.DIVISION) {
                    value = ((IntegerDatatype) Left_IDT).getValue() / ((IntegerDatatype) Right_IDT).getValue();
                    return_NODE = new IntegerDatatype((value));

                } else if (((MathOpNode) node).getOperator() == MathOpNode.MATHOPERATION.MULTIPLY) {
                    value = ((IntegerDatatype) Left_IDT).getValue() * ((IntegerDatatype) Right_IDT).getValue();
                    return_NODE = new IntegerDatatype((value));

                } else if (((MathOpNode) node).getOperator() == MathOpNode.MATHOPERATION.MOD) {
                    value = ((IntegerDatatype) Left_IDT).getValue() % ((IntegerDatatype) Right_IDT).getValue();
                    return_NODE = new IntegerDatatype((value));

                }

            }


        }else if(node instanceof VariableReferenceNode){


         // If the node is a variable reference node, the method is called to see whether the variable name is a hashmap, and if so, the method returns the IDT.


          return_NODE = variableReferenceNode(local_Variables,((VariableReferenceNode) node));






          //if(variableReferenceNode(local_Variables,((VariableReferenceNode) node)) != null) {
             // return_NODE = local_Variables.get(((VariableReferenceNode) node).getName());

         // }








            // If the node is an integer node then a new integer IDT is created

        } else if(node instanceof IntegerNode){
            int return_value = ((IntegerNode) node).getInteger();

            return_NODE = new IntegerDatatype(return_value);

            // If node is a realnode then a new real IDT is created
        } else if(node instanceof RealNode){
            double return_value  = ((RealNode)node).getValue();
            return_NODE = new RealDataType(return_value);
        }

        return return_NODE;
    }
// This is deals with assignment node
    public void assignmentNodes(AssignmentNode node) throws Exception {
        // An assignment node has a left and a right side, so left is the variable reference node and the right contains the value

        VariableReferenceNode left = node.getTarget();



        InterpreterDataType Left_return;
        InterpreterDataType right_return;


 // Expression is called for Left which is the variable refrence and it makes sure the values is already in the hashmap
    Left_return = expression((left));








// If it exists the right can be handled


    if(Left_return != null){
        // Expression returns integer IDT, Real IDT or String IDT
       right_return = expression(node.getValue());












         // Depending on the return value each IDT is called and the value is set to the variable
       if(right_return instanceof IntegerDatatype) {


           IntegerDatatype existingValue = (IntegerDatatype) local_Variables.get(node.getTarget().getName());



           existingValue.setValue( ((IntegerDatatype) right_return).getValue());

           local_Variables.put(node.getTarget().getName(), existingValue);









            
       } else if(right_return instanceof RealDataType){
           RealDataType existingValue = (RealDataType) local_Variables.get(node.getTarget().getName());

           existingValue.setValue( ((RealDataType) right_return).getValue());
           local_Variables.put(node.getTarget().getName(), existingValue);

       } else if (right_return instanceof StringDataType) {
           StringDataType existingValue = (StringDataType) local_Variables.get(node.getTarget().getName());

           existingValue.setString_value( ((StringDataType) right_return).getString_value());
           local_Variables.put(node.getTarget().getName(), existingValue);

       }
        //local_Variables.toString();





    }






    }


// This extracts fhe fuction node from the hashmap
    public FunctionNode FunctionLookup(FunctionCallNode node){
       FunctionNode functionNode = null;
        functionNode = ProgramNode.ProgramMap.get(node.getName());
// if the hashmap isn't accessible this expression will be called else the function node is returned
       if( functionNode == null){
           throw new SyntaxErrorException().FunctionValidity(node);

       }else {
           return functionNode;
       }




    }

    public void Functioncall(NODE node) throws Exception {


        // Returns a function node
        FunctionNode functionLookup = FunctionLookup((FunctionCallNode) node);










        // This array is used to store parameter IDR's
        ArrayList<InterpreterDataType> function_Parameters = new ArrayList<>();
        //ArrayList<InterpreterDataType> new_collection_IDT =  new ArrayList<>();


        if(functionLookup.getClass().equals(FunctionNode.class)) {
            if (((FunctionCallNode) node).getParameters().getVariableReference().size() + ((FunctionCallNode) node).getParameters().getNodeexpression().size() != functionLookup.getParameters().size() && !functionLookup.isVariadic()) {

                throw new SyntaxErrorException().MissingParameters();
            }

        }

        // This loops through the parameters,and expression is called to a return an idt
        for (NODE parameterNode : ((FunctionCallNode) node).getParameters().getNodeexpression()) {


          InterpreterDataType idt =  expression(parameterNode);



          function_Parameters.add(idt);


      }
     // This loops through the parameters and expression is called to a return an idt
        for(NODE parameterNode: ((FunctionCallNode)node).getParameters().getVariableReference() ){

            InterpreterDataType idt = expression(parameterNode);




            function_Parameters.add(idt);


        }

 // Check if return is instance of BuiltinRead/Built in Right
        // THen call execute if it's a built-in or call interpretfunction if it's function node

        // (I'm not too sure about implementing function calls and accepting returns values. Any comments)
        InterpreterDataType idt;

        if(functionLookup instanceof BuiltinRead){  

            ((BuiltinRead) functionLookup).execute(function_Parameters);

        } else if (functionLookup instanceof BuiltinWrite) {


            ((BuiltinWrite) functionLookup).execute(function_Parameters);

        } else if(functionLookup instanceof Left){
            ((Left) functionLookup).execute(function_Parameters);

        } else if (functionLookup instanceof Right) {
            ((Right) functionLookup).execute(function_Parameters);

        } else if(functionLookup instanceof SquareRoot){



            ((SquareRoot) functionLookup).execute(function_Parameters);



        } else if (functionLookup instanceof Substring) {
            ((Substring) functionLookup).execute(function_Parameters);

        } else if (functionLookup instanceof getRandom) {
            ((getRandom) functionLookup).execute(function_Parameters);
        } else if (functionLookup instanceof IntegertoReal) {
            ((IntegertoReal) functionLookup).execute(function_Parameters);

        } else if(functionLookup instanceof RealtoInteger){
            ((RealtoInteger) functionLookup).execute(function_Parameters);


        } else if (functionLookup instanceof Start) {
            ((Start) functionLookup).execute(function_Parameters);

        } else if (functionLookup instanceof End){

            ((End) functionLookup).execute(function_Parameters);


        } else {


            interpretFunction(functionLookup,function_Parameters);

            }
        //Looks at the invocation parameters to determine if any of them are var and see if function node type is variadic
        // if that's the case, then the values are updated in the hashmap
        for( int i = 0; i < function_Parameters.size(); i++){

            if(functionLookup.isVariadic() && ((FunctionCallNode) node).getParameters().getVariableReference() != null){

              local_Variables.put(((FunctionCallNode) node).getName(),function_Parameters.get(i));
            }


        }
        }
    }





