import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppSidebarComponent } from './sidebar.component';

describe('SidebarComponent', () => {
  let component: AppSidebarComponent;
  let fixture: ComponentFixture<AppSidebarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AppSidebarComponent]
    });
    fixture = TestBed.createComponent(AppSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
