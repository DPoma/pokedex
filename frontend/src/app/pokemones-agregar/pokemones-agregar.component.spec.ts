import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokemonesAgregarComponent } from './pokemones-agregar.component';

describe('PokemonesAgregarComponent', () => {
  let component: PokemonesAgregarComponent;
  let fixture: ComponentFixture<PokemonesAgregarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PokemonesAgregarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PokemonesAgregarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
