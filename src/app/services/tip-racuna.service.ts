import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';;
import { Observable } from 'rxjs';
import { TIPRACUNA_URL } from '../app.constants';
@Injectable({
  providedIn: 'root'
})
export class TipRacunaService {

  constructor(private httpClient: HttpClient) { }

  public getAllTipRacuns(): Observable<any> {
    return this.httpClient.get(`${TIPRACUNA_URL}`)
  }

}
