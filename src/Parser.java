import java.util.ArrayList;
import java.util.List;


public class Parser {



    NODE num = null;




    static FunctionNode programNode1 = null;

   static String elserec = "";

    iFNode ifreturn = null;

    static BooleanCompareNode whilereturn = null;

   // static LinkedList list  = new LinkedList();
    static int end = 0;

    static NODE for_from = null;
   static  NODE for_to = null;
    FunctionNode contnet = null;

    static NODE forReturn = null;

    //static BooleanCompareNode ifreturn = null;

    static String reference_name  = " ";
    VariableReferenceNode varReference = null;
     public static  BooleanCompareNode ifbool = null;
    ArrayList<StatementNode> ifstatements = null;

        static NODE bool = null;

    static  String assignment_Name  = "";

    ProgramNode programNode = new ProgramNode();

    public static FunctionNode function = null;

    static String class_name = "";

    public static ArrayList<VariableNode> constants = new ArrayList<>();

   public  static   ArrayList<VariableNode> parameters = new ArrayList<>();
    public static ArrayList<VariableNode> variables = new ArrayList<>();
      public static ArrayList<StatementNode> statements = new ArrayList<>();
    private static NODE temp;


    List<Token2> Tokens;

    public Parser(List<Token2> Tokens) { // Parser takes the list of tokens as the parameter
        this.Tokens = Tokens;

    }

    private Object matchAndRemove(Token2.tokenType type) { // Token type is accepted as a parameter


        if (type == Tokens.get(0).getType()) { // If the parameter type equals the Tokens at index 0 type then that token will be removed from the list.

            Tokens.remove(0);
            return Tokens.get(0);

        } else if (Tokens.isEmpty() || type != Tokens.get(0).getType()) { // If the list empty or if The next token doesn't match the inputed token type then it returns a null/

            return null;


        }


        return null;
    }


    private void expectEndsOfLine() throws SyntaxErrorException {
        for (int h = 0; h < Tokens.size(); h++) {
            if (Tokens.get(0).getType() == Token2.tokenType.ENDOFLINE) { //Removes the endofLine token if it's at matches the inputed token type.
                Tokens.remove(0); // MatchandRemove was causing an error here


            } else if (Tokens.size() == 0) {

                throw new SyntaxErrorException();
            }

        }


    }

    private Object peek(int number) { // Peek accepts and integer as parameter.
        if(Tokens.get(number).getType() != null){
            return Tokens.get(number).getType();
        } else {
            return null;
        }

    }

    public NODE parse() throws SyntaxErrorException {

        // when parse is called expression is called first.
        // NODE result = expression();









        // This loop continues until the token size is zero, therefore if expression is on a single line, there should be one end of line token after it.



        while(Tokens.size() != 0){



            try {
                function();

                expectEndsOfLine();
            } catch (Exception e) {
                IndexOutOfBoundsException exception;
            }


        }




        if(ProgramNode.ProgramMap.size() >= 1) {

            contnet = ProgramNode.ProgramMap.get(class_name);



            // prints out the token in Node class.
        }


        return contnet;
    }
    // boolcompare is very similar to expression and term

    private NODE boolCompare() throws SyntaxErrorException {

        NODE Left = expression();

        //Left calls expression

        // It checks for operators and then creates a Boolean compare node based on that.
        if (Tokens.get(0).getType() == Token2.tokenType.LESSTHAN) {
            matchAndRemove(Token2.tokenType.LESSTHAN);
            NODE Right = expression();
            Left = new BooleanCompareNode(Left, Right, BooleanCompareNode.BooleanCompare.LESSTHAN);
            // It's using the enums from  BoolCompare node
        } else if (Tokens.get(0).getType() == Token2.tokenType.GREATERTHAN) {
            matchAndRemove(Token2.tokenType.GREATERTHAN);
            NODE Right = expression();
            Left = new BooleanCompareNode(Left, Right, BooleanCompareNode.BooleanCompare.GREATERTHAN);


        }else if (Tokens.get(0).getType() == Token2.tokenType.LESSTHANOREQUALTO) {
            matchAndRemove(Token2.tokenType.LESSTHANOREQUALTO);
            NODE Right = expression();
            Left = new BooleanCompareNode(Left, Right, BooleanCompareNode.BooleanCompare.LESSTHANOREQUALTO);


        }else if (Tokens.get(0).getType() == Token2.tokenType.GREATERTHANOREQUALTO) {
            matchAndRemove(Token2.tokenType.LESSTHANOREQUALTO);
            NODE Right = expression();
            Left = new BooleanCompareNode(Left, Right, BooleanCompareNode.BooleanCompare.GREATERTHANOREQUALTO);


        }else if (Tokens.get(0).getType() == Token2.tokenType.EQUALS) {
            matchAndRemove(Token2.tokenType.EQUALS);
            NODE Right = expression();
            Left = new BooleanCompareNode(Left, Right, BooleanCompareNode.BooleanCompare.EQUAL);


        }else if (Tokens.get(0).getType() == Token2.tokenType.NOTEQUAL) {
            matchAndRemove(Token2.tokenType.NOTEQUAL);
            NODE Right = expression();
            Left = new BooleanCompareNode(Left, Right, BooleanCompareNode.BooleanCompare.NOTEQUAL);


        }










        return Left;
    }

