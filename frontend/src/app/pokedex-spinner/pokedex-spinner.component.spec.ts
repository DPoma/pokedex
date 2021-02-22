import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokedexSpinnerComponent } from './pokedex-spinner.component';

describe('PokedexSpinnerComponent', () => {
  let component: PokedexSpinnerComponent;
  let fixture: ComponentFixture<PokedexSpinnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PokedexSpinnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PokedexSpinnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
