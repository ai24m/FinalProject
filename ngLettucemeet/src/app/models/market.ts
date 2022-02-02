import { Address } from './address';
import { MarketComment } from './market-comment';
import { MarketRating } from './market-rating';
import { Product } from './product';
import { User } from './user';

export class Market {
  id: number;
  name: string;
  description: string;
  imageUrl: string;
  marketDate: string;
  createTime: string;
  updateTime: string;
  user: User;
  address: Address;
  products: Product[] = [];
  marketRatings: MarketRating[] = [];
  marketComments: MarketComment[] = [];
  hide: boolean | undefined;
  disabled: boolean;
  // disabled: boolean = false;

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    imageUrl: string = '',
    marketDate: string = '',
    createTime: string = '',
    updateTime: string = '',
    user: User = new User(),
    address: Address = new Address(),
    products: Product[] = [],
    marketRatings: MarketRating[] = [],
    marketComments: MarketComment[] = [],
    hide: boolean = false,
    disabled: boolean = false
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    this.marketDate = marketDate;
    this.createTime = createTime;
    this.updateTime = updateTime;
    this.user = user;
    this.address = address;
    this.products = products;
    this.marketRatings = marketRatings;
    this.marketComments = marketComments;
    this.hide = hide;
    this.disabled = disabled;
  }
}
