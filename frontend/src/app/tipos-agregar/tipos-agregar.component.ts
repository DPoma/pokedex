import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-tipos-agregar',
  templateUrl: './tipos-agregar.component.html',
  styleUrls: ['./tipos-agregar.component.css']
})
export class TiposAgregarComponent implements OnInit {

  public tipos:any = [];
  public pokemonId:string;
  public form:FormGroup;

  constructor(private activatedRoute:ActivatedRoute, private restService:RestService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe( (paramMap:any) => {
      const {params} = paramMap;
      this.obtenerTipos(params.id);
      this.pokemonId = params.id;
    })

    this.form = this.formBuilder.group({
      tipoId: ['', Validators.required]
    });
  }

  public obtenerTipos(id:string) {
    this.restService.get(`/api/pokemones/${id}/tipos/agregar`).subscribe(respuesta => this.tipos = respuesta);
  }

  public agregarTipo() {
    this.restService.post(`/api/pokemones/${this.pokemonId}/tipos/agregar`,
    {
      id: this.form.value.tipoId
    }).subscribe();
  }
}