    private NODE expression() throws SyntaxErrorException {


        NODE Left = Term();

        for (int i = 0; i < Tokens.size(); i++) {


            // In expression left side equals the Term then a operator, then the right side which is also a term.
            //Loops through all the tokens.

            if (Tokens.get(0).getType() == Token2.tokenType.MINUS) { // For the left half of the expression, term is called, and factor is called, which should return a number based on the input.
                matchAndRemove(Token2.tokenType.MINUS);  //I used enums to represent the math operators in my mathopNode
                NODE Right = Term(); // Term is called once again for the right side
                Left = new MathOpNode(Left, Right, MathOpNode.MATHOPERATION.MINUS); // A new mathnopnode with a left side, an operator, and a right side is created.

            } else if (Tokens.get(0).getType() == Token2.tokenType.PLUS) { // The same method as described above is applied here.
                matchAndRemove(Token2.tokenType.PLUS);
                NODE Right = Term();


                Left = new MathOpNode(Left, Right, MathOpNode.MATHOPERATION.ADD);



            }

        }




        temp = Left;


        return Left; // returns the Node.
    }

    private NODE Term() throws SyntaxErrorException {
        NODE Left = factor();

        for (int j = 0; j < Tokens.size(); j++) { // same form loop
            if (Tokens.get(0).getType() == Token2.tokenType.MULTIPLICATION) { // Same methods as above is used here
                matchAndRemove(Token2.tokenType.MULTIPLICATION);
                NODE Right = Term();
                Left = new MathOpNode(Left, Right, MathOpNode.MATHOPERATION.MULTIPLY);

            } else if (Tokens.get(0).getType() == Token2.tokenType.DIVISION) {
                matchAndRemove(Token2.tokenType.DIVISION);
                NODE Right = Term();
                Left = new MathOpNode(Left, Right, MathOpNode.MATHOPERATION.DIVISION);
            } else if (Tokens.get(0).getType() == Token2.tokenType.MOD) {
                matchAndRemove(Token2.tokenType.MOD);
                NODE Right = Term();

                Left = new MathOpNode(Left, Right, MathOpNode.MATHOPERATION.MOD);


            }


        }


        return Left; // returns the Node


    }


