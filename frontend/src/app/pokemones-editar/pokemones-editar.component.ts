import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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

  constructor(private activatedRoute:ActivatedRoute, private restService:RestService, private formBuilder:FormBuilder, private router:Router) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe( (paramMap:any) => {
      const {params} = paramMap;
      this.obtenerPokemon(params.id);
      this.pokemonId = params.id;
    });

      this.form = this.formBuilder.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      nivelRequerido: ['', Validators.required],
      });
  }

  public obtenerPokemon(id:string) {
    this.restService.get(`/api/pokemones/${id}/editar`).subscribe(respuesta => {
      this.pokemon = respuesta;
      this.form.setValue({
        nombre: this.pokemon.nombre,
        descripcion: this.pokemon.descripcion,
        nivelRequerido: this.pokemon.nivelRequerido});
    });
  }

  public editarPokemon() {
    this.restService.put(`/api/pokemones/${this.pokemonId}/editar`,
    {
      nombre: this.form.value.nombre,
      descripcion: this.form.value.descripcion,
      nivelRequerido: this.form.value.nivelRequerido
    }).subscribe(response => {
      this.router.navigate(['/', 'pokemones', this.pokemonId]);
    });
  }
}
