import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SellerRating } from '../models/seller-rating';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class SellerRatingService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private auth: AuthService) { }

  getOptions() { //always in form of js object
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return options;
  }

  index(): Observable<SellerRating[]> {
    let endpoint = "api/sellerRatings";
    return this.http.get<SellerRating[]>(this.baseUrl + endpoint, this.getOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('TodoService.index() error'));
      })
    );
  }

  show(): Observable<SellerRating[]> {
    let endPoints = "api/sellerRatings/user";
    return this.http.get<SellerRating[]>(this.baseUrl + endPoints, this.getOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('TodoService.show() error'));
      })
    );
  }

  create(sellerRating: SellerRating, sellerId: number): Observable<SellerRating> {
    let endpoints = `api/sellerRatings/${sellerId}`
    return this.http.post<SellerRating>(this.baseUrl + endpoints, sellerRating, this.getOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Create error' + err);
      })
    );
  }

  update(sellerRating: SellerRating, sellerId: number): Observable<SellerRating> {
    let endpoints = `api/sellerRatings/${sellerId}`
    // if (productRating.completed) {
      // let tempDate = this.date.transform(Date.now(), 'shortDate');
      // if (tempDate != null) {productRating.completeDate = tempDate}
    // } else {
    //   todo.completeDate === '';
    // }
    return this.http.put<SellerRating>(this.baseUrl + endpoints, sellerRating, this.getOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error ('Update error'));
      })
    );
  }

  destroy(sellerId: number) {
    let endpoints = `api/sellerRatings/${sellerId}`
    return this.http.delete<SellerRating>(this.baseUrl + endpoints, this.getOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError( //create error object to throw back into component.ts
          () => new Error( 'Destroy Service error'
          )
        );
      })
    );
  }
}
