import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokemonesEditarComponent } from './pokemones-editar.component';

describe('PokemonesEditarComponent', () => {
  let component: PokemonesEditarComponent;
  let fixture: ComponentFixture<PokemonesEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PokemonesEditarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PokemonesEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
