import { MarketComment } from './../models/market-comment';
import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MarketcommentService {
  private url = environment.baseUrl + 'api/marketcomments';

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
  index(): Observable<MarketComment[]> {
    return this.http.get<MarketComment[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('MarketService.index(): error retrieving markets: ' + err)
        );
      })
    );
  }

  GetByMarketCommentId(marketCommentId: number): Observable<MarketComment> {
    return this.http
      .get<MarketComment>(
        this.url + '/' + marketCommentId,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              'MarketCommentService.GetByMarketCommentId(): error retrieving MarketComment :' +
              err
          );
        })
      );
  }
  // array of market comments belonging to market Id
  getByMarketId(marketId: number): Observable<MarketComment[]> {
    return this.http
      .get<MarketComment[]>(
        this.url + '/market/' + marketId
        // this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              'MarketCommentService.GetByMarketCommentId(): error retrieving MarketComment :' +
              err
          );
        })
      );
  }

  createAMarketCommentReply(
    marketCommentId: number,
    marketComment: MarketComment
  ): Observable<MarketComment> {
    console.log(marketComment);

    return this.http
      .post<MarketComment>(
        this.url + '/' + marketCommentId + '/' + 'comments',
        marketComment,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              'MarketCommentService.createANewMarketComment(): error create MarketCommentReply:' +
              err
          );
        })
      );
  }
  createANewMarketComment(
    marketComment: MarketComment,
    marketId: number
  ): Observable<MarketComment> {
    console.log(marketComment);

    return this.http
      .post<MarketComment>(
        this.url + '/' + marketId,
        marketComment,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              'MarketCommentService.createANewMarketComment(): error create New MarketComment:' +
              err
          );
        })
      );
  }
  // update(marketComment: MarketComment): Observable<MarketComment> {
  //   return this.http
  //     .put<MarketComment>(this.url + '/' + market.id, market, this.getHttpOptions())
  //     .pipe(
  //       catchError((problem: any) => {
  //         console.error('TodoService.update(): error deleting todo:');
  //         console.error(problem);
  //         return throwError(
  //           () => new Error('TodoService.update():error update todo')
  //         );
  //       })
  //     );
  // }

  destroyByMarketCommentId(marketCommentId: number): Observable<void> {
    return this.http
      .delete<void>(
        this.url + '/' + marketCommentId + '/' + 'comments',
        this.getHttpOptions()
      )
      .pipe(
        catchError((problem: any) => {
          console.error('TodoService.destroy(): error deleting todo:');
          console.error(problem);
          return throwError(
            () =>
              new Error(
                'MarketCommentService.destroyByMarketCommentId():error deleting MarketComment'
              )
          );
        })
      );
  }
}
