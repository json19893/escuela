import { Component, Inject, OnInit, QueryList, ViewChild, ViewChildren, } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { pointService } from '../services/poinst.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {calificacionMateria} from '../model/calificacionMateria'
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import * as XLSX from 'xlsx';
import * as FileSaver from 'file-saver';
const EXCEL_EXTENSION = '.xlsx';
const EXCEL_TYPE = 'application/vnd.openxmlformats- officedocument.spreadsheetml.sheet;charset=UTF-8';
interface calificacion {
  idUsuario: string;
  idCalificacion:string;
  nombre: string;
  apellido: string;
  materia:string;
  calificacion: number;
  fechaRegistro: string;
}

@Component({
  selector: 'app-calificaciones',
  templateUrl: './calificaciones.component.html',
  styleUrls: ['./calificaciones.component.css'],
  
})

export class CalificacionesComponent implements OnInit {
  public calificacionAlum:calificacionMateria | undefined;
  dataSource = new MatTableDataSource<calificacion>;
  ELEMENT_DATA: calificacion[] = [];
  columnsToDisplay = ['materia', 'calificacion', 'fecha','acciones'];
  color: ThemePalette = 'primary';
  public idAlumno:any;
  public promedio:any;
  public nombre:any;
  public materias:any;
  public val='';
  seleccionada: string = ""
  formRegister: FormGroup | any;
  formUpdate: FormGroup | any;
  @ViewChild('paginator') paginator: MatPaginator | undefined;

  constructor(
    private router: Router,
    private spinner: NgxSpinnerService,
    private service: pointService,
    private _snackBar: MatSnackBar,
    public dialog: MatDialog
   

  ) {
    const regexPattern  = /\-?\d*\.?\d{1,2}/;
    this.formRegister = new FormGroup({
      mate: new FormControl('', [Validators.required]),
      califi: new FormControl('', [Validators.required,Validators.min(2),Validators.pattern(regexPattern)])
    });

    this.formUpdate = new FormGroup({
      califi: new FormControl('', [Validators.required,Validators.min(2),Validators.pattern(regexPattern)])
    });
  }

  ngOnInit() {
    this.idAlumno= localStorage.getItem("idAlumno")
    this.nombre= localStorage.getItem("nombreC")
    this.getCalificaciones(this.idAlumno);
    this.getMaterias()
    setTimeout(() => {
      /** spinner ends after 5 seconds */
      this.spinner.hide();
    }, 1000);

  }

  getCalificaciones(idAlumno:any){
    this.service.getCalificaciones(idAlumno).subscribe(
      res => {
        console.log("res: "+res)
        this.promedio=res.promedio==undefined?0.0:res.promedio==null?0.0:res.promedio=="NaN"?0.0:res.promedio
        this.ELEMENT_DATA = res.calificaciones;
        this.dataSource = new MatTableDataSource<calificacion>(this.ELEMENT_DATA);
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

  delete(id:any){
    this.service.dropCalificaciones(id).subscribe(
      res => {
        this._snackBar.open(res.msg, "cerrar",{
          duration: 3000
        });
        this.getCalificaciones(this.idAlumno); 
      })


  }
  update(id:any){
 localStorage.setItem("idMateria",id)
const dialogRef = this.dialog.open(DialogContentExampleDialog);

dialogRef.afterClosed().subscribe(result => {
  console.log(`Dialog result: ${result}`);
});
}
 
  

  getMaterias(){
    this.service.getMaterias().subscribe(
      res => {
        this.materias=res
        
      })
  }
  agregar(){
    if(!this.formRegister.valid){
      return;
    }
    this.calificacionAlum=new calificacionMateria(this.formRegister.value.mate, this.idAlumno,this.formRegister.value.califi,null);
    this.service.putCalificacion( this.calificacionAlum).subscribe(
      res => {
        this._snackBar.open(res.msg, "cerrar",{
          duration: 3000
        });
        this.getCalificaciones(this.idAlumno); 
        this.formRegister.reset()
      })
      
  }
  ExportTOExcel()
  {
    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(this.ELEMENT_DATA);
    const workbook: XLSX.WorkBook = { Sheets: { 'homologacion': worksheet }, 
   SheetNames: ['homologacion'] };
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' 
  });
    this.saveAsExcelFile(excelBuffer, "Relacion de materias");
    
  }
  saveAsExcelFile(buffer: any, fileName: string) {
    const data: Blob = new Blob([buffer], { type: EXCEL_TYPE });
    FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + 
  EXCEL_EXTENSION);
  }


}




@Component({
  selector: 'dialog-content-example-dialog',
  templateUrl: 'dialog-cotent-example-dialog.html',
})
export class DialogContentExampleDialog  implements OnInit {
  formRegister: FormGroup | any;
  public calificacionAlum:calificacionMateria | undefined;
  public idAlumno:any;
  public idMateria:any;
  constructor(
    private router: Router,
    private spinner: NgxSpinnerService,
    private service: pointService,
    private _snackBar: MatSnackBar,
    public dialog: MatDialog
   

  ) {
    const regexPattern  = /\-?\d*\.?\d{1,2}/;
    this.formRegister = new FormGroup({
      califi: new FormControl('', [Validators.required,Validators.min(2),Validators.pattern(regexPattern)])
    });

  
  }

  ngOnInit() {
    this.idAlumno= localStorage.getItem("idAlumno")
  this.idMateria=  localStorage.getItem("idMateria")

  }
  agregar(){
    if(!this.formRegister.valid){
      return;
    }
    this.calificacionAlum=new calificacionMateria(null, this.idAlumno,this.formRegister.value.califi,this.idMateria);
    this.service.putCalificacion( this.calificacionAlum).subscribe(
      res => {
        this._snackBar.open(res.msg, "cerrar",{
          duration: 3000
        });
        setInterval(() =>   window.location.reload(), 1000);
      
      })
      
  }

}