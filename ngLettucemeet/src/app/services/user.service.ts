import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { User } from '../models/user';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {

  constructor(private http: HttpClient, private auth: AuthService) { }

  private url = environment.baseUrl + 'api/users';

  getOptions() {
    //always in form of js object
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json',
      },
    };
    return options;
  }

  getUserByProduct(productId: number): Observable<User> {
    let endPoints = `/findUserByProduct/${productId}`;
    return this.http.get<User>(this.url + endPoints).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('Error finding User: '));
      })
    );
  }

  getByUsername(): Observable<User> {
    let endPoints = `/username`;
    return this.http.get<User>(this.url + endPoints, this.getOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('Error finding User: '));
      })
    );
  }

  update(user: User): Observable<User> {
    let endpoints = '/' + user.id;
    return this.http
      .put<User>(this.url + endpoints, user, this.getOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(() => new Error('Error updating User: ' + user));
        })
      );
  }

  destroy(id: number) {
    let endpoints = '/' + id;
    return this.http.delete<User>(this.url + endpoints, this.getOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error('Error deleting user'));
      })
    );
  }

  index1(): Observable<User[]> {
    return this.http.get<User[]>(this.url, this.getOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error('Error indexing users'));
      })
    );
  }
}
