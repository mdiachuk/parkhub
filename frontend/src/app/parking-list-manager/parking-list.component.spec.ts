import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParkingListComponentManager} from "./parking-list.component";

describe('ParkingListComponent', () => {
  let component: ParkingListComponentManager;
  let fixture: ComponentFixture<ParkingListComponentManager>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParkingListComponentManager ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParkingListComponentManager);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
