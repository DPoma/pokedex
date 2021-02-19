import { RestService } from './../rest.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-habilidades-eliminar',
  templateUrl: './habilidades-eliminar.component.html',
  styleUrls: ['./habilidades-eliminar.component.css']
})
export class HabilidadesEliminarComponent implements OnInit {

  public habilidades:any = [];
  public pokemonId:string;
  public form:FormGroup;

  constructor(private activatedRoute:ActivatedRoute, private restService:RestService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe( (paramMap:any) => {
      const {params} = paramMap;
      this.obtenerHabilidades(params.id);
      this.pokemonId = params.id;
    })

    this.form = this.formBuilder.group({
      habilidadId: ['', Validators.required]
    });
  }

  public obtenerHabilidades(id:string) {
    this.restService.get(`/api/pokemones/${id}/habilidades/eliminar`).subscribe(respuesta => this.habilidades = respuesta);
  }

  public eliminarHabilidad() {
    this.restService.post(`/api/pokemones/${this.pokemonId}/habilidades/eliminar`,
    {
      id: this.form.value.habilidadId
    }).subscribe();
    window.location.href=`http://localhost:4200/pokemones/${this.pokemonId}`;
  }

}
