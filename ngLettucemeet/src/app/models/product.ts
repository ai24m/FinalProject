import { Type } from "./type";
import { Market } from "./market";
import { ProductComment } from "./product-comment";
import { ProductRating } from "./product-rating";
import { User } from "./user";

export class Product {
  id: number;
  name: string;
  description: string;
  organic: boolean;
  unitPrice: number;
  imageUrl: string | undefined;
  quantity: number | undefined;
  availableDate: string | undefined;
  type: Type;
  user: User;
  ratings: ProductRating[] = [];
  comments: ProductComment[] = [];
  markets: Market[] = [];
  hide: boolean | undefined;

  constructor(
    id: number = 0,
    name: string = "",
    description: string = "",
    organic: boolean = false,
    unitPrice: number = 0.0,
    imageUrl?: string,
    quantity?: number,
    availableDate?: string,
    user: User = new User,
    type: Type = new Type,
    ratings: ProductRating[] = [],
    comments: ProductComment[] = [],
    markets: Market[] = [],
    hide: boolean = false
) {
    this.id = id
    this.name = name
    this.description = description
    this.organic = organic
    this.unitPrice = unitPrice
    this.imageUrl = imageUrl
    this.quantity = quantity
    this.availableDate = availableDate
    this.user = user
    this.type = type
    this.ratings = ratings
    this.comments = comments
    this.markets = markets
    this.hide = hide
  }

}
