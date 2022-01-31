import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Market } from 'src/app/models/market';
import { MarketComment } from 'src/app/models/market-comment';
import { MarketRating } from 'src/app/models/market-rating';
import { AuthService } from 'src/app/services/auth.service';
import { MarketcommentService } from 'src/app/services/market-comment.service';
import { MarketRatingService } from 'src/app/services/market-rating.service';
import { MarketService } from 'src/app/services/market.service';

@Component({
  selector: 'app-market-id',
  templateUrl: './market-id.component.html',
  styleUrls: ['./market-id.component.css'],
})
export class MarketIdComponent implements OnInit {
  selected: Market = new Market();
  myMarketId: number = 0;
  marketComments: MarketComment[] = [];
  marketRatings: MarketRating[] = [];

  constructor(
    private MarketSev: MarketService,
    private marketCommentSvc: MarketcommentService,
    private marketRatingSvc: MarketRatingService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    let idString = this.route.snapshot.paramMap.get('id');
    if (idString) {
      let id = Number.parseInt(idString);
      console.log(id);
      if (!isNaN(id)) {
        this.MarketSev.GetByMarketId(id).subscribe({
          next: (market) => {
            this.selected = market;
            this.myMarketId = id;
            this.loadComments(id);
            this.averageRatings(id);
          },
          error: (fail) => {
            console.error('TodoListComponent.ngoninit()');
            this.router.navigateByUrl('notfound'); //fall through in app router
          },
        });
      } else {
        this.router.navigateByUrl('notfound');
      }
      this.reload(id);
    }
  }

  loadComments(id: number) {
    this.marketCommentSvc.getByMarketId(id).subscribe({
      next: (comments) => {
        this.marketComments = comments;
      },
    });
  }

  // loadUsers(id: product) {

  // }

  averageRatings(id: number) {
    // show average of ratings call function by marketid to get average ratings
  }

  reload(id: number) {
    this.MarketSev.GetByMarketId(id).subscribe({
      next: (m) => {
        this.selected = m;
      },
      error: (err) => {
        console.error('MarketComponent(): Error retrieving markets');
        console.error(err);
      },
    });
  }

  organic(organic: boolean) {
    if (organic) {
      return 'Organic';
    } else if (!organic) {
      return 'Non-organic';
    } else {
      return false;
    }
  }
}
