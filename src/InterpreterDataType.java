import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
// Interpreterdatatype abstract class
public abstract class InterpreterDataType {
    //has a tostring and a from string that accepts a string as input
    public abstract String ToString();

    public abstract void FromString(String input);

}
// Integer data type accepts a integer value
class IntegerDatatype extends InterpreterDataType{


    private int value;

// Constructor
    public IntegerDatatype(int value){
        this.value = value;

    }

// Getters / Setters
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;

    }
// The toString method prints out the Integer value
    @Override
    public String ToString() {
        return "Integer" + value;
    }

    @Override
    public void FromString(String input) {

    }
}
//Realatatype

class RealDataType extends InterpreterDataType{
    //RealDataType type is a double
   private double value;


//Constructor
   public RealDataType (double value){
       this.value = value;
   }

// Getters and Setters
    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
// The to string method prints out the "double" value
    @Override
    public String ToString() {
        return "Double" + " = " + value;
    }

    @Override
    public void FromString(String input) {

    }
}
// Stringdatatype accepts a string value.

class StringDataType extends InterpreterDataType{


   private String string_value;

// Constructor
   public StringDataType (String string_value){
       this.string_value = string_value;
   }
// Getters and Setters
    public String getString_value() {
        return string_value;
    }

    public void setString_value(String string_value) {
        this.string_value = string_value;
    }
    //
//
// The toString function returns a String value.
    @Override
    public String ToString() {
        return "String" + string_value;
    }

    @Override
    public void FromString(String input) {

    }
}
// A char value is stored in the CharacterDataType class.
class CharacterDataType extends InterpreterDataType{


    private char char_value;
// Constructor

    public CharacterDataType(char char_value){
        this.char_value = char_value;
    }
// Getters and Setts for the value
    public char getChar_value() {
        return char_value;
    }

    public void setChar_value(char char_value) {
        this.char_value = char_value;
    }
// Prints out the stored value
    @Override
    public String ToString() {
        return "Character" + char_value;
    }

    @Override
    public void FromString(String input) {

    }
}

// The booleantypes stores a boolean

class BooleanDataType extends InterpreterDataType{

    Boolean Boolean_value;
    // Constructor
     public BooleanDataType(Boolean Boolean_value){
         this.Boolean_value = Boolean_value;
     }
    // Getters and Setters
    public Boolean getBoolean_value() {
        return Boolean_value;
    }

    public void setBoolean_value(Boolean boolean_value) {
        Boolean_value = boolean_value;
    }
// ToString method
    @Override
    public String ToString() {
        return "boolean" + Boolean_value;
    }

    @Override
    public void FromString(String input) {

    }
}

//Because the array data type is generic, it takes Interpreter data types.
class ArrayDataType <IDT extends InterpreterDataType> extends InterpreterDataType{


// It takes a start and an end value to hold the beginning and ending indices.
   private final int start;

   private final int end;


// Constructor
    public ArrayDataType(int start, int end){

        this.start = start;
        this.end  = end;
    }

// Getters and Setters


    public void setArray_Start(int start) {
        start = start;
    }

    public void setArray_end(int end) {
        end  = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
// TOstring prints out the start and end indices
    @Override
    public String ToString() {
        return "Array " + "start:" + start + "end:" + end;
    }

    @Override
    public void FromString(String input) {

    }
}
//Built in read extends FunctionNode
class BuiltinRead extends FunctionNode{
    // constructor from Function node is imported
    public BuiltinRead(String Name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants, ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) {
        super(Name, parameters, constants, variables, statements);
    }
    // BuitinRead's Execute() method
    public void execute(ArrayList<InterpreterDataType> List){
        // Scans the value and convert  the value into it's specific datatype
        Scanner scan = new Scanner(System.in);
        // The scanner is used to read the data, and the for loop is used to traverse over the list in order to find the data.
        for(int i = 0; i < List.size(); i++ ){
            //The list contains many types, the intanceof function is used to determine whether they are equal.
            // Instance of a IntgereDataTye
            if(List.get(i) instanceof IntegerDatatype){

                int integer_value = scan.nextInt();
                //Casting is used, and the value is assigned to the specified types.
                ((IntegerDatatype) List.get(i)).setValue(integer_value);

            } else if (List.get(i) instanceof  RealDataType) {
                double Real_value = scan.nextDouble();
                ((RealDataType) List.get(i)).setValue(Real_value);
            }  else if (List.get(i) instanceof BooleanDataType) {
                boolean bool_value = scan.nextBoolean();
                ((BooleanDataType) List.get(i)).setBoolean_value(bool_value);

            }


            else if (List.get(i) instanceof StringDataType) {
                String string_value  = scan.next();
                ((StringDataType) List.get(i)).setString_value(string_value);

            } else if (List.get(i) instanceof CharacterDataType) {
                //Char is extracted by first accepting it as a string and then obtaining the string's index 0
                String String_value = scan.next();
                char char_value = String_value.charAt(0);
                ((CharacterDataType) List.get(i)).setChar_value(char_value);


            } else if(List.get(i) instanceof ArrayDataType<?>) {
                // Start and end of the array is stored
                int start = scan.nextInt();
                int end = scan.nextInt();
                ((ArrayDataType<?>) List.get(i)).setArray_end(start);
                ((ArrayDataType<?>) List.get(i)).setArray_end(end);
            }


        }





    }

