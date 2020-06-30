import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from 'src/app/services/registration.service';
import { Report } from 'src/app/common/report';
import { ReportService } from 'src/app/services/report.service';
import { FormGroup,FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { NotificationServiceService } from 'src/app/services/notification-service.service';

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
  report = new Report();
  objectArray = [];
  reportForm: FormGroup;
  userName: string;

  constructor(private reportService: ReportService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private authenticationService: RegistrationService,
    private notificationService: NotificationServiceService) { 
      
    }
    

  ngOnInit() {
    this.isLoggedIn = true;
    this.reportForm = this.formBuilder.group({
      team: ['', Validators.required],
      assignee:['', Validators.required],
      jiraID:['', Validators.required],
      taskDescription:['', Validators.required],
      comments:['', Validators.required],
      onCall:['', Validators.required],
      deliveryDate:['', Validators.required],
      status:['', Validators.required],
      blockers:['', Validators.required]
    });
    this.checkRole()
    this.userName = sessionStorage.getItem("authenticatedUser");
  }

  handleLogout() {
    this.authenticationService.logout();
  }

  onSubmit(){

    this.objectArray.push(this.report); 
    this.mapped = Object.keys(this.report).map(key => ({type: key, value: this.report[key]}));
    this.itemsArray.push(this.mapped);
    this.hasValues = true;
    this.report = new Report();
  }

  submitReport(){
    if(this.report.comment==null||this.report.comment==''){
      
      this.report.comment="-";
    }
    this.reportService.sendReport(this.objectArray).subscribe(data=>
      this.notificationService.success('Submission Complete'));
      this.itemsArray.splice(0);
      this.objectArray.splice(0);
      this.reportForm.reset();
      this.hasValues = false;
  }

  checkRole(){
    if(sessionStorage.getItem('authenticatedRole') === 'Admin'){
      this.isAdmin = true;
    }
  }

  onDelete(data: number){
    this.itemsArray.splice(data,1);
    this.objectArray.splice(data,1)
  }
}
