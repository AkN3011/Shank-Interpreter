import java.util.ArrayList;
import java.util.HashMap;

abstract class NODE { // Node class consisting the abstract tostring
    public abstract String toString();
}

class IntegerNode extends NODE{  // Integer node extends the parent Node


    private int integer;

    // Constructor
  public IntegerNode(int Integer){
      this.integer = Integer;

  }
    // ACCESSOR
    public int getInteger(){

        return integer;
    }


    @Override
    public String toString() { // toString for non decimal Integer
        return " IntegerNode (" + integer  + ") ";
    }
}
class RealNode extends NODE{ // Real node also extends the Node class

private float value;

// constructor
 public RealNode(float value) {

    this.value = value;

}
// ACCESSOR
public float getValue(){

    return value;
}



    @Override // Float number tostring
    public String toString() {
        return "( "  + "Real "+ value + ")";
    }
}

class MathOpNode extends NODE {


    public enum MATHOPERATION { // Enum is used to hold all the names of different operators.

        ADD, MINUS, MULTIPLY, DIVISION, MOD
    }

    private NODE Left; // sub node left
    private NODE Right; // sub node right

    private MATHOPERATION Operator; // Enum

    public MathOpNode(NODE Left, NODE Right, MATHOPERATION Operator) {

        this.Left = Left;
        this.Right = Right;
        this.Operator = Operator;
    }


    // ACCESSORS
    public NODE getLeft() {
        return Left;
    }

    public NODE getRight() {
        return Right;
    }

    public MATHOPERATION getOperator() {
        return Operator;
    }

    @Override
    public String toString() {
        return "MathOperation " + "( " + Operator + " " + Left + " " + Right + ")"; // The tree is printed using the to string.
    }

    // Boolean node to create boolean data types
 // extends node
}class BooleanNode extends NODE{


    private boolean bool;
// CONSTRUCTOR
    public BooleanNode(boolean bool){
        this.bool = bool;
    }
// getter
  public boolean getbool(){
        return bool;
  }

    @Override
    public String toString() {
        return  "Boolean";
    }
}
// Character node for char data types
class CharacterNode extends NODE{
    private char chanode;

// CONSTRUCTOR
    public CharacterNode(char chanode){
        this.chanode = chanode;
    }

// GETTER
    public  char getChanode(){
        return chanode;
    }

// TOstring
    @Override
    public String toString() {
        return "Character";
    }
}

// String node for strings
class StringNode extends NODE{
   private String StrNode;

// CONSTRUCTOR
    public StringNode (String StrNode){
        this.StrNode = StrNode;


    }
// getter
    public String getStrNode() {
        return StrNode;
    }

    @Override
    public String toString() {
        return "String" + "(" +StrNode +")";
    }
}
// Variable node class with variable name, two datatype variables, one for regular data types and the other for constants.
class VariableNode extends NODE{
  private String name;
  private NODE type;

  private NODE constantsType;

  private boolean changeable;  // changeable depending on if it's allowed to change

  private int from; // from and to for arrays

  private int to;

  private float Realfrom;

  private float Realto;



// CONSTRUCTORS\

    public VariableNode (String name, NODE type, NODE constantsType, boolean changeable, int from, int to){
        this.name = name;
        this.type = type;
        this.constantsType = constantsType;
        this.changeable = changeable;
        this.from =  from;
        this.to = to;

    }



  public VariableNode (String name, NODE type, NODE constantsType, boolean changeable, float Realfrom, float Realto){
        this.name = name;
        this.type = type;
        this.constantsType = constantsType;
        this.changeable = changeable;
        this.Realfrom =  Realfrom;
        this.Realto = Realto;

    }


// getters

  public NODE getConstantsType(){

        return constantsType;
  }

  public String getName(){
      return name;
  }
  public NODE getType(){
      return type;
  }
  public void setType(NODE type){
     this.type= type;
  }
  public boolean isChangeable(){
      return isChangeable();

  }
  public int getFrom(){
      return from;
  }
  public int getTo(){
      return to;
  }


    public float getRealfrom() {
        return Realfrom;
    }

    public float getRealto() {
        return Realto;
    }

    @Override
    public String toString() {
      if( getRealto() != 0) {
          return "(" + name + ")" + "(" + type + ")" + "(" + changeable + ")" + "(" + Realfrom + ")" + "(" + Realto + ")";
      } else if ( getTo() != 0) {

          return "(" + name + ")" + "(" + type + ")" + "(" + changeable + ")" + "(" + from + ")" + "(" + to + ")";

      } else{

          //System.out.println(getTo());
          return "(" + name + ")" + "(" + type + ")" + "(" + changeable + ")" + "(" + from + ")" + "(" + to + ")";

      }


    }
}

// Function node extends NODE

class FunctionNode extends NODE{

    // 4 different Arraylists to care of parameters, constants, variables, and statements
  private String Name;
  private ArrayList<VariableNode> parameters;
  private ArrayList<VariableNode> constants;
  private ArrayList<VariableNode> variables;

  private ArrayList<StatementNode> statements;
//CONSTRUCTOR
  public FunctionNode(String Name,ArrayList<VariableNode> parameters,ArrayList<VariableNode> constants, ArrayList<VariableNode> variables,ArrayList<StatementNode> statements){
      this.Name = Name;
      this.parameters = parameters;
      this.constants = constants;
      this.variables = variables;
      this.statements = statements;
  }



    // GETTERS
    public String getName() {
        return Name;
    }

    public ArrayList<VariableNode> getParameters() {
        return parameters;
    }

    public ArrayList<VariableNode> getConstants() {
        return constants;
    }

    public ArrayList<StatementNode> getStatements() {
        return statements;
    }

    public ArrayList<VariableNode> getVariables() {
        return variables;
    }

    boolean isVariadic(){
      return false;
    }



    @Override
    public String toString() {
        return "(" + Name + ")" + "(" + parameters + ")" + "("+ constants +")" + "("+ variables + ")" + "(" + statements+ ")";
    }
}
// statement node extends NODE


// program node which contains the hashmap
class ProgramNode extends NODE{
   public static HashMap<String, FunctionNode> ProgramMap = new HashMap<>();





    //public void addnode(FunctionNode programMap){
       // ProgramMap.put(programMap.getName(), programMap);
    //}

    @Override
    public String toString() {
       return null;
    }
}
// Variable reference node
class VariableReferenceNode extends NODE{

    // Varaiable reference node has a name and an optional index

    private String name;

   private NODE optional;
// Constructor
   public VariableReferenceNode ( String name, NODE optional){
       this.name = name;
       this.optional = optional;




    }
// GETTERS
    public String getName() {
        return name;
    }

    public NODE getOptional() {
        return optional;
    }
// TO STRING
    @Override
    public String toString() {
        return  name  +"[ "+ ""+ optional + "]";
    }
} // Boolean compare node
class BooleanCompareNode extends NODE {

    // enums for each of the various types of operators
    public enum BooleanCompare { // Enum is used to hold all the names of different operators.

        LESSTHAN, GREATERTHAN, LESSTHANOREQUALTO, GREATERTHANOREQUALTO, EQUAL,NOTEQUAL
    }
  // Boolean compare has left and right side like an expression
    private NODE Left; // sub node left
    private NODE Right; // sub node right

    private BooleanCompare boolOperator; // Enum
// Constructor
    public  BooleanCompareNode(NODE Left, NODE Right, BooleanCompare boolOperator) {

        this.Left = Left;
        this.Right = Right;
        this.boolOperator = boolOperator;
    }


    // ACCESSORS
    public NODE getLeft() {
        return Left;
    }

    public NODE getRight() {
        return Right;
    }

    public BooleanCompare getOperator() {
        return boolOperator;
    }
// Tostring method
    @Override
    public String toString() {
        return "BooleanCompare " + "( " + boolOperator + " (" + Left + " " + Right + ")"; // The tree is printed using the to string.
    }

    // Boolean node to create boolean data types
    // extends node
}
// statement node extends Node

class StatementNode extends NODE {





    @Override
    public String toString() {
        return null;
    }
}
// Assignment node has a variable refrence node and a value which is the other side
class  AssignmentNode extends StatementNode{


    private VariableReferenceNode target;
   private NODE value;
   // constructor
    public AssignmentNode(VariableReferenceNode target,NODE value ){
        this.target = target;
        this.value = value;




    }
// Getters
    public NODE getValue() {
        return value;
    }

    public VariableReferenceNode getTarget(){
        return target;
    }
// tostring
    @Override
    public String toString(){

            
        return  target + ":=" + value;
    }
}

// IF node has expression/boolean, set of statement nodes and the next if node

class iFNode extends StatementNode{


   private BooleanCompareNode boolcompare;
    private  ArrayList<StatementNode> statementNode;
// The next node if any
   iFNode nextif;

// Constructor
    public iFNode(BooleanCompareNode boolcompare,ArrayList<StatementNode> statementNode){
        this.boolcompare = boolcompare;
        this.statementNode = statementNode;

    }

// getters
    public BooleanCompareNode getBoolcompare() {
        return boolcompare;
    }

    public ArrayList<StatementNode> getStatementNode() {
        return statementNode;
    }

    public void setBoolcompare(BooleanCompareNode boolcompare) {
        this.boolcompare = boolcompare;
    }

    public void setStatementNode(ArrayList<StatementNode> statementNode) {
        this.statementNode = statementNode;
    }

    @Override
    public String toString() {
        return " "+ "(" + boolcompare + ")" +   "statement"  +  statementNode + " "+ "(" + nextif + ")";
    }
}
// While node has Boolean compare node and a list of statement nodes
class WhileNODE extends StatementNode{


    private BooleanCompareNode boolcompare;
    private ArrayList<StatementNode> statementNode;

// Constructors
    public WhileNODE(BooleanCompareNode boolcompare,  ArrayList<StatementNode> statementNode){
        this.boolcompare = boolcompare;
        this.statementNode = statementNode;

    }

// Getters
    public BooleanCompareNode getBoolcompare() {
        return boolcompare;
    }

    public ArrayList<StatementNode> getStatementNode() {
        return statementNode;
    }


    @Override
    public String toString() {


        return  "while" + "(" + boolcompare + ")" + "{" + statementNode + "}";
    }
}
// RepeatNode has booleancompare node and a list of statement nodes
class RepeatNODE extends StatementNode{
    private BooleanCompareNode boolcompare;
    private ArrayList<StatementNode> statementNode;

// Constructor
    public RepeatNODE(BooleanCompareNode boolcompare,ArrayList<StatementNode> statementNode){
        this.boolcompare = boolcompare;
        this.statementNode = statementNode;

    }

// GETTERS
    public BooleanCompareNode getBoolcompare() {
        return boolcompare;
    }

    public ArrayList<StatementNode> getStatementNode() {
        return statementNode;
    }
// Setters
    @Override
    public String toString() {
        return " Repeat until " + boolcompare + "statments"+ statementNode;
    }
}

class ForNODE extends StatementNode{


    private NODE from;

   private NODE to;

   private ArrayList<StatementNode> statementNode;

    NODE type;
public ForNODE (NODE from, NODE to, ArrayList<StatementNode> statementNode){
    this.from = from;
    this.to = to;
    this.statementNode = statementNode;

}
    public NODE getFrom() {
        return from;
    }

    public NODE getTo() {
        return to;
    }
    public ArrayList<StatementNode> getStatementNode() {
        return statementNode;
    }

    @Override
    public String toString() {
        return "for " + "From" + from + "to" + to  + "[ " + statementNode + "]";
    }
}
// Function call node has name and parameter list
class FunctionCallNode extends StatementNode{
 private String Name;

 private ParameterNode parameters;
// Constructor
public FunctionCallNode(String Name , ParameterNode parameters){
    this.Name = Name;
    this.parameters = parameters;
}
// Getters
    public String getName() {
        return Name;
    }

    public ParameterNode getParameters() {
        return parameters;
    }
// Tostring method
    @Override
    public String toString() {
        return Name + "[" + parameters + "]";
    }
}
// ParameterNode to deal with the expression in the parameter
class ParameterNode extends StatementNode{

private ArrayList<VariableReferenceNode> variableReference;

private ArrayList<NODE> nodeexpression;
// Constructor
public ParameterNode(ArrayList<VariableReferenceNode> variableReference, ArrayList<NODE> nodeexpression){
    this.variableReference = variableReference;
    this.nodeexpression = nodeexpression;
}
// GETTERS
    public ArrayList<VariableReferenceNode> getVariableReference() {
        return variableReference;
    }


    public void setNodeexpression(ArrayList<NODE> nodeexpression) {
        this.nodeexpression = nodeexpression;
    }

    public ArrayList<NODE> getNodeexpression() {
        return nodeexpression;
    }

    // tOstring method
    @Override
    public String toString() {
           if(variableReference != null) {
               return "[" + variableReference + "]" + "or" + "[" + nodeexpression + "]";
           } else if (variableReference == null) {
             return   "[" + nodeexpression + "]";

           } else {
               return "[" + nodeexpression;
           }
    }
}