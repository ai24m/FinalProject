import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductComment } from 'src/app/models/product-comment';
import { ProductCommentService } from 'src/app/services/product-comment.service';

@Component({
  selector: 'app-product-comment',
  templateUrl: './product-comment.component.html',
  styleUrls: ['./product-comment.component.css'],
})
export class ProductCommentComponent implements OnInit {
  @Input() productComments: ProductComment[] = [];
  newProductComment: ProductComment = new ProductComment();
  editProductComment: ProductComment = new ProductComment();
  destroyProductComment: ProductComment = new ProductComment();
  productId: number = 0;
  @Input() product: Product = new Product();
  // editPC: boolean = false;
  // addPC: boolean = false;
  // destroyPC: boolean = false;
  // selectedProductComment: ProductComment | null = null;

  constructor(
    private productCommentService: ProductCommentService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  // ngOnInit(): void {
  //   this._productCommentService.index().subscribe({
  //     next: (allProductComments) => {
  //       this.productComments = allProductComments;
  //       this.reloadProductComment();
  //     },
  //     error: (fail) => { console.error('ProductRatingComponent FAIL')}
  //   })
  //   this.reloadProductComment();
  // }

  loadCommentsForProduct(productId: number) {
    if (!isNaN(productId)) {
      this.productCommentService.findByProductId(productId).subscribe({
        next: (comments) => {
          this.productComments = comments;
          console.log('COMMENTS');
          console.log(comments);
        },
      });
    }
  }

  ngOnInit(): void {
    if (this.route.parent != null) {
      let idString = this.route.parent.snapshot.paramMap.get('id');
      if (idString) {
        let id = Number.parseInt(idString);
        this.productId = id;

        // this.loadCommentsForProduct(id);
      }
    }
    // this.loadCommentsForProduct(this.product.id);ß

    // this.reloadProductComment();
  }

  // reloadProductComment() {
  //   this.productCommentService.index().subscribe(
  //     productComment => this.productComments = productComment,
  //     err => console.error('Reload error' + err)
  //   );
  // }

  addProductComment(productComment: ProductComment, productId: number) {
    if (productComment.myReplies.length > 0) {
      this.productCommentService
        .createReply(productComment, productId)
        .subscribe((success) => {
          // this.loadCommentsForProduct(this.product.id);
          this.loadCommentsForProduct(this.productId);

          // this.reloadProductComment();
        });
    }
    this.productCommentService.create(productComment, productId).subscribe(
      (success) => {
        //another way to write: function that has parameters todos next: (todos) => { do this function }, error: (wrong) => { }
        this.newProductComment = new ProductComment();
        // this.loadCommentsForProduct(this.product.id);
        this.loadCommentsForProduct(productId);

        // this.reloadProductComment();
      },
      (err) => console.error('Addtodo error' + err)
    );
  }

  updateProductComment(
    productComment: ProductComment,
    productCommentId: number,
    productId: number
  ): void {
    this.productCommentService
      .update(productComment, productCommentId, productId)
      .subscribe({
        next: () => {
          this.loadCommentsForProduct(this.product.id);

          // this.editTodo = null;
          // if (goToDetails) { this.selected = productRating; }
          // this.selectedProductComment = productComment;
          // this.reloadProductComment();
        },
        error: (fail) => {
          console.error('Error updating' + fail);
        },
      });
  }

  deleteProductRating(productId: number) {
    this.productCommentService.destroy(productId).subscribe({
      next: () => {
        this.loadCommentsForProduct(this.product.id);
      },
      error: () => {
        console.error('Destroy component.ts ');
      },
    });
  }
}
