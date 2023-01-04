import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
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
  showButton = true;
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

    let res:Subscription =   this.http.get<string[]>(this.resourcesURL+"/"+this.type,{params:params}).subscribe(
      (data:string[])=>{
        this.data = data;
        if(data[0] == 'No services available'){
          this.showButton = false;
        }
        this.loading = false;
        console.log(this.loading);
      },(error:any)=>{
        this.loading = false;
        this.data[0] = "Unknown error occured";
        this.showButton = false;
      }
    );

    setTimeout(()=>{
      if(this.loading == true){
        res.unsubscribe();
        this.loading = false;
        this.data = ['No response from server'];
        this.showButton = false;
      }
    },30000);
    
  }

  onSubmit(server:string){
    this.serverService.setServer(server);
    this.router.navigate(["upload"]);
  }

}
