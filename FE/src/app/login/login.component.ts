import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../models/login.model';
import { User } from '../models/register-user.model';
import { UserService } from '../services/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public isSignup = false;
  private email = '';
  private password = '';
  public isLoginPageVisible = true;

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  onSignupClick(): void {
    this.isSignup = true;
  }

  isSignUp(): boolean {
    return this.isSignup;
  }

  login(): void {
    this.getDataFromInputs();

    const customer: Customer = {
      email: this.email,
      password: this.password
    }

    this.userService.LoginUser(customer).subscribe((data: Customer) => {
      if (data?.type == 'client') {
        this.isLoginPageVisible = false;
        this.userService.user_id.next(data.id_user as number);
        this.router.navigate(['/', 'client-homepage']);
      } else if (data?.type == 'admin') {
        this.isLoginPageVisible = false;
        this.router.navigate(['/', 'admin-homepage'])
      } else {
        alert('Invalid credentials!!!');
      }
    })
  }

  getDataFromInputs(): void {
    const emailInput = document.getElementById('inputEmail') as HTMLInputElement;
    const passwordInput = document.getElementById('inputPassword') as HTMLInputElement;

    this.email = emailInput.value;
    this.password = passwordInput.value;
  }
}
