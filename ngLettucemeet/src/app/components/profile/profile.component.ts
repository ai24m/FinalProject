import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Market } from 'src/app/models/market';
import { Product } from 'src/app/models/product';
import { User } from 'src/app/models/user';
import { Type } from 'src/app/models/type';
import { AuthService } from 'src/app/services/auth.service';
import { MarketService } from 'src/app/services/market.service';
import { ProductService } from 'src/app/services/product.service';
import { SellerRatingService } from 'src/app/services/seller-rating.service';
import { UserService } from 'src/app/services/user.service';
import { TypeService } from 'src/app/services/type.service';
import { Address } from 'src/app/models/address';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  user: User = new User();
  edit: boolean = false;
  delete: boolean = false;
  users: User[] = [];
  products: Product[] = [];
  markets: Market[] = [];
  types: Type[] = [];
  newProduct: Product = new Product();
  addProductToMarket: boolean = false;
  ProductBeingEdited: Product = new Product();
  ProductBeingDeleted: Product = new Product();
  bioedit: boolean = false;
  userUpdatingInfo: User = new User();
  addressUpdatingInfo: Address = new Address();
  adminLogin: boolean = false;
  userEdit: User | null = null;
  userSelect: User | null = null;
  prodEdit: Product | null = null;
  prodSelect: Product | null = null;
  mrktEdit: Market | null = null;
  mrktSelect: Market | null = null;

  constructor(
    private router: Router,
    private userSvc: UserService,
    private product: ProductService,
    private rating: SellerRatingService,
    private market: MarketService,
    private auth: AuthService,
    private typeSvc: TypeService
  ) { }

  ngOnInit(): void {
    this.auth.getCurrentUser().subscribe({
      next: (user) => {
        this.reloadPage(user);
      },
    });
  }

  reloadPage(user: User) {
    this.user = user;
    if (this.user.role === 'admin') {
      this.adminLogin = true;
    }
    this.getProducts();
    this.getTypes();
    this.userUpdatingInfo = user;
    console.log(user);
  }

  // resetPassword() {

  // }

  organic(organic: boolean) {
    if (organic) {
      return 'Organic';
    } else if (!organic) {
      return 'Non-Organic';
    } else {
      return false;
    }
  }

  addProduct(newProduct: Product) {
    console.log(newProduct);
    this.product.createProduct(newProduct).subscribe(
      (success) => {
        //another way to write: function that has parameters todos next: (todos) => { do this function }, error: (wrong) => { }
        this.newProduct = new Product();
        this.reloadPage(this.user);
      },
      (err) => console.error('Add Product error' + err)
    );
  }

  editForm(product: Product) {
    this.ProductBeingEdited = product;
  }

  updateProduct(ProductBeingEdited: Product) {
    this.product.updateProduct(ProductBeingEdited).subscribe(
      (success) => {
        window.location.reload();
      },
      (err) => console.error('Edit Product error' + err)
    );
  }

  deleteForm(product: Product) {
    this.ProductBeingDeleted = product;
  }

  // deleteProduct(product: Product) {
  //   this.product.destroyProduct(product.id).subscribe(
  //     success => {
  //       this.ngOnInit();
  //     },
  //     err => console.error('Delete Product error')
  //   );
  // }

  getProducts() {
    this.product.getUserProduct().subscribe({
      next: (products) => {
        this.products = products;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  getTypes() {
    this.typeSvc.index().subscribe({
      next: (types) => {
        this.types = types;
      },
      error: (error) => {
        console.log(error + 'get types error in profilecomponent');
      },
    });
  }

  updateBio(user: User, address: Address) {
    this.user.address.id = address.id;
    this.userSvc.update(user).subscribe({
      next: () => {
        this.reloadPage(this.user);
      },
      error: (err) => {
        console.log(err + 'update bio error in profilecomponent');
      },
    });
  }

  productIndex() {
    this.product.productIndex().subscribe({
      next: (prods) => {
        this.products = prods;
      },
      error: (err) => {
        throw new Error('Error getting products.');
      }
    });
  }

  getMarkets() {
    this.market.index().subscribe({
      next: (mrkts) => {
        this.markets = mrkts;
      },
      error: (err) => {
        throw new Error('Error getting markets.');
      }
    });
  }

  getUsers() {
    this.userSvc.index1().subscribe({
      next: (user) => {
        this.users = user;
      },
      error: (fail) => {
        console.error('Error getting users' + fail);
      },
    });
  }

  deleteUser(user: User) {
    if (user.disabled == true) {
      user.disabled = false;
    } else {
      user.disabled = true;
    }
    this.userSvc.update(user).subscribe({
      next: () => {
        this.getUsers();
      },
      error: (fail) => {
        console.error('Error deleting user' + fail);
      },
    });
  }

  deleteProduct(prod: Product) {
    if (prod.disabled == true) {
      prod.disabled = false;
    } else {
      prod.disabled = true;
    }
    this.product.updateProduct(prod).subscribe({
      next: () => {
        this.getProducts();
      },
      error: (fail) => {
        console.error('Error deleting user' + fail);
      },
    });
  }

  deleteMarket(mrkt: Market) {
    if (mrkt.disabled == true) {
      mrkt.disabled = false;
    } else {
      mrkt.disabled = true;
    }
    this.market.update(mrkt).subscribe({
      next: () => {
        this.getMarkets;
      },
      error: (fail) => {
        console.error('Error deleting user' + fail);
      },
    });
  }
}
