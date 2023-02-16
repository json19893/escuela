import { Component, Inject, OnInit, QueryList, ViewChild, ViewChildren, } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { pointService } from '../services/poinst.service';
import * as XLSX from 'xlsx';
import * as FileSaver from 'file-saver';
const EXCEL_EXTENSION = '.xlsx';
const EXCEL_TYPE = 'application/vnd.openxmlformats- officedocument.spreadsheetml.sheet;charset=UTF-8';
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

  verDetalle(id:any,nombre:any,apterno:any,materno:any){
    localStorage.setItem("home","false")
    localStorage.setItem("alumnos","false")
    localStorage.setItem("calificaciones","true");
    localStorage.setItem("idAlumno",id);
    localStorage.setItem("nombreC",nombre+" "+apterno+" "+ materno);
    window.location.reload()
  }

  ExportTOExcel()
  {
    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(this.ELEMENT_DATA);
    const workbook: XLSX.WorkBook = { Sheets: { 'homologacion': worksheet }, 
   SheetNames: ['homologacion'] };
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' 
  });
    this.saveAsExcelFile(excelBuffer, "Relacion de alumnos");
    
  }
  saveAsExcelFile(buffer: any, fileName: string) {
    const data: Blob = new Blob([buffer], { type: EXCEL_TYPE });
    FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + 
  EXCEL_EXTENSION);
  }

}



