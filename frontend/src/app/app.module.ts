import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HabilidadesAgregarComponent } from './habilidades-agregar/habilidades-agregar.component';
import { HabilidadesEliminarComponent } from './habilidades-eliminar/habilidades-eliminar.component';
import { TiposEliminarComponent } from './tipos-eliminar/tipos-eliminar.component';
import { TiposAgregarComponent } from './tipos-agregar/tipos-agregar.component';
import { EjemplaresListarComponent } from './ejemplares-listar/ejemplares-listar.component';
import { EjemplaresAgregarComponent } from './ejemplares-agregar/ejemplares-agregar.component';
import { EvolucionesAgregarComponent } from './evoluciones-agregar/evoluciones-agregar.component';
import { PokemonesListarComponent } from './pokemones-listar/pokemones-listar.component';
import { PokemonesAgregarComponent } from './pokemones-agregar/pokemones-agregar.component';
import { PokemonesEditarComponent } from './pokemones-editar/pokemones-editar.component';
import { HttpClientModule } from '@angular/common/http';
import { PokedexHomeComponent } from './pokedex-home/pokedex-home.component';
import { PokedexHeaderComponent } from './pokedex-header/pokedex-header.component';
import { PokedexFooterComponent } from './pokedex-footer/pokedex-footer.component';
import { PokedexLoginComponent } from './pokedex-login/pokedex-login.component';
import { PokedexLogoutComponent } from './pokedex-logout/pokedex-logout.component';
import { PokedexRegistroComponent } from './pokedex-registro/pokedex-registro.component';
import { PokemonesDetallarComponent } from './pokemones-detallar/pokemones-detallar.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PokedexSpinnerComponent } from './pokedex-spinner/pokedex-spinner.component';

@NgModule({
  declarations: [
    AppComponent,
    HabilidadesAgregarComponent,
    HabilidadesEliminarComponent,
    TiposEliminarComponent,
    TiposAgregarComponent,
    EjemplaresListarComponent,
    EjemplaresAgregarComponent,
    EvolucionesAgregarComponent,
    PokemonesListarComponent,
    PokemonesAgregarComponent,
    PokemonesEditarComponent,
    PokedexHomeComponent,
    PokedexHeaderComponent,
    PokedexFooterComponent,
    PokedexLoginComponent,
    PokedexLogoutComponent,
    PokedexRegistroComponent,
    PokemonesDetallarComponent,
    PokedexSpinnerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
