import { Injectable } from '@angular/core';
import { Report } from '../common/report';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  baseUrl ="http://localhost:8080/employees";

  constructor(private httpClient : HttpClient) { }

  // getReport(): Observable<any[]>{
  //   return this.httpClient.get<any[]>(`${this.baseUrl}/getReport`);
  // }

  downloadReport(){
    return this.httpClient.get('http://localhost:8080/download/tasks');
  }

  sendReport(reports: Object[]): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/pushreport`, reports);
  }
}
