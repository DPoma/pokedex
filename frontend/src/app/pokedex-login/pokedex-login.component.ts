import { Component, OnInit } from '@angular/core';
import { RestService } from '../rest.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-pokedex-login',
  templateUrl: './pokedex-login.component.html',
  styleUrls: ['./pokedex-login.component.css']
})
export class PokedexLoginComponent implements OnInit {

  public mensajeError: boolean;
  public form: FormGroup;

  constructor(private restService: RestService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit(): void {

    this.form = this.formBuilder.group({
      usuario: ['', Validators.required],
      contrasena: ['', Validators.required],
    });
  }

  public iniciarSesion() {
    let usuario:string = this.form.value.usuario;
    let contrasena:string = this.form.value.contrasena;
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(usuario + ':' + contrasena) });
    return this.restService.get(`/api/login`, { headers, responseType: 'text' as 'json'})
    .subscribe(
    response => {
      this.mensajeError = false;
      localStorage.setItem('usuario', usuario);
      this.router.navigate(['/']);
    },
    error => {this.mensajeError = true;});
  }
}
