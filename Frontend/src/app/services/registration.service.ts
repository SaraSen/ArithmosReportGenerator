import { Injectable } from '@angular/core';
import { User } from '../common/user';
import { Observable, BehaviorSubject } from 'rxjs';
import {HttpClient} from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  
  baseUrl ="http://18.237.246.125:8080/employees";
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
  USER_NAME_SESSION_ATTRIBUTE_ROLE = 'authenticatedRole'

  public username: String;
  public password: String;
  public role: String;


  constructor(private httpClient : HttpClient) {}

   authenticate(user: User) {
    if (user) {
      sessionStorage.setItem('username', user.username)
      return true;
    } else {
      return false;
    }
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    this.username = null;
    this.password = null;
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return false
    return true
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ''
    return user
  }

  registerSuccessfulLogin(user: any) {
    if(user!=null){
      sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, user.username)
      sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_ROLE, user.role)
      return true
    }else{
      return false;
    }
  }

  public loginUserFormRemote(user: Object): Observable<Object> {    
    return this.httpClient.post(`${this.baseUrl}/login`, user);
  }

}
