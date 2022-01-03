import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BanklistComponent } from './banklist.component';
import {BankcardComponent} from '../bankcard/bankcard.component';

describe('BanklistComponent', () => {
  let component: BanklistComponent;
  let fixture: ComponentFixture<BanklistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BanklistComponent , BankcardComponent]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BanklistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
