import { TestBed } from '@angular/core/testing';

import { AddAuctionServiceService } from './add-auction-service.service';

describe('AddAuctionServiceService', () => {
  let service: AddAuctionServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddAuctionServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
