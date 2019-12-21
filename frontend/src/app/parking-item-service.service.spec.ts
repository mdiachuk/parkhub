import { TestBed } from '@angular/core/testing';

import { ParkingItemServiceService } from './parking-item-service.service';

describe('ParkingItemServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ParkingItemServiceService = TestBed.get(ParkingItemServiceService);
    expect(service).toBeTruthy();
  });
});
