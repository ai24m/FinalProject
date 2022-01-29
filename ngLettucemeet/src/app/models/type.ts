import { Product } from "./product";

export class Type {
  id: number;
  name: String;
  products: Product[] = [];

  constructor(id: number = 0, name: String = '',  products: Product[] = []) {
    this.id = id;
    this.name = name;
    this.products = products;
  }

}
