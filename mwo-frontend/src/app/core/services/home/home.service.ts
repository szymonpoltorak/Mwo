import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Book } from "@core/data/home/Book";
import { environment } from "@environments/environment";

@Injectable({
    providedIn: 'root'
})
export class HomeService {
    constructor(private http: HttpClient) {
    }

    getBooks(): Observable<Book[]> {
        return this.http.get<Book[]>(`${environment.httpBackend}/api/book/getBooks`, {});
    }

    addNewBook(bookName: string): Observable<Book> {
        return this.http.post<Book>(`${environment.httpBackend}/api/book/addNewBook`, {}, {
            params: {
                bookName: bookName
            }
        });
    }

    deleteBook(bookId: number): Observable<Book> {
        return this.http.delete<Book>(`${environment.httpBackend}/api/book/removeBook`, {
            params: {
                bookId: bookId
            }
        });
    }
}
