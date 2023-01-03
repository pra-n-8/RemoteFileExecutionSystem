import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServerDetailsService } from '../server-details.service';

@Component({
  selector: 'app-server-list-component',
  templateUrl: './server-list-component.component.html',
  styleUrls: ['./server-list-component.component.css']
})
export class ServerListComponentComponent implements OnInit {
  type:string ="";
  // Change URL here to get the list of servers
  resourcesURL: string = "http://localhost:8080/getresources"
  data: string[] = [];
  loading: boolean = true;
  constructor(
    private http: HttpClient,
    private activatedRoute: ActivatedRoute,
    private serverService: ServerDetailsService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.type = this.activatedRoute.snapshot.params['type']
    })

    const params = new HttpParams();
    params.set('type',this.type);
    this.http.get<string[]>(this.resourcesURL+"/"+this.type,{params:params}).subscribe(
      (data:string[])=>{
        this.data = data;
        this.loading = false;
        console.log(this.loading);
      }
    );
  }

  onSubmit(server:string){
    this.serverService.setServer(server);
    this.router.navigate(["upload"]);
  }

}
