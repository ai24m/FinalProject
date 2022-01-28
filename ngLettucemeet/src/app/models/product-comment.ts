import { Product } from "./product";
import { User } from "./user";


export class ProductComment {
  id: number;
  comment: string | undefined;
  created: string | undefined;
  updated: string | undefined;
  ProductComment: ProductComment | undefined;
  myReplies: ProductComment[] = [];
  product: Product | undefined;
  user: User | undefined;

  constructor(
    id: number = 0,
    rating?: number,
    comment?: string,
    User?: User,
    Product?: Product) {
    // this.rating = rating;
    // this.comment = comment;
    // this.User = User;
    // this.Product = Product;
    this.id = id;
  }
}
