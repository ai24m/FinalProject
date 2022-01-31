import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SampleTemplate2Component } from './sample-template2.component';

describe('SampleTemplate2Component', () => {
  let component: SampleTemplate2Component;
  let fixture: ComponentFixture<SampleTemplate2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SampleTemplate2Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SampleTemplate2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
