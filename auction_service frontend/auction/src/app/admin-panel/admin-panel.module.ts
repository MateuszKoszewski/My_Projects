import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminPanelComponent } from './admin-panel.component';
import { AdminPanelService } from './admin-panel.service';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [AdminPanelComponent],
  imports: [
    CommonModule, FormsModule, HttpClientModule
  ],
  providers: [AdminPanelService]
})
export class AdminPanelModule { }
