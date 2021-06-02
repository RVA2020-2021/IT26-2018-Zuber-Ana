import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { TipRacuna } from 'src/app/models/tipRacuna';
import { RacunService } from 'src/app/services/racun.service';
import { Racun } from 'src/app/models/racun';
import { TipRacunaService } from 'src/app/services/tip-racuna.service';


@Component({
  selector: 'app-racun-dialog',
  templateUrl: './racun-dialog.component.html',
  styleUrls: ['./racun-dialog.component.css']
})
export class RacunDialogComponent implements OnInit {

  tipoviRacuna: TipRacuna[];
  public flag: number;
  tipRacunaSubscription: Subscription;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<RacunDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Racun,
    public racunService: RacunService,
    public tipRacunaService: TipRacunaService) { }

  ngOnDestroy() {
    this.tipRacunaSubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.tipRacunaSubscription = this.tipRacunaService.getAllTipRacuns().subscribe(
      data => {
        this.tipoviRacuna = data;
      }
    ),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      }
  }

  compareTo(a, b) {
    return a.id === b.id;
  }

  public add(): void {
    this.racunService.addRacun(this.data)
      .subscribe(() => {
        this.snackBar.open('Uspešno dodat racun!', 'U redu', {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greška!', 'Zatvori', {
          duration: 1500
        })
      };
  }

  public update(): void {
    this.racunService.updateRacun(this.data)
      .subscribe(() => {
        this.snackBar.open('Uspešno modifikovan racun!', 'U redu', {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greška!', 'Zatvori', {
          duration: 1500
        })
      };
  }

  public delete(): void {
    this.racunService.deleteRacun(this.data.id)
      .subscribe(() => {
        this.snackBar.open('Uspešno obrisan racun!', 'U redu', {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greška!', 'Zatvori', {
          duration: 1500
        })
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste!', 'Zatvori', {
      duration: 1500
    })
  }

}







