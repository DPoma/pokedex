import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-evoluciones-agregar',
  templateUrl: './evoluciones-agregar.component.html',
  styleUrls: ['./evoluciones-agregar.component.css']
})
export class EvolucionesAgregarComponent implements OnInit {

  public pokemon:any;
  public pokemonId:string;
  public form:FormGroup;

  constructor(private activatedRoute:ActivatedRoute, private restService:RestService, private formBuilder:FormBuilder, private router:Router) { }

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe( (paramMap:any) => {
      const {params} = paramMap;
      this.obtenerEvolucion(params.id);
      this.pokemonId = params.id;
    });

    this.form = this.formBuilder.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      nivelRequerido: ['', Validators.required],
      imagen: ['']
    });
  }

  public obtenerEvolucion(id:string) {
    this.restService.get(`/api/pokemones/${id}/habilidades/agregar`).subscribe();
  }

  public agregarEvolucion() {
    this.restService.post(`/api/pokemones/${this.pokemonId}/evoluciones/agregar`,
    {
      nombre: this.form.value.nombre,
      descripcion: this.form.value.descripcion,
      nivelRequerido: this.form.value.nivelRequerido,
      imagen: this.form.value.imagen
    }).subscribe(() => this.router.navigate(['/', 'pokemones', this.pokemonId]));
  }
}
