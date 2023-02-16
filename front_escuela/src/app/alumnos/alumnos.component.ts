import { Component, Inject, OnInit, QueryList, ViewChild, ViewChildren, } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { pointService } from '../services/poinst.service';
interface alumno {
  id: string;
  nombre: string;
  ap_paterno: string;
  ap_materno: string;
  activo: number;
}

@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  styleUrls: ['./alumnos.component.css'],
  
})

export class AlumnosComponent implements OnInit {
  dataSource = new MatTableDataSource<alumno>;
  ELEMENT_DATA: alumno[] = [];
  columnsToDisplay = ['nombre', 'paterno', 'materno', 'estatus','acciones'];
  color: ThemePalette = 'primary';
  @ViewChild('paginator') paginator: MatPaginator | undefined;
  constructor(
    private router: Router,
    private spinner: NgxSpinnerService,
    private service: pointService

  ) {}

  ngOnInit() {
    this.getAlumnos();
    setTimeout(() => {
      /** spinner ends after 5 seconds */
      this.spinner.hide();
    }, 1000);

  }

  getAlumnos(){
    this.service.getAlumnos().subscribe(
      res => {
        console.log("res: "+res)
        this.ELEMENT_DATA = res;
        this.dataSource = new MatTableDataSource<alumno>(this.ELEMENT_DATA);
        this.dataSource!.paginator = this.paginator!;
        this.spinner.hide();
      })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
    this.dataSource!.paginator = this.paginator!;
  }

  verDetalle(id:any){
    localStorage.setItem("home","false")
    localStorage.setItem("alumnos","false")
    localStorage.setItem("calificaciones","true");
    localStorage.setItem("idAlumno",id);
    window.location.reload()
  }

}



