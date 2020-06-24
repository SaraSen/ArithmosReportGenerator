import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms'
import { RegistrationService } from 'src/app/services/registration.service';
import { User } from 'src/app/common/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = new User();
  constructor(private service : RegistrationService) { }

  ngOnInit(): void {
  }

  loginUser(){
    this.service.loginUserFormRemote(this.user).subscribe(
      data => console.log("response received"),
      error => console.log("error occured")
    );
  }
}
