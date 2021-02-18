import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RestService } from '../rest.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-pokemones-editar',
  templateUrl: './pokemones-editar.component.html',
  styleUrls: ['./pokemones-editar.component.css']
})
export class PokemonesEditarComponent implements OnInit {

  public pokemon:any;
  public pokemonId:string;
  public form:FormGroup;

  constructor(private activatedRoute:ActivatedRoute, private restService:RestService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe( (paramMap:any) => {
      const {params} = paramMap;
      this.obtenerPokemon(params.id);
      this.pokemonId = params.id;
    })

    this.form = this.formBuilder.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      nivelRequerido: ['', Validators.required]
    });
  }

  public obtenerPokemon(id:string) {
    this.restService.get(`/api/pokemones/${id}/editar`).subscribe(respuesta => this.pokemon = respuesta);
  }

  public editarPokemon() {
    this.restService.put(`/api/pokemones/${this.pokemonId}/editar`,
    {
      nombre: this.form.value.nombre,
      descripcion: this.form.value.descripcion,
      nivelRequerido: this.form.value.nivelRequerido
    }).subscribe();
  }
}
