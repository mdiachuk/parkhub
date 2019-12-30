package ua.com.parkhub.model;

public enum UuidTokenTypeModel {
    EMAIL("EMAIL"),
    PASSWORD("PASSWORD");

    private String type;

    UuidTokenTypeModel(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
