import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketIdComponent } from './market-id.component';

describe('MarketIdComponent', () => {
  let component: MarketIdComponent;
  let fixture: ComponentFixture<MarketIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MarketIdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
