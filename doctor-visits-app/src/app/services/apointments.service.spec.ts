import { TestBed } from '@angular/core/testing';

import { ApointmentsService } from './apointments.service';

describe('ApointmentsService', () => {
  let service: ApointmentsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApointmentsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
