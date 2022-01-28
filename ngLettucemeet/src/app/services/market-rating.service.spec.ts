import { TestBed } from '@angular/core/testing';

import { MarketRatingService } from './market-rating.service';

describe('MarketRatingService', () => {
  let service: MarketRatingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MarketRatingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
