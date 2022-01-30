import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductRating } from 'src/app/models/product-rating';
import { ProductRatingService } from 'src/app/services/product-rating.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  keyword: string = "";
  selected: Product | null = null;
  newProduct: Product = new Product();
  editProduct: Product | null = null;
  products: Product[] = [];
  productRatings: ProductRating[] = [];

  constructor(
    private prodSvc: ProductService,
    private prSvc: ProductRatingService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    let idStr = this.route.snapshot.paramMap.get("id");
    if (!this.selected && idStr) {
      let id = Number.parseInt(idStr);
      if (!isNaN(id)) {
        this.prodSvc.showProduct(id).subscribe({
          next: (product) => {
            this.selected = product;
          },
          error: (err) => {
            console.error("ProductComponent.ngOnInit(): invalid productId");
            console.error(err);
          }
        });
        this.prSvc.index().subscribe({
          next: (ratings) => { this.productRatings = ratings}
        })
      } else {
        this.router.navigateByUrl("FOF");
      }
    }
    this.reloadProdList();
  }

  reloadProdList() {
    this.prodSvc.productIndex().subscribe({
      next: (products) => {
        this.products = products;
      },
      error: (err) => {
        console.error("ProductComponent.reloadProdList(): error retrieving products");
        console.error(err);
      }
    });
  }

  setEditProduct() {
    this.editProduct = Object.assign({}, this.selected);
  }

  search(keyword: string) {

  }

}