    // isVariadic() is overridden
    @Override
    boolean isVariadic(){
        return true;
    }
}
// Built in write extends Function node
class BuiltinWrite extends FunctionNode{


    public BuiltinWrite(String Name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants, ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) {
        super(Name, parameters, constants, variables, statements);



    }
// Execute accepts a Arraylist of InterpreterDataTypes

    public void execute(ArrayList<InterpreterDataType> List){

        for(int  i =0; i < List.size(); i++){
            //A for loop is used to go through the list and output it out.
            // As seen in built-in Read, if statements are used to identify each data type, and the value is retrieved using a getter and printed out.
            if(List.get(i) instanceof IntegerDatatype){

                int value  = ((IntegerDatatype) List.get(i)).getValue();
                System.out.println(value);
            } else if(List.get(i) instanceof RealDataType){
                // getvalue is used to extract value
                Double Real_value  = ((RealDataType) List.get(i)).getValue();
                System.out.println(Real_value);
            } else if(List.get(i) instanceof StringDataType){
                // getter is used to extract the value and then a print statement is used to print out the value
                String string_value = ((StringDataType) List.get(i)).getString_value();
                System.out.println(string_value);
            } else if(List.get(i) instanceof CharacterDataType){
                char char_value = ((CharacterDataType) List.get(i)).getChar_value();
                System.out.println(char_value);
            } else if (List.get(i) instanceof BooleanDataType) {
                boolean bool_value = ((BooleanDataType) List.get(i)).getBoolean_value();
                System.out.println(bool_value);

            }else if(List.get(i) instanceof ArrayDataType<?>){
                int start =  ((ArrayDataType<InterpreterDataType>) List.get(i)).getStart();
                int end = ((ArrayDataType<InterpreterDataType>) List.get(i)).getEnd();

                System.out.println(start + end );
            }

        }






    }


    // isVariadic is also overridden once again
    @Override
    boolean isVariadic(){
        return true;
    }
}


// Lefts extends Functionnode
class Left extends FunctionNode{

    // constructor
    public Left(String Name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants, ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) {
        super(Name, parameters, constants, variables, statements);
    }




    // Execute Accepts a list of InterpreterDataTypes
    public void execute(ArrayList<InterpreterDataType> List){
        StringDataType String_value = (StringDataType) List.get(0);
        IntegerDatatype Length = (IntegerDatatype) List.get(1);
        //Output is used to output the value
        StringDataType output = (StringDataType) List.get(2);
        int length;

        // If the specified length equals the whole length of the string, the length is the total length of the string.
        // if not the inputted length is used

        if(Length.getValue() >= String_value.getString_value().length()){

            length = String_value.getString_value().length();
        } else {
            length = Length.getValue();
        }
        //Substring is used to extract the Left by starting with index 0 and ending with the length which is the end index
        output.setString_value(String_value.getString_value().substring(0,length));




    }
}
//Right is very similar to left

class Right extends FunctionNode{
// Constructor

    public Right(String Name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants, ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) {
        super(Name, parameters, constants, variables, statements);
    }

