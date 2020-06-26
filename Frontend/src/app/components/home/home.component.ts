import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from 'src/app/services/registration.service';
import { MatTableDataSource } from '@angular/material/table';
import { Report } from 'src/app/common/report';
import { ReportService } from 'src/app/services/report.service';
import { FormGroup,FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  isLoggedIn = false;
  ItemsArray= [];
  submitted = false;
  mapped =[];
  tableData =[];
  objectArray=[];
  reportForm: FormGroup;
  displayedColumns: string[] = ['team'];
 //dataSource = ELEMENT_DATA;
  
 //displayedColumns: string[] = ['team', 'assignee', 'jiraID','taskDescription','comments','onCall',
  //'deliveryDate','status','blockers'];
  report = new Report();
  reports: Observable<Report[]>;
  public dataSource = new MatTableDataSource<Report>()

  constructor(private reportService: ReportService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: RegistrationService) { }
    

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
      console.log(res);
      this.dataSource.data = res as Report[];
    })

    
    // this.dataSource.data = this.report as Report[];
    // initColumns = this.report;
    // this.isLoggedIn = this.authenticationService.isUserLoggedIn();
    // this.dataSource.data = this.report  Report[];
    // console.log('menu ->' + this.isLoggedIn);
  }

  // ngAfterViewInit(): void {
  //   this.dataSource.filterPredicate = (data, filter) => {
  //     return this.displayedColumns.some(ele => {
  //       return ele != 'actions' && data[ele].toLowerCase().indexOf(filter) != -1;
  //     });
  //   };
  // }


  handleLogout() {
    this.authenticationService.logout();
  }

  loadData(){

  }
  addTask(){
    
  }
  onSubmit(){
    this.mapped = Object.keys(this.report).map(key => ({type: key, value: this.report[key]}));
    this.ItemsArray.push(this.mapped);
    console.log(this.ItemsArray);
    this.objectArray.push(this.report);
    console.log(this.objectArray);
  }

  submitReport(){
    this.reportService.sendReport(this.objectArray).subscribe(data=>
      console.log(data))
  }
  
}
