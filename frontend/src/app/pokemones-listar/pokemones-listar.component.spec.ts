import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokemonesListarComponent } from './pokemones-listar.component';

describe('PokemonesListarComponent', () => {
  let component: PokemonesListarComponent;
  let fixture: ComponentFixture<PokemonesListarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PokemonesListarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PokemonesListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
