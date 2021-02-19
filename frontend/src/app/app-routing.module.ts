import { PokedexRegistroComponent } from './pokedex-registro/pokedex-registro.component';
import { PokedexLoginComponent } from './pokedex-login/pokedex-login.component';
import { PokedexHomeComponent } from './pokedex-home/pokedex-home.component';
import { PokemonesEditarComponent } from './pokemones-editar/pokemones-editar.component';
import { EjemplaresAgregarComponent } from './ejemplares-agregar/ejemplares-agregar.component';
import { EjemplaresListarComponent } from './ejemplares-listar/ejemplares-listar.component';
import { EvolucionesAgregarComponent } from './evoluciones-agregar/evoluciones-agregar.component';
import { TiposEliminarComponent } from './tipos-eliminar/tipos-eliminar.component';
import { TiposAgregarComponent } from './tipos-agregar/tipos-agregar.component';
import { HabilidadesEliminarComponent } from './habilidades-eliminar/habilidades-eliminar.component';
import { HabilidadesAgregarComponent } from './habilidades-agregar/habilidades-agregar.component';
import { PokemonesListarComponent } from './pokemones-listar/pokemones-listar.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PokemonesAgregarComponent } from './pokemones-agregar/pokemones-agregar.component';
import { PokemonesDetallarComponent } from './pokemones-detallar/pokemones-detallar.component';

const routes: Routes = [
  {
    path:"",redirectTo:"login",pathMatch:"full"
  },
  {
    path:'',
    component:PokedexHomeComponent
  },
  {
    path:'login',
    component:PokedexLoginComponent
  },
  {
    path:'registro',
    component:PokedexRegistroComponent
  },
  {
    path:'pokemones',
    component:PokemonesListarComponent
  },
  {
    path:'pokemones/agregar',
    component:PokemonesAgregarComponent
  },
  {
    path:'pokemones/:id',
    component:PokemonesDetallarComponent
  },
  {
    path:'pokemones/:id/editar',
    component:PokemonesEditarComponent
  },
  {
    path:'pokemones/:id/habilidades/agregar',
    component:HabilidadesAgregarComponent
  },
  {
    path:'pokemones/:id/habilidades/eliminar',
    component:HabilidadesEliminarComponent
  },
  {
    path:'pokemones/:id/tipos/agregar',
    component:TiposAgregarComponent
  },
  {
    path:'pokemones/:id/tipos/eliminar',
    component:TiposEliminarComponent
  },
  {
    path:'pokemones/:id/evoluciones/agregar',
    component:EvolucionesAgregarComponent
  },
  {
    path:'ejemplares',
    component:EjemplaresListarComponent
  },
  {
    path:'ejemplares/agregar',
    component:EjemplaresAgregarComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
