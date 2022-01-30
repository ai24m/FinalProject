import { MarketRating } from './../models/market-rating';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MarketRatingService {
  private url = environment.baseUrl + 'api/market';
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
  GetByMarketId(marketId: number): Observable<MarketRating[]> {
    return this.http
      .get<MarketRating[]>(
        this.url + '/' + marketId + '/' + 'marketratings',
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              'MarketRatingService.GetByMarketId(): error retrieving marketRating :' +
              err
          );
        })
      );
  }
  create(
    marketRating: MarketRating,
    marketId: number
  ): Observable<MarketRating> {
    console.log(marketRating);

    return this.http
      .post<MarketRating>(
        this.url + '/' + marketId + '/' + 'marketratings',
        marketRating,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              'MarketRatingService.create(): error create MarketRating:' + err
          );
        })
      );
  }
  update(
    marketRating: MarketRating,
    marketId: number
  ): Observable<MarketRating> {
    return this.http
      .put<MarketRating>(
        this.url + '/' + marketId + '/' + 'marketratings',
        marketRating,
        this.getHttpOptions()
      )
      .pipe(
        catchError((problem: any) => {
          console.error(
            'MarketRatingService.update(): error deleting MarketRating:'
          );
          console.error(problem);
          return throwError(
            () =>
              new Error(
                'MarketRatingService.update():error update MarketRating'
              )
          );
        })
      );
  }
  destroy(marketId: number): Observable<void> {
    return this.http
      .delete<void>(
        this.url + '/' + marketId + '/' + 'marketratings',
        this.getHttpOptions()
      )
      .pipe(
        catchError((problem: any) => {
          console.error(
            'MarketRatingService.destroy(): error deleting MarketRating:'
          );
          console.error(problem);
          return throwError(
            () =>
              new Error(
                'MarketRatingService.destroy():error deleting MarketRating'
              )
          );
        })
      );
  }
}
