import { Router } from '@angular/router';
import { RestService } from './../rest.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-pokedex-logout',
  templateUrl: './pokedex-logout.component.html',
  styleUrls: ['./pokedex-logout.component.css']
})
export class PokedexLogoutComponent implements OnInit {

  public form: FormGroup;

  constructor(private restService:RestService, private formBuilder: FormBuilder, private router:Router) { }

  ngOnInit(): void {

    this.form = this.formBuilder.group({});
  }

  public cerrarSesion() {
    this.restService.post('/api/logout', {}).subscribe();
    this.router.navigate(['/login']);
    localStorage.removeItem('usuario');
  }

}
