import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Book} from '../model/book';
import {Observable} from 'rxjs';
import {NewUser} from '../model/new-user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  getAssignedBook$(): Observable<Book[]> {
    return this.http.get<Book[]>(`http://localhost:8080/user/assignedBooks`);
  }
  regNewUser$(newUser:NewUser): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/register/newUser`, newUser);
  }


}
