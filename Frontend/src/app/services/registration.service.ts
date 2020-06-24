import { Injectable } from '@angular/core';
import { User } from '../common/user';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  baseUrl ="";
  constructor(private http:HttpClient) { }

  public loginUserFormRemote(user: User):Observable<any>{
      return this.http.post<any>(this.baseUrl,user);
  }
}