    private NODE factor() throws SyntaxErrorException {


        String variableReference = "";
        NODE LEFT = null;

        for (int k = 0; k < Tokens.size(); k++) { // Loops through all the tokens
            if (Tokens.get(0).getType() == Token2.tokenType.MINUS) { // If a minus is found it's a indication that it's going to be negative number.
                matchAndRemove(Token2.tokenType.MINUS); // Match and removes the Token
                String value = "-" + Tokens.get(0).getValue(); // concatenated a negative sign on to number.

                int int_value = Integer.parseInt(value); // changed the data type to an integer, since IntegerNode only accepts ints
                Tokens.remove(0); // Number token is also removed without the use of match and remove, since it creates duplicates other wise.
                LEFT = new IntegerNode(int_value);
                return LEFT;
            } else if (Tokens.get(0).getType() == Token2.tokenType.NUMBER) {
                if (Tokens.get(0).getValue().contains(".")) { // This deals with float by checking if the number has a decimal
                    float double_value = Float.parseFloat(Tokens.get(0).getValue()); // String is turned into float
                    matchAndRemove(Token2.tokenType.NUMBER);
                    LEFT = new RealNode(double_value);
                    // Match and remove is used to remove that number from the tokens
                    return LEFT; // new node is created
                }
                // If the token ia a regular number, the string is converted to an Integer and then Matchedandremoved from the exsisting tokens.
                int convert = Integer.parseInt(Tokens.get(0).getValue());
                matchAndRemove(Token2.tokenType.NUMBER);
                LEFT = new IntegerNode(convert);


                return LEFT;  // Returns the integer node
            } else if (Tokens.get(0).getType() == Token2.tokenType.LPAREN) { // If the token is Lparen, then match and remove are used to remove it, and expression is then called.

                matchAndRemove(Token2.tokenType.LPAREN);
                LEFT = expression();

            } else if (Tokens.get(0).getType() == Token2.tokenType.RPAREN)
            { // Rparen will be removed the same way.

                matchAndRemove(Token2.tokenType.RPAREN);
                // Factor now deals with reference nodes, so first it checks for and Identifier
            } else if (Tokens.get(0).getType() == Token2.tokenType.STRINGLITERAL) {

                LEFT = new StringNode(Tokens.get(0).getValue());

            } else if (Tokens.get(0).getType() == Token2.tokenType.CHARACTERLITERAL) {
                LEFT = new CharacterNode(Tokens.get(0).getValue().charAt(0));

            } else if(Tokens.get(0).getType() == Token2.tokenType.IDENTIFIER){
                variableReference = Tokens.get(0).getValue();
                matchAndRemove(Token2.tokenType.IDENTIFIER);
                // it could have a left bracket or not

                // if it has a left bracket, matchandremoved is called and expression is called to deal with the expression inside the bracket
                if(Tokens.get(0).getType() == Token2.tokenType.LEFTBRRACKET){
                    matchAndRemove(Token2.tokenType.LEFTBRRACKET);
                    // If it's another leftbracket expression deal with it repeatedly until it finds a right bracket
                    NODE exp = expression();
                    // Checks for the right bracket and removes it
                    if(Tokens.get(0).getType() == Token2.tokenType.RIGHTBRACKET){
                        matchAndRemove( Token2.tokenType.RIGHTBRACKET);
                        LEFT = new VariableReferenceNode(variableReference, exp);
                    } else {
                        // anything else an exception is thrown
                        throw  new SyntaxErrorException();
                    }


                } // if the left bracket doesn't exist a reference node is created
                else{
                    LEFT =  new VariableReferenceNode(variableReference ,null );


                }

            }


        }


        return LEFT;
    }
    // There are several bugs in this code that I was unable to debug, so any advice would be very appreciated.
    private NODE  function() throws SyntaxErrorException {
        NODE functioncall;



        // Loops through the tokes
        //Match and remove are used to eliminate tokens from a list, thus if the first token is defined, it is deleted from the list.


        if (Tokens.get(0).getType() == Token2.tokenType.DEFINE) {


            matchAndRemove(Token2.tokenType.DEFINE);

            if (Tokens.get(0).getType() == Token2.tokenType.IDENTIFIER) {
// That identifier is the name of the method

                if(Tokens.get(1).getType()== Token2.tokenType.LPAREN ) {

                    class_name = Tokens.get(0).getValue();
                    reference_name = class_name;

                }


                matchAndRemove(Token2.tokenType.IDENTIFIER);
            }


        }
// Looks for an identifier



// Searches for left parenthesis, matches and removes it, and then calls parameterDeclaration to handle parameter
        else if (Tokens.get(0).getType() == Token2.tokenType.LPAREN) {

            matchAndRemove(Token2.tokenType.LPAREN);
            parameters = parameterDeclaration();







            if (Tokens.get(0).getType() == Token2.tokenType.RPAREN) {


                matchAndRemove(Token2.tokenType.RPAREN);


            }

            // endofline is also expected and is removed from the list
             if (Tokens.get(0).getType() == Token2.tokenType.ENDOFLINE) {

                matchAndRemove(Token2.tokenType.ENDOFLINE);



            }


        }

// searches for the R side of the parenthesis and removes it from the list

        // then expects constants keyword word and invokes variable method.

        else if (Tokens.get(0).getType() == Token2.tokenType.CONSTANTS) {

            constants = constvariables();




            //matchAndRemove(Token2.tokenType.ENDOFLINE);


            // Then an ident is expected, and the token is deleted from the list, and the expression is called.

            // Then a dedent is expected and that is also removed from the list


        } else if (Tokens.get(0).getType() == Token2.tokenType.VARIABLES) {


            variables = variables();




        } else{


            statements = statements();




            function = new FunctionNode(class_name, parameters, constants, variables, statements);







            if (function != null && !statements.isEmpty()) {




                ProgramNode.ProgramMap.put(class_name, function);



                // This adds all built-in nodes by name to the program node hashmap.
                ProgramNode.ProgramMap.put("Read", new BuiltinRead(class_name,parameters,constants,variables,statements));
                ProgramNode.ProgramMap.put("Write", new BuiltinWrite(class_name,parameters,constants,variables,statements));
                ProgramNode.ProgramMap.put("SquareRoot", new SquareRoot(class_name,parameters,constants,variables,statements));
                ProgramNode.ProgramMap.put("Left", new Left(class_name,parameters,constants,variables,statements));
                ProgramNode.ProgramMap.put("Right", new Right(class_name,parameters,constants,variables,statements));
                ProgramNode.ProgramMap.put("Substring", new Substring(class_name,parameters,constants,variables,statements));
                ProgramNode.ProgramMap.put("GetRandom",new getRandom(class_name,parameters,constants,variables,statements));
                ProgramNode.ProgramMap.put("IntegertoReal",new IntegertoReal(class_name,parameters,constants,variables,statements));
                ProgramNode.ProgramMap.put("Realtointeger", new RealtoInteger(class_name,parameters,constants,variables,statements));
                ProgramNode.ProgramMap.put("Start", new Start(class_name,parameters,constants,variables,statements));
                ProgramNode.ProgramMap.put("End", new End(class_name,parameters,constants,variables,statements));

                if(Tokens.get(0).getType() == Token2.tokenType.DEFINE){

                    class_name = " ";
                    statements = new ArrayList<>(); // Create a new empty ArrayList for statements
                    variables = new ArrayList<>(); // Create a new empty ArrayList for variables
                    parameters = new ArrayList<>(); // Create a new empty ArrayList for parameters
                    constants = new ArrayList<>();
                }






            }





        }












            //
            //
            // A new programnode has been created. The function node has been added to the hashmap.












        // function node is created











        //Tokens.remove(0);






       return ProgramNode.ProgramMap.get(class_name);
    }

