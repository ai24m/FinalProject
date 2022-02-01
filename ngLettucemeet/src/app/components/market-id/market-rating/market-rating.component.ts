import { MarketRatingService } from '../../../services/market-rating.service';
import { Component, Input, OnInit } from '@angular/core';
import { MarketRating } from 'src/app/models/market-rating';
import { Market } from 'src/app/models/market';
import { NumberSymbol } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-market-rating',
  templateUrl: './market-rating.component.html',
  styleUrls: ['./market-rating.component.css'],
})
export class MarketRatingComponent implements OnInit {
  @Input() marketRatings: MarketRating[] = [];
  selected: MarketRating | null = null;
  newMarketRating: MarketRating = new MarketRating();
  @Input() market: Market = new Market();
  editMarketRating: MarketRating | null = null;
  editMR: boolean = false;
  addMR: boolean = false;
  destroyMR: boolean = false;

  constructor(
    private MarketRatingSev: MarketRatingService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    if (this.route.parent != null) {
      let idString = this.route.parent.snapshot.paramMap.get('id');
      if (idString) {
        let id = Number.parseInt(idString);
        if (!isNaN(id)) {
          // this.MarketRatingSev.GetByMarketId(id).subscribe({
          //   next: (ratings) => {
          //     console.log(ratings);
          //     this.marketRatings = ratings;
          //     this.findAverageRating(this.marketRatings);
          //   },
          // });
        }
      }
    }
    // this.reload();
  }

  reload(marketId: number) {
    this.MarketRatingSev.GetByMarketId(marketId).subscribe({
      next: (m) => {
        this.marketRatings = m;
      },
      error: (err) => {
        console.error(
          'MarketRatingComponent(): Error retrieving marketRatings'
        );
        console.error(err);
      },
    });
  }

  // showRating(rating: number) {
  //   if (rating === 0) {
  //     return 'Not Rated Yet! Be the First!';
  //   } else {
  //     return rating + ' / 5 Rating';
  //   }
  // }

  findAverageRating() {
    let totalRatings: number = 0;
    for (let mr of this.marketRatings) {
      totalRatings += mr.marketRating;
      console.log(mr.marketRating);
      console.log(mr);
    }
    let average = totalRatings / this.marketRatings.length;
    // this.newMarketRating.ratingAverage = average;
    return average;
  }

  addMarketRating(marketRating: MarketRating, marketId: number) {
    this.MarketRatingSev.create(marketRating, marketId).subscribe({
      next: (m) => {
        this.newMarketRating = new MarketRating();
        this.reload(marketId);
      },
      error: (err) => {
        console.error('Error creating marketRating');
        console.error(err);
      },
    });
  }
  updateMarketRating(
    marketRating: MarketRating,
    marketId: NumberSymbol,
    gotoDetails = true
  ) {
    this.MarketRatingSev.update(marketRating, marketId).subscribe({
      next: (t) => {
        this.editMarketRating = null;
        if (gotoDetails) {
          this.selected = t;
        }
        this.reload(marketId);
      },
      error: (err) => {
        console.error(
          'MarketRatingComponent.updateRoute(): Error update marketRating'
        );
        console.error(err);
      },
    });
  }
  setEditMarketRating() {
    this.editMarketRating = Object.assign({}, this.selected);
  }

  deletedMarketRating(marketId: number) {
    this.MarketRatingSev.destroy(marketId).subscribe({
      next: () => {
        this.reload(marketId);
      },
      error: (fail) => {
        console.error(
          'MarketRatingComponent.deletedMarket(): Error delete marketRating'
        );
        console.error(fail);
      },
    });
  }
  displayTable() {
    this.selected = null;
  }
  displayMarket(marketRating: MarketRating) {
    this.selected = marketRating;
  }

  // storeAverage(rating: number) {
  //   let allRatings: number[] = [];
  //   allRatings.push(rating);

  // }

  findAverage(ratings: number[]) {
    if (ratings != null) {
      const sum = ratings.reduce((a, b) => a + b, 0);
      const avg = sum / ratings.length || 0;
      return avg;
    }
    return null;
  }
}
