import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Credencials } from 'src/app/models/credencials';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credencial: Credencials = {
    email: '',
    password: ''
  }

  email = new FormControl(null, Validators.email);
  password = new FormControl(null, Validators.minLength(3));

  constructor(private toast: ToastrService) { }

  ngOnInit(): void {
  }

  login() {
    this.toast.error('Usuário e/ou senha inválidos!', 'Login');
    this.credencial.password = '';
  }

  validateFields(): boolean {
    if (this.email.valid && this.password.valid) {
      return true;
    }
    return false;
  }

}
