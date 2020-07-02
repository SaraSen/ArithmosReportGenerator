import { Injectable } from '@angular/core';
import { Report } from '../common/report';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ReportService {

  baseUrl = "http://18.237.246.125:8080/report";

  constructor(private httpClient: HttpClient) { }

  // getReport(): Observable<any[]>{
  //   return this.httpClient.get<any[]>(`${this.baseUrl}/getReport`);
  // }

  downloadReport(map: any): Observable<Object> {
    console.log(map)
    return this.httpClient.post('http://18.237.246.125:8080/download/tasks', map, { responseType: 'blob' });
  }

  sendReport(reports: Object[]): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/pushreport`, reports);
  }

  getReport(map:any): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}/viewReport`,map);
  }

  getReportByTeam(map:any): Observable<any>{
    return this.httpClient.post(`${this.baseUrl}/viewReportByTeam`,map);
  }
}