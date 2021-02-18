import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HabilidadesEliminarComponent } from './habilidades-eliminar.component';

describe('HabilidadesEliminarComponent', () => {
  let component: HabilidadesEliminarComponent;
  let fixture: ComponentFixture<HabilidadesEliminarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HabilidadesEliminarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HabilidadesEliminarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
