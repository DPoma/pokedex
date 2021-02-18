import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokedexLogoutComponent } from './pokedex-logout.component';

describe('PokedexLogoutComponent', () => {
  let component: PokedexLogoutComponent;
  let fixture: ComponentFixture<PokedexLogoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PokedexLogoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PokedexLogoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
