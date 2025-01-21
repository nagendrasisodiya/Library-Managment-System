import { Component } from '@angular/core';
import {DatePipe, NgForOf} from '@angular/common';
import {Book} from '../../model/book';
import {Subscription} from 'rxjs';
import {UserService} from '../../Service/user.service';

class UserServiceq {
}

@Component({
  selector: 'app-dash-board',
  imports: [
    NgForOf
  ],
  templateUrl: './dash-board.component.html',
  styleUrl: './dash-board.component.css'
})
export class DashBoardComponent {
    books:Book[]=[];
    sub?:Subscription;
  constructor(private htttpUserService:UserService) {
    }
    ngOnInit() {
    this.getAssignedBooks()
    }
    getAssignedBooks():any{
      this.sub=this.htttpUserService.getAssignedBook$().subscribe(
        (response:any) =>{ this.books = response},
        (error:any) =>{ console.log(error)}
      )
    }

}
