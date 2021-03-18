import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoughtAuctionsComponent } from './bought-auctions.component';

describe('BoughtAuctionsComponent', () => {
  let component: BoughtAuctionsComponent;
  let fixture: ComponentFixture<BoughtAuctionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoughtAuctionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoughtAuctionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
