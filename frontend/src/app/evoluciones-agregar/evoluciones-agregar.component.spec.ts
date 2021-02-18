import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvolucionesAgregarComponent } from './evoluciones-agregar.component';

describe('EvolucionesAgregarComponent', () => {
  let component: EvolucionesAgregarComponent;
  let fixture: ComponentFixture<EvolucionesAgregarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EvolucionesAgregarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EvolucionesAgregarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
