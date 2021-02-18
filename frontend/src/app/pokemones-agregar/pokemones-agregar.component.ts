import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-pokemones-agregar',
  templateUrl: './pokemones-agregar.component.html',
  styleUrls: ['./pokemones-agregar.component.css']
})
export class PokemonesAgregarComponent implements OnInit {

  public pokemon:any;
  public pokemonId:string;
  public form:FormGroup;

  constructor(private restService:RestService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {

    this.form = this.formBuilder.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      nivelRequerido: ['', Validators.required],
      imagen: ['']
    });
  }

  public agregarPokemon() {
    this.restService.post(`/api/pokemones/agregar`,
    {
      nombre: this.form.value.nombre,
      descripcion: this.form.value.descripcion,
      nivelRequerido: this.form.value.nivelRequerido,
      imagen: this.form.value.image
    }).subscribe();
  }
}
