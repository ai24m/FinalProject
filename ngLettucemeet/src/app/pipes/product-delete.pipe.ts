import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../models/product';

@Pipe({
  name: 'productDelete'
})
export class ProductDeletePipe implements PipeTransform {

  transform(products: Product[], showAll?: boolean): Product[] {
    const result: Product[] = [];
    if (showAll) {
      return products;
    }
    products.forEach(product => {
      if (!product.disabled) {
        result.push(product);
      }
    })
    return result;
  }

}
