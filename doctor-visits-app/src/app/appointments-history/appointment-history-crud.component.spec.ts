import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentHistoryCrudComponent } from './appointment-history-crud.component';

describe('AppointmentHistoryCrudComponent', () => {
  let component: AppointmentHistoryCrudComponent;
  let fixture: ComponentFixture<AppointmentHistoryCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentHistoryCrudComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentHistoryCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
