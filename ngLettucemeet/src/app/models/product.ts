export class Product {
  id: number;
  name: string;
  description: string;
  organic: boolean | undefined;
  unitPrice: number | undefined;
  imageUrl: string | undefined;
  quantity: number | undefined;
  availableDate: string | undefined;

  constructor(
    id: number = 0,
    name: string = "",
    description: string = "",
    organic?: boolean,
    unitPrice?: number,
    imageUrl?: string,
    quantity?: number,
    availableDate?: string
) {
    this.id = id
    this.name = name
    this.description = description
    this.organic = organic
    this.unitPrice = unitPrice
    this.imageUrl = imageUrl
    this.quantity = quantity
    this.availableDate = availableDate
  }

}
