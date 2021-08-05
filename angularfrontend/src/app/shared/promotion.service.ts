import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PromotionService {

  listPromotionsUrl: string = 'http://localhost:8081/api/promotion/listPromotions';

  constructor(private http: HttpClient) { }

  getPromotions(): Observable<any> {
    return this.http.get(`${this.listPromotionsUrl}`);
  }
}
