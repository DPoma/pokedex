import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokedexLogoComponent } from './pokedex-logo.component';

describe('PokedexLogoComponent', () => {
  let component: PokedexLogoComponent;
  let fixture: ComponentFixture<PokedexLogoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PokedexLogoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PokedexLogoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
