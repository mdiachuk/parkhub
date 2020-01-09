package ua.com.parkhub.model;

public enum TicketTypeModel {
    MANAGER_REGISTRATION_REQUEST("Manager registration request");

    private String type;
    private Long id;

    TicketTypeModel(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
