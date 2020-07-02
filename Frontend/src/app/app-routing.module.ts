import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './common/auth-guard';
import { ReportComponent } from './components/report/report/report.component';
import { HomeComponent } from './components/home/home.component';
import { ReportGeneratorComponent } from './components/report-generator/report-generator.component';


const routes: Routes = [
  { path: '', component: ReportComponent,canActivate:[AuthGuard]},
  { path: 'login', component: LoginComponent },
  {path:'report-generator',component:ReportGeneratorComponent,canActivate:[AuthGuard]},
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
