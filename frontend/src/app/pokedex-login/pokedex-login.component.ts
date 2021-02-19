import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pokedex-login',
  templateUrl: './pokedex-login.component.html',
  styleUrls: ['./pokedex-login.component.css']
})
export class PokedexLoginComponent implements OnInit {

  public usuario:any;
  public error:boolean;
  public form:FormGroup;

  constructor(private restService:RestService, private formBuilder:FormBuilder, private router:Router) { }

  ngOnInit(): void {

    this.form = this.formBuilder.group({
      usuario: ['', Validators.required],
      contrasena: ['', Validators.required],
    });
  }

  public iniciarSesion() {
    let resp = this.restService.login(this.form.value.usuario, this.form.value.contrasena);
    console.log(this.form.value.usuario);
    console.log(this.form.value.contrasena);
    resp.subscribe(data => {
     console.log(data);
     //this.router.navigate(["/pokemones"]);
    });
  }
/*
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
        localStorage.setItem('usuario', this.usuario.username);
        window.location.href=`http://localhost:4200`;
      } else {
        this.error = true;
      }
    });
  }
*/
}
