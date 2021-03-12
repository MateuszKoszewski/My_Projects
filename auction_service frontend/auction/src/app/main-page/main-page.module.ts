import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainPageComponent } from './main-page.component';
import { FormsModule } from '@angular/forms';
import { MainPageService } from './main-page.service';
import { HttpClientModule } from '@angular/common/http';
import { MaterialsModule } from '../materials/materials.module';

@NgModule({
  declarations: [MainPageComponent],
  imports: [
    CommonModule, FormsModule, HttpClientModule, MaterialsModule
  ],
  exports: [],
  providers: [MainPageService]
})
export class MainPageModule { }
