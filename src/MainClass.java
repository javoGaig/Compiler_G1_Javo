import Lexicon.Token;
import Syntactic.Parser;

import java.io.FileNotFoundException;
import java.util.concurrent.LinkedTransferQueue;

public class MainClass {
    public static SymbolTable symbolTable;

    public static void main(String[] args) {
        // Initialize symbol table
        symbolTable = new SymbolTable();

        // Initialize queues
        LinkedTransferQueue<Token> scannerOutParserInQueue = new LinkedTransferQueue<Token>();

        // Create Parser
        Parser parser = null;
        try {
            //parser = new Parser(scannerOutParserInQueue);
            parser = new Parser();

            parser.run();
            try {
                parser.join();
            } catch (InterruptedException e) {
                System.out.println("Parser interrupted!");
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Impossible to compile. File does not exist!");
            e.printStackTrace();
        }



    }
}
