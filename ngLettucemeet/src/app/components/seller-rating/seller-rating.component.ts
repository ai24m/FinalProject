import { Component, OnInit } from '@angular/core';
import { SellerRating } from 'src/app/models/seller-rating';
import { SellerRatingService } from 'src/app/services/seller-rating.service';

@Component({
  selector: 'app-seller-rating',
  templateUrl: './seller-rating.component.html',
  styleUrls: ['./seller-rating.component.css']
})
export class SellerRatingComponent implements OnInit {
  sellerRatings: SellerRating[] = [];
  newSellerRating: SellerRating = new SellerRating;
  editSellerRating: SellerRating = new SellerRating;
  destroySellerRating: SellerRating = new SellerRating;
  selectedSellerRating: SellerRating | null = null;

  constructor(
    private _sellerRatingService: SellerRatingService
  ) { }

  ngOnInit(): void {
    this._sellerRatingService.index().subscribe({
      next: (allSellerRatings) => {
        this.sellerRatings = allSellerRatings;
      },
      error: (fail) => { console.error('SellerRatingComponent FAIL')}
    })
    this.reloadSellerRating();
  }

  reloadSellerRating(){
    this._sellerRatingService.index().subscribe(
      sellerRating => this.sellerRatings = sellerRating,
      err => console.error('Reload error' + err)
    )
  };

  addSellerRating(sellerRating: SellerRating, sellerId: number) {
    this._sellerRatingService.create(sellerRating, sellerId).subscribe(
      success => { //another way to write: function that has parameters todos next: (todos) => { do this function }, error: (wrong) => { }
        this.newSellerRating = new SellerRating();
        this.reloadSellerRating();
      },
      err => console.error('Addtodo error' + err)
    );
  }

  updateSellerRating(sellerRating: SellerRating, sellerId: number): void {
    this._sellerRatingService.update(sellerRating, sellerId).subscribe({
      next: (sellerRating) => {
        // this.editTodo = null;
        // if (goToDetails) { this.selected = productRating; }
        this.selectedSellerRating = sellerRating;
        this.reloadSellerRating();
      },
      error: (fail) => { console.error('Error updating' + fail);}
    });
  }

  deleteSellerRating(sellerId: number) {
    this._sellerRatingService.destroy(sellerId).subscribe({
      next: () => { this.reloadSellerRating()},
      error: () => { console.error('Destroy component.ts ')}
    });
  }

}
