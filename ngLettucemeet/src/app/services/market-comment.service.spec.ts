import { TestBed } from '@angular/core/testing';

import { MarketcommentService } from './market-comment.service';

describe('MarketcommentService', () => {
  let service: MarketcommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MarketcommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
