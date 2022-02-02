import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../models/product';

@Pipe({
  name: 'keyword',
})
export class KeywordPipe implements PipeTransform {
  transform(products: Product[], keyword: string): Product[] {
    const results: Product[] = [];
    if (keyword.toLowerCase() === '') {
      return products;
    }
    products.forEach((prod) => {
      if (
        prod.description.toLowerCase().includes(keyword.toLowerCase()) ||
        prod.name.toLowerCase().includes(keyword.toLowerCase())
      ) {
        results.push(prod);
      }
    });
    return results;
  }
}
