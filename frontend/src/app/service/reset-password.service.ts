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
        return this.http.post('/api/send-token-to-email', email);
    }

    checkIsExpired(token: string) {
        return this.http.get('/api/check-token/' + token);;
    }

    resetPassword(password: Password) {
        return this.http.post('/api/reset-password', password);
    }

    resendTokenToEmail(token: Token) {
        return this.http.post('/api/resend-token-to-email', token);
    }

    verifyEmail(token: string) {
        return this.http.post('/api/verify-email', token);
    }
}
