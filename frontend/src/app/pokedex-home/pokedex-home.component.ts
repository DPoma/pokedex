import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pokedex-home',
  templateUrl: './pokedex-home.component.html',
  styleUrls: ['./pokedex-home.component.css']
})
export class PokedexHomeComponent implements OnInit {

  public usuario:string;

  constructor() { }

  ngOnInit(): void {
    this.usuario = localStorage.getItem('usuario');
  }

}
