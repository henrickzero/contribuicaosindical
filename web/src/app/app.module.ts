import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AuthGuard } from './guards/auth.guard';
import { AuthService } from './service/auth.service'; 
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { HelpModalComponent } from './components/help-modal/help.modal.component';
import { TaskFormComponent } from './components/task-form/task.form.component';
import { TaskModalComponent } from './components/task-modal/task.modal.component';
import { QuadrantComponent } from './components/quadrant/quadrant.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { SankeyChartComponent } from './components/sankey/sankey.component'; 
import { SankeyAppComponent } from './components/sankey-app/sankey-app.component'; 
import { MatrizComponent } from './components/matriz/matriz.component'; 
import { HomeComponent } from './components/home/home.component'; 
import { HomeHeaderComponent } from './components/home/header.component'; 
import { FooterComponent } from './components/home/footer.component'; 
import { VisualizationCardComponent } from './components/home/visualization-card.component'; 
import { LoginComponent } from './components/login/login.component'; 
import { SignupComponent } from './components/signup/signup.component'; 
import { SankeyDataComponent } from './components/sankey-app/sankey-data.component'; 
import { AuthInterceptor } from './guards/auth-interceptor.interceptor'; 




@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HelpModalComponent,
    TaskFormComponent,
    TaskModalComponent,
    QuadrantComponent,
    StatisticsComponent,
    SankeyChartComponent,
    SankeyAppComponent,
    MatrizComponent,
    HomeComponent,
    HomeHeaderComponent,
    FooterComponent,
    VisualizationCardComponent,
    LoginComponent,
    SignupComponent
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