    private ArrayList<VariableNode> parameterDeclaration() {


        String[] variableName = new String[10];
        int i = 0;
        NODE dataType =null;
        boolean changeable = false;
        int from = 0;
        int to = 0;

        // loops over the list until it reaches the right parenthesis
        while (Tokens.get(0).getType() != Token2.tokenType.RPAREN) {


            // First identifier is the variabke name
            if (Tokens.get(0).getType() == Token2.tokenType.IDENTIFIER) {
                // checks if there is a colon after the identifier by looking ahead
                if (Tokens.get(1).getType() == Token2.tokenType.COLON) {
                    // variable name is set to the identifier's value

                    variableName[i] = Tokens.get(0).getValue();

                    i++;

                    // Both tokens are removed from the list
                    matchAndRemove(Token2.tokenType.IDENTIFIER);
                    matchAndRemove(Token2.tokenType.COLON);

                } else if(Tokens.get(1).getType() == Token2.tokenType.COMMA){


                    variableName[i] = Tokens.get(0).getValue();

                    i++;

                    // Both tokens are removed from the list
                    matchAndRemove(Token2.tokenType.IDENTIFIER);
                    matchAndRemove(Token2.tokenType.COMMA);
                }



                else {
                    return null;
                }
                //verifying the data types and assigning the dataType variable if the data type is found
            } else if (Tokens.get(0).getType() == Token2.tokenType.INTEGER) {

                dataType = new IntegerNode(0);



                matchAndRemove(Token2.tokenType.INTEGER);

            } else if (Tokens.get(0).getType() == Token2.tokenType.REAL) {
                dataType = new RealNode(0);
                matchAndRemove(Token2.tokenType.REAL);
                //verifying the data types and assigning the dataType variable if the data type is found
            } else if (Tokens.get(0).getType() == Token2.tokenType.BOOLEAN) {
                dataType = new BooleanNode(true);
                matchAndRemove(Token2.tokenType.BOOLEAN);
                //verifying the data types and assigning the dataType variable if the data type is found
            } else if (Tokens.get(0).getType() == Token2.tokenType.STRING) {
                dataType = new StringNode("");
                matchAndRemove(Token2.tokenType.STRING);

                // If a comma or a semicolon is found, the item is deleted from the list.
            }  else if (Tokens.get(0).getType() == Token2.tokenType.VAR) {
                changeable = true;
                if (Tokens.get(1).getType() == Token2.tokenType.IDENTIFIER) {
                    // Verify that an identifier exists immediately following the var key word and that it is assigned to the variable name.
                    matchAndRemove(Token2.tokenType.VAR);
                    variableName[i] = Tokens.get(0).getValue();
                    if (Tokens.get(1).getType() == Token2.tokenType.COLON) {
                        matchAndRemove(Token2.tokenType.COLON);
                    }
                } else {

                    return null;
                }
            }




        }



       /** else if (Tokens.get(0).getType() == Token2.tokenType.COMMA || Tokens.get(0).getType() == Token2.tokenType.SEMICOLON) {
            if (Tokens.get(0).getType() == Token2.tokenType.COMMA) {
                matchAndRemove(Token2.tokenType.COMMA);
            } else {
                matchAndRemove(Token2.tokenType.SEMICOLON);
                parameters.add(new VariableNode(variableName, dataType, null, changeable, from, to));


            }
            // If the var key word is found inside the parentheses, changeable is set to true.
        }
        **/




        // A new variable node is added to the arraylist

        for(int j = 0; j < i; j++) {

            parameters.add(new VariableNode(variableName[j],dataType, null, changeable, from, to));
        }



        return parameters;
    }
    private ArrayList<VariableNode> variables() throws SyntaxErrorException {
        String const_Name = "";
        String variable_Name = "";
        NODE variable_DataType = null;
        String Identifier;
        boolean changeable = true;
        NODE const_Datatype = null;
        int from = 0;
        int to = 0;

       float Realfrom = 0.0F;
       float Realto = 0.0F;
       float range  = (Realfrom + Realto);



        while (Tokens.get(0).getType() != Token2.tokenType.ENDOFLINE){



            if (Tokens.get(0).getType() == Token2.tokenType.VARIABLES) {

                // changeable is set to true;


                matchAndRemove(Token2.tokenType.VARIABLES);
            }
            // next identifiers are the variable names

            // while is used to get all the variable names

            else  if (Tokens.get(0).getType() == Token2.tokenType.IDENTIFIER) {
                // Variable names are assigned, and new Varibles nodes are added to the variables arraylist.
                variable_Name = Tokens.get(0).getValue();





                // each tokens are removed after they are used
                matchAndRemove(Token2.tokenType.IDENTIFIER);
            }
            else if (Tokens.get(0).getType() == Token2.tokenType.COMMA) {

                // commas are removed after each variables
                matchAndRemove(Token2.tokenType.COMMA);

                variables.add(new VariableNode(variable_Name, variable_DataType, null, changeable, from, to));


            } else if(Tokens.get(0).getType() == Token2.tokenType.COLON){
                matchAndRemove(Token2.tokenType.COLON);
            }








            // Several data types are verified below, and if they match, the arraylist is modified and the data type is added.

            else if (Tokens.get(0).getType() == Token2.tokenType.INTEGER) {



                variable_DataType = new IntegerNode(0);

                matchAndRemove(Token2.tokenType.INTEGER);

                if(Tokens.get(0).getType() == Token2.tokenType.FROM){
                    matchAndRemove(Token2.tokenType.FROM);
                    from = Integer.parseInt(Tokens.get(0).getValue());
                    matchAndRemove(Token2.tokenType.NUMBER);


                    if(Tokens.get(0).getType() == Token2.tokenType.TO){
                        matchAndRemove(Token2.tokenType.TO);
                        to = Integer.parseInt(Tokens.get(0).getValue());

                        matchAndRemove(Token2.tokenType.NUMBER);
                    }

                }
                //Several data types are verified below, and if they match, the arraylist is modified and the data type is added.
            } else if (Tokens.get(0).getType() == Token2.tokenType.STRING) {
                variable_DataType = new StringNode("");
                matchAndRemove(Token2.tokenType.STRING);

                if(Tokens.get(0).getType() == Token2.tokenType.FROM){
                    matchAndRemove(Token2.tokenType.FROM);
                    from = Integer.parseInt(Tokens.get(0).getValue());
                    matchAndRemove(Token2.tokenType.NUMBER);


                    if(Tokens.get(0).getType() == Token2.tokenType.TO){
                        matchAndRemove(Token2.tokenType.TO);
                        to = Integer.parseInt(Tokens.get(0).getValue());
                        if(to > 20){
                            to = 20;
                        }

                        matchAndRemove(Token2.tokenType.NUMBER);
                    }

                }




                //  Several data types are verified below, and if they match, the arraylist is modified and the data type is added.
            } else if (Tokens.get(0).getType() == Token2.tokenType.CHARACTER) {
                variable_DataType = new CharacterNode(' ');

                matchAndRemove(Token2.tokenType.CHARACTER);
            }else if(Tokens.get(0).getType() == Token2.tokenType.REAL){
                variable_DataType = new RealNode(0.0F);


                matchAndRemove(Token2.tokenType.REAL);


                if(Tokens.get(0).getType() == Token2.tokenType.FROM){
                    matchAndRemove(Token2.tokenType.FROM);

                    Realfrom = Float.parseFloat(Tokens.get(0).getValue());



                    matchAndRemove(Token2.tokenType.NUMBER);


                    if(Tokens.get(0).getType() == Token2.tokenType.TO){
                        matchAndRemove(Token2.tokenType.TO);
                        Realto = Float.parseFloat(Tokens.get(0).getValue());




                        matchAndRemove(Token2.tokenType.NUMBER);
                    }

                }
            }








        }


        if(variable_DataType instanceof RealNode){



            variables.add(new VariableNode(variable_Name, variable_DataType, (NODE) null, changeable,Realfrom,Realto));


        }else {


            variables.add(new VariableNode(variable_Name, variable_DataType, null, changeable, from, to));

        }


        for(int i =0; i < variables.size(); i++){
            variables.get(i).setType(variable_DataType);


        }

         if(Tokens.get(0).getType() == Token2.tokenType.ENDOFLINE){
            Tokens.remove(0);
        }






        // Then expects endofline and that is remvoed from the list










        return variables;
    }














