import { Product } from "./product";
import { User } from "./user";

export class ProductRating {
  rating: number;
  ratingAverage: number;
  comment: string | undefined;
  user: User | undefined;
  product: Product;
  disabled: boolean = false;

  constructor(
    rating: number = 0,
    ratingAverage: number = 0,
    comment?: string,
    user?: User,
    product: Product = new Product(),
    disabled: boolean = false
    ) {
    this.rating = rating;
    this.ratingAverage = ratingAverage;
    this.comment = comment;
    this.user = user;
    this.product = product;
    this.disabled = disabled;
  }
}
