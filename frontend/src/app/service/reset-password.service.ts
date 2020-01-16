import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Email } from '../model/email';
import { Password } from '../model/password';
import { Token } from '../model/token';

@Injectable({
    providedIn: 'root'
})
export class ResetPasswordService {

    constructor(private http: HttpClient) { }

    sendTokenToEmail(email: Email) {
        return this.http.post('/api/user/token', email);
    }

    checkIsExpired(token: string) {
        return this.http.get('/api/user/token/' + token);
    }

    resetPassword(password: Password) {
        return this.http.post('/api/user/password/reset', password);
    }

    resendTokenToEmail(token: Token) {
        return this.http.post('/api/user/token/refresh', token);
    }

    verifyEmail(token: string) {
        return this.http.post('/api/user/verify', token);
    }
}
