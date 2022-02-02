import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductComment } from 'src/app/models/product-comment';
import { ProductRating } from 'src/app/models/product-rating';
import { ProductCommentService } from 'src/app/services/product-comment.service';
import { ProductRatingService } from 'src/app/services/product-rating.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-id',
  templateUrl: './product-id.component.html',
  styleUrls: ['./product-id.component.css']
})
export class ProductIdComponent implements OnInit {
  selected: Product = new Product();
  myProductId: number = 0;
  productComments: ProductComment[] =[];
  productRatings: ProductRating[] = [];

  constructor(
    private ProductSev: ProductService,
    private productCommentSvc: ProductCommentService,
    private productRatingSvc: ProductRatingService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    let idString = this.route.snapshot.paramMap.get('id');
    if (idString) {
      let id = Number.parseInt(idString);
      console.log(id);
      if (!isNaN(id)) {
        this.ProductSev.showProduct(id).subscribe({
          next: (product) =>  {
             this.load(product, id);
          },
          error: (fail) => {
            console.error('TodoListComponent.ngoninit()')
            this.router.navigateByUrl('notfound'); //fall through in app router
          }
        })
      } else {
        this.router.navigateByUrl('notfound');
      } this.reload(id);

    }
  }

  load(product: Product, id: number){
    this.selected = product;
    console.log(product);
    this.myProductId = id;
    this.loadComments(id);
    this.loadRatings(id);
    // this.averageRatings(id);

  }

  organic(organic: boolean) {
    if (organic) {
      return 'organic';
    } else if (!organic) {
      return 'non-organic';
    } else {
      return false;
    }
  }

  loadComments(id : number){
    this.productCommentSvc.findByProductId(id).subscribe({
      next: (comments) => {
        console.log("COMMENTS")
        console.log(comments);
        this.productComments = comments;
      }
    })
  }

  loadRatings(id : number){
    this.productRatingSvc.getByProductId(id).subscribe({
      next: (ratings) => {
        console.log("RATINGS")
        console.log(ratings);
        this.productRatings = ratings;
      }
    })
  }



  reload(id: number) {
    this.ProductSev.showProduct(id).subscribe({
      next: (p) => {
        this.selected = p;
      },
      error: (err) => {
        console.error('MarketComponent(): Error retrieving markets');
        console.error(err);
      },
    });
  }
}
