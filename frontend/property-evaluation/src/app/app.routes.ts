import { Routes } from '@angular/router';
import { EvaluationComponent } from './evaluation/evaluation.component';
import { ValidationRequestsComponent } from './validation-requests/validation-requests.component';
import { LoginComponent } from './login/login.component';
import { authGuard } from './auth.guard';

export const routes: Routes = [
    {path:'home',loadComponent:()=>EvaluationComponent, canActivate:[authGuard]},
    {path:'valuation', loadComponent:()=> ValidationRequestsComponent, canActivate:[authGuard]},
    { path: 'login', component: LoginComponent },
    { path: '', redirectTo: 'home', pathMatch: 'full' }
];