    public void execute(ArrayList<InterpreterDataType> List){
        StringDataType String_value = (StringDataType) List.get(0);
        IntegerDatatype Length = (IntegerDatatype) List.get(1);
        StringDataType output = (StringDataType) List.get(2);
        int length;
        // If the length of the inputted string is more than the length of the entire string, the length equals the length of the full string.
        if(Length.getValue() >= String_value.getString_value().length() ){
            length = String_value.getString_value().length();
            // If that case is true then the substring is used extract Right by starting from index 0 and ending at the full length
            output.setString_value(String_value.getString_value().substring(0,length));
        } else{
            // Instead, the length is computed by subtracting the input length from the total length of the String and then using substring.

            length = String_value.getString_value().length() - Length.getValue();
            output.setString_value(String_value.getString_value().substring(length));

        }



    }
}

//Built in Substring

class Substring extends FunctionNode{

    // Constructor
    public Substring(String Name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants, ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) {
        super(Name, parameters, constants, variables, statements);
    }
    // Execute
    public void execute(ArrayList<InterpreterDataType> List){

        StringDataType String_value = (StringDataType) List.get(0);
        IntegerDatatype index = (IntegerDatatype) List.get(1);
        IntegerDatatype length = (IntegerDatatype) List.get(2);
        StringDataType output = (StringDataType) List.get(3);
        // To determine the end of the string, the length of the entire string is determined, and intial index is entered into the  parameter.
        int Lengthof_String = String_value.getString_value().length();

        output.setString_value(String_value.getString_value().substring(index.getValue(),Lengthof_String));
    }
}

// Integer to real
class IntegertoReal extends FunctionNode{

    // Constructor
    public IntegertoReal(String Name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants, ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) {
        super(Name, parameters, constants, variables, statements);
    }
    // Type casting is used to convert the Integer to double
    public void execute(ArrayList<InterpreterDataType> collections){
        IntegerDatatype input = (IntegerDatatype) collections.get(0);
        RealDataType output =  new RealDataType(0);
        // Typecasted  the input value as a double
        output.setValue((double) input.getValue());
    }



}


// Square root function
class SquareRoot extends FunctionNode{

    // Constructor
    public SquareRoot(String Name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants, ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) {
        super(Name, parameters, constants, variables, statements);
    }

    // The math.sqrt is used to calculate the square root
    public void execute(List<InterpreterDataType> collection){

        // Input and output is initialized
     IntegerDatatype input = (IntegerDatatype)collection.get(0);


       IntegerDatatype output = new IntegerDatatype(0);



        // output is set by square rooting the input value
        output.setValue((int) Math.sqrt(input.getValue()));



        System.out.println(output.getValue());




    }


}
// Real to Integer

class RealtoInteger extends FunctionNode{

    // Constructor
    public RealtoInteger(String Name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants, ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) {
        super(Name, parameters, constants, variables, statements);
    }


    // In this case, type casting is used to convert a real to an integer.
    // same as Integer to real but with a different type cast
    public void execute(ArrayList<InterpreterDataType> collection){
        RealDataType input = (RealDataType) collection.get(0);
        IntegerDatatype output= new IntegerDatatype(0);
        output.setValue((int)output.getValue());



    }


}
// Get rando, generates random numbers
class getRandom extends FunctionNode{

    public getRandom(String Name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants, ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) {
        super(Name, parameters, constants, variables, statements);
    }

    public void execute(ArrayList<InterpreterDataType> List){
        IntegerDatatype output = new IntegerDatatype(0);
        // Random is used to generate random numbers
        Random random = new Random();
        // That value is set to the output value
        output.setValue(random.nextInt());






    }
}
// Start is the start index of an array
class Start extends FunctionNode{

    // Constructor
    public Start(String Name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants, ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) {
        super(Name, parameters, constants, variables, statements);
    }
    // Accepts a single var parameter
    public void execute(ArrayList<InterpreterDataType> list){
        ArrayDataType<?> start_output = (ArrayDataType<?>) list.get(0);
        // that value is retrieved and assigned to the variable start output
        start_output.setArray_Start(start_output.getStart());

    }

}

// End is the end index of the array
class End extends FunctionNode{


    public End(String Name, ArrayList<VariableNode> parameters, ArrayList<VariableNode> constants, ArrayList<VariableNode> variables, ArrayList<StatementNode> statements) {
        super(Name, parameters, constants, variables, statements);
    }

    //Accepts a single var parameter
    public void execute(ArrayList<InterpreterDataType> list){
        // Extract it from the list
        ArrayDataType<InterpreterDataType> end = (ArrayDataType<InterpreterDataType>) list.get(0);

        // The getter is used to set end with the end index.
        end.setArray_end(end.getEnd());




    }

}
