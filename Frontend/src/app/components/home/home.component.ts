import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/services/registration.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isAdmin = false;
  isLoggedIn = false;

  constructor(private service: RegistrationService) { }

  ngOnInit(): void {
    this.isLoggedIn = true;
    this.checkAdmin()
    console.log(sessionStorage.getItem("authenticatedRole"));
  }

  handleLogout() {
    this.service.logout();
  }

  checkAdmin(){
    if(sessionStorage.getItem('authenticatedRole') === 'Admin'){
      this.isAdmin = true;
    }
  }

}
