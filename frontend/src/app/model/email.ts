export class Email {
    email: string;
    tokenType: string;

    constructor(email: string, tokenType: string) {
        this.email = email;
        this.tokenType = tokenType;
    }
}