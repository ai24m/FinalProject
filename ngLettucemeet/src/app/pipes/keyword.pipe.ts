import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../models/product';

@Pipe({
  name: 'keyword'
})
export class KeywordPipe implements PipeTransform {

  transform(products: Product[], keyword: string): Product[] {
    const results: Product[] = [];
    if (keyword === "") {
      return products;
    }
    products.forEach((prod) => {
      if (prod.description.includes(keyword) || prod.name.includes(keyword)) {
        results.push(prod);
      }
    });
    return results;
  }

}
