import { User } from "./user";

export class SellerRating {
  rating: number;
  comment: string | undefined;
  user: User | undefined;
  seller: User | undefined;

  constructor(
    rating: number = 0,
    comment?: string,
    user?: User,
    seller?: User) {
    this.rating = rating;
    this.comment = comment;
    this.user = user;
    this.seller = seller;
  }
}
