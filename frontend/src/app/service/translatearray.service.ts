import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {TranslateService} from '@ngx-translate/core';

@Injectable({
  providedIn: 'root'
})
export class TranslateArrayService {

  private subj: BehaviorSubject<string[]> = new BehaviorSubject([]);

  constructor(private translate: TranslateService) {
  }

  changeArray() {
    this.translate.get('Cities').subscribe(tr => {
      this.subj.next(tr);
    });
  }

  asObservable(): Observable<string[]> {
    return this.subj.asObservable();
  }
}
