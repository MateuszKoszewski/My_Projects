import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  categoryName: String;
  description: String;
  showCategoryAddingMenu: boolean = false;

  getCategoryAddingMenu() {
    console.log(this.showCategoryAddingMenu)
    this.showCategoryAddingMenu = !this.showCategoryAddingMenu;
  }

}
