import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Market } from '../models/market';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class MarketService {
  private url = environment.baseUrl + 'api/markets';

  constructor(private http: HttpClient, private auth: AuthService) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }
  index(): Observable<Market[]> {
    return this.http.get<Market[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('MarketService.index(): error retrieving markets: ' + err)
        );
      })
    );
  }
  GetByMarketId(marketId: number): Observable<Market> {
    return this.http
      .get<Market>(this.url + '/' + marketId, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              'MarketService.GetByMarketId(): error retrieving market :' + err
          );
        })
      );
  }

  create(market: Market): Observable<Market> {
    console.log(market);

    return this.http.post<Market>(this.url, market, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => 'MarketService.create(): error create Market:' + err
        );
      })
    );
  }
  update(market: Market): Observable<Market> {
    return this.http
      .put<Market>(this.url + '/' + market.id, market, this.getHttpOptions())
      .pipe(
        catchError((problem: any) => {
          console.error('TodoService.update(): error deleting todo:');
          console.error(problem);
          return throwError(
            () => new Error('TodoService.update():error update todo')
          );
        })
      );
  }
  destroy(marketId: number): Observable<void> {
    return this.http
      .delete<void>(this.url + '/' + marketId, this.getHttpOptions())
      .pipe(
        catchError((problem: any) => {
          console.error('TodoService.destroy(): error deleting todo:');
          console.error(problem);
          return throwError(
            () => new Error('TodoService.destroy():error deleting todo')
          );
        })
      );
  }
}
