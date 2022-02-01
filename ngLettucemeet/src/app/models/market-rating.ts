import { Market } from './market';
import { User } from './user';

export class MarketRating {
  marketRating: number;
  ratingAverage: number;
  comment: string;
  user: User | undefined;
  market: Market;
  disabled: boolean = false;

  constructor(
    marketRating: number = 0,
    ratingAverage: number = 0,
    comment: string = '',
    user?: User,
    market: Market = new Market(),
    disabled: boolean = false
  ) {
    this.market = market;
    this.user = user;
    this.comment = comment;
    this.marketRating = marketRating;
    this.ratingAverage = ratingAverage;
    this.disabled = disabled;
  }
}
