import { RestService } from './../rest.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pokemones-detallar',
  templateUrl: './pokemones-detallar.component.html',
  styleUrls: ['./pokemones-detallar.component.css']
})
export class PokemonesDetallarComponent implements OnInit {

  public pokemon:any;
  public dataCargada:boolean;

  constructor(private activatedRoute:ActivatedRoute, private restService:RestService) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe( (paramMap:any) => {
      const {params} = paramMap;
      this.obtenerPokemon(params.id);
    })
  }

  public obtenerPokemon(id:string) {
    this.restService.get(`/api/pokemones/${id}`).subscribe(respuesta => {
      this.pokemon = respuesta;
      this.dataCargada=true;
    });
  }

}
