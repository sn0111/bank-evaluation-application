import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InitiatorDetailsComponent } from './initiator-details.component';

describe('InitiatorDetailsComponent', () => {
  let component: InitiatorDetailsComponent;
  let fixture: ComponentFixture<InitiatorDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InitiatorDetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InitiatorDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
