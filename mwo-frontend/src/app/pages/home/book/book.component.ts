import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Book } from "@core/data/home/Book";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent {
  @Input() book !: Book;
  @Output() protected readonly bookIsRead: EventEmitter<Book> = new EventEmitter<Book>();
}
