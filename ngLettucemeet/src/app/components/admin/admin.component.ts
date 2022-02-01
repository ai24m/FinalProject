import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Market } from 'src/app/models/market';
import { Product } from 'src/app/models/product';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { MarketService } from 'src/app/services/market.service';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  admin: User | null = null;
  markets: Market[] = [];
  users: User[] = [];
  products: Product[] = [];

  constructor(
    private authSvc: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private mrktSvc: MarketService,
    private userSvc: UserService,
    private prodSvc: ProductService
  ) { }

  ngOnInit(): void {
    this.authSvc.getCurrentUser().subscribe({
      next: (user) => {
        this.admin = user;
        this.getMarkets();
        this.getUsers();
        this.getProducts();
      },
      error: (err) => {
        console.error("AdminComponent.ngOnInit(): error getting current user");
        console.error(err);
      }
    });
  }

  getMarkets() {
    this.mrktSvc.index().subscribe({
      next: (mrkts) => {
        this.markets = mrkts;
      }
    })
    throw new Error('Function not implemented.');
  }

  getUsers() {
    this.userSvc.index().subscribe({
      next: (user) => {
        this.users = user;
      }
    })
    throw new Error('Function not implemented.');
  }

  getProducts() {
    this.prodSvc.productIndex().subscribe({
      next: (product) => {
        this.products = product;
      }
    })
    throw new Error('Function not implemented.');
  }

}
