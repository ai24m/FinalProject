import { MarketcommentService } from './../../services/market-comment.service';
import { MarketService } from './../../services/market.service';
import { Component, OnInit } from '@angular/core';
import { Market } from 'src/app/models/market';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from 'src/app/models/address';

@Component({
  selector: 'app-market',
  templateUrl: './market.component.html',
  styleUrls: ['./market.component.css'],
})
export class MarketComponent implements OnInit {
  markets: Market[] = [];
  selected: Market | null = null;
  newMarket: Market = new Market();
  editMarket: Market | null = null;
  address: Address = new Address();
  keyword: string = '';


  constructor(
    private MarketSev: MarketService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private MarketCommentSev: MarketcommentService
  ) {}

  ngOnInit(): void {
    this.reload();
  }
  get(markID: number) {
    this.MarketCommentSev.destroyByMarketCommentId(markID);
  }
  reload() {
    this.MarketSev.index().subscribe({
      next: (m) => {
        this.markets = m;
      },
      error: (err) => {
        console.error('MarketComponent(): Error retrieving markets');
        console.error(err);
      },
    });
  }
  addMarket(market: Market) {
    this.MarketSev.create(market).subscribe({
      next: (m) => {
        this.newMarket = new Market();
        this.reload();
      },
      error: (err) => {
        console.error('Error creating market');
        console.error(err);
      },
    });
  }
  // updateMarket(market: Market, gotoDetails = true) {
  //   this.MarketSev.update(market).subscribe({
  //     next: (t) => {
  //       this.editMarket = null;
  //       if (gotoDetails) {
  //         this.selected = t;
  //       }
  //       this.reload();
  //     },
  //     error: (err) => {
  //       console.error('MarketComponent.updateRoute(): Error update market');
  //       console.error(err);
  //     },
  //   });
  // }
  setEditMarket() {
    this.editMarket = Object.assign({}, this.selected);
  }

  deletedMarket(marketId: number) {
    this.MarketSev.destroy(marketId).subscribe({
      next: () => {
        this.reload();
      },
      error: (fail) => {
        console.error('MarketComponent.deletedMarket(): Error delete market');
        console.error(fail);
      },
    });
  }
  displayTable() {
    this.selected = null;
  }
  displayMarket(market: Market) {
    this.selected = market;
  }
}
