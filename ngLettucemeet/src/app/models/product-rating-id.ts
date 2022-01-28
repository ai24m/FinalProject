export class ProductRatingId {
  userId: number;
  productId: number;

  constructor(
    userId: number = 0,
    productId: number = 0)
    {
    this.userId = userId;
    this.productId = productId;
  }
}
