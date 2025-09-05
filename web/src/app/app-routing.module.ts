import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { SankeyAppComponent } from './components/sankey-app/sankey-app.component'; 
import { MatrizComponent } from './components/matriz/matriz.component'; 
import { HomeComponent } from './components/home/home.component'; 
import { LoginComponent } from './components/login/login.component'; 
import { SignupComponent } from './components/signup/signup.component'; 
import { SankeyDataComponent } from './components/sankey-app/sankey-data.component'; 
import { SindicatoComponent } from './components/sindicato/sindicato.component';


const routes: Routes = [
  // { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'sankey', component: SankeyAppComponent, canActivate: [AuthGuard] },
    { path: 'editsankey', component: SankeyDataComponent, canActivate: [AuthGuard] },
  { path: 'matriz', component: MatrizComponent, canActivate: [AuthGuard] },
  { path: 'sindicato/:id', component: SindicatoComponent },
    { path: 'login', component: LoginComponent },
    { path: 'signup', component: SignupComponent },
  { path: '**', component: HomeComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{scrollPositionRestoration: 'top'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }