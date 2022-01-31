import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductRating } from 'src/app/models/product-rating';
import { ProductRatingService } from 'src/app/services/product-rating.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-rating',
  templateUrl: './product-rating.component.html',
  styleUrls: ['./product-rating.component.css']
})
export class ProductRatingComponent implements OnInit {
  @Input() productRatings: ProductRating[] = [];
  newProductRating: ProductRating = new ProductRating;
  editProductRating: ProductRating = new ProductRating;
  destroyProductRating: ProductRating = new ProductRating;
  editPR: boolean = false;
  addPR: boolean = false;
  destroyPR: boolean = false;
  selectedProductRating: ProductRating | null = null;
  @Input() product: Product = new Product();


  constructor(
    private _productRatingService: ProductRatingService,
    private productSvc: ProductService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void{
    if (this.route.parent != null) {
      let idString = this.route.parent.snapshot.paramMap.get('id');
      if (idString) {
        let id = Number.parseInt(idString);
        console.log(id);
        if (!isNaN(id)) {
          // this.loadRatingsForProduct(id);
        }
      }
    }
  }

  loadRatingsForProduct(productId: number){
    this._productRatingService.getByProductId(productId).subscribe({
      next: (ratings) => {
        console.log(ratings);
        this.productRatings = ratings;
        this.findAverageRating(this.productRatings);
      }
    })
  }

  showRating(rating: number) {
    if (rating === 0) {
      return 'Not Rated Yet! Be the First!'
    } else {
      return rating + ' / 5 Rating'
    }
  }

  findAverageRating(productRatings: ProductRating[]){
    let  totalRatings = 0;
    for (let pr of productRatings) {
      totalRatings += pr.rating;
    }
    let average = totalRatings / this.productRatings.length;
    return average;
  }

  showAddForm() {
      this.newProductRating = this.newProductRating;
      this.addPR = !this.addPR;
  }

  addProductRating(productRating: ProductRating, productId: number) {
    this._productRatingService.create(productRating, productId).subscribe(
      success => { //another way to write: function that has parameters todos next: (todos) => { do this function }, error: (wrong) => { }
        this.newProductRating = new ProductRating();
        this.ngOnInit();
      },
      err => console.error('Addtodo error' + err)
    );
  }

  showEditForm(PRBeingEdited: ProductRating) {
    this.editProductRating = PRBeingEdited;
    this.editPR = !this.editPR;
  }

  updateProductRating(productRating: ProductRating, productId: number): void {
    this._productRatingService.update(productRating, productId).subscribe({
      next: (productRating) => {
        this.selectedProductRating = productRating;
        this.loadRatingsForProduct(this.product.id);
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
      next: () => { this.loadRatingsForProduct(this.product.id)},
      error: () => { console.error('Destroy component.ts ')}
    });
  }
}
