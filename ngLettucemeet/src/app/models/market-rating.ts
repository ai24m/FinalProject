import { Market } from './market';
import { User } from './user';

export class MarketRating {
  id: number;
  marketRating: string;
  user: User | undefined;
  market: Market;
  comment: string | undefined;
  constructor(
    id: number = 0,
    marketRating: string = '',
    user?: User,
    market: Market = new Market(),
    comment?: string
  ) {
    this.id = id;
    this.market = market;
    this.user = user;
    this.comment = comment;
    this.marketRating = marketRating;
  }
}
