import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerParkingListComponent } from './manager-parking-list.component';

describe('ManagerParkingListComponent', () => {
  let component: ManagerParkingListComponent;
  let fixture: ComponentFixture<ManagerParkingListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagerParkingListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerParkingListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
