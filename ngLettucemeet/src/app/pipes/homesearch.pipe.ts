import { Pipe, PipeTransform } from '@angular/core';
import { Market } from '../models/market';

@Pipe({
  name: 'homeSearch'
})
export class HomeSearchPipe implements PipeTransform {

  transform(markets: Market[], keyword: string): Market[] {
    const results: Market[] = [];
    if (keyword === "") {
      return markets;
    }
    markets.forEach((market) => {
      if (market.description.includes(keyword) ||
      market.name.includes(keyword) ||
      market.address.city.includes(keyword) ||
      market.address.state.includes(keyword)) {
        results.push(market);
      }
    });
    return results;
  }

}
