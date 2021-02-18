import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EjemplaresListarComponent } from './ejemplares-listar.component';

describe('EjemplaresListarComponent', () => {
  let component: EjemplaresListarComponent;
  let fixture: ComponentFixture<EjemplaresListarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EjemplaresListarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EjemplaresListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
