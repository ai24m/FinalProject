import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ProductRating } from '../models/product-rating';
import { AuthService } from './auth.service';
import { catchError, Observable, throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProductRatingService {

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

  index(): Observable<ProductRating[]> {
    let endpoint = "api/productRatings";
    return this.http.get<ProductRating[]>(this.baseUrl + endpoint).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('TodoService.index() error'));
      })
    );
  }

  show(): Observable<ProductRating[]> {
    let endPoints = "api/productRatings/user";
    return this.http.get<ProductRating[]>(this.baseUrl + endPoints).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('TodoService.show() error'));
      })
    );
  }

  getByProductId(productId: number): Observable<ProductRating[]> {
    let endPoints = `api/productRatings/product/${productId}`;
    return this.http.get<ProductRating[]>(this.baseUrl + endPoints).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('TodoService.show() error'));
      })
    );
  }

  create(productRating: ProductRating, productId: number): Observable<ProductRating> {
    let endpoints = `api/productRatings/${productId}`
    return this.http.post<ProductRating>(this.baseUrl + endpoints, productRating, this.getOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Create error' + err);
      })
    );
  }

  update(productRating: ProductRating, productId: number): Observable<ProductRating> {
    let endpoints = `api/productRatings/${productId}`
    // if (productRating.completed) {
      // let tempDate = this.date.transform(Date.now(), 'shortDate');
      // if (tempDate != null) {productRating.completeDate = tempDate}
    // } else {
    //   todo.completeDate === '';
    // }
    return this.http.put<ProductRating>(this.baseUrl + endpoints, productRating, this.getOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error ('Update error'));
      })
    );
  }

  destroy(productId: number) {
    let endpoints = `api/productRatings/${productId}`
    return this.http.delete<ProductRating>(this.baseUrl + endpoints, this.getOptions()).pipe(
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
