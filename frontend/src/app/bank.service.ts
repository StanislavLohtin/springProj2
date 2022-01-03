import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ListOfBanks} from './listOfBanks';
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BankService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getBanks(): Observable<ListOfBanks> {
    return this.http.get<ListOfBanks>(`${this.apiServerUrl}/banks`);
  }
}
