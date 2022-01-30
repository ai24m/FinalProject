import { Market } from './market';
import { User } from './user';

export class MarketRating {
  rating: number;
  comment: string;
  user: User | undefined;
  market: Market;

  constructor(
    rating: number = 0,
    comment: string ='',
    user?: User,
    market: Market = new Market(),
  ) {
    this.market = market;
    this.user = user;
    this.comment = comment;
    this.rating = rating;

  }
}
