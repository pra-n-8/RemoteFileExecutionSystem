import { ServerListComponentComponent } from './server-list-component/server-list-component.component';
import { HomeComponent } from './home/home.component';
import { UploadComponentComponent } from './upload-component/upload-component.component';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'servers/:type',
    component: ServerListComponentComponent
  },
  {
    path: 'upload',
    component: UploadComponentComponent
  },
  {
    path: '**',
    redirectTo: '404'
  },
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
