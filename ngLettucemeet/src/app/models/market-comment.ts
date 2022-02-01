import { Market } from './market';
import { User } from './user';

export class MarketComment {
  id: number;
  comment: string;
  createTime: string | undefined;
  updateTime: string | undefined;
  replyTo: string | undefined;
  user: User | undefined;
  market: Market;
  replies: MarketComment[] = [];

  constructor(
    id: number = 0,
    comment: string = '',
    createTime?: string,
    updateTime?: string,
    replyTo?: string,
    user?: User,
    market: Market = new Market(),
    replies: MarketComment[] = []
  ) {
    this.id = id;
    this.comment = comment;
    this.createTime = createTime;
    this.updateTime = updateTime;
    this.replyTo = replyTo;
    this.user = user;
    this.market = market;
    this.replies = replies;
  }
}
