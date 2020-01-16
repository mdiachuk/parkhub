package ua.com.parkhub.model;

public class ManagerRegistrationDataModel {

    private UserModel user;
    private String companyName;
    private String usreouCode;
    private String comment;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUsreouCode() {
        return usreouCode;
    }

    public void setUsreouCode(String usreouCode) {
        this.usreouCode = usreouCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
