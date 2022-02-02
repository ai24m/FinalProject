import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../models/product';

@Pipe({
  name: 'productDelete'
})
export class ProductDeletePipe implements PipeTransform {

  transform(products: Product[]): Product[] {
    const result: Product[] = [];
    products.forEach(product => {
      if (!product.disabled) {
        result.push(product);
      }
    })
    return result;
  }

}
