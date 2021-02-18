import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokedexLoginComponent } from './pokedex-login.component';

describe('PokedexLoginComponent', () => {
  let component: PokedexLoginComponent;
  let fixture: ComponentFixture<PokedexLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PokedexLoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PokedexLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
