import { Product } from "./product";
import { User } from "./user";


export class ProductComment {
  id: number;
  comment: string;
  created: string | undefined;
  updated: string | undefined;
  replyTo: string;
  myReplies: ProductComment[] = [];
  product: Product;
  user: User | undefined;
  disabled: boolean = false;

  constructor(
    id: number = 0,
    comment: string = '',
    created?: string,
    updated?: string,
    replyTo: string = '',
    myReplies: ProductComment[] = [],
    user?: User,
    product: Product = new Product(),
    disabled: boolean = false
    ) {
      this.id = id;
      this.comment = comment;
      this.created = created;
      this.updated = updated;
      this.replyTo = replyTo;
      this.myReplies = myReplies;
      this.product = product;
      this.user = user;
      this.disabled = disabled;
  }
}
