import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokemonesDetallarComponent } from './pokemones-detallar.component';

describe('PokemonesDetallarComponent', () => {
  let component: PokemonesDetallarComponent;
  let fixture: ComponentFixture<PokemonesDetallarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PokemonesDetallarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PokemonesDetallarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
