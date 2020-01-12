package ua.com.parkhub.model.enums;

public enum TicketTypeModel {

    MANAGER_REGISTRATION_REQUEST("Manager registration request");

    private String value;
    private Long id;

    TicketTypeModel(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
