import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Market } from 'src/app/models/market';
import { MarketComment } from 'src/app/models/market-comment';
import { MarketRating } from 'src/app/models/market-rating';
import { MarketRatingService } from 'src/app/services/market-rating.service';
import { MarketService } from 'src/app/services/market.service';

@Component({
  selector: 'app-market-id',
  templateUrl: './market-id.component.html',
  styleUrls: ['./market-id.component.css']
})
export class MarketIdComponent implements OnInit {
  selected: Market | null = null;
  marketComments: MarketComment[] = [];
  marketRatings: MarketRating[] = [];

  constructor(
    private MarketSev: MarketService,
    // private marketCommentSvc: MarketCommentService,
    private marketRatingSvc: MarketRatingService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    let idString = this.route.snapshot.paramMap.get('id');
    if (!this.selected && idString) {
      let id = Number.parseInt(idString);
      if (!isNaN(id)) {
        this.MarketSev.GetByMarketId(id).subscribe({
          next: (market) => {
            this.selected = market;
            this.loadComments(id);
            this.averageRatings(id);
          },
          error: (fail) => {
            console.error('TodoListComponent.ngoninit()')
            this.router.navigateByUrl('notfound'); //fall through in app router
          }
        })
      } else {
        this.router.navigateByUrl('notfound');
      }this.reload(id);

    }
  }

  loadComments(id : number){

  }

  averageRatings(id : number){
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

}
