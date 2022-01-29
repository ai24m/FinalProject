import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Type } from '../models/type';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  constructor(private http: HttpClient, private auth: AuthService) { }

  private url = environment.baseUrl + 'api/users';

  getOptions() {
    //always in form of js object
    let options = {
      headers: {
        Authorization: 'Basic' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

    index(): Observable<Type[]> {
      return this.http.get<Type[]>(this.url, this.getOptions()).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(() => new Error('TodoService.index() error'));
        })
      );
    }


}
