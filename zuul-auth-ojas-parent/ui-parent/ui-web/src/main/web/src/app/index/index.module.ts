import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ForgotComponent } from './forgot/forgot.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ShowHidePasswordModule } from 'ngx-show-hide-password';
@NgModule({
  declarations: [LoginComponent, SignUpComponent, ForgotComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    ShowHidePasswordModule
  ]
})
export class IndexModule { }
