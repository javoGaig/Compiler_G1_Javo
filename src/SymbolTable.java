import java.util.ArrayList;
import java.util.HashMap;

import static Lexicon.ConstValues.GLOBAL_SCOPE;
import static Lexicon.ConstValues.MAIN_SCOPE;


public class SymbolTable{
    // Inner class
    public class Symbol{
        // Attributes
        private String name;
        private int type; // (variable, function)

        // Constructor
        public Symbol(){}
        public Symbol(String name, int type){
            this.name = name;
            this.type = type;
        }

        // Getters & Setters
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }
        public void setType(int type) {
            this.type = type;
        }
    }

    // Constant values
    public static final int NOT_EXISTS = -3;
    private static final int NOT_CREATED = -4;
    private static final int VARIABLE_TYPE = 1;


    // Attributes
    private HashMap<String, Symbol> globalScope;
    private HashMap<String, Symbol> mainScope;
    private ArrayList<HashMap<String, Symbol>> functionScopes;


    // Constructor
    public SymbolTable(){
        globalScope = new HashMap<String, Symbol>();
        mainScope = new HashMap<String, Symbol>();
        functionScopes = new ArrayList<HashMap<String, Symbol>>();
    }


    // Getters & Setters
    public HashMap getGlobalScope() {
        return globalScope;
    }

    public void setGlobalScope(HashMap globalScope) {
        this.globalScope = globalScope;
    }

    public HashMap getMainScope() {
        return mainScope;
    }

    public void setMainScope(HashMap mainScope) {
        this.mainScope = mainScope;
    }

    public ArrayList<HashMap<String, Symbol>> getFunctionScopes() {
        return functionScopes;
    }

    public void setFunctionScopes(ArrayList<HashMap<String, Symbol>> functionScopes) {
        this.functionScopes = functionScopes;
    }


    // Functions
    public boolean existsTable(int scope){
        if (scope == GLOBAL_SCOPE || scope == MAIN_SCOPE){
            return true;
        }
        return functionScopes.size() <= scope;
    }

    public int createNewScope(){
        functionScopes.add(new HashMap<String, Symbol>());
        return functionScopes.size() - 1; //We return the index of the new table
    }

    public int existsKey(String key, int scope){
        int b = NOT_EXISTS;

        switch (scope){
            case GLOBAL_SCOPE:
                // Tan solo mirar el scope global
                if (globalScope.get(key) != null){
                    b = GLOBAL_SCOPE;
                }else {
                    b = NOT_EXISTS;
                }
                break;

            case MAIN_SCOPE:
                /* Mirar:
                     - Scope main
                     - Scope global
                 */
                if (mainScope.get(key) != null){
                    b = MAIN_SCOPE;
                }else if (globalScope.get(key) != null){
                    b = GLOBAL_SCOPE;
                }else{
                    b = NOT_EXISTS;
                }
                break;

            default:
                /* Mirar:
                     - Scope actual
                     - Scope main
                     - Scope global
                 */
                if (scope >= functionScopes.size()){
                    b = NOT_CREATED;
                }else{
                    if (functionScopes.get(scope).get(key) != null){
                        b = scope;
                    }else if (mainScope.get(key) != null){
                        b = MAIN_SCOPE;
                        if (globalScope.get(key) != null){
                            b = GLOBAL_SCOPE;
                        }else{
                            b = NOT_EXISTS;
                        }
                    }
                }
                break;
        }
        return b;
    }


    public boolean insertKey(String key, int scope){
        if (!existsTable(scope)){
            scope = createNewScope();
        }
        if (existsKey(key, scope) != scope){
            Symbol symbol = new Symbol(key, VARIABLE_TYPE);
            // TODO: Por el momento solo guardamos variables, + adelante también características de funciones
            // (ahora también guardamos los scopes de las funciones pero no cambiamos del scope GLOBAL, por el momento)
            switch (scope){
                case GLOBAL_SCOPE:
                    globalScope.put(key, symbol);
                    break;
                case MAIN_SCOPE:
                    mainScope.put(key, symbol);
                    break;
                default:
                    functionScopes.get(scope).put(key, symbol);
                    break;
            }
            return true;
        }
        return false; // Not inserted again because it has been inserted before and: ERROR -> It's not possible to declare the same
    }

}
