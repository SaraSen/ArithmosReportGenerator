import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from 'src/app/services/registration.service';
import { MatTableDataSource } from '@angular/material/table';
import { Report } from 'src/app/common/report';
import { ReportService } from 'src/app/services/report.service';
import { FormGroup,FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
];

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  displayedColumns: string[] = ['team', 'assignee', 'jiraID', 'taskDescription','onCall','deliveryDate','status','blockers'];
  // dataSource = ELEMENT_DATA;
  isLoggedIn = false;
  ItemsArray= [];
  submitted = false;
  mapped =[];
  tableData =[];
  objectArray=[];
  reportForm: FormGroup;
  public dat
//  dataSource;
  
 //displayedColumns: string[] = ['team', 'assignee', 'jiraID','taskDescription','comments','onCall',
  //'deliveryDate','status','blockers'];
  report = new Report();
  reports: Observable<Report[]>;
  public dataSource;

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
      // this.dataSource = res as Report[];
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
    console.log(ELEMENT_DATA)
    this.objectArray.push(this.report);
    console.log(this.objectArray);
    this.dataSource = this.ItemsArray[0];
    
    console.log("ssssss"+this.dataSource);
  }

  submitReport(){
    this.reportService.sendReport(this.objectArray).subscribe(data=>
      console.log(data))
  }
  
}
