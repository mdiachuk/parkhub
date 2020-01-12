package ua.com.parkhub.model.enums;

public enum UuidTokenType {
    EMAIL("EMAIL"),
    PASSWORD("PASSWORD");

    private String type;

    UuidTokenType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
