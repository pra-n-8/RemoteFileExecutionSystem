import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServerDetailsService {
  private server:string = "";
  constructor() { }

  getServer():string{
    return this.server;
  }

  setServer(server:string){
    this.server = server;
  }
}
