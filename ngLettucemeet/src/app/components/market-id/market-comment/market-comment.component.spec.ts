import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketCommentComponent } from './market-comment.component';

describe('MarketCommentComponent', () => {
  let component: MarketCommentComponent;
  let fixture: ComponentFixture<MarketCommentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MarketCommentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketCommentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
