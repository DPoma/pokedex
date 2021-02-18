import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokedexFooterComponent } from './pokedex-footer.component';

describe('PokedexFooterComponent', () => {
  let component: PokedexFooterComponent;
  let fixture: ComponentFixture<PokedexFooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PokedexFooterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PokedexFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
