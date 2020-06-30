import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from 'src/app/services/registration.service';
import { Report } from 'src/app/common/report';
import { ReportService } from 'src/app/services/report.service';
import { FormGroup,FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  isLoggedIn = false;
  isAdmin = false;
  itemsArray= [];
  submitted = false;
  hasValues = false;
  mapped =[];
  tableData =[];
  objectArray=[];
  reportForm: FormGroup;
  report = new Report();
  reports: Observable<Report[]>;
  i:Number;

  constructor(private reportService: ReportService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: RegistrationService) { 
      
    }
    

  ngOnInit() {
    this.isLoggedIn = true;
    this.reportForm = this.formBuilder.group({
      team: ['', Validators.required],
      assignee:['', Validators.required],
      jiraID:['', Validators.required],
      taskDescription:['', Validators.required],
      comments:[],
      onCall:['', Validators.required],
      deliveryDate:['', Validators.required],
      status:['', Validators.required],
      blockers:[]
    });
    this.checkRole()
  }

  handleLogout() {
    this.authenticationService.logout();
  }

  resetForm(){
    console.log("ho")
    
  }

  onSubmit(){
   
    this.mapped = Object.keys(this.report).map(key => ({type: key, value: this.report[key]}));
    this.itemsArray.push(this.mapped);
    this.objectArray.push(this.report);
    this.hasValues = true;
  }

  submitReport(){
    this.reportService.sendReport(this.objectArray).subscribe(data=>
      alert("Submission Successful"));
      this.itemsArray.splice(0);
      for(let i=0; i<=this.itemsArray.length; i++){
        this.itemsArray.pop();
        this.mapped.pop();
        console.log(this.itemsArray)
      }
      this.reportForm.reset();
      this.hasValues = false;
  }

  checkRole(){
    if(sessionStorage.getItem('authenticatedRole') === 'Admin'){
      this.isAdmin = true;
    }
  }
}
