import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './components/app/app.component';
import { RouterModule, Routes } from '@angular/router';
import { MdButtonModule, MdIconModule, MdSidenavModule, MdToolbarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpModule } from '@angular/http';

const routes: Routes = [
  {
    path: '',
    children: []
  }
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,

    RouterModule.forRoot(routes),

    HttpModule,

    MdSidenavModule,
    MdToolbarModule,
    MdIconModule,
    MdButtonModule
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule
{
}
