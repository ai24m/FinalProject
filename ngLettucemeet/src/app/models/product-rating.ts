import { Product } from "./product";
import { User } from "./user";

export class ProductRating {
  rating: number | undefined;
  comment: string | undefined;
  User: User | undefined;
  Product: Product | undefined;

  constructor(
    rating?: number,
    comment?: string,
    User?: User,
    Product?: Product) {
    this.rating = rating;
    this.comment = comment;
    this.User = User;
    this.Product = Product;
  }
}
