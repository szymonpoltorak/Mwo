import { Injectable } from '@angular/core';
import { SideMenuActions } from "@core/interfaces/home/SideMenuActions";
import { UtilService } from "@core/services/utils/util.service";
import { RouterPaths } from "@enums/RouterPaths";

@Injectable({
    providedIn: 'root'
})
export class SideMenuService implements SideMenuActions {
    constructor(private utilService: UtilService) {
    }

    logoutUser(): void {
        this.utilService.clearStorage();

        this.utilService.navigate(RouterPaths.LOGIN_DIRECT);
    }
}
