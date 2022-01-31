import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Market } from 'src/app/models/market';
import { Product } from 'src/app/models/product';
import { SellerRating } from 'src/app/models/seller-rating';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { MarketService } from 'src/app/services/market.service';
import { ProductService } from 'src/app/services/product.service';
import { SellerRatingService } from 'src/app/services/seller-rating.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User = new User();
  edit: boolean = false;
  products: Product[] = [];
  markets: Market[] = [];

  constructor(
    private router: Router,
    private userSvc: UserService,
    private product: ProductService,
    private rating: SellerRatingService,
    private market: MarketService,
    private auth: AuthService
  ) { }

  ngOnInit(): void {
    this.auth.getCurrentUser().subscribe({
      next: (user) => {
        this.user = user;
        this.getProducts();
        console.log(user);
      }
    })
  }

  resetPassword() {}

  organic(organic: boolean) {
    if (organic) {
      return 'Organic';
    } else if (!organic) {
      return 'Non-Organic';
    } else {
      return false;
    }
  }

  getProducts() {
    this.product.getUserProduct().subscribe({
      next: (products) => {
        this.products = products;
      }
    });
  }
}
