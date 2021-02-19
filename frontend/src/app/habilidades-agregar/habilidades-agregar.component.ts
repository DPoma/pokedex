import { ActivatedRoute } from '@angular/router';
import { RestService } from './../rest.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-habilidades-agregar',
  templateUrl: './habilidades-agregar.component.html',
  styleUrls: ['./habilidades-agregar.component.css']
})
export class HabilidadesAgregarComponent implements OnInit {

  public habilidades:any = [];
  public pokemonId:string;
  public form:FormGroup;

  constructor(private activatedRoute:ActivatedRoute, private restService:RestService, private formBuilder:FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe( (paramMap:any) => {
      const {params} = paramMap;
      this.obtenerHabilidades(params.id);
      this.pokemonId = params.id;
    });

    this.form = this.formBuilder.group({
      habilidadId: ['', Validators.required]
    });
  }

  public obtenerHabilidades(id:string) {
    this.restService.get(`/api/pokemones/${id}/habilidades/agregar`).subscribe(respuesta => this.habilidades = respuesta);
  }

  public agregarHabilidad() {
    this.restService.post(`/api/pokemones/${this.pokemonId}/habilidades/agregar`,
    {
      id: this.form.value.habilidadId
    }).subscribe();
    window.location.href=`http://localhost:4200/pokemones/${this.pokemonId}`;
  }

}
