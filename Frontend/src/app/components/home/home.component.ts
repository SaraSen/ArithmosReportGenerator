import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from 'src/app/services/registration.service';
import { MatTableDataSource } from '@angular/material/table';
import { Report } from 'src/app/common/report';
import { ReportService } from 'src/app/services/report.service';
import { FormGroup,FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isLoggedIn = false;
  form: FormGroup;
  public dataSource = new MatTableDataSource<Report>();
  displayedColumns: string[] = ['name','grade','gender','phone','actions'];

  constructor(private reportService: ReportService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: RegistrationService) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: [],
      password:[]
    });
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();
    // console.log('menu ->' + this.isLoggedIn);
  }

  ngAfterViewInit(): void {
    this.dataSource.filterPredicate = (data, filter) => {
      return this.displayedColumns.some(ele => {
        return ele != 'actions' && data[ele].toLowerCase().indexOf(filter) != -1;
      });
    };
  }


  handleLogout() {
    this.authenticationService.logout();
  }
}
