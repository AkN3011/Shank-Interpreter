import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;


public class Lexer2 {


     public  ArrayList<Token2> Tokens = new ArrayList<Token2>();// This holds the tokens.



    HashMap<String, Token2.tokenType> knownWords = new HashMap<String, Token2.tokenType>(); // HOLDS THE KEY IDENTIFIERS


    private enum state {  // All the various states
        NONE,
        IDENTIFIER,
        NUMBERDOT,
        NUMBERNODOT,

        OPERATORS,
        STRINGANDCHARACTERLITERAL,

        COMMENT


    }

    public static int linenumber = 0;
    public static int indentlevel = 0;
    public static int endoflinechecker = 0;


    private static int indent = 0;

     String presentToken = "";

  public static state prevstate;


    public void lex(String input) throws Exception{
                                  // lex method that holds a single parameter

        linenumber += 1; // keeps track of the line number



        knownWords.put("while", Token2.tokenType.WHILE); // The key words I was able to find and sorry if I missed any
        knownWords.put("for", Token2.tokenType.FOR);
        knownWords.put("var", Token2.tokenType.VAR);
        knownWords.put("variables", Token2.tokenType.VARIABLES);
        knownWords.put("constants", Token2.tokenType.CONSTANTS);
        knownWords.put("write", Token2.tokenType.WRITE);
        knownWords.put("define", Token2.tokenType.DEFINE);
        knownWords.put("if", Token2.tokenType.IF);
        knownWords.put("elsif", Token2.tokenType.ELSIF);
        knownWords.put("else", Token2.tokenType.ELSE);
        knownWords.put("mod", Token2.tokenType.MOD);
        knownWords.put("from", Token2.tokenType.FROM);
        knownWords.put("then", Token2.tokenType.THEN);
        knownWords.put("to", Token2.tokenType.TO);

        knownWords.put("integer", Token2.tokenType.INTEGER);
        knownWords.put("real", Token2.tokenType.REAL);
        knownWords.put("string", Token2.tokenType.STRING);
        knownWords.put("repeat", Token2.tokenType.REPEAT);
        knownWords.put("until",Token2.tokenType.UNTIL);
        knownWords.put("boolean", Token2.tokenType.BOOLEAN);
        knownWords.put("array", Token2.tokenType.ARRAY);
        knownWords.put("character", Token2.tokenType.CHARACTER);
        knownWords.put("of", Token2.tokenType.OF);

        
               // Empty string that used as  accumulator.
        state currentstate = state.NONE;

        // currentstate  holds all the various states.



        if(prevstate == state.COMMENT){  //Prevestate is still reading comments if it is in Comment state, so current state is set to comment state
    currentstate = state.COMMENT;
        } else {
           currentstate = state.NONE;
        }

        // The state machine starts at none
        for (int i = 0; i < input.length(); i++) {



            char cha = input.charAt(i);




            // iterating through each character
            switch (currentstate) {

                case NONE:
                    // Switch would be used to move through various states

                    if (cha == '\t'){ // Indetation is taken care of here
                        if(prevstate == state.COMMENT){
                            currentstate = state.COMMENT;
                        }
                        while (input.charAt(i) == '\t'){ // counts each tab until there are not remaing tabs
                            indent++;
                           i++;
                        }
                        i--;
                         // decrement by one since since it stops after encounter the first letter in the line.

                        if(indent > indentlevel){ // if indent is greater than than the indenlevel a token is created

                            indentlevel = 0;
                            indentlevel = indent;
                                          //The old indent is reset to zero, and the new indent level is sav
                            Tokens.add(new Token2(linenumber, "Token INDENT level " + indentlevel, Token2.tokenType.INDENT));
                             indent = 0; // After the token is created indent goes back to zero and begins once again


                        } else if(indent == indentlevel){  // If indent is equal to indent level, it remains at the same level
                            indentlevel = indent;
                            indent = 0;


                        } else if(indent < indentlevel){ // If the current indent is less than the pevious indent level a token will be created for a DEDENT
                            indentlevel = 0;  // The old indent level is zeroed out, and a new indent level is added.
                            indentlevel = indent;
                            Tokens.add(new Token2(linenumber, "Token DEDENT level " + indentlevel, Token2.tokenType.DEDENT));
                           indent = 0;
                        }


                    }
                   else if (cha == '+') {                         // Creating tokens for various arthimetic operators

                        Tokens.add(new Token2(linenumber, "", Token2.tokenType.PLUS));
                        currentstate = state.NONE;

                    } else if (cha == '-') {
                        Tokens.add(new Token2(linenumber, "", Token2.tokenType.MINUS));
                        currentstate = state.NONE;
                    } else if (cha == '/') {
                        Tokens.add(new Token2(linenumber, "", Token2.tokenType.DIVISION));
                        currentstate = state.NONE;

                    } else if (cha == '*') {
                        Tokens.add(new Token2(linenumber, "", Token2.tokenType.MULTIPLICATION));
                        currentstate = state.NONE;


                    } else if (cha == '>') {

                        presentToken += cha;
                        currentstate = state.OPERATORS;
                    } else if (cha == ':') {

                        if (input.charAt(i + 1) == '=') {         // Creating an assignment operator by checking if current index + 1 =  "="

                            Tokens.add(new Token2(linenumber, "", Token2.tokenType.ASSIGNMENT));
                            currentstate = state.NONE;
                            i++;

                        } else {

                            Tokens.add(new Token2(linenumber, "", Token2.tokenType.COLON));  // if not a colon token is created
                            currentstate = state.NONE;
                        }


                    } else if (cha == '=') {  // if it's just an equal sign, a equals token is created

                        Tokens.add(new Token2(linenumber, "", Token2.tokenType.EQUALS));
                    } else if (cha == '<') { // Token for lest than symbol

                        presentToken += cha;
                        currentstate = state.OPERATORS;
                    } else if (Character.isWhitespace(cha)) {

                            currentstate = state.NONE;
                        }
                    else if (Character.isLetter(cha)) {

                        presentToken += cha;
                        currentstate = state.IDENTIFIER;        // If those characters are found, more tokens are created.
                    } else if (Character.isDigit(cha)) {
                        presentToken += cha;
                        currentstate = state.NUMBERNODOT;
                    } else if (cha == '.') {
                        presentToken += cha;
                        currentstate = state.NUMBERDOT;
                    } else if (cha == '\"') {  // This handles string and character literals by changing the current state.


                        currentstate = state.STRINGANDCHARACTERLITERAL;
                    } else if (cha == '\'') {
                        presentToken += cha;
                        currentstate = state.STRINGANDCHARACTERLITERAL;
                    } else if (cha == '(') {

                        Tokens.add(new Token2(linenumber, "", Token2.tokenType.LPAREN));

                    } else if (cha == ')') {
                        Tokens.add(new Token2(linenumber, "", Token2.tokenType.RPAREN));

                    } else if (cha == ',') {
                        Tokens.add(new Token2(linenumber, "", Token2.tokenType.COMMA));
                    } else if (cha == '{' ) { // Enters comment state
                      currentstate = state.COMMENT;
                    }




                     else if (cha == ';') {
                        Tokens.add(new Token2(linenumber, "", Token2.tokenType.SEMICOLON));


                    } else if(cha == '['){
                         Tokens.add(new Token2(linenumber,"", Token2.tokenType.LEFTBRRACKET));
                    } else if(cha == ']'){
                         Tokens.add(new Token2(linenumber,"", Token2.tokenType.RIGHTBRACKET));
                    }

                     else { //A custom exception for any characters that are not previously defined is entered.


                        if (Tokens.size() > 1) {
                            int num = Tokens.size();
                            // The is outputs the previous token prior to the error
                            throw new SyntaxErrorException(linenumber, Tokens.get(num - 1), cha);
                        } else if (Tokens.size() == 0) {
                                // if the current token size is empty I can't get the previoys token, so I created a else if statement for that
                                //often occurs at the start of a new line.
                            throw new Exception("line number " + "(" + linenumber + ")" + "  invalid character is " + cha);

                        }



                    }
                    break;
                case NUMBERDOT:       // The NUMBERDOT state determines if the value is a digit or a dot and adds it to the accumulator.
                    if (Character.isDigit(cha) || cha == '.') {
                        presentToken += cha;
                        currentstate = state.NUMBERDOT;          // The state is updated after each character
                    } else {
                        Tokens.add(new Token2(linenumber, presentToken, Token2.tokenType.NUMBER));   // When the character is neither a number or a dot, a new token is produced.
                        currentstate = state.NONE;
                        i--;                      // The state will be reset to its original state, and the accumulator will be emptied.
                        presentToken = "";

                    }

                    break;


                case NUMBERNODOT:
                    if (Character.isDigit(cha)) {      // Just checks if the character is digit and state remains the NUMBERNOTDOT
                        // adds each character
                        presentToken += cha;
                        currentstate = state.NUMBERNODOT;
                    } else if (cha == '.') {         // If the character is a dot, the state is switched to NUMBERDOT, and the character is added to the presenttoken.
                        presentToken += cha;
                        currentstate = state.NUMBERDOT;


                    } else {

                        Tokens.add(new Token2(linenumber, presentToken, Token2.tokenType.NUMBER));// Anything else a new token is created for NUMBER
                        presentToken = "";
                        i--;  // Fixed a bug
                        currentstate = state.NONE;

                    }
                    break;

                case IDENTIFIER:
                    if (Character.isLetterOrDigit(cha)) {
                        presentToken += cha;
                        currentstate = state.IDENTIFIER;
                    } else {
                        if (knownWords.containsKey(presentToken)) {  // If the word is inside the hashamap a token will be created for that identifier
                            Tokens.add(new Token2(linenumber, "", knownWords.get(presentToken)));
                        } else {
                            Tokens.add(new Token2(linenumber, presentToken, Token2.tokenType.IDENTIFIER)); // else a regular a token will be created
                        }
                        presentToken = "";

                        i--;

                        currentstate = state.NONE;
                    }
                    break;

                case OPERATORS:


                    if (presentToken.equals("<")) { // //If there is an equal sign at the current index, it will be added to the current token and an LESSTHNOREQUAL TOKEN will be generated later.


                        if (input.charAt(i) == '=') {
                            presentToken += cha;
                            currentstate = state.OPERATORS;
                        }
                        else if( input.charAt(i) == '>'){
                            Tokens.add(new Token2(linenumber,"", Token2.tokenType.NOTEQUAL));
                            presentToken = "";
                            currentstate = state.NONE;


                        }



                        else {
                            Tokens.add(new Token2(linenumber, "", Token2.tokenType.LESSTHAN));
                            presentToken = ""; // Lessthan token is created
                            currentstate = state.NONE;
                        }


                    } else if (presentToken.equals("<=")) {
                        Tokens.add(new Token2(linenumber, "", Token2.tokenType.LESSTHANOREQUALTO));
                        presentToken = "";
                        currentstate = state.NONE;


                    }
                    if (presentToken.equals(">")) {  // I implemented the same concept as "Lessthan/equalto"


                        if (input.charAt(i) == '=') {
                            presentToken += cha;
                            currentstate = state.OPERATORS;
                        } else {
                            Tokens.add(new Token2(linenumber, "", Token2.tokenType.GREATERTHAN));
                            presentToken = "";
                            currentstate = state.NONE;
                        }


                    } else if (presentToken.equals(">=")) {
                        Tokens.add(new Token2(linenumber, "", Token2.tokenType.GREATERTHANOREQUALTO));
                        presentToken = "";
                        currentstate = state.NONE;


                    }
                    break;

                case STRINGANDCHARACTERLITERAL:

                    if (cha == '\"') { // String literal, so the first half of the string was counted in State.NONE, which is included inside the variable presenttoken.
                                    // if the second second quotation mark is encountered it will be added to the present token variable
                        // and the length will be checked to make sure both quoation are in there.


                        System.out.println(presentToken);

                         //With this if statement, I tried using two quottion marks, but it only made the situation worse.

                            Tokens.add(new Token2(linenumber, presentToken, Token2.tokenType.STRINGLITERAL));
                            presentToken = "";
                            currentstate = state.NONE;

                    } else {
                        currentstate = state.STRINGANDCHARACTERLITERAL;
                        presentToken += cha;
                    }
                    if (cha == '\'') { // Same implementation as STRINGLITERAL

                        presentToken += cha;
                        if (presentToken.length() == 2) {
                            Tokens.add(new Token2(linenumber, "", Token2.tokenType.CHARACTERLITERAL));
                            presentToken = "";
                            currentstate = state.NONE;
                        }
                    } else {
                        currentstate = state.STRINGANDCHARACTERLITERAL;
                    }

                    break;


                case COMMENT: // This state machine deals with  comments
                             // It was so buggy earlier , so I created comment state

                    if(cha == '}'){
                    currentstate =state.NONE;
                    prevstate = state.NONE;


                    } else  { // This state continues until the "right curly braces" is found.


                        currentstate = state.COMMENT;
                        //This is how multiline comments are handled .
                     prevstate = currentstate;  // This is used to store the current state after each iteration.



                     }
                 break;

                default:     // As the default case, an exception



                       throw new IllegalStateException("Invalid character " + cha);

            }


        }


            //( This was done to fix a bug)

            if (!presentToken.equals("")) {
                // This ensures that the presentoken is  empty before adding the ENDOFLINE token.
                if (knownWords.containsKey(presentToken)) {
                    Tokens.add(new Token2(linenumber, "", knownWords.get(presentToken)));
                } else if (currentstate == state.IDENTIFIER) {
                    Tokens.add(new Token2(linenumber, presentToken, Token2.tokenType.IDENTIFIER));

                } else if (currentstate == state.NUMBERDOT || currentstate == state.NUMBERNODOT) {
                    Tokens.add(new Token2(linenumber, presentToken, Token2.tokenType.NUMBER));
                }
                presentToken = "";
            }

            if(linenumber != endoflinechecker){
                if(Tokens.size() > 0) {
                    if(input.length() !=0) {
                        Tokens.add(new Token2(linenumber, "ENDOFLINE", Token2.tokenType.ENDOFLINE));
                        endoflinechecker = linenumber;
                    }
                }

             
            }

            if (input.isEmpty()) {
                // Dedent tokens will be created
                // This outputs dedent token if the identlevel is isn't zero at the end

                while (indentlevel > 0) {

                    Tokens.add(new Token2(linenumber, "", Token2.tokenType.DEDENT));
                    indentlevel--;
                }

               // End of line token at the end

            }



    }

}

















