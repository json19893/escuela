import { Component, Inject, OnInit, QueryList, ViewChild, ViewChildren, } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';



@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']

})

export class MenuComponent implements OnInit {
  public home:any;
  public alumnos:any;
  public calificaciones:any;
  color: ThemePalette = 'warn';
  public checked: any;
  disabled = true;
  panelJobs = false;
  isExpanded = false;
  showSubmenu: boolean = false;
  showSubmenu2: boolean = false;
  isShowing = false;
  constructor(
    private router: Router,
    private spinner: NgxSpinnerService,

  ) { }
  ngOnInit() {
    this.home= localStorage.getItem("home");
    this.alumnos= localStorage.getItem("alumnos");
    this.calificaciones= localStorage.getItem("calificaciones");
 
  }
  mouseenter() {
    if (!this.isExpanded) {
      //this.isShowing = true;
    }
  }

  
mouseleave() {
    if (!this.isExpanded) {
      this.isShowing = false;
    }
  }
  homeFuncion(){
    localStorage.setItem("home","true")
    localStorage.setItem("alumnos","false")
    localStorage.setItem("calificaciones","false");
    window.location.reload()
  }
  alumnosFuncion(){
    localStorage.setItem("home","false")
    localStorage.setItem("alumnos","true")
    localStorage.setItem("calificaciones","false");
    window.location.reload()
  }
 
}




