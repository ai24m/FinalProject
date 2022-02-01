import { Component, OnInit } from '@angular/core';
import { Market } from 'src/app/models/market';
import { MarketComment } from 'src/app/models/market-comment';
import { MarketRating } from 'src/app/models/market-rating';
import { User } from 'src/app/models/user';
import { MarketcommentService } from 'src/app/services/market-comment.service';
import { MarketRatingService } from 'src/app/services/market-rating.service';
import { MarketService } from 'src/app/services/market.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  markets: Market[] = [];
  marketComments: MarketComment[] = [];
  marketRatings: MarketRating[] = [];
  vendors: User[] = [];
  keyword: string = '';

  constructor(
    private marketSvc: MarketService,
    private marketCommentSvc: MarketcommentService,
    private marketRatingSvc: MarketRatingService,
    ) { }

  ngOnInit(): void {
    this.marketSvc.index().subscribe({
      next: (m) => {
        this.markets = m;
      },
      error: (err) => {
        console.error('MarketComponent(): Error retrieving markets');
        console.error(err);
      },
    });
  }

  // reload() {
  //   this.marketSvc.index().subscribe({
  //     next: (m) => {
  //       this.markets = m;
  //     },
  //     error: (err) => {
  //       console.error('MarketComponent(): Error retrieving markets');
  //       console.error(err);
  //     },
  //   });
  // }

  getCount(marketComments: MarketComment[]) {
    return marketComments.length;

  }

}
