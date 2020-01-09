import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTicketDetailViewerComponent } from './admin-ticket-detail-viewer.component';

describe('AdminTicketDetailViewerComponent', () => {
  let component: AdminTicketDetailViewerComponent;
  let fixture: ComponentFixture<AdminTicketDetailViewerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminTicketDetailViewerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminTicketDetailViewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
