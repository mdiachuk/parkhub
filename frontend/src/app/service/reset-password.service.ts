import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Email } from '../model/email';
import { Password } from '../model/password';

@Injectable({
    providedIn: 'root'
})
export class ResetPasswordService {

    constructor(private http: HttpClient) { }

    sendTokenToEmail(email: Email) {
        return this.http.post('/api/email', email);
    }

    resetPassword(password: Password) {
        return this.http.post('/api/reset-password', password);
    }
}