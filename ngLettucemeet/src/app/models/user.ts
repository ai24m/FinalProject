import { Address } from './address';
import { Market } from './market';
import { MarketComment } from './market-comment';
import { MarketRating } from './market-rating';
import { Product } from './product';
import { ProductComment } from './product-comment';
import { ProductRating } from './product-rating';
import { SellerRating } from './seller-rating';

export class User {
  id: number;
  username: string;
  password: string;
  email: string | undefined;
  role: string | undefined;
  firstName: string;
  lastName: string;
  businessname: string;
  imageurl: string;
  address: Address;
  productratings: ProductRating[] = [];
  sellerratings: SellerRating[] = [];
  userratings: SellerRating[] = [];
  products: Product[] = [];
  marketratings: MarketRating[] = [];
  markets: Market[] = [];
  productcomments: ProductComment[] = [];
  marketcomments: MarketComment[] = [];
  disabled: boolean = false;

  constructor(
    id: number = 0,
    username: string = '',
    password: string = '',
    email?: string,
    role?: string,
    firstName: string = '',
    lastName: string = '',
    businessname: string = '',
    imageurl: string = '',
    address: Address = new Address(),
    productrating: ProductRating[] = [],
    sellerratings: SellerRating[] = [],
    userratings: SellerRating[] = [],
    products: Product[] = [],
    marketratings: MarketRating[] = [],
    markets: Market[] = [],
    productcomments: ProductComment[] = [],
    marketcomments: MarketComment[] = [],
    disabled: boolean = false
  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.businessname = businessname;
    this.imageurl = imageurl;
    this.address = address;
    this.productratings = productrating;
    this.sellerratings = sellerratings;
    this.userratings = userratings;
    this.products = products;
    this.marketratings = marketratings;
    this.markets = markets;
    this.productcomments = productcomments;
    this.marketcomments = marketcomments;
    this.disabled = disabled;
  }
}
