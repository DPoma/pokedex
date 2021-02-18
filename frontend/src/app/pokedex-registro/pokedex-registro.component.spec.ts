import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokedexRegistroComponent } from './pokedex-registro.component';

describe('PokedexRegistroComponent', () => {
  let component: PokedexRegistroComponent;
  let fixture: ComponentFixture<PokedexRegistroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PokedexRegistroComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PokedexRegistroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
