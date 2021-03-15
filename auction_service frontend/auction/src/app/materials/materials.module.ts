import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button'
import { MatIconModule } from '@angular/material/icon'
import { MatButtonToggleModule } from '@angular/material/button-toggle'
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner'
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatSidenavModule } from '@angular/material/sidenav'
import { MatBadgeModule } from '@angular/material/badge'
import { MatMenuModule } from '@angular/material/menu'
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input'
import { MatSelectModule } from '@angular/material/select'



const MaterialComponents = [
  MatButtonModule, MatIconModule, MatButtonToggleModule, MatProgressSpinnerModule, MatToolbarModule, MatSidenavModule, MatBadgeModule, MatMenuModule, MatFormFieldModule, MatInputModule, MatSelectModule
]

@NgModule({
  declarations: [],
  imports: [MaterialComponents],
  exports: [MaterialComponents],
})
export class MaterialsModule { }
