import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TiposAgregarComponent } from './tipos-agregar.component';

describe('TiposAgregarComponent', () => {
  let component: TiposAgregarComponent;
  let fixture: ComponentFixture<TiposAgregarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TiposAgregarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TiposAgregarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
