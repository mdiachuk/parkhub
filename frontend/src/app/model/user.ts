import { Customer } from './customer';

export class User {
    customer: Customer;
    firstName: string;
    lastName: string;
    password: string;
    email: string;
    matchingPassword: string;

    constructor(firstName: string, lastName: string,
        email: string, phoneNumber: string,
        password: string, matchingPassword: string) {

        this.customer = new Customer(phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }
}