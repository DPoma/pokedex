import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HabilidadesAgregarComponent } from './habilidades-agregar.component';

describe('HabilidadesAgregarComponent', () => {
  let component: HabilidadesAgregarComponent;
  let fixture: ComponentFixture<HabilidadesAgregarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HabilidadesAgregarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HabilidadesAgregarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
