import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ProductComment } from '../models/product-comment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProductCommentService {
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

  index(): Observable<ProductComment[]> {
    let endpoint = "api/productcomments";
    return this.http.get<ProductComment[]>(this.baseUrl + endpoint).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('TodoService.index() error'));
      })
    );
  }

  findByProductId(productId: number): Observable<ProductComment[]> {
    let endPoints = `api/productcomments/product/${productId}`;
    return this.http.get<ProductComment[]>(this.baseUrl + endPoints).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('TodoService.show() error'));
      })
    );
  }

  show(pcId: number): Observable<ProductComment[]> {
    let endPoints = `api/productcomments/${pcId}`;
    return this.http.get<ProductComment[]>(this.baseUrl + endPoints).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('TodoService.show() error'));
      })
    );
  }

  createReply(productComment: ProductComment, productCommentId: number): Observable<ProductComment> {
    let endpoints = `api/productcomments/${productCommentId}/comments`
    return this.http.post<ProductComment>(this.baseUrl + endpoints, productComment, this.getOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error ('Create reply error'));
      })
    );
  }

  create(productComment: ProductComment, productCommentId: number) {
    let endpoints = `api/productcomments/${productCommentId}`
    return this.http.post<ProductComment>(this.baseUrl + endpoints, productComment, this.getOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error ('Create error'));
      })
    );
  }

  update(productComment: ProductComment, productCommentId: number, productId: number): Observable<ProductComment> {
    let endpoints = `api/products/${productId}/productcomments/${productCommentId}`
    // if (productRating.completed) {
      // let tempDate = this.date.transform(Date.now(), 'shortDate');
      // if (tempDate != null) {productRating.completeDate = tempDate}
    // } else {
    //   todo.completeDate === '';
    // }
    return this.http.put<ProductComment>(this.baseUrl + endpoints, productComment, this.getOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error ('Update error'));
      })
    );
  }

  destroy(commentId: number) {
    let endpoints = `api/productcomments/${commentId}`
    return this.http.delete<ProductComment>(this.baseUrl + endpoints, this.getOptions()).pipe(
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
