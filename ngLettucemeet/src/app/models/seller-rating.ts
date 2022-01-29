import { User } from "./user";

export class SellerRating {
  rating: number | undefined;
  comment: string | undefined;
  user: User | undefined;
  seller: User | undefined;

  constructor(
    rating?: number,
    comment?: string,
    user?: User,
    seller?: User) {
    this.rating = rating;
    this.comment = comment;
    this.user = user;
    this.seller = seller;
  }
}
