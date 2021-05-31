import { Component, OnInit, Inject, OnDestroy } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Kredit } from 'src/app/models/kredit';
import { KreditService } from 'src/app/services/kredit.service';
import { Klijent } from 'src/app/models/klijent';
import { KlijentService } from 'src/app/services/klijent.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-klijent-dialog',
  templateUrl: './klijent-dialog.component.html',
  styleUrls: ['./klijent-dialog.component.css']
})
export class KlijentDialogComponent implements OnInit, OnDestroy {

  krediti: Kredit[];
  public flag: number;
  kreditSubscription: Subscription;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<KlijentDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Klijent,
    public klijentService: KlijentService,
    public kreditService: KreditService) { }

  ngOnInit(): void {
    this.kreditSubscription = this.kreditService.getAllKredits()
      .subscribe(krediti => {
        this.krediti = krediti
      })
  }

  ngOnDestroy(): void {
    this.kreditSubscription.unsubscribe();
  }


  compareTo(a, b) {
    return a.id == b.id;
  }

  public add(): void {
    this.klijentService.addKlijent(this.data)
      .subscribe(() => {
        this.snackBar.open('Uspešno dodat klijent', "U redu", {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + '-->' + error.message);
        this.snackBar.open('Dogodila se greska. Pokusajte ponovo!', 'Zatvori', {
          duration: 2500
        });
      };
  }

  public update(): void {
    this.klijentService.updateKlijent(this.data)
      .subscribe(() => {
        this.snackBar.open('Uspešno modifikovan klijent: ' + this.data.id, "U redu", {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + '-->' + error.message);
        this.snackBar.open('Dogodila se greska. Pokusajte ponovo!', 'Zatvori', {
          duration: 2500
        });
      };
  }

  public delete(): void {
    this.klijentService.deleteKlijent(this.data.id)
      .subscribe(() => {
        this.snackBar.open('Uspešno obrisan klijent: ' + this.data.id, "U redu", {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + '-->' + error.message);
        this.snackBar.open('Dogodila se greska. Pokusajte ponovo!', 'Zatvori', {
          duration: 2500
        });
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste!', 'Zatvori', {
      duration: 1500
    });
  }


}

