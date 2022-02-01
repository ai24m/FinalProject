import { Market } from './market';
import { User } from './user';

export class MarketRating {
  marketRating: number;
  ratingAverage: number;
  comment: string;
  user: User | undefined;
  market: Market;

  constructor(
    marketRating: number = 0,
    ratingAverage: number = 0,
    comment: string = '',
    user?: User,
    market: Market = new Market()
  ) {
    this.market = market;
    this.user = user;
    this.comment = comment;
    this.marketRating = marketRating;
    this.ratingAverage = ratingAverage;
  }
}
