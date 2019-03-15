package Lexicon;

public abstract class ConstValues {
    // -------------- TOKENS ---------------------
    // Unknown
    public static final int UNKNOWN_TYPEID_VALUE = 0;

    // Number ( num )
    public static final int INTG_TYPEID_VALUE = 1;
    public static final int INTG_VALUE = 5;

    // Character (char)
    public static final int CHAR_TYPEID_VALUE = 2;
    public static final int CHAR_VALUE = 6;

    // String (str)
    public static final int STR_TYPEID_VALUE = 3;
    public static final int STR_VALUE = 7;

    // Boolean (bool)
    public static final int BOOL_TYPEID_VALUE = 4;
    public static final int BOOL_VALUE = 8;

    // Name of variables
    public static final int NAME = 9;

    // Sentence delimiter ( ; )
    public static final int DELIM_VALUE = 30;

    // Assignation equal ( = )
    public static final int ASSIGN_EQUAL_VALUE = 14;

    // Math operation ( + - * / % )
    public static final int MATH_OPERATION_VALUE = 16;

    // Opening parenthesis
    public static final int PARENTHESIS_OPENING = 17;
    public static final int PARENTHESIS_CLOSING = 18;

    //FUNCTION OPENING AND CLOSING
    public static final int FUNCTION_OPENING = 19;  // {
    public static final int FUNCTION_CLOSING = 20;  // }

    // -------------- SCOPES ---------------------
    public static final int GLOBAL_SCOPE = -2;

    public static final int MAIN_SCOPE = -1;


}
