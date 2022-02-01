import { MarketcommentService } from '../../../services/market-comment.service';
import { MarketComment } from '../../../models/market-comment';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Market } from 'src/app/models/market';

@Component({
  selector: 'app-market-comment',
  templateUrl: './market-comment.component.html',
  styleUrls: ['./market-comment.component.css'],
})
export class MarketCommentComponent implements OnInit {
  @Input() marketComments: MarketComment[] = [];
  selected: MarketComment | null = null;
  newMarketCommet: MarketComment = new MarketComment();
  marketCommentReply: MarketComment = new MarketComment();
  marketId: number = 0;
  @Input() market: Market = new Market();

  constructor(
    private MarketCommentSev: MarketcommentService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    if (this.route.parent != null) {
      let idString = this.route.parent.snapshot.paramMap.get('id');
      if (idString) {
        let id = Number.parseInt(idString);
        if (!isNaN(id)) {
          this.MarketCommentSev.getByMarketId(id).subscribe({
            next: (comments) => {
              this.marketComments = comments;
            },
          });
        }
      }
    }
    // this.reload();
  }

  getCommentsByMarketId(marketId: number) {
    this.MarketCommentSev.getByMarketId(marketId).subscribe({
      next: (comments) => {
        this.marketComments = comments;
        console.log(this.marketComments);
      },
    });
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
  // getBymarketId(marketid: number) {}
  addANewMarketComment(marketId: number, marketComment: MarketComment) {
    this.MarketCommentSev.createANewMarketComment(
      marketComment,
      marketId
    ).subscribe({
      next: (marketComment) => {
        this.newMarketCommet = new MarketComment();
        this.ngOnInit();
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
        // this.ngOnInit();
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
        // this.ngOnInit();
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
