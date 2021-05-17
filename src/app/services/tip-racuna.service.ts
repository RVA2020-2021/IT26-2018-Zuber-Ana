import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';;
import { Observable } from 'rxjs';
import { TIPRACUNA_URL } from '../app.constants';
import { TipRacuna } from '../models/tipRacuna';
@Injectable({
  providedIn: 'root'
})
export class TipRacunaService {

  constructor(private httpClient: HttpClient) { }

  public getAllTipRacuns(): Observable<any> {
    return this.httpClient.get(`${TIPRACUNA_URL}`)
  }

  public addTipRacuna(tipRacuna: TipRacuna): Observable<any> {
    tipRacuna.id = 0;
    return this.httpClient.post(`${TIPRACUNA_URL}`, tipRacuna);
  }

  public updateTipRacuna(tipRacuna: TipRacuna): Observable<any> {
    return this.httpClient.put(`${TIPRACUNA_URL}`, tipRacuna);
  }

  public deleteTipRacuna(id: number): Observable<any> {
    return this.httpClient.delete(`${TIPRACUNA_URL}/${id}`);
  }

}
