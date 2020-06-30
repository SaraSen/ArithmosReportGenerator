import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms'
import { RegistrationService } from 'src/app/services/registration.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/common/user';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { NotificationServiceService } from 'src/app/services/notification-service.service';

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
  userRole: string;

  constructor(private service : RegistrationService,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private notificationService: NotificationServiceService) { 

      if (this.service.isUserLoggedIn) { 
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
      .subscribe(res => {
        
        if(this.service.registerSuccessfulLogin(res)){
        this.getUserRole(res)
        this.router.navigate([this.returnUrl]);
        }else{
         this.notificationService.warn('Please check the credentials and try agian')
        }
      });
  }

  getUserRole(user: any){
    return this.userRole=user.role;
  }
}
