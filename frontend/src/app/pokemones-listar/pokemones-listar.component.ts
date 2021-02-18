import { Component, OnInit, Input } from '@angular/core';
import { RestService } from '../rest.service';

@Component({
  selector: 'app-pokemones-listar',
  templateUrl: './pokemones-listar.component.html',
  styleUrls: ['./pokemones-listar.component.css']
})
export class PokemonesListarComponent implements OnInit {

  public pokemones:any = [];

  constructor(private restService:RestService) {}

  ngOnInit(): void {
    this.obtenerPokemones();
  }

  public obtenerPokemones() {
    this.restService.get(`/api/pokemones`).subscribe(respuesta => this.pokemones = respuesta);
  }
}
