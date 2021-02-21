import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-ejemplares-agregar',
  templateUrl: './ejemplares-agregar.component.html',
  styleUrls: ['./ejemplares-agregar.component.css']
})
export class EjemplaresAgregarComponent implements OnInit {

  public pokemones:any = [];
  public form:FormGroup;

  constructor(private restService:RestService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {

    this.obtenerPokemones();

    this.form = this.formBuilder.group({
      pokemonId: ['', Validators.required],
      nivelActual: ['', Validators.required]
    });
  }

  public obtenerPokemones() {
    this.restService.get(`/api/pokemones`).subscribe(respuesta => this.pokemones = respuesta);
  }

  public agregarEjemplar() {
    this.restService.post(`/api/ejemplares/agregar`,
    {
      pokemon: {
          id: this.form.value.pokemonId
      },
      nivelActual: this.form.value.nivelActual
  }).subscribe();
  window.location.href=`http://localhost:4200/ejemplares`;
  }
}
