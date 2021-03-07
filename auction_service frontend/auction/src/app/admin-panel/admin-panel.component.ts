import { Component, OnInit } from '@angular/core';
import { CategoryEntity } from '../CategoryEntity';
import { AdminPanelService } from './admin-panel.service';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  constructor(private service: AdminPanelService) { }

  ngOnInit(): void {
  }
  categoryName: String;
  description: String;
  showCategoryAddingMenu: boolean = false;
  category: CategoryEntity;

  getCategoryAddingMenu() {
    console.log(this.showCategoryAddingMenu)
    this.showCategoryAddingMenu = !this.showCategoryAddingMenu;
  }
  addCategory() {
    this.category = new CategoryEntity(this.categoryName, this.description);
    this.service.postCategory(this.category).subscribe(data => console.log(data))
  }

}
