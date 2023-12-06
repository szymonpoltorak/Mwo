import { Component, OnInit } from '@angular/core';
import { SideMenuActions } from "@core/interfaces/home/SideMenuActions";
import { SideMenuService } from "@core/services/home/side-menu.service";
import { Book } from "@core/data/home/Book";
import { FormControl, Validators } from "@angular/forms";
import { UtilService } from "@core/services/utils/util.service";
import { StorageKeys } from "@enums/auth/StorageKeys";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements SideMenuActions, OnInit {
    protected headerName: string = "";
    protected books: Book[] = [{ name: '"The Lords Of The Rings", J.R.R Tolkien', bookId: 0 }];
    protected readonly bookFormControl: FormControl = new FormControl<string>("", [
        Validators.required
    ]);

    constructor(private sideMenuService: SideMenuService,
                private utilService: UtilService) {
    }

    changeToCreateNoteView(): void {
    }

    changeToHomeView(): void {
    }

    changeToProfileView(): void {
    }

    logoutUser(): void {
        this.sideMenuService.logoutUser();
    }

    addNewBook(): void {
        if (this.bookFormControl.invalid) {
            return;
        }
        const bookName: string = this.bookFormControl.value;

        this.books.push({
            bookId: this.books.length,
            name: bookName
        });
    }

    ngOnInit(): void {
        this.headerName = this.utilService.getKeyValuePairFromStorage(StorageKeys.USERNAME);
    }

    removeBookFromList(event: Book): void {
        console.log(event);

        this.books = this.books.filter(book => book != event);
    }
}
