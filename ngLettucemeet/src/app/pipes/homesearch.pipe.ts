import { Pipe, PipeTransform } from '@angular/core';
import { Market } from '../models/market';

@Pipe({
  name: 'homeSearch',
})
export class HomeSearchPipe implements PipeTransform {
  transform(markets: Market[], keyword: string): Market[] {
    const results: Market[] = [];
    if (keyword === '') {
      return markets;
    }
    markets.forEach((market) => {
      if (
        market.description.toLowerCase().includes(keyword.toLowerCase()) ||
        market.name.toLowerCase().includes(keyword.toLowerCase()) ||
        market.address.city.toLowerCase().includes(keyword.toLowerCase()) ||
        market.address.state.toLowerCase().includes(keyword.toLowerCase())
      ) {
        results.push(market);
      }
    });
    return results;
  }
}
