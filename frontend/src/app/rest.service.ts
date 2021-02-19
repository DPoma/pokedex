import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  constructor(private http: HttpClient ) { }

  public get(url: string) {
    return this.http.get(url);
  }

  public post(url:string, body) {
    return this.http.post(url, body);
  }

  public put(url:string, body) {
    return this.http.put(url, body);
  }

  public login(username:string, password:string){
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.http.get(`/api/login`,{headers,responseType: 'text' as 'json'})
  }
}
