import { Component, OnInit } from '@angular/core';
import { SideMenuActions } from "@core/interfaces/home/SideMenuActions";
import { SideMenuService } from "@core/services/home/side-menu.service";
import { Book } from "@core/data/home/Book";
import { FormControl, Validators } from "@angular/forms";
import { UtilService } from "@core/services/utils/util.service";
import { StorageKeys } from "@enums/auth/StorageKeys";
import { HomeService } from "@core/services/home/home.service";
import { take } from "rxjs";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements SideMenuActions, OnInit {
    protected headerName: string = "";
    protected books: Book[] = [];
    protected readonly bookFormControl: FormControl = new FormControl<string>("", [
        Validators.required
    ]);

    constructor(private sideMenuService: SideMenuService,
                private homeService: HomeService,
                private utilService: UtilService) {
    }

    logoutUser(): void {
        this.sideMenuService.logoutUser();
    }

    addNewBook(): void {
        if (this.bookFormControl.invalid) {
            return;
        }
        const bookName: string = this.bookFormControl.value;

        console.log(bookName);

        this.homeService
            .addNewBook(bookName)
            .pipe(take(1))
            .subscribe(book => this.books.push(book));
    }

    ngOnInit(): void {
        this.headerName = this.utilService.getKeyValuePairFromStorage(StorageKeys.USERNAME).split(" ")[1];
        this.homeService
            .getBooks()
            .pipe(take(1))
            .subscribe(books => this.books = books);
    }

    removeBookFromList(event: Book): void {
        this.books = this.books.filter(book => book != event);
        this.homeService
            .deleteBook(event.bookId)
            .pipe(take(1))
            .subscribe();
    }
}
