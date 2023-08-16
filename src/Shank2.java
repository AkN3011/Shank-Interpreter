

import org.w3c.dom.Node;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Shank2 {


    static ArrayList<Token2> Filledtokens = new ArrayList<Token2>();
    static int  tokencheck = 0;
    public static int file_Size = 0;


    public static void main(String[] args) throws Exception {
        Lexer2 lex = new Lexer2();

        if (args.length > 1) {       // Args must have one argument.

            throw new Exception("Invalid arguments");
        }

        String filename = "C:\\Users\\akhil\\Downloads\\Testing1.txt";    //The argument must be at index 0;
        Path path = Paths.get(filename);

        try {
            List<String> readAllLines = Files.readAllLines(path);// Path to the file is given to readallLines function

            if (readAllLines.isEmpty()) {// checks if the file is empty, if it is, ENDOFLINE token will be emitted.

                Token2 endOfLine = new Token2(0,"", Token2.tokenType.ENDOFLINE);
                System.out.println(endOfLine);

            }




            else {          // If not, each string is inserted as a parameter to the lex function in the Lexer class.
                try {


                    for (String str : readAllLines) {
                        file_Size++;


                        lex.lex(str); // Str is the lex method parameter.

                        System.out.println(lex.Tokens);


                    }
                }catch (IndexOutOfBoundsException e){}



                Parser parse = new Parser(lex.Tokens);
                try {

                    FunctionNode contnet = parse.contnet;



                   while (contnet == null){


                        parse.parse();


                       contnet = parse.contnet;









                   }
                } catch (IndexOutOfBoundsException e) {

                }

               SemanticAnalysis semanticAnalysis = new SemanticAnalysis();


                semanticAnalysis.CheckAssignments(ProgramNode.ProgramMap.get(parse.contnet.getName()));


               Interpreter interpreter = new Interpreter();


               ArrayList<FunctionCallNode> functionCallNodes = new ArrayList<>();






               try {
                   for (StatementNode node : parse.contnet.getStatements()) {


                       if (node instanceof FunctionCallNode) {


                           functionCallNodes.add((FunctionCallNode) node);


                           FunctionNode functionNode = (FunctionNode) interpreter.FunctionLookup((FunctionCallNode) node);
                           if (functionNode.getClass().equals(FunctionNode.class)) {


                              interpreter.Functioncall(node);



                               parse.contnet.getStatements().remove(node);

                           }


                       }

                   }

               }catch (Exception e){
                   
               }
                    try {
                        for (StatementNode node : parse.contnet.getStatements()) {

                            if (node instanceof FunctionCallNode) {




                                for (FunctionCallNode builtin : functionCallNodes) {

                                    FunctionNode builtin_functionNode = (FunctionNode) interpreter.FunctionLookup((FunctionCallNode) builtin);

                                    if (builtin_functionNode instanceof BuiltinWrite) {

                                        interpreter.Functioncall(builtin);
                                        functionCallNodes.remove(builtin);
                                    } else if (builtin_functionNode instanceof BuiltinRead) {

                                        interpreter.Functioncall(builtin);
                                        functionCallNodes.remove(builtin);
                                    }else if(builtin_functionNode instanceof SquareRoot){
                                        interpreter.Functioncall(builtin);
                                        functionCallNodes.remove(builtin);
                                    } else if (builtin_functionNode instanceof Left) {
                                        interpreter.Functioncall(builtin);
                                        functionCallNodes.remove(builtin);

                                    } else if (builtin_functionNode instanceof Right) {
                                        interpreter.Functioncall(builtin);
                                        functionCallNodes.remove(builtin);

                                    } else if (builtin_functionNode instanceof getRandom) {
                                        interpreter.Functioncall(builtin);
                                        functionCallNodes.remove(builtin);
                                    } else if (builtin_functionNode instanceof IntegertoReal) {
                                        interpreter.Functioncall(builtin);
                                        functionCallNodes.remove(builtin);

                                    } else if (builtin_functionNode instanceof RealtoInteger) {
                                        interpreter.Functioncall(builtin);
                                        functionCallNodes.remove(builtin);

                                    } else if (builtin_functionNode instanceof Start) {
                                        interpreter.Functioncall(builtin);
                                        functionCallNodes.remove(builtin);

                                    } else if (builtin_functionNode instanceof End) {
                                        interpreter.Functioncall(builtin);
                                        functionCallNodes.remove(builtin);

                                    }


                                }
                            }
                        }
                    }catch (Exception e){

                    }


















            /**catch (Exception e) { // If an error occurs during the lexing process, an exception is thrown.


                    System.out.println("Error during lexing occurred");
               */ }




        }catch (
                IOException e) { // This exceptions will be executed if an error happens during the file reading process.
            System.out.println("File wasn't readable");


        }


            }


}




