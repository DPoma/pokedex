import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EjemplaresAgregarComponent } from './ejemplares-agregar.component';

describe('EjemplaresAgregarComponent', () => {
  let component: EjemplaresAgregarComponent;
  let fixture: ComponentFixture<EjemplaresAgregarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EjemplaresAgregarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EjemplaresAgregarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
