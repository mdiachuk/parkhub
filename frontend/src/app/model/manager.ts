import { User } from './user';

export class Manager {
    user: User;
    companyName: string;
    usreouCode: string;
    phoneNumber: string;
    comment: string;
    constructor(firstName: string, lastName: string,
                companyName: string, usreouCode: string,
                email: string, phoneNumber: string,
                password: string, matchingPassword: string,
                comment: string) {

        this.user = new User(firstName, lastName, email, phoneNumber, password, matchingPassword);
        this.companyName = companyName;
        this.usreouCode = usreouCode;
        this.comment = comment;
    }
}
