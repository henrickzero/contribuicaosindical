import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AuthGuard } from './guards/auth.guard';
import { AuthService } from './service/auth.service'; 
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HelpModalComponent } from './components/help-modal/help.modal.component';
import { HomeComponent } from './components/home/home.component'; 
import { HeaderComponent } from './components/home/header.component'; 
import { FooterComponent } from './components/home/footer.component'; 
import { LoginComponent } from './components/login/login.component'; 
import { SignupComponent } from './components/signup/signup.component'; 
import { AuthInterceptor } from './guards/auth-interceptor.interceptor'; 
import { SindicatoComponent } from './components/sindicato/sindicato.component'; 


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    HelpModalComponent,
    LoginComponent,
    SignupComponent,
    SindicatoComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [AuthService,     AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }