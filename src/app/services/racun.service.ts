import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';;
import { Observable } from 'rxjs';
import { RACUN_PO_KLIJENTU_URL, RACUN_URL, RACUN_PO_TIPU_URL } from '../app.constants';
import { Racun } from '../models/racun';
@Injectable({
  providedIn: 'root'
})
export class RacunService {

  constructor(private httpClient: HttpClient) { }

  public getRacunPoKlijentu(id: number): Observable<any> {
    return this.httpClient.get(`${RACUN_PO_KLIJENTU_URL}/${id}`);
  }

  public getRacunPoTipuRacuna(id: number): Observable<any> {
    return this.httpClient.get(`${RACUN_PO_TIPU_URL}/${id}`);
  }

  public getAllRacuns(): Observable<any> {
    return this.httpClient.get(`${RACUN_URL}`)
  }

  public addRacun(racun: Racun): Observable<any> {
    racun.id = 0;
    return this.httpClient.post(`${RACUN_URL}`, racun);
  }

  public updateRacun(racun: Racun): Observable<any> {
    return this.httpClient.put(`${RACUN_URL}`, racun);
  }

  public deleteRacun(id: number): Observable<any> {
    return this.httpClient.delete(`${RACUN_URL}/${id}`);
  }

}
