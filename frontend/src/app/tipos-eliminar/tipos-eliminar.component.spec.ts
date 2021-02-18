import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TiposEliminarComponent } from './tipos-eliminar.component';

describe('TiposEliminarComponent', () => {
  let component: TiposEliminarComponent;
  let fixture: ComponentFixture<TiposEliminarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TiposEliminarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TiposEliminarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
