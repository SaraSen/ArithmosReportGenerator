import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/services/registration.service';
import { ReportService } from 'src/app/services/report.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { saveAs } from 'file-saver';
import { MatTableDataSource } from '@angular/material/table';
import { Report } from 'src/app/common/report';
import { Observable } from 'rxjs';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-report-generator',
  templateUrl: './report-generator.component.html',
  styleUrls: ['./report-generator.component.css']
})
export class ReportGeneratorComponent implements OnInit {

  isLoggedIn = false;
  isAdmin = false;
  showTable=false;
  reportGenerateForm: FormGroup;
  startDate: Date;
  endDate: Date;
  reports: Observable<Report[]>;
  displayedColumns: string[] = ['team','assignee','jiraID','taskDesc','comment','onCall','deliveryDate','status','blockers'];
  public tempDataSet = new MatTableDataSource<Report>()
  public tableDataSource = new MatTableDataSource<Report>()
  public dataSource = new MatTableDataSource<Report>()

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

  viewReport(){
    this.showTable = true;
    let map = { "startDate": this.startDate, "endDate": this.endDate };
    this.reportService.getReport(map).subscribe(data=>{
      this.tableDataSource.data = data as Report[];
    })
   
  }

  showInfrastructure(){
    this.showTable = true;
    let map = { "startDate": this.startDate.toDateString(), "endDate": this.endDate.toDateString(),"team":"Infrastructure" };
    this.reportService.getReportByTeam(map).subscribe(data=>{
      this.tableDataSource.data = data as Report[];
    })
  }

  showDevQa(){
    this.showTable = true;
    let map = { "startDate": this.startDate.toDateString(), "endDate": this.endDate.toDateString(),"team":"DEVQA" };
    this.reportService.getReportByTeam(map).subscribe(data=>{
      this.tableDataSource.data = data as Report[];
    })
  }

  showSvoc(){
    this.showTable = true;
    let map = { "startDate": this.startDate.toDateString(), "endDate": this.endDate.toDateString(),"team":"SVOC" };
    this.reportService.getReportByTeam(map).subscribe(data=>{
      this.tableDataSource.data = data as Report[];
    })
  }
}
