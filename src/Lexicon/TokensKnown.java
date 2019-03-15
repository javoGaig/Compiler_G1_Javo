package Lexicon;


import java.util.regex.Pattern;

public enum TokensKnown {

    MAIN_SCOPE("main"){
        public Token create(String lexema){
            return new Token(ConstValues.MAIN_SCOPE, lexema);
        }
    },

    TYPE_CHAR("char") {
        public Token create(String lexema) {
            return new Token(ConstValues.CHAR_TYPEID_VALUE, lexema);
        }
    },

    TYPE_STR("str") {
        public Token create(String lexema) {
            return new Token(ConstValues.STR_TYPEID_VALUE, lexema);
        }
    },

    TYPE_NUM("intg") {
        public Token create(String lexema) {
            return new Token(ConstValues.INTG_TYPEID_VALUE, lexema);
        }
    },

    TYPE_BOOL("bool") {
        public Token create(String lexema) {
            return new Token(ConstValues.BOOL_TYPEID_VALUE, lexema);
        }
    },

    NUMBER("[0-9]+") {

        public Token create(String lexema) {
            return new Token(ConstValues.INTG_VALUE, lexema);
        }

    },

    CHARACTER("'[a-zA-Z0-9]'") {

        public Token create(String lexema) {
            return new Token(ConstValues.CHAR_VALUE, lexema);
        }

    },

    BOOLEAN("true|false") {
        public Token create(String lexema) {
            return new Token(ConstValues.BOOL_VALUE, lexema);
        }
    },

    STRING("[a-zA-Z0-9]+") {
        public Token create(String lexema) {
            return new Token(ConstValues.NAME, lexema);
        }
    },

    STRING_VALOR("\"[a-zA-Z0-9]+\"") {
        public Token create(String lexema) {
            return new Token(ConstValues.STR_VALUE, lexema);
        }
    },

    OPERATION("\\+|-|\\*|/|%") {
        public Token create(String lexema) {
            return new Token(ConstValues.MATH_OPERATION_VALUE, lexema);
        }
    },

    DELIMITER(";") {
        public Token create(String lexema) {
            return new Token(ConstValues.DELIM_VALUE, lexema);
        }
    },

    EQUAL("=") {
        public Token create(String lexema) {
            return new Token(ConstValues.ASSIGN_EQUAL_VALUE, lexema);
        }
    },

    UNKNOWN_TOKEN("UNKNOWN") {
        public Token create(String lexema) {
            return new Token(ConstValues.UNKNOWN_TYPEID_VALUE, lexema);
        }
    },

    OPEN_FUNCTION("\\{") {
        public Token create(String lexema) {
            return new Token(ConstValues.FUNCTION_OPENING, lexema);
        }
    },

    CLOSE_FUNCTION("\\}") {
        public Token create(String lexema) {
            return new Token(ConstValues.FUNCTION_CLOSING, lexema);
        }
    },

    OPEN_PARENTHESIS("\\("){
        public Token create(String lexema) {return new Token(ConstValues.PARENTHESIS_OPENING, lexema);}
    },

    CLOSE_PARENTHESIS("\\)"){
        public Token create(String lexema) {return new Token(ConstValues.PARENTHESIS_CLOSING, lexema);}
    };

    protected Pattern pattern;

    TokensKnown(String pattern) {
        this.pattern = Pattern.compile(pattern);

    }

    public abstract Token create(String lexema);

    public static Token checkToken(String lexema) {
        for (TokensKnown token : TokensKnown.values()) {
            if (token.pattern.matcher(lexema).matches()) {
                return token.create(lexema);
            }
        }

        return UNKNOWN_TOKEN.create(lexema);
    }
}
