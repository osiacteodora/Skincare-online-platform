import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { User } from '../models/register-user.model';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {
  @Input() isSignup = true;
  @Output() isSignupChange = new EventEmitter<boolean>();
  private isLoginBtnClicked = false;
  private nume = '';
  private prenume = '';
  private buget = 0;
  private email = '';
  private password = '';
  private type = '';

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  onLoginClick(): void {
    this.isLoginBtnClicked = true;
    this.isSignup = false;
    this.isSignupChange.emit(this.isSignup);
  }

  isLoginClicked(): boolean {
    return this.isLoginBtnClicked;
  }

  register(): void {
    this.getDataFromInputs();

    const user: User = {
      nume: this.nume,
      prenume: this.prenume,
      budget: this.buget,
      email: this.email,
      type: this.type,
      password: this.password
    }

    this.userService.registerUser(user).subscribe((response) => {
      if (response) {
        alert('User was registered!');
        this.onLoginClick();
      } else {
        alert('Something went wrong, please try again :(');
      }
    })
  }

  getDataFromInputs(): void {
    const numeInput = document.getElementById('inputNume') as HTMLInputElement;
    const prenumeInput = document.getElementById('inputPrenume') as HTMLInputElement;
    const bugetInput = document.getElementById('inputBuget') as HTMLInputElement;
    const emailInput = document.getElementById('inputEmail') as HTMLInputElement;
    const passwordInput = document.getElementById('inputPassword') as HTMLInputElement;
    const typeInput = document.getElementById('inputType') as HTMLSelectElement;

    this.nume = numeInput.value;
    this.prenume = prenumeInput.value;
    this.buget = +bugetInput.value;
    this.email = emailInput.value;
    this.password = passwordInput.value;
    this.type = typeInput.value;
  }
}
