import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketRatingComponent } from './market-rating.component';

describe('MarketRatingComponent', () => {
  let component: MarketRatingComponent;
  let fixture: ComponentFixture<MarketRatingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MarketRatingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketRatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
