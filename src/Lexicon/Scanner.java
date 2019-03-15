package Lexicon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Scanner{
    // Constant Values
    private static final String FILENAME = "files/test_1.lolo";

    // Attributes
    //private LinkedTransferQueue<Token> outQueue;
    private FileInputStream fileInputStream;
    private int scope;
    private ArrayList<Token> tokens;

    // Constructor
    public Scanner() throws FileNotFoundException {
        //this.outQueue = queue;
        // Get the file
        scope = ConstValues.GLOBAL_SCOPE;
        tokens = new ArrayList<Token>();
        File file = new File(FILENAME);
        if (!file.exists()){
            System.out.println("L'arxiu no existeix!");
            throw new FileNotFoundException("No existeix l'arxiu");
        }else {
            fileInputStream = new FileInputStream(file);
        }
    }




    // Functions and Procedure
    public Token nextToken() throws IOException {

        if (tokens.size() == 0){
            char c = '-'; // we will read the file char by char
            StringBuilder stringBuilder = new StringBuilder();
            Token token = null;

            // while file is not readen at all or we don't have created any token
            while (fileInputStream.available() > 0 && token == null){
                // get character
                c = (char) fileInputStream.read();

                if (c == ';'){
                    // sentences delimiters
                    String sentence = stringBuilder.toString();
                    stringBuilder = new StringBuilder();

                    if (sentence.length() > 0){
                        // Block before ;
                        token = TokensKnown.checkToken(sentence);

                        //outQueue.put(token);
                        tokens.add(token);
                    }

                    // -- TOKEN of ';'
                    stringBuilder.append(c);
                    sentence = stringBuilder.toString();
                    stringBuilder = new StringBuilder();

                    token = TokensKnown.checkToken(sentence);

                    //outQueue.put(token);
                    tokens.add(token);

                }else if (c == ' ' || c == '\n' || c == '\r' || String.valueOf(c).equals(System.lineSeparator())){
                    // new block OR multi spaces OR new line
                    if (stringBuilder.toString().length() != 0){
                        // new instruction
                        String sentence = stringBuilder.toString();
                        stringBuilder = new StringBuilder();

                        token = TokensKnown.checkToken(sentence);

                        //outQueue.put(token);
                        tokens.add(token);
                    }


                }else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == ')'){
                    String sentence = stringBuilder.toString();

                    // Previous TOKEN (sentence)
                    if (sentence.length() > 0){
                        // new instruction
                        stringBuilder = new StringBuilder();

                        token = TokensKnown.checkToken(sentence);

                        //outQueue.put(token);
                        tokens.add(token);
                    }

                    // TOKEN of the Mathematic operation
                    stringBuilder.append(c);
                    sentence = stringBuilder.toString();
                    stringBuilder = new StringBuilder();

                    token = TokensKnown.checkToken(sentence);

                    //outQueue.put(token);
                    tokens.add(token);

                }else if (c == '(') {
                    String sentence = stringBuilder.toString();

                    stringBuilder.append(c);
                    sentence = stringBuilder.toString();
                    stringBuilder = new StringBuilder();

                    token = TokensKnown.checkToken(sentence);

                    //outQueue.put(token);
                    tokens.add(token);
                }else{
                        // we still in construction
                        stringBuilder.append(c);
                }
            }
            if (stringBuilder.toString().length() > 0){
                // Hemos ido leyendo pero encontramos el final del fichero
                token = TokensKnown.checkToken(stringBuilder.toString());
                //outQueue.put(token);
                tokens.add(token);
            }
        }

        if (tokens.isEmpty()){
            return null;
        }

        Token token = tokens.get(0);
        tokens.remove(0);
        return token;
    }
}
