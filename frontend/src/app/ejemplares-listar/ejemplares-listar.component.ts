import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-ejemplares-listar',
  templateUrl: './ejemplares-listar.component.html',
  styleUrls: ['./ejemplares-listar.component.css']
})
export class EjemplaresListarComponent implements OnInit {

  public ejemplares:any = [];

  constructor(private restService:RestService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    this.obtenerEjemplares()
  }

  public obtenerEjemplares() {
    this.restService.get(`/api/ejemplares`).subscribe(
      respuesta => {
      this.ejemplares = respuesta;
    });
  }
}
