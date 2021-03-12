import { TestBed } from '@angular/core/testing';

import { MyAuctionsService } from './my-auctions.service';

describe('MyAuctionsService', () => {
  let service: MyAuctionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MyAuctionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
