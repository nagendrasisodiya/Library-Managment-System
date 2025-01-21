import { Component } from '@angular/core';
import {NgForOf} from "@angular/common";
import {User} from '../../model/user';
import {Book} from '../../model/book';
import {AdminService} from '../../Service/admin.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-assigned-books',
    imports: [
        NgForOf
    ],
  templateUrl: './assigned-books.component.html',
  styleUrl: './assigned-books.component.css'
})
export class AssignedBooksComponent {
  users: User[]=[];
  constructor(private httpAdminService: AdminService) {}
  sub?:Subscription;
  ngOnInit():void {
    this.getAssigendBooks()
  }
  getAssigendBooks(){
    this.sub=this.httpAdminService.getAllUser$().subscribe(
      (response:any) => {this.users = response;},
    (error:any) => {console.log(error);}
  )
  return this.users;
  }
  ngOnDestroy() {
    this.sub?.unsubscribe();
  }
}
