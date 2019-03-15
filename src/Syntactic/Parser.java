package Syntactic;

import Lexicon.ConstValues;
import Lexicon.Scanner;
import Lexicon.Token;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Parser extends Thread{
    // Attributes
    private Scanner scanner;

    public Parser() throws FileNotFoundException {
        this.scanner = new Scanner();
    }


    @Override
    public void run() {

        boolean end = false;

        while (!end){
            Token firstToken = null;
            try {
                firstToken = scanner.nextToken();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (firstToken == null){
                end = true;// End of file
            }
            else{
                // Mirar los first:
                boolean correct = true;

                switch (firstToken.getType_id()){
                    case ConstValues.INTG_TYPEID_VALUE:
                    case ConstValues.CHAR_TYPEID_VALUE:
                    case ConstValues.STR_TYPEID_VALUE:
                    case ConstValues.BOOL_TYPEID_VALUE:

                        // Declaración
                        try {
                            System.out.println(firstToken.getName());
                            correct = isDeclaracio();
                        } catch (IOException e) {
                            // TODO: Mirar por que la excepción y si se puede ignorar o tratar
                            e.printStackTrace();
                        }

                        break;

                    case ConstValues.NAME:
                        // Asignación
                        try {
                            System.out.println(firstToken.getName());
                            correct = isAssignacio();
                        } catch (IOException e) {
                            // TODO: Mirar por que la excepción y si se puede ignorar o tratar
                            e.printStackTrace();
                        }
                        break;

                    case ConstValues.MAIN_SCOPE:
                        // Main
                        try {
                            System.out.println(firstToken.getName());
                            Token token = scanner.nextToken();
                            int id = token.getType_id();
                            if (!isOpeningFunction(id)) {
                                correct = false;
                            }

                            System.out.println("Main");
                        } catch (IOException e) {
                            // TODO: Mirar por que la excepción y si se puede ignorar o tratar
                            e.printStackTrace();
                        }
                        break;

                    default:
                        // ERROR, no pertenece a ninguna norma de producción
                        break;


                }

            }

        }
    }

    private boolean isDeclaracio() throws IOException {
        Token token = scanner.nextToken();
        System.out.println(token.getName());
        int id = token.getType_id();
        // TODO: Añadir a lista para semantico
        if (isVariable(id)){
            token = scanner.nextToken();
            System.out.println(token.getName());
            id = token.getType_id();

            if (isIgualAssignacio(id)){
                token = scanner.nextToken();
                System.out.println(token.getName());
                id = token.getType_id();

                if (isVariable(id) || isLiteral(id)){
                    token = scanner.nextToken();
                    System.out.println(token.getName());
                    id = token.getType_id();

                    if (isDelimiter(id)){
                        System.out.println("ES DECLARACIÓN!");
                        return true;
                    }else {
                        token = scanner.nextToken();
                        System.out.println(token.getName());
                        id = token.getType_id();
                        System.out.println("comprobamos operacion!");
                        isOperacion(id);
                    }
                }else if (isOpeningParentesis(id)){
                //    token = scanner.nextToken();
                //    System.out.println(token.getName());
                //   id = token.getType_id();
                    System.out.println("comprobamos operacion!");
                    isOperacion(id);
                }

            }else{
                if (isDelimiter(id)){
                    System.out.println("IS DECLARACIO!");
                    return true;
                }else{
                    return false;
                }
            }
        }

        System.out.println("ES DECLARACIÓN!");
        return true;
    }


    private boolean isAssignacio() throws IOException {
        Token token = scanner.nextToken();
        System.out.println(token.getName());
        int id = token.getType_id();
        // TODO: Añadir a lista para semantico
        if (isIgualAssignacio(id)){
            token = scanner.nextToken();
            System.out.println(token.getName());
            id = token.getType_id();

            if (isVariable(id) || isLiteral(id)){
                token = scanner.nextToken();
                System.out.println(token.getName());
                id = token.getType_id();

                if (isDelimiter(id)){
                    System.out.println("ES ASIGNACIÓN!");
                    return true;
                }else {
                    token = scanner.nextToken();
                    System.out.println(token.getName());
                    id = token.getType_id();
                    System.out.println("comprobamos operacion!");
                    isOperacion(id);
                }
            }else if (isOpeningParentesis(id)){
              //  token = scanner.nextToken();
              //  System.out.println(token.getName());
              //  id = token.getType_id();
                System.out.println("comprobamos operacion!");
                isOperacion(id);
            }
        }else return false;

        System.out.println("ES ASIGNACION!");
        return true;
    }


    private boolean isOperacion(int id) throws IOException {
        Token token;

        if (isVariable(id) || isLiteral(id)){
            token = scanner.nextToken();
            System.out.println(token.getName());
            id = token.getType_id();
            isOperacion(id);

            if (isDelimiter(id)){
                return true;
            }else if (isMathOperation(id)){
                token = scanner.nextToken();
                System.out.println(token.getName());
                id = token.getType_id();

                if (isClosingParentesis(id) ||isDelimiter(id)){
                    return false;
                }else{
                    if (isOperacion(id)){
                        return true;
                    }
                }
            }else return false;

        }else if (isOpeningParentesis(id)){
            System.out.println("abrimos parentesis");
            token = scanner.nextToken();
            System.out.println(token.getName());
            id = token.getType_id();

            if (isClosingParentesis(id) || isMathOperation(id)){
                return false;
            }else{
                isOperacion(id);
                token = scanner.nextToken();
                System.out.println(token.getName());
                id = token.getType_id();

                if (isClosingParentesis(id)){
                    isOperacion(id);
                    token = scanner.nextToken();
                    System.out.println(token.getName());
                    id = token.getType_id();

                    if (isDelimiter(id)) {
                        return true;
                    } else if (isMathOperation(id)) {
                        token = scanner.nextToken();
                        System.out.println(token.getName());
                        id = token.getType_id();
                        isOperacion(id);
                    } else return false;
                }else {
                    System.out.println(") expected!");
                }
            }
        }
        return true;
    }

    private boolean isDelimiter(int id) {
        return id == ConstValues.DELIM_VALUE;
    }


    private boolean isLiteral(int id){
        return (id == ConstValues.INTG_VALUE
                || id == ConstValues.CHAR_VALUE
                || id == ConstValues.STR_VALUE
                || id == ConstValues.BOOL_VALUE);
    }

    private boolean isVariable(int id){
        return id == ConstValues.NAME;
    }

    private boolean isIgualAssignacio(int id) {
        return id == ConstValues.ASSIGN_EQUAL_VALUE;
    }

    private boolean isOpeningParentesis(int id) {
        return id == ConstValues.PARENTHESIS_OPENING;
    }

    private boolean isMathOperation(int id){
        return id == ConstValues.MATH_OPERATION_VALUE;
    }

    private boolean isClosingParentesis(int id){
        return id == ConstValues.PARENTHESIS_CLOSING;
    }

    private boolean isOpeningFunction(int id) {
        return id == ConstValues.FUNCTION_OPENING;
    }
}
