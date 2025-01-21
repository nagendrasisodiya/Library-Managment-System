import {ChangeDetectorRef, Component} from '@angular/core';
import {Book} from '../../model/book';
import {retry, Subscription} from 'rxjs';
import {NgForOf} from '@angular/common';
import {CommonService} from '../../Service/common.service';

@Component({
  selector: 'app-books',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './books.component.html',
  styleUrl: './books.component.css'
})
export class BooksComponent {
  books:Array<Book>=[];
  sub1?:Subscription;
  constructor(private httpCommonService:CommonService, private cdr: ChangeDetectorRef) {
    this.getBooks();
  }
ngOnInit() {
    this.cdr.detectChanges();
}

  getBooks():any{
    this.sub1=this.httpCommonService.getBooks$().subscribe(
      (response:any) =>this.books = response,
      (error:any) => {console.log(error)}
    )
    return this.books;
  }
  ngOnDestroy() {
    this.sub1?.unsubscribe();
  }
}