    // This method takes care of constants and regular variables
    private ArrayList<VariableNode> constvariables() throws SyntaxErrorException {
        String const_Name = "";
        String variable_Name = "";
        NODE variable_DataType = null;
        String Identifier;
        boolean changeable = false;
        NODE const_Datatype = null;
        int from = 0;
        int to = 0;


        // If the token is constant, it will go into this block; otherwise, it will go into the block below.

        while (Tokens.get(0).getType() != Token2.tokenType.ENDOFLINE){
        if (Tokens.get(0).getType() == Token2.tokenType.CONSTANTS) {

            matchAndRemove(Token2.tokenType.CONSTANTS);


            // removes the constant key word token from the list
        } else if (Tokens.get(0).getType() == Token2.tokenType.IDENTIFIER) {

            const_Name = Tokens.get(0).getValue(); // The identifier is set as the variable name

            matchAndRemove(Token2.tokenType.IDENTIFIER);
        } else if (Tokens.get(0).getType() == Token2.tokenType.EQUALS) { // checks for equals
            matchAndRemove(Token2.tokenType.EQUALS);


        } else if (Tokens.get(0).getType() == Token2.tokenType.NUMBER) {


            if (Tokens.get(0).getValue().contains(".")) {

                float Real_number = Float.parseFloat(Tokens.get(0).getValue());
                const_Datatype = new RealNode(Real_number);
                matchAndRemove(Token2.tokenType.NUMBER);
            } else if (Tokens.get(0).getValue().contains("-")) {

                int negative_num = Integer.parseInt(Tokens.get(0).getValue());
                const_Datatype = new IntegerNode(negative_num);
                matchAndRemove(Token2.tokenType.NUMBER);
            } else {
                int num = Integer.parseInt(Tokens.get(0).getValue());

                const_Datatype = new IntegerNode(num);
                matchAndRemove(Token2.tokenType.NUMBER);
            }

        }
        // If it's an Identifier, it can be boolean, string, or char.
        else if (Tokens.get(0).getType().equals(Token2.tokenType.STRINGLITERAL)) {

            if (Tokens.get(0).getValue().equals("true")) { // Checks if the Identifier's value is "true" or "false."
                boolean bool_value = Boolean.parseBoolean(Tokens.get(0).getValue());  // If the value is either true or false, parse it and create a new boolean node.
                const_Datatype = new BooleanNode(bool_value);
                matchAndRemove(Token2.tokenType.IDENTIFIER);
            } else if (Tokens.get(0).getValue().equals("false")) {
                boolean bool_value = Boolean.parseBoolean(Tokens.get(0).getValue());
                const_Datatype = new BooleanNode(bool_value);
                matchAndRemove(Token2.tokenType.IDENTIFIER);

            } else { // If it is not one of the options listed above, a string node is created and assigned to the datatype variable.
                String identifier = Tokens.get(0).getValue();
                const_Datatype = new StringNode(identifier);
                // removes the tokens
                matchAndRemove(Token2.tokenType.STRINGLITERAL);

            }
        } else if (Tokens.get(0).getType() == Token2.tokenType.CHARACTERLITERAL) {


            // if the character is a letter then a character
            const_Datatype = new CharacterNode(' ');

            // removes the tokens
            matchAndRemove(Token2.tokenType.CHARACTERLITERAL);

        }

    }
             if(Tokens.get(0).getType() == Token2.tokenType.ENDOFLINE){
                Tokens.remove(0);


            }














        // fixes a bug


        //A new variable node is added to the constant arraylist.





        // IF the key word is a variable then it goes into this code bloc

// returns constants arraylist


        constants.add(new VariableNode(const_Name, const_Datatype, null, changeable, from, to));



        return constants;

    }
    // statement method
    // statements has the collection of statement nodes
    private ArrayList<StatementNode> statements () throws SyntaxErrorException {





        // Statements checks for an indent and removes that token from the list

        if(Tokens.get(0).getType() == Token2.tokenType.INDENT) {


            matchAndRemove(Token2.tokenType.INDENT);


        }
        // After the indent statement is called
        NODE statementReturn = statement();



        // if statementReturn is not null and the Token list is empty then a statement node is added to the list
        // Statement will return nodes until it encounters dedent
        while(statementReturn != null ) {


            statements.add((StatementNode) statementReturn);






            statementReturn = statement();














        }




        // Removes dedent from the list
         if(Tokens.get(0).getType() == Token2.tokenType.DEDENT){







           Tokens.remove(0);

        }



        // returns the collection os nodes
        return statements;
    }

