import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductComment } from 'src/app/models/product-comment';
import { ProductCommentService } from 'src/app/services/product-comment.service';

@Component({
  selector: 'app-product-comment',
  templateUrl: './product-comment.component.html',
  styleUrls: ['./product-comment.component.css']
})
export class ProductCommentComponent implements OnInit {
  productComments: ProductComment[] = [];
  newProductComment: ProductComment = new ProductComment();
  editProductComment: ProductComment = new ProductComment();
  destroyProductComment: ProductComment = new ProductComment();
  editPC: boolean = false;
  addPC: boolean = false;
  destroyPC: boolean = false;
  selectedProductComment: ProductComment | null = null;

  constructor(
    private _productCommentService: ProductCommentService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this._productCommentService.index().subscribe({
      next: (allProductComments) => {
        this.productComments = allProductComments;
        this.reloadProductComment();
      },
      error: (fail) => { console.error('ProductRatingComponent FAIL')}
    })
    this.reloadProductComment();
  }

  reloadProductComment() {
    this._productCommentService.index().subscribe(
      productComment => this.productComments = productComment,
      err => console.error('Reload error' + err)
    );
  }

  addProductRating(productComment: ProductComment, productId: number) {
    if (productComment.myReplies.length > 0) {
      this._productCommentService.createReply(productComment, productId).subscribe(
        success => {
          this.reloadProductComment();
        },
      )
    }
    this._productCommentService.create(productComment, productId).subscribe(
      success => { //another way to write: function that has parameters todos next: (todos) => { do this function }, error: (wrong) => { }
        this.newProductComment = new ProductComment();
        this.reloadProductComment();
      },
      err => console.error('Addtodo error' + err)
    );
  }

  updateProductComment(productComment: ProductComment, productCommentId: number, productId: number): void {
    this._productCommentService.update(productComment, productCommentId, productId).subscribe({
      next: () => {
        // this.editTodo = null;
        // if (goToDetails) { this.selected = productRating; }
        // this.selectedProductComment = productComment;
        this.reloadProductComment();
      },
      error: (fail) => { console.error('Error updating' + fail);}
    });
  }

  deleteProductRating(productId: number) {
    this._productCommentService.destroy(productId).subscribe({
      next: () => { this.reloadProductComment()},
      error: () => { console.error('Destroy component.ts ')}
    });
  }

}
