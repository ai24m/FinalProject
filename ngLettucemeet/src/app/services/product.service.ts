import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../models/product';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private url = environment.baseUrl + "api/products";

  constructor(private http: HttpClient, private auth: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: "Basic " + this.auth.getCredentials(),
        "X-Requested-With": "XMLHttpRequest"
      }
    };
    return options;
  }

  productIndex(): Observable<Product[]> {
    return this.http.get<Product[]>(this.url).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error("ProductService.productIndex(): error retrieving product list:" + err));
      })
    );
  }

  showProduct(id: number): Observable<Product> {
    return this.http.get<Product>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error("ProductService.showProduct(): error retrieving product:" + err));
      })
    );
  }

  getUserProduct(): Observable<Product[]> {
    let endPoints = `/user`;
    return this.http.get<Product[]>(this.url + endPoints, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error("ProductService.createProduct(): error creating product:" + err));
      })
    );
  }

  createProduct(product: Product): Observable<Product[]> {
    return this.http.post<Product[]>(this.url, product, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(() => new Error("ProductService.createProduct(): error creating product:" + err));
      })
    );
  }

  updateProduct(product: Product) {
    return this.http.put<Product>(this.url + "/" + product.id, product, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error("ProductService.updateProduct(): error updating product:");
        console.error(err);
        return throwError(() => new Error("ProductService.productIndex(): error updating product"));
      })
    );
  }

  destroyProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error("ProductService.destroy(): error deleting product");
        console.error(err);
        return throwError(() => new Error("ProductService.productIndex(): error deleting product"))
      })
    );
  }

}
