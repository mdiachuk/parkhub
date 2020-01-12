import { TestBed } from '@angular/core/testing';

import { AdminTicketService } from './admin-ticket.service';

describe('AdminTicketService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdminTicketService = TestBed.get(AdminTicketService);
    expect(service).toBeTruthy();
  });
});
