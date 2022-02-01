import { Market } from './market';
import { User } from './user';

export class MarketComment {
  id: number;
  comment: string;
  created: string | undefined;
  updated: string | undefined;
  replyTo: string | undefined;
  user: User | undefined;
  market: Market;
  replies: MarketComment[] = [];

  constructor(
    id: number = 0,
    comment: string = '',
    created?: string,
    updated?: string,
    replyTo?: string,
    user?: User,
    market: Market = new Market(),
    replies: MarketComment[] = []
  ) {
    this.id = id;
    this.comment = comment;
    this.created = created;
    this.updated = updated;
    this.replyTo = replyTo;
    this.user = user;
    this.market = market;
    this.replies = replies;
  }
}
