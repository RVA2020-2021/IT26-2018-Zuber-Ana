import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { TipRacuna } from 'src/app/models/tipRacuna';
import { TipRacunaService } from 'src/app/services/tip-racuna.service';

@Component({
  selector: 'app-tip-racuna',
  templateUrl: './tip-racuna.component.html',
  styleUrls: ['./tip-racuna.component.css']
})

export class TipRacunaComponent implements OnInit, OnDestroy {

  displayedColumns = ['id', 'naziv', 'oznaka', 'opis', 'actions'];
  dataSource: MatTableDataSource<TipRacuna>;
  subscription: Subscription;

  constructor(private tipRacunaService: TipRacunaService) { }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData() {
    this.subscription = this.tipRacunaService.getAllTipRacuns().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data);
      }
    ),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      }
  }
}
