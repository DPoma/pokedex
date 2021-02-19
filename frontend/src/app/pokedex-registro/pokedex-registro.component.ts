import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-pokedex-registro',
  templateUrl: './pokedex-registro.component.html',
  styleUrls: ['./pokedex-registro.component.css']
})
export class PokedexRegistroComponent implements OnInit {

  public usuario:any;
  public error:boolean;
  public form:FormGroup;

  constructor(private restService:RestService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      usuario: ['', Validators.required],
      contrasena: ['', Validators.required],
    });
  }

  public iniciarSesion() {
    this.restService.post(`/api/registro`,
    {
      username: this.form.value.usuario,
      password: this.form.value.contrasena,
    }).subscribe(respuesta => {
      this.usuario = respuesta;
      if(this.usuario != null) {
        window.location.href=`http://localhost:4200/login`;
      } else {
        this.error = true;
      }
    });
  }
}
