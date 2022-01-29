import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductRating } from 'src/app/models/product-rating';
import { ProductRatingService } from 'src/app/services/product-rating.service';

@Component({
  selector: 'app-product-rating',
  templateUrl: './product-rating.component.html',
  styleUrls: ['./product-rating.component.css']
})
export class ProductRatingComponent implements OnInit {
  productRatings: ProductRating[] = [];
  newProductRating: ProductRating = new ProductRating();
  editProductRating: ProductRating = new ProductRating();
  destroyProductRating: ProductRating = new ProductRating();
  editPR: boolean = false;
  addPR: boolean = false;
  destroyPR: boolean = false;
  selectedProductRating: ProductRating | null = null;

  constructor(
    private _productRatingService: ProductRatingService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this._productRatingService.index().subscribe({
      next: (allProductRatings) => {
        this.productRatings = allProductRatings;
      },
      error: (fail) => { console.error('ProductRatingComponent FAIL')}
    })
    this.reloadProductRating();
  }

  reloadProductRating() {
    this._productRatingService.index().subscribe(
      productRating => this.productRatings = productRating,
      err => console.error('Reload error' + err)
    );
  }

  showAddForm() {
      this.newProductRating = this.newProductRating;
      this.addPR = !this.addPR;
  }

  addProductRating(productRating: ProductRating, productId: number) {
    this._productRatingService.create(productRating, productId).subscribe(
      success => { //another way to write: function that has parameters todos next: (todos) => { do this function }, error: (wrong) => { }
        this.newProductRating = new ProductRating();
        this.reloadProductRating();
      },
      err => console.error('Addtodo error' + err)
    );
  }

  showEditForm(PRBeingEdited: ProductRating) {
    this.editProductRating = PRBeingEdited;
    this.editPR = !this.editPR;
  }

  // updateTodo(todo: Todo, goToDetails=true): void{
  updateProductRating(productRating: ProductRating, productId: number): void {
    this._productRatingService.update(productRating, productId).subscribe({
      next: (productRating) => {
        // this.editTodo = null;
        // if (goToDetails) { this.selected = productRating; }
        this.selectedProductRating = productRating;
        this.reloadProductRating();
      },
      error: (fail) => { console.error('Error updating' + fail);}
    });
  }

  showDeleteForm(PRBeingDeleted: ProductRating) {
    this.destroyProductRating = PRBeingDeleted;
    this.destroyPR = !this.destroyPR;
  }

  deleteProductRating(productId: number) {
    this._productRatingService.destroy(productId).subscribe({
      next: () => { this.reloadProductRating()},
      error: () => { console.error('Destroy component.ts ')}
    });
  }

}