import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SampleTemplate3Component } from './sample-template3.component';

describe('SampleTemplate3Component', () => {
  let component: SampleTemplate3Component;
  let fixture: ComponentFixture<SampleTemplate3Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SampleTemplate3Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SampleTemplate3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
