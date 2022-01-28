import { TestBed } from '@angular/core/testing';

import { SellerRatingService } from './seller-rating.service';

describe('SellerRatingService', () => {
  let service: SellerRatingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SellerRatingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