    // Assignment method
    public NODE assignment() throws SyntaxErrorException {








        NODE assigmentnode = null;


        int Lbrac = 0;
        int Rbrac = 0;


        //NODE newname = variables.get(1).getType();

        int from = 0;
        int to = 0;
        boolean changeable = true;


        // This methods deals with variable assignment


        while (Tokens.get(0).getType() != Token2.tokenType.ENDOFLINE) {

            // checks for an indetifier, which becomes the variable name
            if (Tokens.get(0).getType() == Token2.tokenType.IDENTIFIER) {


                assignment_Name = Tokens.get(0).getValue();



                matchAndRemove(Token2.tokenType.IDENTIFIER);

                // it could be a left bracket or assignment operator
            } else if (Tokens.get(0).getType() == Token2.tokenType.LEFTBRRACKET) {

                matchAndRemove(Token2.tokenType.LEFTBRRACKET);

                // if it's a left bracket then  bool compare is called to deal with expression inside the bracket

                num =  boolCompare();






                // Then it expects a right bracket, which is removed from the list.
            }  else if (Tokens.get(0).getType() == Token2.tokenType.RIGHTBRACKET){
                matchAndRemove(Token2.tokenType.RIGHTBRACKET);
            }

            // When an assignment operator is expected, boolcompare() is called again to deal with the other side of the equal sign.
            else if (Tokens.get(0).getType() == Token2.tokenType.ASSIGNMENT) {


                matchAndRemove(Token2.tokenType.ASSIGNMENT);


                bool = boolCompare();


                // if boolcompare() is null them an exception is thrown regarding a missing right side
                if(bool == null){
                    RuntimeException exception = new SyntaxErrorException().MissingRightSide();
                    System.out.println(exception);
                }







                // A new variable reference node is created

                varReference = new VariableReferenceNode(assignment_Name, num);



                assignment_Name = "";


            } else if(Tokens.get(0).getType() == Token2.tokenType.WRITE){

                matchAndRemove(Token2.tokenType.WRITE);


            } else if (Tokens.get(0).getType() == Token2.tokenType.STRINGLITERAL) {
                matchAndRemove(Token2.tokenType.STRINGLITERAL);

            } else if (Tokens.get(0).getType() == Token2.tokenType.COMMA) {

                matchAndRemove(Token2.tokenType.COMMA);

            } else{

               return assigmentnode;

            }


        }

        // As long as the variable reference node is not null and bool compare yields a valid expression, a new assignment node is created.

        if(varReference != null && bool != null) {





            assigmentnode = new AssignmentNode(varReference, bool);


            if(Tokens.get(0).getType() == Token2.tokenType.ENDOFLINE){


               Tokens.remove(0);
            }






        }


        // Removes endofline token from the collection of tokens at the end








        // returns an assignment node that is currently added to the collection of statement nodes
        return assigmentnode;
    }
    // Statement is repeatedly called until a dedent is encountered

    public  NODE statement() throws SyntaxErrorException {



        NODE assignmentinStatment = null;










        // assignment is called


// These three staments are used to parse call parsing methods


        if(Tokens.size() > 1) {

            if (Tokens.get(0).getType() == Token2.tokenType.IF || Tokens.get(0).getType() == Token2.tokenType.ELSE || Tokens.get(0).getType() == Token2.tokenType.ELSIF) {

                assignmentinStatment = parseif();


                // After each call to a method the statement arraylist is emptied
            } else if (Tokens.get(0).getType() == Token2.tokenType.WHILE) {
                assignmentinStatment = parsewhile();


            } else if (Tokens.get(0).getType() == Token2.tokenType.FOR) {

                assignmentinStatment = parsefor();



            } else if (Tokens.get(0).getType() == Token2.tokenType.REPEAT) {
                assignmentinStatment = parseRepeat();
            } else if (Tokens.get(0).getType() == Token2.tokenType.IDENTIFIER && Tokens.get(1).getType() == Token2.tokenType.NUMBER || Tokens.get(1).getType() == Token2.tokenType.VAR || Tokens.get(1).getType() == Token2.tokenType.IDENTIFIER) {


                assignmentinStatment = functioncall();


            } else if (Tokens.get(0).getType() == Token2.tokenType.IDENTIFIER && Tokens.get(1).getType() == Token2.tokenType.ASSIGNMENT) { // If it's none of that then assignment is called


                assignmentinStatment = assignment();




            }


        } else {

            assignmentinStatment = null;
        }












// statement returns the assignment node from assignment();
        return assignmentinStatment;
    }

