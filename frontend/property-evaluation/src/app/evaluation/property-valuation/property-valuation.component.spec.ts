import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertyValuationComponent } from './property-valuation.component';

describe('PropertyValuationComponent', () => {
  let component: PropertyValuationComponent;
  let fixture: ComponentFixture<PropertyValuationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PropertyValuationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PropertyValuationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
