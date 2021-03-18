import { TestBed } from '@angular/core/testing';

import { BoughtService } from './bought.service';

describe('BoughtService', () => {
  let service: BoughtService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BoughtService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