    public iFNode parseif() throws SyntaxErrorException {

        LinkedList list = new LinkedList();


       if(LinkedList.head != null){
          LinkedList.head = null;
       }




        NODE ifassigmmentcall;

        while (Tokens.get(0).getType() == Token2.tokenType.IF || Tokens.get(0).getType() == Token2.tokenType.ELSE || Tokens.get(0).getType() == Token2.tokenType.ELSIF) {

            // parseIF checks if the first key word in that line is "if"
            if (Tokens.get(0).getType() == Token2.tokenType.IF) {
                ArrayList<StatementNode> ifstatement = new ArrayList<>();

                matchAndRemove(Token2.tokenType.IF);


                // These boolcompare functions are used to deal with expression
                ifbool = (BooleanCompareNode) boolCompare();


                // Then is removed from that list and a endofline is expected
                if (Tokens.get(0).getType() == Token2.tokenType.THEN) {
                    matchAndRemove(Token2.tokenType.THEN);

                    if (Tokens.get(0).getType() == Token2.tokenType.ENDOFLINE) {
                        Tokens.remove(0);
                    } else {
                        return null;
                    }

                    if (Tokens.get(0).getType() == Token2.tokenType.INDENT) {

                        matchAndRemove(Token2.tokenType.INDENT);

                        while (Tokens.get(0).getType() != Token2.tokenType.DEDENT) {


                            ifassigmmentcall = assignment();
                            ifstatement.add((StatementNode) ifassigmmentcall);


                        }
                        if (Tokens.get(0).getType() == Token2.tokenType.DEDENT) {
                            matchAndRemove(Token2.tokenType.DEDENT);
                        }



                       LinkedList.inserdata(ifbool, ifstatement);














                    }


                }


            }
            /// It could be an else or elseif
           else if (Tokens.get(0).getType() == Token2.tokenType.ELSE) {

                ArrayList<StatementNode> ifstatement = new ArrayList<>();

                matchAndRemove(Token2.tokenType.ELSE);

                if (Tokens.get(0).getType() == Token2.tokenType.ENDOFLINE) {
                    matchAndRemove(Token2.tokenType.ENDOFLINE);

                    if (Tokens.get(0).getType() == Token2.tokenType.INDENT) {
                        matchAndRemove(Token2.tokenType.INDENT);

                        while (Tokens.get(0).getType() != Token2.tokenType.DEDENT) {





                            ifassigmmentcall = assignment();



                            ifstatement.add((StatementNode) ifassigmmentcall);


                        }

                        if (Tokens.get(0).getType() == Token2.tokenType.DEDENT) {

                            Tokens.remove(0);
                        }

                      ifbool = null;
                        list.inserdata(ifbool, ifstatement);











                        //return ifreturn;


                    }


                }

            } else if (Tokens.get(0).getType() == Token2.tokenType.ELSIF) {
                matchAndRemove(Token2.tokenType.ELSIF);
                ArrayList<StatementNode> ifstatement = new ArrayList<>();



                ifbool = (BooleanCompareNode) boolCompare();



                // Then is removed from that list and a endofline is expected
                if (Tokens.get(0).getType() == Token2.tokenType.THEN) {
                    matchAndRemove(Token2.tokenType.THEN);

                    if (Tokens.get(0).getType() == Token2.tokenType.ENDOFLINE) {
                        Tokens.remove(0);
                    }
                    if (Tokens.get(0).getType() == Token2.tokenType.INDENT) {

                        matchAndRemove(Token2.tokenType.INDENT);

                        while (Tokens.get(0).getType() != Token2.tokenType.DEDENT) {






                            ifassigmmentcall = assignment();
                            ifstatement.add((StatementNode) ifassigmmentcall);


                        }
                        if (Tokens.get(0).getType() == Token2.tokenType.DEDENT) {
                          Tokens.remove(0);
                        }


                        list.inserdata(ifbool, ifstatement);









                        ifbool = null;




                    }


                }

            }
        }


       ifreturn = LinkedList.head;











      return ifreturn;
    }

// This parses for statments
    public ForNODE parsefor () throws SyntaxErrorException {


    // This loops through all the tokes in a line and return the from and to by calling boolcompare
        ForNODE forReturn = null;
        ArrayList<StatementNode> forstatments = new ArrayList<>();
       NODE fornode = null;
        while (Tokens.get(0).getType() != Token2.tokenType.DEDENT) {

            // For loop expects the for key word then an identifier
            if (Tokens.get(0).getType() == Token2.tokenType.FOR) {
                matchAndRemove(Token2.tokenType.FOR);
            } else if (Tokens.get(0).getType() == Token2.tokenType.IDENTIFIER) {
                matchAndRemove(Token2.tokenType.IDENTIFIER);
            } else if (Tokens.get(0).getType() == Token2.tokenType.FROM) { // Bool compare is used to pare from and tp
                matchAndRemove(Token2.tokenType.FROM);

                for_from = boolCompare();





            } else if (Tokens.get(0).getType() == Token2.tokenType.TO) {
                matchAndRemove(Token2.tokenType.TO);
                for_to = boolCompare();

          // At the end endofline is expected and then removed from the list

            } else if (Tokens.get(0).getType() == Token2.tokenType.ENDOFLINE) {
                Tokens.remove(0);
            }

            if(Tokens.get(0).getType() == Token2.tokenType.INDENT){

                matchAndRemove(Token2.tokenType.INDENT);

                while (Tokens.get(0).getType() != Token2.tokenType.DEDENT){

                    fornode = statement();




                   forstatments.add((StatementNode) fornode);
                }

            }


        }

        if(Tokens.get(0).getType() == Token2.tokenType.DEDENT){

           Tokens.remove(0);
        }
            forReturn = new ForNODE(for_from, for_to,forstatments);




      return forReturn;
    }

