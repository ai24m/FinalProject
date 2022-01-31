import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductComment } from 'src/app/models/product-comment';
import { ProductRating } from 'src/app/models/product-rating';
import { ProductCommentService } from 'src/app/services/product-comment.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-id',
  templateUrl: './product-id.component.html',
  styleUrls: ['./product-id.component.css']
})
export class ProductIdComponent implements OnInit {
  selected: Product | null = null;
  myProductId: number = 0;
  productComments: ProductComment[] =[];
  productRating: ProductRating[] = [];

  constructor(
    private ProductSev: ProductService,
    private productCommentSvc: ProductCommentService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    let idString = this.route.snapshot.paramMap.get('id');
    if (!this.selected && idString) {
      let id = Number.parseInt(idString);
      console.log(id);
      if (!isNaN(id)) {
        this.ProductSev.showProduct(id).subscribe({
          next: (product) => {
            this.selected = product;
            this.myProductId = id;
            this.loadComments(id);
            // this.averageRatings(id);

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
    this.productCommentSvc.findByProductId(id).subscribe({
      next: (comments) => {
        this.productComments = comments;
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
