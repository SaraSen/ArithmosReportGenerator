import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/services/registration.service';
import { ReportService } from 'src/app/services/report.service';

@Component({
  selector: 'app-report-generator',
  templateUrl: './report-generator.component.html',
  styleUrls: ['./report-generator.component.css']
})
export class ReportGeneratorComponent implements OnInit {

  isLoggedIn = false;
  isAdmin = false;

  constructor(private authenticationService: RegistrationService,
    private reportService: ReportService) { }

  ngOnInit(): void {
    this.isLoggedIn=true;
    this.checkRole();
  }

  handleLogout() {
    this.authenticationService.logout();
  }

  checkRole(){
    if(sessionStorage.getItem('authenticatedRole') === 'Admin'){
      this.isAdmin = true;
    }
  }

  downloadReport()
  {
    this.reportService.downloadReport().subscribe(data=>{
  
    })
  }
}
