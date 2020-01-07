export class Manager {
    firstName: string;
    lastName: string;
    companyName: string;
    usreouCode: string;
    password: string;
    email: string;
    phoneNumber: string;
    matchingPassword: string;
    comment: string;

    constructor(firstName: string, lastName: string,
                companyName: string, usreouCode: string,
                email: string, phoneNumber: string,
                password: string, matchingPassword: string,
                comment: string) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.usreouCode = usreouCode;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.matchingPassword = matchingPassword;
        this.comment = comment;
    }
}
