import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  constructor(private http: HttpClient) { }

  public get(url: string, options?) {
    return this.http.get(url, options);
  }

  public post(url: string, body) {
    return this.http.post(url, body);
  }

  public put(url: string, body) {
    return this.http.put(url, body);
  }
}
