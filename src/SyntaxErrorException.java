public class SyntaxErrorException extends Exception{ // CUSTOM EXCEPTION CLASS

    private Token2 Token;
    private char InvalidCharacter;

    private int linenumber;
// CONSTRUCTORS
    public SyntaxErrorException (int linenumber,Token2 Token, char InvalidCharacter){
        this.Token = Token;
        this.InvalidCharacter = InvalidCharacter;
       this.linenumber = linenumber;

    }

    public SyntaxErrorException() { // The parser now has a new syntax error method.
       System.out.println("Syntax error occured");
    }

    public RuntimeException FunctionValidity(FunctionCallNode node){
        return new RuntimeException( node.getName() + " is not a valid function name ");


    }

    public Exception MissingParameters(){
        return new Exception("Missing parameters or isVariadic is false" );
    }



// If the assignment node's right side is missing, then this is called
    public RuntimeException MissingRightSide(){
        return  new RuntimeException( "Right side is missing");
    }

    // GETTER TO RETURN TOKEN
    public Token2 getToken(){

        return Token;
    }
    // GETTER TO RETURN THE INVALID CHARACTER
    public char getInvalidCharacter(){

        return InvalidCharacter;
    }


    @Override
            // TO STRING METHOD TO OUTPUT THE ERROR MESSAGE
    public String toString(){

        return Token + " " + "The invalid character is" + "(" + InvalidCharacter + ")";


    }
    




}
