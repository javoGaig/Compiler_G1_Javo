package Lexicon;

//import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.LinkedTransferQueue;

public class ScannerTest {

    /*
    @Test
    public void testScanner() {
        LinkedTransferQueue<Token> scannerOutParserInQueue = new LinkedTransferQueue<Token>();
        Scanner scanner = new Scanner(scannerOutParserInQueue);
        int error = 0;
        scanner.run();
        try {
            scanner.join();
            error = comprovaTokens(scannerOutParserInQueue);
        } catch (InterruptedException e) {
            e.printStackTrace();
            error = 1;
        }

        assertEquals(error, 0);
    }

    private int comprovaTokens(LinkedTransferQueue<Token> scannerOutParserInQueue) {
        int size = scannerOutParserInQueue.size();
        int error = 0;

        for(Token t : scannerOutParserInQueue){
            if (!(TokensKnown.checkToken(t.getName()).getType_id() == t.getType_id())){
                return 1;
            }
        }
        return 0;
    }*/

    /**
     * Este test sirve para ver si el scanner recoge bien los tokens y los crea
     * correctamente, con el correspondiente ConstValue y su nombre.
     * Funciona con el fichero test_scanner.lolo en la cual solo hay los tipo de
     * tokens.
     * En tokensknown no funciona string value, char_value, ( )
     */
    @Test
    public void scannerTest() throws FileNotFoundException {

        Scanner scanner = new Scanner();


        //test test_scanner.lolo
        ArrayList<Token> tokens = new ArrayList<Token>();
        tokens.add(new Token(ConstValues.INTG_TYPEID_VALUE,"intg"));
        tokens.add(new Token(ConstValues.NAME,"a"));
        tokens.add(new Token(ConstValues.ASSIGN_EQUAL_VALUE,"="));
        tokens.add(new Token(ConstValues.INTG_VALUE,"2"));
        tokens.add(new Token(ConstValues.DELIM_VALUE,";"));
        tokens.add(new Token(ConstValues.MAIN_SCOPE,"main"));
        tokens.add(new Token(ConstValues.FUNCTION_OPENING,"{"));
        tokens.add(new Token(ConstValues.CHAR_TYPEID_VALUE,"char"));
        tokens.add(new Token(ConstValues.STR_TYPEID_VALUE,"str"));
        tokens.add(new Token(ConstValues.BOOL_TYPEID_VALUE,"bool"));
        tokens.add(new Token(ConstValues.MATH_OPERATION_VALUE,"+"));
        tokens.add(new Token(ConstValues.MATH_OPERATION_VALUE,"-"));
        tokens.add(new Token(ConstValues.MATH_OPERATION_VALUE,"*"));
        tokens.add(new Token(ConstValues.MATH_OPERATION_VALUE,"/"));
        tokens.add(new Token(ConstValues.MATH_OPERATION_VALUE,"%"));
        //tokens.add(new Token(ConstValues.PARENTHESIS_OPENING,"("));
        //tokens.add(new Token(ConstValues.PARENTHESIS_CLOSING,")"));
        //tokens.add(new Token(ConstValues.STR_VALUE,"hola"));
        //tokens.add(new Token(ConstValues.BOOL_VALUE,"true"));
        //tokens.add(new Token(ConstValues.CHAR_VALUE,"a"));
        tokens.add(new Token(ConstValues.FUNCTION_CLOSING,"}"));

        try {

            for(Token token : tokens){
                Token token1 = scanner.nextToken();
                Assert.assertEquals(token.getName(),token1.getName());
                Assert.assertEquals(token.getType_id(),token1.getType_id());

            }


        }catch (IOException err){
            err.printStackTrace();
        }

    }
}