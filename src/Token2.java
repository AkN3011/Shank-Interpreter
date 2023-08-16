public class Token2 {


    // enum containing name of the tokens
    public enum tokenType  {
        IDENTIFIER,
        NUMBER,
        ENDOFLINE,
        WHILE,
        FOR,
        VAR,
        VARIABLES,
        CONSTANTS,
        WRITE,
        DEFINE,
        IF,
        ELSIF,
        ELSE,
        PLUS,
        MINUS,
        MULTIPLICATION,
        DIVISION,

        LESSTHAN,

        GREATERTHAN,

        ASSIGNMENT,

        LESSTHANOREQUALTO,

        GREATERTHANOREQUALTO,

        MOD,
        FROM,

        STRINGLITERAL,
        CHARACTERLITERAL,

        THEN,

        TO,
        LPAREN,
        RPAREN,

        COMMA,
        COLON,
        INDENT,
        DEDENT,

        INTEGER,
        REAL,
        STRING,
        EQUALS,

        SEMICOLON
        ,REPEAT,
        UNTIL,
        BOOLEAN,
        ARRAY,
        CHARACTER,

        NOTEQUAL,

        OF,

        LEFTBRRACKET,
        RIGHTBRACKET


    }


    private String value; // The value of the  token
    private tokenType type; // Holds the type of token

    private int linenumber;

    // constructors
    public Token2(int linenumber, String value, tokenType type){
        this.value = value;
        this.type = type;
        this.linenumber = linenumber;
    }
    // getters to return the value
    public String getValue() {
        return value;
    }

    public tokenType getType(){
        return type;
    }



   @Override      // tostring method to printout the tokentype and the input value that equals the token type
    public  String toString(){
        return " line number "+ linenumber+ " " + type + "(" + value + ")";


    }



}