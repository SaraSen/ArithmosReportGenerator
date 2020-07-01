import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/services/registration.service';
import { ReportService } from 'src/app/services/report.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-report-generator',
  templateUrl: './report-generator.component.html',
  styleUrls: ['./report-generator.component.css']
})
export class ReportGeneratorComponent implements OnInit {

  isLoggedIn = false;
  isAdmin = false;
  reportGenerateForm: FormGroup;
  startDate: Date;
  endDate: Date;

  constructor(private authenticationService: RegistrationService,
    private reportService: ReportService,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.isLoggedIn = true;
    this.checkRole();
    this.reportGenerateForm = this.formBuilder.group({
      startDate: [],
      endDate: []
    });
  }

  handleLogout() {
    this.authenticationService.logout();
  }

  checkRole() {
    if (sessionStorage.getItem('authenticatedRole') === 'Admin') {
      this.isAdmin = true;
    }
  }


  onSubmit() {
    let map = { "startDate": this.startDate, "endDate": this.endDate };
    this.reportService.downloadReport(map).
      subscribe((resp: Blob) => {
        console.log(resp)
        saveAs(resp, `Status_Rreport.xlsx`)
      });
  }
}
