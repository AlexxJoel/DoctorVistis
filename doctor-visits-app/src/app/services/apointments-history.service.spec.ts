import { TestBed } from '@angular/core/testing';

import { ApointmentsHistoryService } from './apointments-history.service';

describe('ApointmentsHistoryService', () => {
  let service: ApointmentsHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApointmentsHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
