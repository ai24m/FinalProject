import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Market } from 'src/app/models/market';
import { MarketComment } from 'src/app/models/market-comment';
import { MarketRating } from 'src/app/models/market-rating';
import { Product } from 'src/app/models/product';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { MarketcommentService } from 'src/app/services/market-comment.service';
import { MarketRatingService } from 'src/app/services/market-rating.service';
import { MarketService } from 'src/app/services/market.service';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-market-id',
  templateUrl: './market-id.component.html',
  styleUrls: ['./market-id.component.css'],
})
export class MarketIdComponent implements OnInit {
  selected: Market = new Market();
  myMarketId: number = 0;
  marketComments: MarketComment[] = [];
  marketRatings: MarketRating[] = [];
  user: User = new User();
  products: Product[] = [];
  productToAdd: Product = new Product();


  constructor(
    private MarketSev: MarketService,
    private marketCommentSvc: MarketcommentService,
    private marketRatingSvc: MarketRatingService,
    private product: ProductService,
    private auth: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    let idString = this.route.snapshot.paramMap.get('id');
    if (idString) {
      let id = Number.parseInt(idString);
      console.log(id);
      if (!isNaN(id)) {
       this.reload(id);
      } else {
        this.router.navigateByUrl('notfound');
      }
      this.reload(id);
      this.loadProductsOfUser();
    }
  }

  reload(id: number){
    this.MarketSev.GetByMarketId(id).subscribe({
      next: (market) => {
        this.selected = market;
        this.myMarketId = id;
        this.loadComments(id);
        this.loadingRatings(id);
      },
      error: (fail) => {
        console.error('TodoListComponent.ngoninit()');
        this.router.navigateByUrl('notfound'); //fall through in app router
      },
    });
  }

  loadComments(id: number) {
    this.marketCommentSvc.getByMarketId(id).subscribe({
      next: (comments) => {
        this.marketComments = comments;
      },
    });
  }

  loadingRatings(id: number) {
    this.marketRatingSvc.GetByMarketId(id).subscribe({
      next: (ratings) => {
        console.log('Ratings');
        console.log(ratings);
        this.marketRatings = ratings;
      },
    });
  }

  organic(organic: boolean) {
    if (organic) {
      return 'Organic';
    } else if (!organic) {
      return 'Non-organic';
    } else {
      return false;
    }
  }

  loadProductsOfUser(){
    this.auth.getCurrentUser().subscribe({
      next: (user) => {
        this.user = user;
        this.getProducts();
      }
    })
  }

  getProducts() {
    this.product.getUserProduct().subscribe({
      next: (products) => {
        this.products = products;
        this.reload(this.selected.id);
      },
      error: (err) => {
        console.error('MarketIdComponent(): Error retrieving markets');
        console.error(err);
      },
    });
  }

  addProductToMarket(productToAddId: number) {
    this.product.showProduct(productToAddId).subscribe({
      next: (product) => {
        product = product;
        product.user = this.user;
        this.MarketSev.update(this.selected).subscribe({
          next: (updatedMarketWithProducts) => {
            this.selected = updatedMarketWithProducts;
            this.reload(this.selected.id);
          },
          error: (err) => {
            console.error('MarketIdComponent(): Error adding product to market');
            console.error(err);
          },
        });
      },
      error: (err) => {
        console.error('MarketIdComponent(): Error adding product to market');
        console.error(err);
      },
    });
    this.reload(this.selected.id);
  }




}

