import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KitchenManagementComponent } from './kitchen-management.component';

describe('KitchenManagementComponent', () => {
  let component: KitchenManagementComponent;
  let fixture: ComponentFixture<KitchenManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KitchenManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KitchenManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
