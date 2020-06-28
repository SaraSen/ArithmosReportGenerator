import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from 'src/app/services/registration.service';
import { Report } from 'src/app/common/report';
import { ReportService } from 'src/app/services/report.service';
import { FormGroup,FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  isLoggedIn = false;
  itemsArray= [];
  submitted = false;
  mapped =[];
  tableData =[];
  objectArray=[];
  reportForm: FormGroup;
  report = new Report();
  reports: Observable<Report[]>;

  constructor(private reportService: ReportService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: RegistrationService) { 
      
    }
    

  ngOnInit() {
    this.reportForm = this.formBuilder.group({
      team: [],
      assignee:[],
      jiraID:[],
      taskDescription:[],
      comments:[],
      onCall:[],
      deliveryDate:[],
      status:[],
      blockers:[]
    });
    this.reportService.getReport().subscribe(res=>{
    })
  }

  handleLogout() {
    this.authenticationService.logout();
  }

  onSubmit(){
    this.mapped = Object.keys(this.report).map(key => ({type: key, value: this.report[key]}));
    this.itemsArray.push(this.mapped);
    this.objectArray.push(this.report);
  }

  submitReport(){
    this.reportService.sendReport(this.objectArray).subscribe(data=>
      console.log(data))
  }

}
