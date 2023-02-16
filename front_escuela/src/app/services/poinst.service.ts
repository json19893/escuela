import { BehaviorSubject } from 'rxjs';
import { Http, Response, Headers } from '@angular/http';
import { HttpClientModule, HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Component, OnInit, OnDestroy, Injectable } from '@angular/core';
import { AppUrlSettings } from './AppUrlSettings';
import { DataSource } from '@angular/cdk/collections';
import { AnyCatcher } from 'rxjs/internal/AnyCatcher';
import { RelativeStrengthIndex } from '@amcharts/amcharts5/.internal/charts/stock/indicators/RelativeStrengthIndex';
@Injectable({
  providedIn: 'root'
})
export class pointService {
  public to: any;
  constructor(private http: HttpClient) { }
  getAlumnos(): Observable<any> {
    /*var headers = new HttpHeaders({
      'Authorization': token,
      'mode':'no-cors'});*/
    return this.http.get<any>(AppUrlSettings.BASE_API + AppUrlSettings.GET_ALUMNOS);
  }
  getCalificaciones(idAlumno:any): Observable<any> {
    /*var headers = new HttpHeaders({
      'Authorization': token,
      'mode':'no-cors'});*/
    return this.http.get<any>(AppUrlSettings.BASE_API + AppUrlSettings.GET_CALIFICACIONES+"?idAlumno="+idAlumno);
  }
  dropCalificaciones(idCalificacion:any): Observable<any> {
    /*var headers = new HttpHeaders({
      'Authorization': token,
      'mode':'no-cors'});*/
    return this.http.get<any>(AppUrlSettings.BASE_API + AppUrlSettings.DROP_CALIFICACIONES+"?idCalificacion="+idCalificacion);
  }
  getMaterias(): Observable<any> {
    /*var headers = new HttpHeaders({
      'Authorization': token,
      'mode':'no-cors'});*/
    return this.http.get<any>(AppUrlSettings.BASE_API + AppUrlSettings.GET_MATERIAS);
  }

  putCalificacion(calificacion:any): Observable<any> {
    /*var headers = new HttpHeaders({
      'Authorization': token,
      'mode':'no-cors'});*/
    return this.http.post<any>(AppUrlSettings.BASE_API + AppUrlSettings.PUT_CALIFICACION,calificacion);
  }
}
