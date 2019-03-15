package Lexicon;

public class Token {
    // Attributes
    private int type_id;
    private String name;

    // Constructor
    public Token(int type_id, String name) {
        this.type_id = type_id;
        this.name = name;
    }

    // Getters & Setters
    public int getType_id() {
        return type_id;
    }
    public String getName() {
        return name;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
    public void setName(String name) {
        this.name = name;
    }


    // Functions
    public boolean isVariable(){
        return getType_id() == ConstValues.NAME;
    }
}
