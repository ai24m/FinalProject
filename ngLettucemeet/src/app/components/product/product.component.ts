import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductRating } from 'src/app/models/product-rating';
import { User } from 'src/app/models/user';
import { ProductRatingService } from 'src/app/services/product-rating.service';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  keyword: string = '';
  selected: Product | null = null;
  newProduct: Product = new Product();
  editProduct: Product | null = null;
  products: Product[] = [];
  productRatings: ProductRating[] = [];
  user: User = new User();

  constructor(
    private prodSvc: ProductService,
    private prSvc: ProductRatingService,
    private userSvc: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.reloadProdList();
  }

  reloadProdList() {
    this.prodSvc.productIndex().subscribe({
      next: (products) => {
        this.products = products;
      },
      error: (err) => {
        console.error(
          'ProductComponent.reloadProdList(): error retrieving products'
        );
        console.error(err);
      },
    });
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

  setEditProduct() {
    this.editProduct = Object.assign({}, this.selected);
  }
}
