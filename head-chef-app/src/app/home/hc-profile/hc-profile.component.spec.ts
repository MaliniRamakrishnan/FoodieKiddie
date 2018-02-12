import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HcProfileComponent } from './hc-profile.component';

describe('HcProfileComponent', () => {
  let component: HcProfileComponent;
  let fixture: ComponentFixture<HcProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HcProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HcProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
