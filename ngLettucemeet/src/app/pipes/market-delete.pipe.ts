import { Pipe, PipeTransform } from '@angular/core';
import { Market } from '../models/market';

@Pipe({
  name: 'marketDelete'
})
export class MarketDeletePipe implements PipeTransform {

  transform(markets: Market[], showAll?: boolean): Market[] {
    const result: Market[] = [];
    if (showAll) {
      return markets;
    }
    markets.forEach(market => {
      if (!market.disabled) {
        result.push(market);
      }
    })
    return result;
  }

}
