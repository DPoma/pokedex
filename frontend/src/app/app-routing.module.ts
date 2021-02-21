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
import { AuthGuardService } from './auth.service';

const routes: Routes = [
  { path: 'login', component: PokedexLoginComponent },
  { path: 'registro', component: PokedexRegistroComponent },
  { path: '', component: PokedexHomeComponent, canActivate: [AuthGuardService] },
  { path: 'pokemones', component: PokemonesListarComponent, canActivate: [AuthGuardService] },
  { path: 'pokemones/agregar', component: PokemonesAgregarComponent, canActivate: [AuthGuardService] },
  { path: 'pokemones/:id', component: PokemonesDetallarComponent, canActivate: [AuthGuardService] },
  { path: 'pokemones/:id/editar', component: PokemonesEditarComponent, canActivate: [AuthGuardService] },
  { path: 'pokemones/:id/habilidades/agregar', component: HabilidadesAgregarComponent, canActivate: [AuthGuardService] },
  { path: 'pokemones/:id/habilidades/eliminar', component: HabilidadesEliminarComponent, canActivate: [AuthGuardService] },
  { path: 'pokemones/:id/tipos/agregar', component: TiposAgregarComponent, canActivate: [AuthGuardService] },
  { path: 'pokemones/:id/tipos/eliminar', component: TiposEliminarComponent, canActivate: [AuthGuardService] },
  { path: 'pokemones/:id/evoluciones/agregar', component: EvolucionesAgregarComponent, canActivate: [AuthGuardService] },
  { path: 'ejemplares', component: EjemplaresListarComponent, canActivate: [AuthGuardService] },
  { path: 'ejemplares/agregar', component: EjemplaresAgregarComponent, canActivate: [AuthGuardService] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
