export class ProductRatingId {
  userId: number;
  productId: number;
  disabled: boolean = false;

  constructor(
    userId: number = 0,
    productId: number = 0,
    disabled: boolean = false
  ) {
    this.userId = userId;
    this.productId = productId;
    this.disabled = disabled;
  }
}
