import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-pokedex-login',
  templateUrl: './pokedex-login.component.html',
  styleUrls: ['./pokedex-login.component.css']
})
export class PokedexLoginComponent implements OnInit {

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
    this.restService.post(`/api/login`,
    {
      username: this.form.value.usuario,
      password: this.form.value.contrasena,
    }).subscribe(
      respuesta => {
      this.usuario = respuesta;
      if(this.usuario != null) {
        localStorage.setItem('usuarioId', this.usuario.id);
        window.location.href=`http://localhost:4200/pokemones`;
      } else {
        this.error = true;
      }
    });
  }
}