     // This method parse the while loop
    public WhileNODE parsewhile () throws SyntaxErrorException {
        BooleanCompareNode whileExp = null;
        ArrayList<StatementNode> whilenode = new ArrayList<>();
        NODE whileStatement = null;
        WhileNODE whileNODE = null;

            // This expects the while key word and the method returns the expression retured from boolcompare()
           while (Tokens.get(0).getType() != Token2.tokenType.DEDENT) {
               if (Tokens.get(0).getType() == Token2.tokenType.WHILE) {
                  // First by checking the while key word
                   matchAndRemove(Token2.tokenType.WHILE);
                   whileExp = (BooleanCompareNode) boolCompare();
               } else if(Tokens.get(0).getType() == Token2.tokenType.ENDOFLINE){
                   matchAndRemove(Token2.tokenType.ENDOFLINE);
               }

               else if (Tokens.get(0).getType() == Token2.tokenType.INDENT){
                   matchAndRemove(Token2.tokenType.INDENT);

                   while (Tokens.get(0).getType() != Token2.tokenType.DEDENT){

                       whileStatement = assignment();


                       whilenode.add((StatementNode) whileStatement);


                   }
               }






           }

           if(Tokens.get(0).getType() == Token2.tokenType.DEDENT){
               matchAndRemove(Token2.tokenType.DEDENT);
           }

           whileNODE = new WhileNODE(whileExp,whilenode);





        return whileNODE;
    }
    public RepeatNODE parseRepeat() throws SyntaxErrorException {
        RepeatNODE repeatNode = null;
        BooleanCompareNode Repeatbool = null;
        NODE statement;

        ArrayList<StatementNode> RepeatStatments = new ArrayList<>();


        while (Tokens.get(0).getType() != Token2.tokenType.DEDENT){

            if(Tokens.get(0).getType() == Token2.tokenType.REPEAT){
                matchAndRemove(Token2.tokenType.REPEAT);
            } else if(Tokens.get(0).getType() == Token2.tokenType.UNTIL){
                matchAndRemove(Token2.tokenType.UNTIL);
                Repeatbool = (BooleanCompareNode) boolCompare();


            }else if(Tokens.get(0).getType() == Token2.tokenType.ENDOFLINE){
                matchAndRemove(Token2.tokenType.ENDOFLINE);
            }else if (Tokens.get(0).getType() == Token2.tokenType.INDENT){
                matchAndRemove(Token2.tokenType.INDENT);
                while (Tokens.get(0).getType() != Token2.tokenType.DEDENT){
                     statement = statement();



                     RepeatStatments.add((StatementNode) statement);


                }

            }

        }

        if(Tokens.get(0).getType() == Token2.tokenType.DEDENT){

            matchAndRemove(Token2.tokenType.DEDENT);
        }


        repeatNode = new RepeatNODE(Repeatbool, RepeatStatments);




       return repeatNode;
    }






// I didn't finish this, but this is suppose to parse the function call and then use another method to parse the functioncall node returned from here
    public StatementNode functioncall() throws SyntaxErrorException {

        String name;
        //ArrayList<VariableNode> parameters;
        ParameterNode parameternode = null;
        ArrayList<VariableReferenceNode> var_parameter = new ArrayList<>();
        NODE nonvar_parameter = null;
        ArrayList<NODE> nonvar_parameters = new ArrayList<NODE>();
        String num;
        FunctionCallNode function_return = null;
        if(Tokens.get(0).getType() == Token2.tokenType.IDENTIFIER){
            name = Tokens.get(0).getValue();









                matchAndRemove(Token2.tokenType.IDENTIFIER);
                while(Tokens.get(0).getType() != Token2.tokenType.ENDOFLINE){



                    if(Tokens.get(0).getType() == Token2.tokenType.VAR && Tokens.get(1).getType() == Token2.tokenType.IDENTIFIER || Tokens.get(0).getType()== Token2.tokenType.IDENTIFIER){
                        if(Tokens.get(0).getType() == Token2.tokenType.VAR) {
                            matchAndRemove(Token2.tokenType.VAR);
                        }
                         var_parameter.add(new VariableReferenceNode(Tokens.get(0).getValue(),null));



                        matchAndRemove(Token2.tokenType.IDENTIFIER);




                    } else if(Tokens.get(0).getType() == Token2.tokenType.COMMA ){

                        matchAndRemove(Token2.tokenType.COMMA);
                    }
                    else  {

                        nonvar_parameter = boolCompare();






                            nonvar_parameters.add(nonvar_parameter);









                    }

                }

                if(Tokens.get(0).getType() == Token2.tokenType.ENDOFLINE){

                  Tokens.remove(0);


                  parameternode = new ParameterNode(var_parameter,nonvar_parameters);









                }

                function_return = new FunctionCallNode(name,parameternode);





        } else return null;


        return function_return;
    }

}























