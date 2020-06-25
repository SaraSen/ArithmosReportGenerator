import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms'
import { RegistrationService } from 'src/app/services/registration.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/common/user';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = new User();
  submitted = false;
  form: FormGroup;
  graterThanZero : boolean;
  returnUrl: string;

  constructor(private service : RegistrationService,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,) { 

      if (this.service.isUserLoggedIn) { 
        console.log("workign")
        this.router.navigate(['/']);
      }
    }

    ngOnInit() {
      this.form = this.formBuilder.group({
        username: [],
        password:[]
      });

      this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit() {
    this.router.navigate([this.returnUrl])
    this.submitted = true;
    this.loginUser();
  }
  
  loginUser(){
    this.submitted = true;

    if (this.form.invalid) {
        return;
    }

    this.service.loginUserFormRemote(this.user).pipe(first())
      .subscribe(data => {

       if(this.service.registerSuccessfulLogin(data)){
       this.router.navigate([this.returnUrl]);
       }else{
         window.alert("check your credentials");
       }
        
      });
  }
}
