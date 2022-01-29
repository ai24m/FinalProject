import { Product } from "./product";
import { User } from "./user";

export class ProductRating {
  rating: number | undefined;
  comment: string | undefined;
  user: User | undefined;
  product: Product | undefined;

  constructor(
    rating?: number,
    comment?: string,
    user?: User,
    product?: Product) {
    this.rating = rating;
    this.comment = comment;
    this.user = user;
    this.product = product;
  }
}
