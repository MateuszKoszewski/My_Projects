import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplaySingleAuctionComponent } from './display-single-auction.component';

describe('DisplaySingleAuctionComponent', () => {
  let component: DisplaySingleAuctionComponent;
  let fixture: ComponentFixture<DisplaySingleAuctionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplaySingleAuctionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplaySingleAuctionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
