import { MarketcommentService } from './../../services/market-comment.service';
import { MarketComment } from './../../models/market-comment';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Market } from 'src/app/models/market';

@Component({
  selector: 'app-market-comment',
  templateUrl: './market-comment.component.html',
  styleUrls: ['./market-comment.component.css'],
})
export class MarketCommentComponent implements OnInit {
  marketComments: MarketComment[] = [];
  selected: MarketComment | null = null;
  newMarketCommet: MarketComment = new MarketComment();
  marketCommentReply: MarketComment = new MarketComment();
  market: Market = new Market();

  constructor(private MarketCommentSev: MarketcommentService) {}

  ngOnInit(): void {
    this.reload();
  }

  reload() {
    this.MarketCommentSev.index().subscribe({
      next: (mC) => {
        this.marketComments = mC;
      },
      error: (err) => {
        console.error(
          'MarketCommentComponent(): Error retrieving MarketComments'
        );
        console.error(err);
      },
    });
  }
  getBymarketId(marketid: number) {}
  addANewMarketComment(marketId: number, marketComment: MarketComment) {
    this.MarketCommentSev.createANewMarketComment(
      marketId,
      marketComment
    ).subscribe({
      next: (m) => {
        this.newMarketCommet = new MarketComment();
        this.reload();
      },
      error: (err) => {
        console.error('Error creating A new marketComment');
        console.error(err);
      },
    });
  }
  addAMarketCommentReply(
    marketCommentId: number,
    marketComment: MarketComment
  ) {
    this.MarketCommentSev.createAMarketCommentReply(
      marketCommentId,
      marketComment
    ).subscribe({
      next: (m) => {
        this.marketCommentReply = new MarketComment();
        this.reload();
      },
      error: (err) => {
        console.error('Error creating A new marketComment');
        console.error(err);
      },
    });
  }
  deletedMarketComment(marketCommentId: number) {
    this.MarketCommentSev.destroyByMarketCommentId(marketCommentId).subscribe({
      next: () => {
        this.reload();
      },
      error: (fail) => {
        console.error(
          'MarketCommentComponent.deletedMarketComment(): Error delete marketComment'
        );
        console.error(fail);
      },
    });
  }
  displayTable() {
    this.selected = null;
  }
  displayMarketComment(marketComment: MarketComment) {
    this.selected = marketComment;
  }
}