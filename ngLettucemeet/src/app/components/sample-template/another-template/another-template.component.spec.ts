import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnotherTemplateComponent } from './another-template.component';

describe('AnotherTemplateComponent', () => {
  let component: AnotherTemplateComponent;
  let fixture: ComponentFixture<AnotherTemplateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnotherTemplateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnotherTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
