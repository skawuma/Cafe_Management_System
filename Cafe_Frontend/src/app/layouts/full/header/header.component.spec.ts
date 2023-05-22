import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppHeaderComponent } from './header.component';

describe('HeaderComponent', () => {
  let component: AppHeaderComponent;
  let fixture: ComponentFixture<AppHeaderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AppHeaderComponent]
    });
    fixture = TestBed.createComponent(AppHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
