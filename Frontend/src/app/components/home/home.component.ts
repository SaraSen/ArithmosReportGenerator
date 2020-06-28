import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/services/registration.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isAdmin = false;

  constructor(private service: RegistrationService) { }

  ngOnInit(): void {

    this.checkAdmin()
    console.log();
  }

  checkAdmin(){
    if(sessionStorage.getItem('authenticatedUserRole') === 'Admin'){
      this.isAdmin = true;
    }
  }

}
